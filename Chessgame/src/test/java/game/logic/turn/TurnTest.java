
package game.logic.turn;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class TurnTest {
    
    Turn turn;
    
    @Before
    public void setUp() {
        
        this.turn = new Turn();
    }
    
    @Test
    public void testRightColorHasTurn() {
        turn.setTurnNumber(1);
        assertTrue(turn.activeColor().equals("WHITE"));
        
        turn.setTurnNumber(333);
        assertTrue(turn.activeColor().equals("WHITE"));
        
        turn.setTurnNumber(460);
        assertTrue(turn.activeColor().equals("BLACK"));
    }
    
    @Test
    public void testTurnProceedWorks() {
        turn.setTurnNumber(7);
        for (int i = 1; i<= 431; i++) {
            turn.proceed();
        }
        
        assertTrue(turn.getTurnNumber()==438);
    }
}
