package game.logic.rules;

import game.logic.components.Board;
import game.logic.components.GamePiece;
import game.logic.components.Square;
import game.logic.move.Move;
import java.util.List;

/**
 * Luokan tehtävä on tarkistaa, onko tietty ruutu uhattu tai onko laudalla
 * shakkitilanne..
 */
public class CheckIdentifier {

    private MoveLegalityIdentifier moveLegalityIdentifier;

    /**
     * Luo shakin tarkastajan.
     *
     */
    public CheckIdentifier() {
    }

    public void setMoveLegalityIdentifier(MoveLegalityIdentifier moveLegalityIdentifier) {
        this.moveLegalityIdentifier = moveLegalityIdentifier;
    }

    /**
     * Metodin tehtävä on selvittää, uhkaako jokin määräty värinen nappula
     * annettua ruutua.
     *
     * @param row Tarkistettavan ruudun rivi
     * @param col Tarkistettavan ruudun sarake
     * @param board pelilauta
     * @param color Mahdollisen uhkaajan väri
     * @return onko ruutu parametrin color värisen nappulan uhkaama
     */
    public boolean squareIsThreatened(int row, int col, Board board, String color) {
        boolean addedShadowPiece = false;
        boolean threatenerFound = false;
        String threatenedColor = "WHITE";
        if (color.equals(threatenedColor)) {
            threatenedColor = "BLACK";
        }

        GamePiece squareContent = board.getSquareContent(row, col);

        if (squareContent == null) {

            GamePiece shadowPiece = new GamePiece("PAWN", threatenedColor, "UNMOVED");

            board.insertPiece(row, col, shadowPiece);
            addedShadowPiece = true;
        }

        List<GamePiece> possibleThreateners = board.getPieces(color);

        for (GamePiece piece : possibleThreateners) {
            if (doesPieceThreatSquare(piece, row, col, color)) {
                threatenerFound = true;
                break;
            }
        }

        if (addedShadowPiece) {
            board.removePiece(board.getSquareContent(row, col));
        }
        return threatenerFound;
    }

    private boolean doesPieceThreatSquare(GamePiece piece, int row, int col, String color) {

        Square pieceLocation = piece.getLocation();

        return (moveLegalityIdentifier.moveIsPossible(new Move(pieceLocation.getRow(), pieceLocation.getColumn(), row, col), color));
    }

    /**
     * Metodi tarkistaa, onko parametrin värinen kuningas shakissa.
     *
     * @param color Tarkistettavan kuninkaan väri
     * @param board Pelilauta
     * @return Onko shakki
     */
    public boolean isCheck(String color, Board board) {
        String attackingColor = "BLACK";
        if (color.equals(attackingColor)) {
            attackingColor = "WHITE";
        }

        Square kingLocation = board.getKingLocation(color);
        return this.squareIsThreatened(kingLocation.getRow(), kingLocation.getColumn(), board, attackingColor);
    }

    /**
     * Metodi tarkistaa olisiko laudalla shakki siirron jälkeen.
     *
     * @param color Siirrettävän nappulan väri
     * @param board Lauta
     * @param move Siirto, joka mahdollisesti voisi aiheuttaa shakin
     * siirrettävän väriselle pelaajalle
     * @return Aiheutuuko shakki
     */
    public boolean isCheckAfterMove(String color, Board board, Move move) {

        GamePiece captured = move.getCaptured();
        if (captured != null) {
            move.setCapturedPreviousStatus(captured.getStatus());
        }

        board.movePiece(move);

        boolean result = isCheck(color, board);
        board.revertMove(move);

        return result;
    }

}
