package game.logic;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class MoveLegalityIdentifierRuleIsApplicableTest {

    Board board;
    MovingRules rules;
    MoveLegalityIdentifier identifier;
    GamePiece piece1;
    GamePiece piece2;
    GamePiece piece3;
    List<MovingRule> inventedRules;
    MovingRule rule1;
    MovingRule rule2;
    MovingRule rule3;

    @Before
    public void setUp() {

        board = new Board();
        rules = new MovingRules();
        identifier = new MoveLegalityIdentifier(board, rules);
        piece1 = new GamePiece("BISHOP", "WHITE", "UNMOVED");
        piece2 = new GamePiece("ROOK", "WHITE", "UNMOVED");
        piece3 = new GamePiece("KNIGHT", "BLACK", "UNMOVED");
        board.insertPiece(1, 1, piece1);
        board.insertPiece(3, 3, piece2);
        board.insertPiece(5, 5, piece3);
        inventedRules = new ArrayList<>();
        rule1 = new MovingRule(1, 1, 'x');
        rule2 = new MovingRule(0, 1, '1');
        rule3 = new MovingRule(-1, 0, 'x');
        rule3.setColorRestriction("BLACK");
    }

    @Test
    public void TestSimpleRuleIsApplicable() {

        assertTrue(identifier.ruleIsApplicable(1, 1, 2, 2, "WHITE", rule1));
    }

    @Test
    public void TestUnmatchingMoveReturnsFalse() {

        assertTrue(!identifier.ruleIsApplicable(1, 1, 1, 2, "WHITE", rule1));
    }

    @Test
    public void TestLimitedRuleWorks() {

        assertTrue(identifier.ruleIsApplicable(1, 1, 1, 2, "BLACK", rule2));
    }

    @Test
    public void TestLimitedRuleDoesntAllowMultiplication() {

        assertTrue(!identifier.ruleIsApplicable(1, 1, 1, 5, "WHITE", rule2));
    }

    @Test
    public void TestSimpleRuleDoesntAllowNegativeMovement() {

        assertTrue(!identifier.ruleIsApplicable(3, 3, 3, 2, "WHITE", rule1));
    }

    @Test
    public void TestColorRestrictionWorks() {

        boolean first = identifier.ruleIsApplicable(3, 3, 1, 3, "WHITE", rule3);
        boolean second = identifier.ruleIsApplicable(5, 5, 4, 5, "BLACK", rule3);

        assertTrue(!first && second);
    }

    @Test
    public void TestCannotLeapPieces() {

        assertTrue(!identifier.ruleIsApplicable(1, 1, 4, 4, "WHITE", rule1));
    }

    @Test
    public void TestCannotEndUpOnSameColoredPiece() {

        assertTrue(!identifier.ruleIsApplicable(1, 1, 3, 3, "WHITE", rule1));
    }

    @Test
    public void TestCaptureIsntPrevented() {

        assertTrue(identifier.ruleIsApplicable(3, 3, 5, 5, "WHITE", rule1));
    }
}
