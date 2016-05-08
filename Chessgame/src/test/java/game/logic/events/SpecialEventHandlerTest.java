
package game.logic.events;

import game.logic.components.Board;
import game.logic.components.GamePiece;
import game.logic.move.Move;
import game.logic.turn.Turn;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class SpecialEventHandlerTest {

    Board board;
    Turn turn;
    SpecialEventHandler seh;
    
    GamePiece whitePawn;
    GamePiece whiteKing;
    GamePiece blackPawn;
    GamePiece whiteRook;    
    Move pawnMove;
    Move blackPawnMove;
    
    @Before
    public void setUp() {
        board = new Board();
        turn = new Turn();
        seh = new SpecialEventHandler();
        
        whitePawn = new GamePiece("PAWN", "WHITE", "MOVED");
        board.insertPiece(8, 7, whitePawn);
        
        blackPawn = new GamePiece("PAWN", "BLACK", "DOUBLEMOVED");
        board.insertPiece(7, 7, blackPawn);
        
        whiteKing = new GamePiece("KING", "WHITE", "MOVED");
        board.insertPiece(1,3,whiteKing);
        
        whiteRook = new GamePiece("ROOK", "WHITE", "UNMOVED");
        board.insertPiece(1, 1, whiteRook);
        
        pawnMove = new Move(7, 6, 8, 7);
        pawnMove.setMoved(whitePawn);
        pawnMove.setSpecialAction("captureEnPassant");
        
        blackPawnMove = new Move(8, 7, 7, 7);
        blackPawnMove.setMoved(blackPawn);
    }
    
    @Test
    public void testPawnGetsPromoted() {
        seh.executeSpecialEvents(pawnMove, board, turn);
        assertTrue(whitePawn.getType().equals("QUEEN"));
    }
    
    @Test
    public void testCaptureEnPassantWorks() {
        seh.executeSpecialEvents(pawnMove, board, turn);
        assertTrue(blackPawn.getStatus().equals("CAPTURED"));
        assertTrue(board.getSquareContent(7, 7) == null);
    }
    
    @Test
    public void testSpecialMoveHappens() {
        Move specialMove = new Move(1,1,1,4);
        specialMove.setMoved(whiteRook);
        
        Move kingMove = new Move(1, 5, 1, 3);
        kingMove.setMoved(whiteKing);
        
        kingMove.setSpecialMove(specialMove);
        seh.executeSpecialEvents(kingMove, board, turn);
        
        assertTrue(whiteRook.getLocation() == board.getSquare(1, 4));
    }
    
    @Test
    public void testPawnIsNotPromotedWrong() {

        
        seh.executeSpecialEvents(blackPawnMove, board, turn);
        assertTrue(blackPawn.getType().equals("PAWN"));
    }
    
    @Test
    public void testDoubleMoveSetsStatusRight() {
        turn.setTurnNumber(15);
        blackPawnMove.setSpecialAction("pawnDoubleMove");
        
        seh.executeSpecialEvents(blackPawnMove, board, turn);
        assertTrue(blackPawn.getStatus().equals("DOUBLEMOVED15"));
    }
    
}
