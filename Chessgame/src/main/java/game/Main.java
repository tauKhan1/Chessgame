package game;

import game.gui.UserInterface;
import game.logic.Game;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        game.setup();

        UserInterface ui = new UserInterface(game);
        SwingUtilities.invokeLater(ui);
    }

}
