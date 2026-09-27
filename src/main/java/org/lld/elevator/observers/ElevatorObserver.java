package org.lld.elevator.observers;

import org.lld.elevator.enums.State;

public class ElevatorObserver implements Observer {
    @Override
    public void changeState(String id, State state) {
        System.out.println("Elevator With Id "+id+" changed it's state to "+state);
    }

    @Override
    public void changeFloor(String id, int floor) {
        System.out.println("Elevator With Id "+id+" changed it's floor to "+floor);
    }
}
