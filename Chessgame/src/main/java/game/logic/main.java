package game.logic;

import game.gui.UserInterface;
import javax.swing.SwingUtilities;

public class main {

    public static void main(String[] args) {
        Game game = new Game();
        game.setup();

        UserInterface ui = new UserInterface(game);
        SwingUtilities.invokeLater(ui);
    }

}
