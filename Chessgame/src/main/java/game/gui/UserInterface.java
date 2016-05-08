package game.gui;

import game.gui.graphics.BoardStateGraphics;
import game.gui.inputs.MouseEventHandler;
import game.gui.inputs.MouseInputTextLabel;
import game.gui.inputs.MoveInputReleaser;
import game.gui.inputs.MoveInputs;
import game.gui.inputs.MovePerformer;
import game.gui.statedisplay.GameStateUpdater;
import game.logic.Game;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

/**
 * Käyttöliittymän pääluokka, joka luo kuvanpiirtämiseen ja syötteiden
 * vastaanottamiseen tarvittavien luokkien ilmentymät.
 */
public class UserInterface implements Runnable {

    private JFrame frame;
    private Game game;
    private MoveInputs inputs;
    private BoardStateGraphics graphics;
    
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
        frame.setPreferredSize(new Dimension(1000, 600));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);

    }

    private void createComponents(Container container) {
        container.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        
        createBoardGraphics(container, constraints);
        createMouseInputTextDisplayers(container, constraints, inputs);
        MovePerformer movePerformer = createMoveButtons(container, constraints);
        
        createGameStateDisplay(container, constraints, movePerformer);
    }
    
    private void createBoardGraphics(Container container, GridBagConstraints constraints) {
        createBoardConstraints(constraints);
        
        graphics = new BoardStateGraphics(game);
        this.inputs = new MoveInputs(this.game, graphics);        
        MouseListener mouseEvents = new MouseEventHandler(this.inputs);
        container.add(graphics, constraints);
        graphics.addMouseListener(mouseEvents);        
    }
    
    private void createMouseInputTextDisplayers(Container cner, GridBagConstraints constraints, MoveInputs inputs) {
        MouseInputTextLabel fromSquare = new MouseInputTextLabel("From: ");
        createTextBoxConstraints(constraints);
        cner.add(fromSquare, constraints);
        
        MouseInputTextLabel toSquare = new MouseInputTextLabel("To: ");
        createTextBoxConstraints(constraints);
        constraints.gridx = 3;
        cner.add(toSquare, constraints);
        
        inputs.setTextFrom(fromSquare);
        inputs.setTextTo(toSquare);
    }
    
    private MovePerformer createMoveButtons(Container cner, GridBagConstraints constraints) {

        JButton moveButton = new JButton("Move!");
        MovePerformer movePerformer = new MovePerformer(inputs);
        moveButton.addActionListener(movePerformer);
        createTextBoxConstraints(constraints);
        constraints.gridy = 2;
        cner.add(moveButton, constraints);

        
        JButton cancelButton = new JButton("Release!");
        cancelButton.addActionListener(new MoveInputReleaser(inputs));
        constraints.gridx = 3;
        cner.add(cancelButton, constraints);
        
        return movePerformer;
    }
    
    private void createGameStateDisplay(Container cner, GridBagConstraints constraints, MovePerformer perf) {

        JLabel activeColor = new JLabel("Turn: WHITE");
        constraints.gridy = 0;
        cner.add(activeColor, constraints);
        
        JLabel state = new JLabel();
        constraints.gridy = 3;
        cner.add(state, constraints);
        
        JLabel winner = new JLabel();
        constraints.gridy = 4;
        cner.add(winner, constraints);
        
        GameStateUpdater stateUpdater = new GameStateUpdater(game, state, activeColor, winner);
        stateUpdater.update();
        perf.setUpdater(stateUpdater);        
    }
    
    private void createBoardConstraints(GridBagConstraints c) {
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.8;
        c.weighty = 1.0;
        c.gridheight = 5;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 0;
    }
    
    private void createTextBoxConstraints(GridBagConstraints c) {
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.1;
        c.weighty = 0.5;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.gridx = 2;
        c.gridy = 1;
    }

    public JFrame getFrame() {
        return frame;
    }
}    

