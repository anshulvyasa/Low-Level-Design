package org.lld.chess.pieces.contrete;

import org.lld.chess.pieces.Piece;
import org.lld.chess.stragety.movement.concrete.QueenMovementStrategy;

public class Queen extends Piece {
    public Queen(boolean isWhite) {
        super(isWhite, new QueenMovementStrategy());
    }
}
