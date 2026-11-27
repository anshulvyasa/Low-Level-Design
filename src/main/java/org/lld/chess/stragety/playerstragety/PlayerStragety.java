package org.lld.chess.stragety.playerstragety;

import org.lld.chess.core.board.Board;
import org.lld.chess.utils.Move;

public interface PlayerStragety {
    Move determineMove(Board board,boolean isWhite);
}
