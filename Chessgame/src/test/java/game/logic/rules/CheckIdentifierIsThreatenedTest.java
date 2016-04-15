package game.logic.rules;

import game.logic.components.Board;
import game.logic.components.GamePiece;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class CheckIdentifierIsThreatenedTest {

    Board board;
    MovingRules rules;
    MoveLegalityIdentifier moveIdentifier;
    CheckIdentifier checkIdentifier;
    GamePiece piece1;
    GamePiece piece2;
    GamePiece piece3;

    @Before
    public void setUp() {

        board = new Board();
        rules = new MovingRules();
        moveIdentifier = new MoveLegalityIdentifier(board, rules);
        checkIdentifier = new CheckIdentifier(moveIdentifier);
        piece1 = new GamePiece("BISHOP", "WHITE", "UNMOVED");
        piece2 = new GamePiece("BISHOP", "WHITE", "UNMOVED");
        piece3 = new GamePiece("BISHOP", "BLACK", "UNMOVED");
        board.insertPiece(1, 1, piece1);
        board.insertPiece(3, 3, piece2);
        board.insertPiece(5, 5, piece3);
    }

    @Test
    public void TestEmptySquareIsThreatened() {
        assertTrue(checkIdentifier.squareIsThreatened(5, 1, board, "WHITE"));
    }

    @Test
    public void TestOccupiedSquareIsThreatened() {
        assertTrue(checkIdentifier.squareIsThreatened(5, 5, board, "WHITE"));
    }
}
