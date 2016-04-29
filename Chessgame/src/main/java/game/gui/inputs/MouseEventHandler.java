package game.gui.inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Luokka tulkitsee käyttäjän hiirellä antamia käskyjä pelilaudalla siirroiksi.
 */
public class MouseEventHandler implements MouseListener {

    MoveInputs inputs;
    
    /**
     * Luo syötteen kuuntelijan.
     * 
     * @param inputs Syöteruudut
     */
    public MouseEventHandler(MoveInputs inputs) {
        this.inputs = inputs;
    }
    

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = this.convertPositionIntoColumnOrRow(e.getX(), 0);
        int y = 9 - this.convertPositionIntoColumnOrRow(e.getY(), 27);
        if (x >= 1 && y < 9) {
            this.inputs.addInput(x, y);
        }
    }

    private int convertPositionIntoColumnOrRow(int x, int mod) {
        
        for (int i = 0; i <= 7; i++) {
            if ((55 + mod + (i * 80) <= x) && (x <= 134 + mod + (i * 80))) {
                return i + 1;
            }
        }

        return 0;
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

}
