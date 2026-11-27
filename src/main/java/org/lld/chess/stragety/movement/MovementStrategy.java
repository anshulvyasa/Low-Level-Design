package org.lld.chess.stragety.movement;

import org.lld.chess.core.board.Board;
import org.lld.chess.utils.Cell;

public interface MovementStrategy {
    boolean canMove(Board board, Cell startCell,Cell endCell);
}
