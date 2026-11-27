package org.lld.chess.pieces.contrete;

import org.lld.chess.pieces.Piece;
import org.lld.chess.stragety.movement.concrete.KingMovementStrategy;

public class King extends Piece {
    public King(boolean isWhite) {
        super(isWhite, new KingMovementStrategy());
    }
}
