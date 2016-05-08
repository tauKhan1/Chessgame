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


public class CheckIdentifierIsCheckAfterMoveTest {

    
    Board board;
    MovingRules rules;
    MoveLegalityIdentifier moveIdentifier;
    CheckIdentifier checkIdentifier;
    GamePiece piece1;
    GamePiece piece2;
    GamePiece piece3;
    GamePiece piece4;
    GamePiece piece5;
    GamePiece piece6;
    
    @Before
    public void setUp() {

        board = new Board();
        rules = new MovingRules();
        checkIdentifier = new CheckIdentifier();
        moveIdentifier = new MoveLegalityIdentifier(board, rules, checkIdentifier);
        checkIdentifier.setMoveLegalityIdentifier(moveIdentifier);
        piece1 = new GamePiece("ROOK", "WHITE", "UNMOVED");
        piece2 = new GamePiece("KING", "WHITE", "UNMOVED");
        piece3 = new GamePiece("KING", "BLACK", "UNMOVED");
        piece4 = new GamePiece("QUEEN", "BLACK", "UNMOVED");
        piece5 = new GamePiece("PAWN", "BLACK", "MOVED");
        piece6 = new GamePiece("KNIGHT", "WHITE", "MOVED");
        board.insertPiece(2, 5, piece1);
        board.insertPiece(1, 5, piece2);
        board.insertPiece(8, 5, piece3);
        board.insertPiece(7, 5, piece4);
        board.insertPiece(3, 7, piece5);
        board.insertPiece(5, 7, piece6);

    }
    
    @Test
    public void movingRookCausesCheck() {
        Move move = new Move(2,5,2,1);
        move.setMoved(piece1);
        move.setMovedPreviousStatus("UNMOVED");
        
        assertTrue(checkIdentifier.isCheckAfterMove("WHITE", board, move));
    }
    
    @Test
    public void movingRookStayingInWayDoesNotCauseCheck() {
        Move move = new Move(2,5,4,5);
        move.setMoved(piece1);
        move.setMovedPreviousStatus("UNMOVED");
        
        assertTrue(!checkIdentifier.isCheckAfterMove("WHITE", board, move));
    }
    
    @Test
    public void movingKingIntoThreatenedSpaceCausesCheck() {
        Move move = new Move(1,5,2,6);
        move.setMoved(piece2);
        move.setMovedPreviousStatus("UNMOVED");
        
        Move move2 = new Move(8, 5, 7, 6);
        move2.setMoved(piece3);
        
        assertTrue(checkIdentifier.isCheckAfterMove("WHITE", board, move));
        assertTrue(checkIdentifier.isCheckAfterMove("BLACK", board, move2));
    }
    



}
