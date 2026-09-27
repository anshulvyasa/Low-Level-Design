package org.lld.elevator.elevator;

import lombok.Getter;
import lombok.Setter;
import org.lld.elevator.command.ElevatorCommand;
import org.lld.elevator.enums.Direction;
import org.lld.elevator.enums.State;
import org.lld.elevator.observers.Observer;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

@Getter
public class Elevator {
    private final String id;
    @Setter  private int floor;

    @Setter  private State elevatorState;
    @Setter private Direction direction;

    private List<Observer> observers=new ArrayList<>();
    private Queue<ElevatorCommand> requests=new ArrayDeque<>();

    public Elevator(String id,int floor){
        this.id=id;
        this.floor=floor;

        elevatorState=State.IDLE;
        direction=Direction.IDLE;
    }

    // Observers Manipulation
    public void addObserver(Observer observer){
        observers.add(observer);
    }

    public void removeObserver(Observer observer){
        observers.remove(observer);
    }

    // Request handling
    public void addRequest(ElevatorCommand command){
        requests.add(command);
    }


    public void notifyStateChange(){
        for(Observer observer:observers){
            observer.changeState(id,elevatorState);
        }
    }

    public void notifyFloorChange(){
        for(Observer observer:observers){
            observer.changeFloor(id,floor);
        }
    }

    public void moveElevator(int destination) throws InterruptedException{
        while (floor!=destination){
            if(direction==Direction.UP) floor++;
            else floor--;
            Thread.sleep(1000);
            notifyFloorChange();
        }

        reachDestination();
    }

    public void reachDestination(){
        setElevatorState(State.STOPPED);
        notifyStateChange();

        requests.removeIf(c->c.getFloor()== this.floor);

        if(requests.isEmpty()){
            setDirection(Direction.IDLE);
            setElevatorState(State.IDLE);
        }
        else{
            setElevatorState(State.MOVING);
        }
        notifyStateChange();
    }
}
