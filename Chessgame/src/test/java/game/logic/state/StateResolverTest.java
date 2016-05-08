
package game.logic.state;

import game.logic.components.Board;
import game.logic.components.GamePiece;
import game.logic.move.MoveLister;
import game.logic.rules.CheckIdentifier;
import game.logic.rules.MoveLegalityIdentifier;
import game.logic.rules.MovingRules;
import game.logic.turn.Turn;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class StateResolverTest {

    Board board;
    MovingRules rules;
    MoveLegalityIdentifier identifier;
    CheckIdentifier ci;
    MoveLister lister;
    Turn turn;
    GameState state;
    StateResolver resolver;
    GamePiece whiteKing;
    GamePiece blackKing;
    GamePiece whiteRook;
    
    @Before
    public void setUp() {
        
        board = new Board();
        rules = new MovingRules();
        ci = new CheckIdentifier();
        identifier = new MoveLegalityIdentifier(board, rules, ci);
        ci.setMoveLegalityIdentifier(identifier);
        lister = new MoveLister(board, identifier, rules);
        turn = new Turn();
        state = new GameState();
        resolver = new StateResolver(turn, state, ci, lister);    
        
        whiteKing = new GamePiece("KING", "WHITE", "UNMOVED");
        blackKing = new GamePiece("KING", "BLACK", "UNMOVED");
        whiteRook = new GamePiece("ROOK", "WHITE", "UNMOVED");
        
        board.insertPiece(1, 1, whiteKing);
        board.insertPiece(8, 8, blackKing);
        board.insertPiece(1, 7, whiteRook);
                
    }
    
    @Test
    public void testNormalStateResolvedCorrectly() {
        turn.setTurnNumber(2);
        resolver.resolve(board);
        assertTrue(state.getTurnStatus().equals("no check") && !state.isOver());
    }
    
    @Test
    public void testWinnerIsIdentified() {
        turn.setTurnNumber(6);
        board.insertPiece(1, 8, new GamePiece("ROOK", "WHITE", "UNMOVED"));
        resolver.resolve(board);
        
        assertTrue(state.getTurnStatus().equals("check") && state.getWinner().equals("WHITE") && state.isOver());
    }
    
    @Test
    public void testDrawIsIdentified() {
        turn.setTurnNumber(8);
        board.insertPiece(5, 6, new GamePiece("BISHOP", "WHITE", "UNMOVED"));
        board.insertPiece(6, 6, new GamePiece("PAWN", "BLACK", "UNMOVED"));
        
        resolver.resolve(board);
        
        assertTrue(state.getTurnStatus().equals("no check") && state.getWinner().equals("draw") && state.isOver());
    }
    
}
