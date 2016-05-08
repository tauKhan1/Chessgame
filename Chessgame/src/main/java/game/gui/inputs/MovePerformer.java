package game.gui.inputs;

import game.gui.statedisplay.GameStateUpdater;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Luokka kuuntelee napinpainalluksia ja suorittaa siirtokäskyn pelille.
 */
public class MovePerformer implements ActionListener {

    private MoveInputs inputs;
    private GameStateUpdater updater;

    /**
     * Luo kuuntelijan.
     *
     * @param inputs Siirtosyötteet
     */
    public MovePerformer(MoveInputs inputs) {
        this.inputs = inputs;
    }

    public void setUpdater(GameStateUpdater updater) {
        this.updater = updater;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (this.inputs.perform()) {
            updater.update();
        }
    }
}
