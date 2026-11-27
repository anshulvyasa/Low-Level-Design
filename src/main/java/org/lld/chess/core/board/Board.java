package org.lld.chess.core.board;

import org.lld.chess.factory.PiecesFactory;
import org.lld.chess.utils.Cell;

public class Board {
    // For Singleton Design Pattern
    private static Board boardInstance;
    private Cell[][] board;

    private Board(int rows){initializeBoard(rows);}

    public static Board getBoardInstance(int rows){
        if(boardInstance==null){
            boardInstance=new Board(rows);
        }

        return boardInstance;
    }

    private void initializeBoard(int rows){
        board=new Cell[rows][rows];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < rows; col++) {
                board[row][col] = new Cell(row, col, null);
            }
        }

        setPieceRow(0,true);
        setPawn(1,true);

        setPawn(rows-2,false);
        setPieceRow(rows-1,false);
    }

    // Following DRY principal here
    private void setPieceRow(int row,boolean isWhite){
        board[row][0]=new Cell(row,0, PiecesFactory.createPiece("rook",isWhite));
        board[row][1]=new Cell(row,1,PiecesFactory.createPiece("knight",isWhite));
        board[row][2]=new Cell(row,2,PiecesFactory.createPiece("bishop",isWhite));
        board[row][3]=new Cell(row,3,PiecesFactory.createPiece("queen",isWhite));
        board[row][4]=new Cell(row,4,PiecesFactory.createPiece("king",isWhite));
        board[row][5]=new Cell(row,5,PiecesFactory.createPiece("bishop",isWhite));
        board[row][6]=new Cell(row,6,PiecesFactory.createPiece("knight",isWhite));
        board[row][7]=new Cell(row,7, PiecesFactory.createPiece("rook",isWhite));
    }

    // Following DRY pricipal here too
    private void setPawn(int rows,boolean isWhite){
        for(int i=0;i<board.length;i++){
            board[rows][i]=new Cell(rows,i,PiecesFactory.createPiece("pawn",isWhite));
        }
    }


    public int getSize() {
        return board.length;
    }

    public Cell getCell(int row, int col) {
        return board[row][col];
    }

    public boolean contains(Cell cell) {
        int row = cell.getRow();
        int col = cell.getCol();
        return row >= 0 && row < board.length && col >= 0 && col < board.length
                && board[row][col] == cell;
    }
}
