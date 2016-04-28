package game.logic.setup;

import game.logic.components.Board;
import game.logic.components.GamePiece;

public class BasicSetup {

    public BasicSetup() {
    }

    public void setup(Board board) {
        addKings(board);
        addQueens(board);
        addAllDoubleBorderPieces(board);
        addSoldiers(board, "WHITE");
        addSoldiers(board, "BLACK");
    }

    private void addKings(Board board) {
        GamePiece whiteKing = new GamePiece("KING", "WHITE", "UNMOVED");
        GamePiece blackKing = new GamePiece("KING", "BLACK", "UNMOVED");

        board.insertPiece(1, 5, whiteKing);
        board.insertPiece(8, 5, blackKing);
    }

    private void addQueens(Board board) {
        GamePiece whiteQueen = new GamePiece("QUEEN", "WHITE", "UNMOVED");
        GamePiece blackQueen = new GamePiece("QUEEN", "BLACK", "UNMOVED");

        board.insertPiece(1, 4, whiteQueen);
        board.insertPiece(8, 4, blackQueen);
    }

    private int borderRow(String color) {
        if (color.equals("WHITE")) {
            return 1;
        } else {
            return 8;
        }
    }

    private void addDoubleBorderRowPieces(Board board, String color, String type, int firstCol, int secondCol) {
        int row = borderRow(color);

        GamePiece left = new GamePiece(type, color, "UNMOVED");
        GamePiece right = new GamePiece(type, color, "UNMOVED");

        board.insertPiece(row, firstCol, left);
        board.insertPiece(row, secondCol, right);
    }

    private void addAllDoubleBorderPieces(Board board) {

        addDoubleBorderRowPieces(board, "WHITE", "ROOK", 1, 8);
        addDoubleBorderRowPieces(board, "BLACK", "ROOK", 1, 8);

        addDoubleBorderRowPieces(board, "WHITE", "KNIGHT", 2, 7);
        addDoubleBorderRowPieces(board, "BLACK", "KNIGHT", 2, 7);

        addDoubleBorderRowPieces(board, "WHITE", "BISHOP", 3, 6);
        addDoubleBorderRowPieces(board, "BLACK", "BISHOP", 3, 6);
    }

    private void addSoldiers(Board board, String color) {
        int row;
        if (color.equals("WHITE")) {
            row = 2;
        } else {
            row = 7;
        }

        for (int i = 1; i <= 8; i++) {
            GamePiece piece = new GamePiece("PAWN", color, "UNMOVED");
            board.insertPiece(row, i, piece);
        }
    }
}
