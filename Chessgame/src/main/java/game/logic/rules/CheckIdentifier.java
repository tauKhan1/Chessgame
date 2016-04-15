package game.logic.rules;

import game.logic.components.Board;
import game.logic.components.GamePiece;
import game.logic.components.Square;
import java.util.List;

public class CheckIdentifier {

    private MoveLegalityIdentifier moveLegalityIdentifier;

    public CheckIdentifier(MoveLegalityIdentifier moveLegalityIdentifier) {
        this.moveLegalityIdentifier = moveLegalityIdentifier;
    }

    public boolean squareIsThreatened(int row, int col, Board board, String color) {
        boolean addedShadowPiece = false;
        String threatenedColor = "WHITE";
        if (color.equals(threatenedColor)) {
            threatenedColor = "BLACK";
        }

        GamePiece squareContent = board.getSquareContent(row, col);

        if (squareContent == null) {

            GamePiece shadowPiece = new GamePiece("SHADOW", threatenedColor, "UNMOVED");   /* The purpose of the shadow piece is to allow special rules that are dependent on
             square content to function correctly. */

            board.insertPiece(row, col, shadowPiece);
            addedShadowPiece = true;
        }

        List<GamePiece> possibleThreateners = board.getPieces(color);

        for (GamePiece piece : possibleThreateners) {
            Square location = piece.getLocation();

            if (moveLegalityIdentifier.moveIsPossible(location.getRow(), location.getColumn(), row, col, color)) {
                if (addedShadowPiece) {
                    board.removePiece(board.getSquareContent(row, col));
                }
                return true;
            }
        }

        if (addedShadowPiece) {
            board.removePiece(board.getSquareContent(row, col));
        }
        return false;
    }
}
