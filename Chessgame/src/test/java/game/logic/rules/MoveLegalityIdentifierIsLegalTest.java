/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author Peikko
 */
public class MoveLegalityIdentifierIsLegalTest {
    
    Board board;
    MovingRules rules;
    MoveLegalityIdentifier identifier;
    CheckIdentifier ci;
    GamePiece kingWhite;
    GamePiece kingBlack;
    GamePiece horseWhite;
    GamePiece queenBlack;

    @Before
    public void setUp() {
        
        board = new Board();
        rules = new MovingRules();
        ci = new CheckIdentifier();
        identifier = new MoveLegalityIdentifier(board, rules, ci);
        ci.setMoveLegalityIdentifier(identifier);
        
        kingWhite = new GamePiece("KING", "WHITE", "UNMOVED");
        kingBlack = new GamePiece("KING", "BLACK", "UNMOVED");
        horseWhite = new GamePiece("KNIGHT", "WHITE", "UNMOVED");
        queenBlack = new GamePiece("QUEEN", "BLACK", "UNMOVED");
        board.insertPiece(1, 1, kingWhite);
        board.insertPiece(8, 1, kingBlack);
        board.insertPiece(2, 1, horseWhite);
        board.insertPiece(7, 1, queenBlack);
    }
    
    @Test
    public void testCannotMoveFrozenKnight() {
        assertTrue(!identifier.isLegal(new Move(2, 1, 4, 2), "WHITE"));
    }
    
    @Test
    public void testCanMoveMovablePieces() {
        assertTrue(identifier.isLegal(new Move(7,1,7,7), "BLACK"));
    }
    
    @Test
    public void testCanCapture() {
        assertTrue(identifier.isLegal(new Move(7,1,2,1), "BLACK"));
    }
    
    @Test
    public void testCannotSelfCheck() {
        board.insertPiece(2, 8, new GamePiece("ROOK", "BLACK", "UNMOVED"));
        assertTrue(!identifier.isLegal(new Move(1,1,2,2),"WHITE"));
    }
    
    @Test
    public void testCannotMoveWrongColoredPiece() {
        assertTrue(!identifier.isLegal(new Move(7,1,7,2), "WHITE"));
    }
    
    @Test
    public void testOutOfBoardMoveIsIllegal() {
        assertTrue(!identifier.isLegal(new Move(7,1,7,9), "BLACK"));
    }
}
