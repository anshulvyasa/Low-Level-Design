package org.lld.chess.pieces.contrete;

import org.lld.chess.pieces.Piece;
import org.lld.chess.stragety.movement.concrete.BishopMovementStrategy;

public class Bishop extends Piece {
    public Bishop(boolean isWhite) {
        super(isWhite, new BishopMovementStrategy());
    }
}
