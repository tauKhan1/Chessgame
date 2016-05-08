package game.gui.inputs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Luokka suorittaa siirron peruuttamisen nappia painettaessa.
 */
public class MoveInputReleaser implements ActionListener {

    private MoveInputs inputs;
    
    /**
     * Luo olion.
     * @param inputs Siirtosyötteet
     */
    public MoveInputReleaser(MoveInputs inputs) {
        this.inputs = inputs;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        inputs.clear();
    }
}
