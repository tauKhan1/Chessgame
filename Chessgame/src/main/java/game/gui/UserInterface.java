package game.gui;

import game.gui.graphics.BoardStateGraphics;
import game.gui.inputs.MouseEventHandler;
import game.gui.inputs.MoveInputs;
import game.logic.Game;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * Käyttöliittymän pääluokka, joka luo kuvanpiirtämiseen ja syötteiden
 * vastaanottamiseen tarvittavien luokkien ilmentymät.
 */
public class UserInterface implements Runnable {

    private JFrame frame;
    private Game game;
    private MoveInputs inputs;
    
    /**
     * Luo olion ja asettaa sille pelin.
     * 
     * @param game Peli 
     */
    public UserInterface(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        frame = new JFrame("Chess");
        frame.setPreferredSize(new Dimension(800, 800));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);

    }

    private void createComponents(Container container) {
        BoardStateGraphics boardGraphics = new BoardStateGraphics(game);
        this.inputs = new MoveInputs(this.game, boardGraphics);        
        MouseListener mouseEvents = new MouseEventHandler(this.inputs);
        container.add(boardGraphics);
        frame.addMouseListener(mouseEvents);

    }

    public JFrame getFrame() {
        return frame;
    }
}    

