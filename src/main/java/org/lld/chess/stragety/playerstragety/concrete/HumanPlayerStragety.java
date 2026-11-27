package org.lld.chess.stragety.playerstragety.concrete;

import org.lld.chess.core.board.Board;
import org.lld.chess.stragety.playerstragety.PlayerStragety;
import org.lld.chess.utils.Cell;
import org.lld.chess.utils.Move;

import java.util.Scanner;

public class HumanPlayerStragety implements PlayerStragety {
    private static final Scanner scanner = new Scanner(System.in);
    @Override
    public Move determineMove(Board board, boolean isWhite) {
        System.out.println((isWhite ? "White" : "Black") + " player's turn.");
        System.out.print("Enter source position (e.g., a2): ");
        String source = scanner.nextLine();
        System.out.print("Enter destination position (e.g., a4): ");
        String destination = scanner.nextLine();

        try {
            return new Move(parseCell(board, source), parseCell(board, destination));
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    private Cell parseCell(Board board, String input) {
        String square = input.trim();
        if (!square.matches("[a-zA-Z][1-9][0-9]*")) {
            throw new IllegalArgumentException("Invalid square: " + square);
        }
        int col = Character.toLowerCase(square.charAt(0)) - 'a';
        int row = Integer.parseInt(square.substring(1)) - 1;
        if (row >= board.getSize() || col >= board.getSize()) {
            throw new IllegalArgumentException("Square outside the board: " + square);
        }
        return board.getCell(row, col);
    }
}
