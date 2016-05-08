package game.logic.rules;

import game.logic.components.Board;
import game.logic.components.GamePiece;
import game.logic.move.Move;

/**
 * Luokan avulla tarkistetaan täyttyvätkö siirtoon mahdollisesti liittyvät
 * erityisehdot.
 */
public class SpecialRuleChecker {

    private CheckIdentifier ci;

    /**
     * Luo erikoissääntöjen tarkastajan.
     *
     * @param cidentifier Shakintarkastaja
     */
    public SpecialRuleChecker(CheckIdentifier cidentifier) {
        this.ci = cidentifier;
    }

    /**
     * Metodi kertoo sallivatko siirtoon mahdollisesti liittyvät erikoissäännöt
     * siirron toteuttamisen.
     *
     * @param move Siirto
     * @param color Siirrettävän nappulan väri
     * @param specialRules Siirtoon liittyvän erikoissäännön nimi
     * @param board Pelilauta
     * @return Tulos siitä, onko siirto erityissäännön mukainen
     */
    public boolean check(Move move, String color, String specialRules, Board board) {

        if (specialRules.equals("pawnMoveForward")) {
            return pawnMoveForwardEvaluation(move, board);
        }

        if (specialRules.equals("pawnDoubleForward")) {
            return pawnDoubleForwardEvaluation(move, board);
        }

        if (specialRules.equals("pawnCapture")) {
            return pawnCaptureEvaluation(move, board, color);
        }

        if (specialRules.equals("kingCastling")) {
            return castlingEvaluation(move, board, color);
        }
        return true;
    }

    private boolean pawnMoveForwardEvaluation(Move move, Board board) {

        return (move.getCaptured() == null);
    }

    private boolean pawnDoubleForwardEvaluation(Move move, Board board) {
        if (move.getMoved().getStatus().equals("UNMOVED") && move.getCaptured() == null && Math.abs(move.getVerticalVector()) == 2) {
            move.setSpecialAction("pawnDoubleMove");
            return true;
        }

        return false;
    }

    private boolean pawnCaptureEvaluation(Move move, Board board, String color) {

        GamePiece targetContent = move.getCaptured();

        if (targetContent == null) {
            return pawnEnPassantEvaluation(move, board, color);
        } else {
            if (!targetContent.getColor().equals(color)) {
                return true;
            }
        }

        return false;
    }

    private boolean pawnEnPassantEvaluation(Move move, Board board, String color) {
        int diff;

        if (color.equals("WHITE")) {
            diff = -1;
        } else {
            diff = 1;
        }

        GamePiece potentialDoubleMovedPawn = board.getSquareContent(move.getNextRow() + diff, move.getNextCol());

        if (potentialDoubleMovedPawn == null) {
            return false;
        } else {
            String status = potentialDoubleMovedPawn.getStatus();
            if (status.contains("DOUBLEMOVED") && !potentialDoubleMovedPawn.getColor().equals(color)) {
                if (status.substring(status.length() - 1, status.length()).equals("" + (move.getTurnNum() - 1))) {
                    move.setSpecialAction("captureEnPassant");
                    return true;
                }
            }
        }

        return false;
    }

    private boolean castlingEvaluation(Move move, Board board, String color) {
        if (!move.getMoved().getStatus().equals("UNMOVED")) {
            return false;
        }

        GamePiece rook;

        rook = board.getSquareContent(move.getPrevRow(), findRookColumn(move));

        if (!evaluateColorExistenceStatusAndType(rook, "ROOK", color)) {
            return false;
        }

        if (!checkSpacesClearBetweenKingAndRook(move, rook, board)) {
            return false;
        }

        if (!checkKingNotThreatened(move, board)) {
            return false;
        }

        createSpecialMove(move, rook);
        return true;
    }

    private boolean evaluateColorExistenceStatusAndType(GamePiece piece, String type, String color) {
        if (piece == null) {
            return false;
        } else {
            if (!(piece.getType().equals(type) && piece.getStatus().equals("UNMOVED"))) {
                return false;
            }

            if (!piece.getColor().equals(color)) {
                return false;
            }

            return true;
        }

    }

    private int findRookColumn(Move move) {
        if (move.getHorizontalVector() < 0) {
            return -4 + move.getPrevCol();
        } else {
            return 3 + move.getPrevCol();
        }
    }

    private boolean checkSpacesClearBetweenKingAndRook(Move move, GamePiece rook, Board board) {
        int kingRow = move.getPrevRow();
        int kingCol = move.getPrevCol();
        int rookCol = rook.getLocation().getColumn();

        for (int i = 1; i <= 8; i++) {
            if (Math.min(kingCol, rookCol) < i && i < Math.max(kingCol, rookCol)) {
                if (!(board.getSquareContent(kingRow, i) == null)) {
                    return false;
                }
            }

        }
        return true;
    }

    private boolean checkKingNotThreatened(Move move, Board board) {
        String threateningColor = "WHITE";
        if (move.getMoved().getColor().equals(threateningColor)) {
            threateningColor = "BLACK";
        }

        for (int i = 0; Math.abs(i) <= 2; i = i + move.getHorizontalVector() / 2) {
            if (ci.squareIsThreatened(move.getPrevRow(), move.getPrevCol() + i, board, threateningColor)) {
                return false;
            }
        }

        return true;
    }

    private void createSpecialMove(Move move, GamePiece rook) {
        Move specialMove = new Move(rook.getLocation().getRow(), rook.getLocation().getColumn(), move.getPrevRow(), move.getPrevCol() + (move.getHorizontalVector() / 2));

        specialMove.setMoved(rook);
        specialMove.setMovedPreviousStatus(rook.getStatus());
        move.setSpecialMove(specialMove);
    }
}
