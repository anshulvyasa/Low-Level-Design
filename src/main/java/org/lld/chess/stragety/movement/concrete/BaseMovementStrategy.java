package org.lld.chess.stragety.movement.concrete;

import org.lld.chess.core.board.Board;
import org.lld.chess.utils.Cell;
import org.lld.chess.stragety.movement.MovementStrategy;

abstract class BaseMovementStrategy implements MovementStrategy {
    @Override
    public final boolean canMove(Board board, Cell start, Cell end) {
        if (board == null || start == null || end == null
            || !board.contains(start) || !board.contains(end) || start == end
            || start.getPiece() == null || start.getPiece().isKilled()) {
            return false;
        }
        if (end.getPiece() != null && start.getPiece().isWhite() == end.getPiece().isWhite()) {
            return false;
        }
        return allowsMove(board, start, end,
            end.getRow() - start.getRow(), end.getCol() - start.getCol());
    }

    abstract boolean allowsMove(Board board, Cell start, Cell end, int rows, int cols);

    // Called only for moves along a rank, file, or diagonal.
    static boolean pathIsClear(Board board, Cell start, Cell end) {
        int rowStep = Integer.compare(end.getRow(), start.getRow());
        int colStep = Integer.compare(end.getCol(), start.getCol());
        int row = start.getRow() + rowStep;
        int col = start.getCol() + colStep;
        while (row != end.getRow() || col != end.getCol()) {
            if (board.getCell(row, col).getPiece() != null) {
                return false;
            }
            row += rowStep;
            col += colStep;
        }
        return true;
    }
}
