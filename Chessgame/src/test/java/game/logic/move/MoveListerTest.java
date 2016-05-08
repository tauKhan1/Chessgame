
package game.logic.move;

import game.logic.components.Board;
import game.logic.components.GamePiece;
import game.logic.components.Square;
import game.logic.rules.CheckIdentifier;
import game.logic.rules.MoveLegalityIdentifier;
import game.logic.rules.MovingRules;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 */
public class MoveListerTest {
    
    Board board;
    MovingRules rules;
    MoveLegalityIdentifier identifier;
    CheckIdentifier ci;
    MoveLister lister;
    
    GamePiece whiteKing;
    GamePiece blackKing;
    GamePiece blackRook;
    GamePiece whiteRook;
    GamePiece whitePawn;
    GamePiece blackPawn;
    
    @Before
    public void setUp() {
        
        board = new Board();
        rules = new MovingRules();
        ci = new CheckIdentifier();
        identifier = new MoveLegalityIdentifier(board, rules, ci);
        ci.setMoveLegalityIdentifier(identifier);
        lister = new MoveLister(board, identifier, rules);

        whiteKing = new GamePiece("KING", "WHITE", "UNMOVED");
        blackKing = new GamePiece("KING", "BLACK", "UNMOVED");
        blackRook = new GamePiece("ROOK", "BLACK", "UNMOVED");
        whiteRook = new GamePiece("ROOK", "WHITE", "UNMOVED");
        whitePawn = new GamePiece("PAWN", "WHITE", "UNMOVED");
        blackPawn = new GamePiece("PAWN", "BLACK", "UNMOVED");
        
        board.insertPiece(1, 3, whiteKing);
        board.insertPiece(8, 3, blackKing);
        board.insertPiece(7, 3, blackRook);
        board.insertPiece(5, 3, whiteRook);
        board.insertPiece(6, 4, blackPawn);
        board.insertPiece(5, 4, whitePawn);
    }
    
    @Test
    public void testWhitePawnHasNoMoves() {
        List<Square> whitePawnMoves = lister.possibleTargetSquares(whitePawn);
        
        assertTrue(whitePawnMoves.isEmpty());
    }
    
    @Test
    public void testBlackPawnHasOnlyCaptureMove() {
        List<Square> blackPawnMoves = lister.possibleTargetSquares(blackPawn);
        Square square = board.getSquare(5, 3);
        
        assertTrue(blackPawnMoves.get(0) == square && (blackPawnMoves.size()==1) );     
    }
    
    @Test
    public void testWhiteRookHasCorrectMoves() {
        List<Square> whiteRookMoves = lister.possibleTargetSquares(whiteRook);
        
        List<Square> movesThatShouldExist = new ArrayList<>();
        
        for (int i=2; i<=7; i++) {
            if (i != 5) {
                movesThatShouldExist.add(board.getSquare(i, 3));
            }
        }
        
        assertTrue(whiteRookMoves.containsAll(movesThatShouldExist) && whiteRookMoves.size() == 5);
    }
}
