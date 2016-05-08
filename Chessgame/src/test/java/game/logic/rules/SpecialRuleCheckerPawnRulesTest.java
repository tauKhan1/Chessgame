
package game.logic.rules;

import game.logic.components.Board;
import game.logic.components.GamePiece;
import game.logic.move.Move;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class SpecialRuleCheckerPawnRulesTest {
    
    CheckIdentifier ci;
    SpecialRuleChecker checker;
    Board board;
    GamePiece piece1;
    GamePiece piece2;
    GamePiece piece3;
    Move move1;
    Move move2;
    
    @Before
    public void setUp() {
        ci = new CheckIdentifier();
        checker = new SpecialRuleChecker(ci);
        board = new Board();
        piece1 = new GamePiece("PAWN", "WHITE", "MOVED");
        piece2 = new GamePiece("PAWN", "BLACK", "UNMOVED");
        piece3 = new GamePiece("PAWN", "BLACK", "DOUBLEMOVED1");
        board.insertPiece(1, 1, piece1);
        board.insertPiece(2, 2, piece2);
        board.insertPiece(1, 2, piece3);
        
        move1 = new Move(1,1,2,1);
        move2 = new Move(1,1,2,2);
        move2.setMoved(piece1);
        move2.setTurnNum(2);
    }

    @Test
    public void TestMoveForwardWorks() {
        assertTrue(checker.check(move1, "WHITE", "pawnMoveForward", board));
    }

    @Test
    public void TestMoveForwardCaptureFails() {
        Move move = new Move(2, 2, 2, 1);
        move.setMoved(piece2);
        board.movePiece(move);
        move1.setCaptured(piece2);
        assertTrue(!checker.check(move1, "WHITE", "pawnMoveForward", board));
    }

    @Test
    public void TestDoubleForwardSucceeds() {
        Move move = new Move(2, 2, 4, 2);
        move.setMoved(piece2);
        assertTrue(checker.check(move, "BLACK", "pawnDoubleForward", board));
    }

    @Test
    public void TestDoubleForwardFailsWithMovedPiece() {
        Move move = new Move(1, 1, 3, 1);
        move.setMoved(piece1);
        assertTrue(!checker.check(move, "WHITE", "pawnDoubleForward", board));
    }

    @Test
    public void TestDoubleForwardCaptureFails() {
        Move move = new Move(1, 1, 4, 2);
        Move captureMove = new Move(2, 2, 4, 2);
        move.setMoved(piece1);
        captureMove.setMoved(piece2);
        captureMove.setCaptured(piece1);
        board.movePiece(move);
        assertTrue(!checker.check(captureMove, "BLACK", "pawnDoubleForward", board));
    }

    @Test
    public void TestCaptureEnPassantSucceeds() {
        assertTrue(checker.check(move2, "WHITE", "pawnCapture", board));
    }

    @Test
    public void TestCaptureEnPassantFailsIfWrongStatus() {
        piece3.setStatus("UNMOVED");
        assertTrue(!checker.check(move2, "WHITE", "pawnCapture", board));
    }
    
    @Test
    public void TestCaptureEnPassantFailsIfWrongTurn() {
        move2.setTurnNum(3);
        assertTrue(!checker.check(move2, "WHITE", "pawnCapture", board));
    }

    @Test
    public void TestCaptureMoveFailsIfNothingToCapture() {
        assertTrue(!checker.check(new Move(2, 2, 3, 3), "BLACK", "pawnCapture", board));
    }
}
