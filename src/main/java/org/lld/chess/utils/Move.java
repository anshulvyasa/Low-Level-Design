package org.lld.chess.utils;

import lombok.Getter;
import org.lld.chess.core.board.Board;
import org.lld.chess.pieces.Piece;
import org.lld.chess.pieces.contrete.King;

@Getter
public class Move {
    private final Cell startCell;
    private final Cell endCell;

    public  Move(Cell startCell,Cell endCell){
        this.startCell=startCell;
        this.endCell=endCell;
    }

    public boolean isValid(Board board, boolean whiteTurn) {
        if (startCell == null || endCell == null || board == null) {
            return false;
        }
        Piece moving = startCell.getPiece();
        Piece captured = endCell.getPiece();
        if (moving == null || moving.isWhite() != whiteTurn || captured instanceof King
                || !moving.canMove(board, startCell, endCell)) {
            return false;
        }

        // Evaluate attacks with the source vacated and any captured piece removed.
        startCell.setPiece(null);
        endCell.setPiece(moving);
        try {
            return !kingInCheck(board, whiteTurn);
        } finally {
            startCell.setPiece(moving);
            endCell.setPiece(captured);
        }
    }

    private boolean kingInCheck(Board board, boolean white) {
        Cell king = null;
        for (int row = 0; row < board.getSize() && king == null; row++) {
            for (int col = 0; col < board.getSize(); col++) {
                Cell cell = board.getCell(row, col);
                if (cell.getPiece() instanceof King && cell.getPiece().isWhite() == white) {
                    king = cell;
                    break;
                }
            }
        }
        if (king == null) {
            return true;
        }
        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                Cell cell = board.getCell(row, col);
                Piece attacker = cell.getPiece();
                if (attacker != null && attacker.isWhite() != white && attacker.canMove(board, cell, king)) {
                    return true;
                }
            }
        }
        return false;
    }
}
