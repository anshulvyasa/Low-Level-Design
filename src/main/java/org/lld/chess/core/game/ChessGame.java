package org.lld.chess.core.game;

import lombok.Setter;
import org.lld.chess.core.board.Board;
import org.lld.chess.enums.Status;
import org.lld.chess.models.Player;
import org.lld.chess.observer.GameObserver;
import org.lld.chess.utils.Move;

import java.util.ArrayList;
import java.util.List;

public class ChessGame implements BoardGame{
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private List<Move> gamelogs;
    private Status status;

    @Setter
    private GameObserver gameObserver;

    private void notifyMoveMade(Move move) {
        if (gameObserver != null) {
            gameObserver.onMoveMade(move);
        }
    }

    private void notifyGameStateChanged(Status state) {
        if (gameObserver != null) {
            gameObserver.onGameStateChanged(state);
        }
    }

    public ChessGame(Player player1,Player player2){
        this.player1=player1;
        this.player2=player2;
        board = Board.getBoardInstance(8);
        currentPlayer = player1.isWhiteSide() ? player1 : player2;
        gamelogs = new ArrayList<>();
        status = Status.ACTIVE;
    }

    @Override
    public void play() {
        while (status==Status.ACTIVE){
            Move move=currentPlayer.makeMove(board);
            if (move == null || !move.isValid(board, currentPlayer.isWhiteSide())) {
                System.out.println("Invalid move. Try again.");
                continue;
            }
            if (move.getEndCell().getPiece() != null) {
                move.getEndCell().getPiece().setKilled(true);
            }
            move.getEndCell().setPiece(move.getStartCell().getPiece());
            move.getStartCell().setPiece(null);

            gamelogs.add(move);
            notifyMoveMade(move);
            currentPlayer = currentPlayer == player1 ? player2 : player1;
        }
    }
}
