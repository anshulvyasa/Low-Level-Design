package org.lld.chess;

import org.lld.chess.core.game.ChessGame;
import org.lld.chess.models.Player;
import org.lld.chess.stragety.playerstragety.concrete.HumanPlayerStragety;

public class Main {
    public static void main(String[] args) {
        Player player1=new Player("Anshul",true,new HumanPlayerStragety());
        Player player2=new Player("Rahul",false,new HumanPlayerStragety());

        ChessGame chessGame=new ChessGame(player1,player2);
        chessGame.play();
    }
}
