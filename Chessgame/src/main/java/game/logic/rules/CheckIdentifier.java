package game.logic.rules;

import game.logic.components.Board;
import game.logic.components.GamePiece;
import game.logic.components.Square;
import java.util.List;

/**
 * Luokan tehtävä on tarkistaa, onko tietty ruutu uhattu tai
 * onko laudalla shakkitilanne..
 */
public class CheckIdentifier {

    private MoveLegalityIdentifier moveLegalityIdentifier;

    /**
     * Luo shakin tarkastajan.
     * 
     * @param moveLegalityIdentifier Liikkeen oikeellisuuden tarkastaja 
     */
    public CheckIdentifier(MoveLegalityIdentifier moveLegalityIdentifier) {
        this.moveLegalityIdentifier = moveLegalityIdentifier;
    }
    
    /**
     * Metodin tehtävä on selvittää, uhkaako jokin määräty värinen nappula
     * annettua ruutua.
     * 
     * @param row   Tarkistettavan ruudun rivi
     * 
     * @param col   Tarkistettavan ruudun sarake
     * 
     * @param board pelilauta
     * 
     * @param color Mahdollisen uhkaajan väri
     * 
     * @return onko ruutu parametrin color värisen nappulan uhkaama
     */
    public boolean squareIsThreatened(int row, int col, Board board, String color) {
        boolean addedShadowPiece = false;
        String threatenedColor = "WHITE";
        if (color.equals(threatenedColor)) {
            threatenedColor = "BLACK";
        }

        GamePiece squareContent = board.getSquareContent(row, col);

        if (squareContent == null) {
            
            GamePiece shadowPiece = new GamePiece("SHADOW", threatenedColor, "UNMOVED");

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
