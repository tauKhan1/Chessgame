package game.gui.statedisplay;

import game.logic.Game;
import game.logic.state.GameState;
import javax.swing.JLabel;

/**
 * Luokka päivittää pelin tilan ikkunaan.
 */
public class GameStateUpdater {

    Game game;

    JLabel state;
    JLabel activeColor;
    JLabel winner;

    /**
     * Luo päivittäjän.
     *
     * @param game Peli
     * @param state Tekstikenttä, johon tila päivitetään
     * @param activeColor Tekstikenttä, johon päivitetään vuorossa oleva väri
     * @param winner Tekstikenttä, jossa näytetään pelin voittaja
     */
    public GameStateUpdater(Game game, JLabel state, JLabel activeColor, JLabel winner) {
        this.game = game;
        this.state = state;
        this.activeColor = activeColor;
        this.winner = winner;
    }

    /**
     * Metodi päivittää pelin tilan.
     */
    public void update() {
        GameState currentState = this.game.getGameState();

        activeColor.setText("Turn:  " + game.activeColor());
        state.setText(currentState.getTurnStatus());

        if (currentState.getWinner() != null) {
            if (currentState.getWinner().equals("draw")) {
                winner.setText("The game ended in draw");
            } else {
                winner.setText(currentState.getWinner() + " wins the game!");
            }

            winner.setVisible(true);
        }

    }

}
