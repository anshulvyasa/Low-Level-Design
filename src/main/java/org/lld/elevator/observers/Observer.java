package org.lld.elevator.observers;

import org.lld.elevator.enums.State;

public interface Observer {
    void changeState(String id, State state);
    void changeFloor(String id,int floor);
}
