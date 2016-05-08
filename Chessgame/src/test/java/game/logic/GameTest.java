
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
    
    @Test
    public void testSampleGame() {
        assertTrue(game.move(2, 4, 4, 4));
        assertTrue(game.move(7, 4, 5, 4));
        assertTrue(game.move(1, 3, 4, 6));
        assertTrue(game.move(8, 2, 6, 3));
        assertTrue(!game.move(1, 3, 3, 3));
        assertTrue(game.getGameState().getWinner() == null);
        assertTrue(game.move(1, 2, 3, 3));
        assertTrue(game.move(8, 4, 6, 4));
        assertTrue(game.move(1, 4, 3, 4));
        assertTrue(game.move(6, 3, 4, 4));
        assertTrue(game.move(1, 5, 1, 3));
        assertTrue(game.move(6, 4, 4, 6));
        assertTrue(game.getGameState().getTurnStatus().equals("check"));
        assertTrue(!game.move(1, 3, 2, 4));        
    }
    
    @Test
    public void testGameReturnsCorrectAmountOfGamePieces() {
        assertTrue((game.getPieces("WHITE").size() == 16) && (game.getPieces("BLACK").size() == 16));
    }
        
}
