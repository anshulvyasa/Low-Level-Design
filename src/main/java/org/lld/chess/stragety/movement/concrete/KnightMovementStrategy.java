package org.lld.chess.stragety.movement.concrete;

import org.lld.chess.core.Board;
import org.lld.chess.utils.Cell;

public class KnightMovementStrategy extends BaseMovementStrategy {
    @Override
    boolean allowsMove(Board board, Cell start, Cell end, int rows, int cols) {
        return Math.min(Math.abs(rows), Math.abs(cols)) == 1
            && Math.max(Math.abs(rows), Math.abs(cols)) == 2;
    }
}
