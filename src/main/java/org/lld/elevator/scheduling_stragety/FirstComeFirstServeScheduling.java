package org.lld.elevator.scheduling_stragety;

import org.lld.elevator.command.ElevatorCommand;
import org.lld.elevator.elevator.Elevator;
import org.lld.elevator.enums.Direction;

import java.util.List;
import java.util.Queue;

public class FirstComeFirstServeScheduling implements  SchedulingStragety{
    @Override
    public int getNextStop(Elevator elevator) {
         if(elevator.getRequests().isEmpty()) return  elevator.getFloor();

        Queue<ElevatorCommand> requests=elevator.getRequests();
        ElevatorCommand command=requests.poll();

        int floor=command.getFloor();
        int elevatorFloor=elevator.getFloor();

        if(floor==elevatorFloor) return floor;

        if(elevator.getDirection()== Direction.IDLE){
            elevator.setDirection(floor>elevatorFloor?Direction.UP:Direction.DOWN);
        }
        else if(elevator.getDirection()==Direction.UP&&floor<elevatorFloor){
             elevator.setDirection(Direction.DOWN);
        }
        else if(floor>elevatorFloor){
            elevator.setDirection(Direction.UP);
        }

        return floor;
    }
}
