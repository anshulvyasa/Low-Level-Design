package org.lld.elevator.elevator;

import lombok.Getter;
import org.lld.elevator.command.ElevatorCommand;
import org.lld.elevator.enums.Direction;
import org.lld.elevator.scheduling_stragety.FirstComeFirstServeScheduling;
import org.lld.elevator.scheduling_stragety.SchedulingStragety;


import java.util.HashMap;
import java.util.Map;

@Getter
public class ElevatorController {
    Map<String,Elevator> elevators;
    int floors;
    SchedulingStragety schedulingStragety;

    public ElevatorController(int noOfElevators,int floors){
        this.elevators=new HashMap<>();
        this.floors=floors;
        this.schedulingStragety=new FirstComeFirstServeScheduling();

        for(int i=0;i<noOfElevators;i++){
            String key="Elevator:"+i;
            elevators.put(key,new Elevator(key,0));
        }
    }

    public Elevator getElevatorById(String key){
        return elevators.get(key);
    }

    public  void requestElevator(String elevatorId, int floor, Direction direction){
         Elevator elevator=elevators.get(elevatorId);
         if(elevator==null) return;

         if(elevator.getFloor()!=floor) {
             elevator.addRequest(new ElevatorCommand(elevatorId,floor,false,direction));
         }
    }

    public  void requestFloor(String elevatorId,int floor){
        Elevator elevator=elevators.get(elevatorId);
        if(elevator==null) return;

        int elevatorFloor= elevator.getFloor();
        Direction direction=Direction.IDLE;

        if(floor>elevatorFloor) direction=Direction.UP;
        else if(floor<elevatorFloor) direction=Direction.DOWN;

        if(direction!=Direction.IDLE){
            elevator.addRequest(new ElevatorCommand(elevatorId,floor,true,direction));
        }
    }



    public void step(){
        for(Elevator elevator:elevators.values()){
            int next=schedulingStragety.getNextStop(elevator);

            try{
                elevator.moveElevator(next);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
