package org.lld.chess.observer;

import org.lld.chess.enums.Status;
import org.lld.chess.utils.Move;

public interface GameObserver {
    void onMoveMade(Move move);
    void onGameStateChanged(Status state);
}
