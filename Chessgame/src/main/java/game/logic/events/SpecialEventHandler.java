package game.logic.events;

import game.logic.components.Board;
import game.logic.components.GamePiece;
import game.logic.move.Move;
import game.logic.turn.Turn;

/**
 * Luokka hoitaa erikoistapahtumien suorituksen kuten sotilaan ylennyksen,
 * tornin siirron linnoituksen yhteydessä yms.
 */
public class SpecialEventHandler {

    /**
     * Metodi luo erikoistapahtumien käsittelijän.
     */
    public SpecialEventHandler() {
    }

    /**
     * Metodi käsittelee erikoistapahtumat.
     *
     * @param move Vuorolla suoritettu siirto
     * @param board Pelilauta
     * @param turn Vuoro
     */
    public void executeSpecialEvents(Move move, Board board, Turn turn) {
        GamePiece moved = move.getMoved();
        if (pawnColorAndLocationMatchForPromotion(moved)) {
            promotePawn(moved);
        }

        if (move.getSpecialMove() != null) {
            board.movePiece(move.getSpecialMove());
        }

        executeSpecialAction(move, board, turn.getTurnNumber());
    }

    private void promotePawn(GamePiece piece) {
        piece.setType("QUEEN");
    }

    private boolean pawnColorAndLocationMatchForPromotion(GamePiece piece) {
        if (piece.getType().equals("PAWN")) {
            if (piece.getColor().equals("BLACK") && piece.getLocation().getRow() == 1) {
                return true;
            } else if (piece.getColor().equals("WHITE") && piece.getLocation().getRow() == 8) {
                return true;
            }
        }
        return false;
    }

    private void executeSpecialAction(Move move, Board board, int turnNumber) {
        String specialAction = move.getSpecialAction();
        if (specialAction != null) {
            if (specialAction.equals("pawnDoubleMove")) {
                move.getMoved().setStatus("DOUBLEMOVED" + turnNumber);
            }

            if (specialAction.equals("captureEnPassant")) {
                captureEnPassant(move, board);
            }
        }
    }

    private void captureEnPassant(Move move, Board board) {
        GamePiece piece = board.getSquareContent(move.getNextRow() - move.getVerticalVector(), move.getNextCol());
        board.capture(piece);
    }

}
