package org.lld.elevator;

import org.lld.elevator.elevator.ElevatorController;
import org.lld.elevator.enums.Direction;
import org.lld.elevator.house.House;
import org.lld.elevator.observers.ElevatorObserver;
import org.lld.elevator.observers.Observer;

public class Main {
    public static void main(String[] args) {
        House house=new House("Paradise",10,4);
        ElevatorController elevatorController=house.getElevatorController();

        // Elevator Request
        elevatorController.requestElevator("Elevator:1",6, Direction.DOWN);
        elevatorController.requestElevator("Elevator:1",9, Direction.DOWN);
        elevatorController.requestElevator("Elevator:1",8, Direction.DOWN);

        // Floor Request
        elevatorController.requestFloor("Elevator:1",10);
        elevatorController.requestFloor("Elevator:1",10);


        Observer consoleObserver=new ElevatorObserver();
        elevatorController.getElevatorById("Elevator:1").addObserver(consoleObserver);

        elevatorController.step();
    }
}
