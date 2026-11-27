package org.lld.chess.models;

import lombok.Getter;
import lombok.Setter;
import org.lld.chess.core.board.Board;
import org.lld.chess.stragety.playerstragety.PlayerStragety;
import org.lld.chess.utils.Move;

@Getter
public class Player {
    private String name;
    private boolean isWhiteSide;
    @Setter
    private PlayerStragety playerStragety;

    public Player(String name,boolean isWhiteSide,PlayerStragety playerStragety){
        this.name=name;
        this.isWhiteSide=isWhiteSide;
        this.playerStragety=playerStragety;
    }

    public Move makeMove(Board board){
        return playerStragety.determineMove(board,isWhiteSide);
    }
}
