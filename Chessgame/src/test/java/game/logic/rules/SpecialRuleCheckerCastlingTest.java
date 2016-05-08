
package game.logic.rules;

import game.logic.components.Board;
import game.logic.components.GamePiece;
import game.logic.move.Move;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class SpecialRuleCheckerCastlingTest {
    
    MovingRules rules;
    MoveLegalityIdentifier mi;
    CheckIdentifier ci;
    SpecialRuleChecker checker;
    Board board;
    GamePiece kingWhite;
    GamePiece kingBlack;
    GamePiece rookWhite1;
    GamePiece rookWhite2;
    GamePiece rookBlack;
    GamePiece queenWhite;
    
    Move move1;
    Move move2;
    
    @Before
    public void setUp() {
        board = new Board();
        ci = new CheckIdentifier();
        rules = new MovingRules();
        mi = new MoveLegalityIdentifier(board, rules, ci);
        ci.setMoveLegalityIdentifier(mi);
        checker = new SpecialRuleChecker(ci);
        kingWhite = new GamePiece("KING", "WHITE", "UNMOVED");
        kingBlack = new GamePiece("KING", "BLACK", "UNMOVED");
        rookWhite1 = new GamePiece("ROOK", "WHITE", "UNMOVED");
        rookWhite2 = new GamePiece("ROOK", "WHITE", "UNMOVED");
        rookBlack = new GamePiece("ROOK", "BLACK", "UNMOVED");
        queenWhite = new GamePiece("QUEEN", "WHITE", "UNMOVED");
        
        board.insertPiece(1, 5, kingWhite);
        board.insertPiece(8, 5, kingBlack);
        board.insertPiece(1, 1, rookWhite1);
        board.insertPiece(1, 8, rookWhite2);
        board.insertPiece(8, 8, rookBlack);
        board.insertPiece(1, 7, queenWhite);
        
        move1 = new Move(1,5,1,7);
        move1.setMoved(kingWhite);
    }
    
    @Test
    public void TestNormalCastlingAllowed() {
        Move move = new Move(1,5,1,3);
        move.setMoved(kingWhite);
        
        assertTrue(checker.check(move, "WHITE", "kingCastling", board));
    }
    
    @Test
    public void TestNoCastlingThroughPiece() {
        assertTrue(!checker.check(move1, "WHITE", "kingCastling", board));
    }
    
    @Test
    public void TestThreateningPiecePreventsCastling() {
        Move move = new Move(8,5,8,7);
        move.setMoved(kingBlack);
        
        assertTrue(!checker.check(move, "BLACK", "kingCastling", board));        
    }
    
    @Test
    public void TestMovedKingCannotCastle() {
        kingWhite.setStatus("MOVED");

        Move move = new Move(1,5,1,3);
        move.setMoved(kingWhite);
        
        assertTrue(!checker.check(move, "WHITE", "kingCastling", board));        
    }
    
    @Test
    public void TestMovedRookCannotCastle() {
        rookWhite1.setStatus("MOVED");
        
        Move move = new Move(1,5,1,3);
        move.setMoved(kingWhite);
        
        assertTrue(!checker.check(move, "WHITE", "kingCastling", board));
    }
    
    @Test
    public void TestCastlingMovesRooksCorrectly() {
        Move queenMove = new Move(1, 7, 2, 4);
        queenMove.setMoved(queenWhite);
        
        board.movePiece(queenMove);
        
        Move whiteCastling = new Move(1, 5, 1, 3);
        whiteCastling.setMoved(kingWhite);
        
        checker.check(whiteCastling, "WHITE", "kingCastling", board);
        
        Move blackCastling = new Move(8, 5, 8, 7);
        blackCastling.setMoved(kingBlack);
        
        checker.check(blackCastling, "BLACK", "kingCastling", board);
        
        board.movePiece(whiteCastling.getSpecialMove());
        board.movePiece(blackCastling.getSpecialMove());
        
        assertTrue(board.getSquareContent(1, 4).equals(rookWhite1));
        assertTrue(board.getSquareContent(8, 6).equals(rookBlack));
    }
}
