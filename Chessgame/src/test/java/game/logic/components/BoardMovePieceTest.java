
package game.logic.components;

import game.logic.components.GamePiece;
import game.logic.components.Board;
import game.logic.components.Square;
import game.logic.move.Move;
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
        Move move = new Move(1,1,5,6);
        move.setMoved(piece1);
        board.movePiece(move);
    }
        
    
    @Test
    public void TestPieceMovesToCorrectSquare(){

        
        Square position = piece1.getLocation();
        assertTrue(position.getRow() == 5 && position.getColumn() == 6);
    }
    
    @Test
    public void TestPieceStatusUpdated(){
        
        assertTrue(piece1.getStatus().equals("MOVED"));
    }
    
    @Test
    public void TestPieceGetsCaptured(){
        Move move2 = new Move(5, 6, 4, 5);
        move2.setMoved(piece1);
        move2.setCaptured(piece2);
        board.movePiece(move2);
        
        assertTrue(piece2.getStatus().equals("CAPTURED"));
    }
}
