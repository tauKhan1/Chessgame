package game.logic.state;

import game.logic.components.Board;
import game.logic.components.GamePiece;
import game.logic.move.MoveLister;
import game.logic.rules.CheckIdentifier;
import game.logic.turn.Turn;

/**
 * Luokan tarkoitus on selvittää pelin tila siirron jälkeen, eli onko shakki ja
 * päättyikö peli.
 */
public class StateResolver {

    private Turn turn;
    private GameState state;
    private CheckIdentifier checkIdentifier;
    private MoveLister moveLister;

    /**
     * Luo pelin tilan selvittäjän.
     *
     * @param turn Vuoro
     * @param state Pelin tila
     * @param checkIdentifier Pelin shakinselvittäjä
     * @param moveLister Mahdollisten siirtojen selvittäjä
     */
    public StateResolver(Turn turn, GameState state, CheckIdentifier checkIdentifier, MoveLister moveLister) {
        this.turn = turn;
        this.state = state;
        this.checkIdentifier = checkIdentifier;
        this.moveLister = moveLister;
    }

    /**
     * Metodi selvittää pelin tilan.
     *
     * @param board Lauta
     */
    public void resolve(Board board) {

        boolean moveExists = canMove(board);

        if (!checkIdentifier.isCheck(turn.activeColor(), board)) {
            state.setTurnStatus("no check");
            if (!moveExists) {
                state.setOver(true);
                state.setWinner("draw");
            }

        } else {
            state.setTurnStatus("check");

            if (!moveExists) {
                state.setOver(true);
                String winner = "WHITE";
                if (turn.activeColor().equals(winner)) {
                    winner = "BLACK";
                }

                state.setWinner(winner);
            }
        }
    }

    private boolean canMove(Board board) {
        for (GamePiece piece : board.getPieces(turn.activeColor())) {
            if (!this.moveLister.possibleTargetSquares(piece).isEmpty()) {
                return true;
            }
        }

        return false;

    }

}
