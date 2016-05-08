
package game.logic;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;


public class GameTest {
    
    Game game;
    
    @Before
    public void setUp() {
        game = new Game();
        game.setup();
    }
    
    @Test
    public void testMovesWorkProperly() {
        assertTrue(game.move(2, 7, 4, 7));
        assertTrue(game.move(7, 7, 6, 7));
        assertTrue(game.move(1, 7, 3, 6));
        assertTrue(game.move(7, 1, 5, 1));
        assertTrue(!game.move(1, 1, 5, 1));
        assertTrue(game.move(1, 6, 2, 7));
    }
        
}
