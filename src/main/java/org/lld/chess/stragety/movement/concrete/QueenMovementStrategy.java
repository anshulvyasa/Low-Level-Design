package org.lld.chess.stragety.movement.concrete;

import org.lld.chess.core.board.Board;
import org.lld.chess.utils.Cell;

public class QueenMovementStrategy extends BaseMovementStrategy {
    @Override
    boolean allowsMove(Board board, Cell start, Cell end, int rows, int cols) {
        boolean straight = rows == 0 || cols == 0;
        boolean diagonal = Math.abs(rows) == Math.abs(cols);
        return (straight || diagonal) && pathIsClear(board, start, end);
    }
}
