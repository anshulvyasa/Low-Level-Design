package org.lld.chess.pieces.contrete;

import org.lld.chess.pieces.Piece;
import org.lld.chess.stragety.movement.concrete.KnightMovementStrategy;

public class Knight extends Piece {
    public Knight(boolean isWhite) {
        super(isWhite, new KnightMovementStrategy());
    }
}
