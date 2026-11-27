package org.lld.chess.utils;

import lombok.Getter;
import lombok.Setter;
import org.lld.chess.pieces.Piece;

@Getter
public class Cell {
    private final int row;
    private final int col;
    @Setter
    private Piece piece;

    public Cell(int row,int col,Piece piece){
        this.row=row;
        this.col=col;
        this.piece=piece;
    }
}
