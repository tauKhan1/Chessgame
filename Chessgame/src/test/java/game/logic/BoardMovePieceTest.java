
package game.logic;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;


public class BoardMovePieceTest {
    
    Board board;
    GamePiece piece1;
    GamePiece piece2;
    
    @Before
    public void setUp(){
        
        board = new Board();
        piece1 = new GamePiece("PAWN", "WHITE", "UNMOVED");
        piece2 = new GamePiece("QUEEN", "BLACK", "UNMOVED");
        
        board.insertPiece(1, 1, piece1);
        board.insertPiece(4, 5, piece2);
        
    }
    
    @Test
    public void TestBadSquareParameters() {
        boolean moved = board.movePiece(0, 4, 3, 6);
        boolean moved2 = board.movePiece(1, 4, 19, 6);
        boolean moved3 = board.movePiece(0, 4, -10, 6);
        
        assertTrue(!(moved && moved2 && moved3));
    }
    
    @Test
    public void TestPieceMoves(){
        boolean moved = board.movePiece(1, 1, 3, 7);
        
        assertTrue(moved);
    }
    
    @Test
    public void TestPieceMovesToCorrectSquare(){
        boolean moved = board.movePiece(1, 1, 5, 6);
        
        Square position = piece1.getLocation();
        assertTrue(position.getRow() == 5 && position.getColumn() == 6);
    }
    
    @Test
    public void TestPieceStatusUpdated(){
        board.movePiece(1, 1, 5, 6);
        
        assertTrue(piece1.getStatus().equals("MOVED"));
    }
    
    @Test
    public void TestPieceGetsCaptured(){
        board.movePiece(1, 1, 4, 5);
        
        assertTrue(piece2.getStatus().equals("CAPTURED"));
    }
}
