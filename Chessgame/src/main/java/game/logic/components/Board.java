package game.logic.components;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private final List<Square> squares;
    private final List<GamePiece> pieces;

    public Board() {
        this.squares = new ArrayList<>();
        this.pieces = new ArrayList<>();

        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                Square newSquare = new Square(i, j);
                this.squares.add(newSquare);
            }

        }
    }

    public GamePiece getSquareContent(int row, int column) {

        return this.squares.get(convertPairToSquare(row, column)).getPiece();
    }
    
    public List<GamePiece> getPieces(String color) {
        List<GamePiece> piecesOfSameColor = new ArrayList<>();
        
        for (GamePiece piece : pieces) {
            if (piece.getColor().equals(color) && !piece.getStatus().equals("CAPTURED")) {
                
                piecesOfSameColor.add(piece);
            }
        }
       
        return piecesOfSameColor;
    }

    public void insertPiece(int row, int column, GamePiece piece) {
        if (isSquare(row, column)) {
            Square square = this.squares.get(convertPairToSquare(row, column));

            square.setPiece(piece);
            piece.setLocation(square);
            pieces.add(piece);

        }

    }
    
    public void removePiece(GamePiece piece) {
        piece.getLocation().setPiece(null);
        pieces.remove(piece);
    }

    public boolean movePiece(int originalRow, int originalColumn, int nextRow, int nextColumn) {
        if (!isSquare(originalRow, originalColumn) || !isSquare(nextRow, nextColumn)) {
            return false;
        }

        Square previous = this.squares.get(convertPairToSquare(originalRow, originalColumn));
        Square next = this.squares.get(convertPairToSquare(nextRow, nextColumn));
        GamePiece moving = previous.getPiece();
        GamePiece captured = next.getPiece();

        if (moving == null) {
            return false;
        }

        if (captured != null) {
            capture(captured);
        }

        previous.setPiece(null);
        next.setPiece(moving);
        moving.setLocation(next);
        moving.setStatus("MOVED");

        return true;
    }

    private int convertPairToSquare(int row, int column) {
        return (row - 1) * 8 + column - 1;
    }

    private void capture(GamePiece piece) {
        piece.setStatus("CAPTURED");
    }

    private boolean isSquare(int row, int column) {
        return 0 < row && 0 < column && row < 9 && column < 9;
    }
}
