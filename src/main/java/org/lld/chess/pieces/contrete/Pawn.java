package org.lld.chess.pieces.contrete;

import org.lld.chess.pieces.Piece;
import org.lld.chess.stragety.movement.concrete.PawnMovementStrategy;

public class Pawn extends Piece {
    public Pawn(boolean isWhite) {
        super(isWhite, new PawnMovementStrategy());
    }
}
