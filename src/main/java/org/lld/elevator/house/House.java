package org.lld.elevator.house;

import lombok.Getter;
import org.lld.elevator.elevator.ElevatorController;

@Getter
public class House {
    private final String name;
    private final int floors;
    private ElevatorController elevatorController;

    public  House(String name,int floors,int noOfElevators){
        this.name=name;
        this.floors=floors;
        elevatorController=new ElevatorController(noOfElevators,floors);
    }
}
