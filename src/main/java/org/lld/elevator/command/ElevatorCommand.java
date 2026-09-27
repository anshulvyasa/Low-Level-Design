package org.lld.elevator.command;

import lombok.Getter;
import org.lld.elevator.elevator.ElevatorController;
import org.lld.elevator.enums.Direction;

@Getter
public class ElevatorCommand implements Command{
    private final String elevatorId;
    private final int floor;
    private  final boolean isInternalRequest;
    private final Direction direction;
    private ElevatorController elevatorController;

    public ElevatorCommand(String elevatorId,int floor,boolean isInternalRequest,Direction direction){
        this.elevatorId=elevatorId;
        this.floor=floor;
        this.isInternalRequest=isInternalRequest;
        this.direction=direction;
//        this.elevatorController=
    }

    @Override
    public void execute() {
        if(isInternalRequest){

        }
        else{

        }
    }
}
