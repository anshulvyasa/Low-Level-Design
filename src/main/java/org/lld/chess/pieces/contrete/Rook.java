package org.lld.chess.pieces.contrete;

import org.lld.chess.pieces.Piece;
import org.lld.chess.stragety.movement.concrete.RookMovementStrategy;

public class Rook extends Piece {
    public Rook(boolean isWhite) {
        super(isWhite, new RookMovementStrategy());
    }
}
