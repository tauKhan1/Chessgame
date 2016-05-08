package game;

import game.gui.UserInterface;
import game.logic.Game;
import javax.swing.SwingUtilities;

public class Main {

    /**
     * Metodi käynnistää ohjelman. 
     * @param args Käynnistyssyötteet
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.setup();

        UserInterface ui = new UserInterface(game);
        SwingUtilities.invokeLater(ui);
    }

}
