package org.lld.chess.pieces;

import lombok.Getter;
import lombok.Setter;
import org.lld.chess.core.board.Board;
import org.lld.chess.stragety.movement.MovementStrategy;
import org.lld.chess.utils.Cell;

@Getter
public abstract class Piece {
    private final boolean isWhite;

    @Setter
    private boolean isKilled;
    @Setter
    private MovementStrategy movementStrategy;

    public Piece(boolean isWhite,MovementStrategy movementStrategy){
        this.isWhite=isWhite;
        this.movementStrategy=movementStrategy;
    }

    public boolean canMove(Board board, Cell startCell,Cell endCell){
        return movementStrategy.canMove(board,startCell,endCell);
    }
}
