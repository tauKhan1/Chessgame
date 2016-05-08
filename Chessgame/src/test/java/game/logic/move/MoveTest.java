
package game.logic.move;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class MoveTest {
    
    @Test
    public void testMoveReturnsCorrectHorizontalVector() {
        Move move = new Move(1,5,2,8);
        
        assertTrue(move.getHorizontalVector()==3);
    }
    
    @Test
    public void testMoveReturnsCorrectVerticalVector() {
        Move move = new Move(5,2,1,4);
        
        assertTrue(move.getVerticalVector()==-4);
    }
}
