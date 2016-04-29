package game.logic.rules;

import game.logic.components.Board;
import game.logic.components.GamePiece;

/**
 * Luokan avulla tarkistetaan täyttyvätkö siirtoon mahdollisesti liittyvät erityisehdot.
 */
public class SpecialRuleChecker {
    
    /**
     * Luo erikoissääntöjen tarkastajan.
     */
    public SpecialRuleChecker() {
    }
    
    /**
     * Metodi kertoo sallivatko siirtoon mahdollisesti liittyvät
     * erikoissäännöt siirron toteuttamisen.
     * 
     * @param origRow   Siirrettävän nappulan alkuperäinen rivi
     * 
     * @param origCol   Siirrettävän nappulan alkuperäinen sarake
     * 
     * @param nextRow   Sirrettävän nappulan kohderivi
     * 
     * @param nextCol   Siirrettävän nappulan kohdesarake
     * 
     * @param color     Siirrettävän nappulan väri
     * 
     * @param specialRules  Siirtoon liittyvän erikoissäännön nimi
     * 
     * @param board Pelilauta
     * 
     * @return Tulos siitä, onko siirto erityissäännön mukainen
     */
    public boolean check(int origRow, int origCol, int nextRow,
            int nextCol, String color, String specialRules, Board board) {

        if (specialRules.equals("pawnMoveForward")) {
            return pawnMoveForwardEvaluation(nextRow, nextCol, board);
        }

        if (specialRules.equals("pawnDoubleForward")) {
            return pawnDoubleForwardEvaluation(origRow, origCol, nextRow, nextCol, board);
        }

        if (specialRules.equals("pawnCapture")) {
            return pawnCaptureEvaluation(nextRow, nextCol, board, color);
        }
        return true;
    }

    private boolean pawnMoveForwardEvaluation(int nextRow,
            int nextCol, Board board) {

        GamePiece targetContent = board.getSquareContent(nextRow, nextCol);

        return (targetContent == null);
    }

    private boolean pawnDoubleForwardEvaluation(int origRow, int origCol, int nextRow, int nextCol, Board board) {
        if (board.getSquareContent(origRow, origCol).getStatus().equals("UNMOVED") && board.getSquareContent(nextRow, nextCol) == null && Math.abs(origRow - nextRow) == 2) {
            return true;
        }

        return false;
    }

    private boolean pawnCaptureEvaluation(int nextRow, int nextCol, Board board, String color) {

        GamePiece targetContent = board.getSquareContent(nextRow, nextCol);

        if (targetContent == null) {
            return pawnEnPassantEvaluation(nextRow, nextCol, board, color);
        } else {
            if (!targetContent.getColor().equals(color)) {
                return true;
            }
        }

        return false;
    }

    private boolean pawnEnPassantEvaluation(int nextRow, int nextCol, Board board, String color) {
        int diff;

        if (color.equals("WHITE")) {
            diff = -1;
        } else {
            diff = 1;
        }

        GamePiece potentialDoubleMovedPawn = board.getSquareContent(nextRow + diff, nextCol);

        if (potentialDoubleMovedPawn == null) {
            return false;
        } else {
            if (potentialDoubleMovedPawn.getStatus().equals("DOUBLEMOVED") && !potentialDoubleMovedPawn.getColor().equals(color)) {
                return true;
            }
        }

        return false;
    }
}
