package org.lld.elevator.scheduling_stragety;

import org.lld.elevator.command.ElevatorCommand;
import org.lld.elevator.elevator.Elevator;

public interface SchedulingStragety {
    int getNextStop(Elevator elevator);
}
