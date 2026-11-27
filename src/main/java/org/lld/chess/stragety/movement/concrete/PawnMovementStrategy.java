package org.lld.chess.stragety.movement.concrete;

import org.lld.chess.core.board.Board;
import org.lld.chess.utils.Cell;

public class PawnMovementStrategy extends BaseMovementStrategy {
    @Override
    boolean allowsMove(Board board, Cell start, Cell end, int rows, int cols) {
        boolean white = start.getPiece().isWhite();
        int forward = white ? 1 : -1;
        if (cols != 0) {
            return Math.abs(cols) == 1 && rows == forward && end.getPiece() != null;
        }
        if (end.getPiece() != null) {
            return false;
        }
        int startingRow = white ? 1 : board.getSize() - 2;
        return rows == forward
            || (start.getRow() == startingRow
                && rows == 2 * forward
                && pathIsClear(board, start, end));
    }
}
