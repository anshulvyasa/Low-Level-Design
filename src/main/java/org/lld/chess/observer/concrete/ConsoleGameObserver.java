package org.lld.chess.observer.concrete;

import org.lld.chess.enums.Status;
import org.lld.chess.observer.GameObserver;
import org.lld.chess.utils.Move;

public class ConsoleGameObserver implements GameObserver {
    @Override
    public void onMoveMade(Move move) {
        System.out.println("Move made from: " + move.getStartCell().getPiece() + " to " + move.getEndCell().getPiece());
    }

    @Override
    public void onGameStateChanged(Status state) {
        System.out.println("Game state changed to: " + state);
    }
}
