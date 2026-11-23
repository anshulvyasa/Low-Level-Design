package org.lld.chess.stragety.movement.concrete;

import org.lld.chess.core.Board;
import org.lld.chess.utils.Cell;

public class RookMovementStrategy extends BaseMovementStrategy {
    @Override
    boolean allowsMove(Board board, Cell start, Cell end, int rows, int cols) {
        return (rows == 0 || cols == 0) && pathIsClear(board, start, end);
    }
}
