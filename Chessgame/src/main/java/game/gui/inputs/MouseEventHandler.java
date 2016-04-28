
package game.gui.inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class MouseEventHandler implements MouseListener {
    
    MoveInputs inputs;

    public MouseEventHandler(MoveInputs inputs) {
        this.inputs = inputs;
    }
    
    @Override
    public void mousePressed(MouseEvent e){
        int x = this.convertPositionIntoColumnOrRow(e.getX());
        System.out.println(x);
        int y = 9 - this.convertPositionIntoColumnOrRow(e.getY());
        System.out.println(y);
        System.out.println(e.getX());
        System.out.println(e.getY());
        if (x >= 1 && y < 9) {
            this.inputs.addInput(x, y);
        }
    }
    
    
    
    private int convertPositionIntoColumnOrRow(int x) {
        
        for (int i = 0; i<=7; i++ ) {
            if (45 + (i*80) <= x &&  x <= 124 + (i*80) ) {
               return i+1;
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
    public void mouseClicked(MouseEvent e) {

    }
    
    
}
