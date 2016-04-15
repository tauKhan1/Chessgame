
package game.logic.rules;

import game.logic.components.Board;
import game.logic.components.GamePiece;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class SpecialRuleCheckerPawnRulesTest {

    SpecialRuleChecker checker;
    Board board;
    GamePiece piece1;
    GamePiece piece2;
    GamePiece piece3;

    @Before
    public void setUp() {

        checker = new SpecialRuleChecker();
        board = new Board();
        piece1 = new GamePiece("PAWN", "WHITE", "MOVED");
        piece2 = new GamePiece("PAWN", "BLACK", "UNMOVED");
        piece3 = new GamePiece("PAWN", "BLACK", "DOUBLEMOVED");
        board.insertPiece(1, 1, piece1);
        board.insertPiece(2, 2, piece2);
        board.insertPiece(1, 2, piece3);
    }

    @Test
    public void TestMoveForwardWorks() {
        assertTrue(checker.check(1, 1, 2, 1, "WHITE", "pawnMoveForward", board));
    }

    @Test
    public void TestMoveForwardCaptureFails() {
        board.movePiece(2, 2, 2, 1);
        assertTrue(!checker.check(1, 1, 2, 1, "WHITE", "pawnMoveForward", board));
    }

    @Test
    public void TestDoubleForwardSucceeds() {
        assertTrue(checker.check(2, 2, 4, 2, "BLACK", "pawnDoubleForward", board));
    }

    @Test
    public void TestDoubleForwardFailsWithMovedPiece() {
        assertTrue(!checker.check(1, 1, 3, 1, "WHITE", "pawnDoubleForward", board));
    }

    @Test
    public void TestDoubleForwardCaptureFails() {
        board.movePiece(1, 1, 4, 2);
        assertTrue(!checker.check(2, 2, 4, 2, "BLACK", "pawnDoubleForward", board));
    }

    @Test
    public void TestCaptureEnPassantSucceeds() {
        assertTrue(checker.check(1, 1, 2, 2, "WHITE", "pawnCapture", board));
    }

    @Test
    public void TestCaptureEnPassantFailsIfWrongStatus() {
        piece3.setStatus("UNMOVED");
        assertTrue(checker.check(1, 1, 2, 2, "WHITE", "pawnCapture", board));
    }

    @Test
    public void TestCaptureMoveFailsIfNothingToCapture() {
        assertTrue(!checker.check(2, 2, 3, 3, "BLACK", "pawnCapture", board));
    }
}
