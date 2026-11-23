package org.lld.chess.stragety.movement.concrete;

import org.lld.chess.core.Board;
import org.lld.chess.utils.Cell;

public class BishopMovementStrategy extends BaseMovementStrategy {
    @Override
    boolean allowsMove(Board board, Cell start, Cell end, int rows, int cols) {
        return Math.abs(rows) == Math.abs(cols) && pathIsClear(board, start, end);
    }
}
