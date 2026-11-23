package org.lld.chess.enums.pieces;

import lombok.Getter;
import lombok.Setter;

@Getter
public abstract class Piece {
    private final boolean isWhite;

    @Setter
    private boolean isKilled;

    public Piece(boolean isWhite){
        this.isWhite=isWhite;
    }
}
