package game.gui.graphics;

import game.logic.Game;
import game.logic.components.GamePiece;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import javax.swing.JPanel;

/**
 * Luokka piirtää pelitilanteen ruudulle.
 */
public class BoardStateGraphics extends JPanel {

    private Images images;
    private Game game;
    
    /**
     * Luo ilmentymän ja kuvat lataavan olion.
     * @param game 
     */
    public BoardStateGraphics(Game game) {
        super();

        this.images = new Images();
        this.game = game;

    }

    @Override
    protected void paintComponent(Graphics graphics) {
        List<GamePiece> whitePieces = game.getPieces("WHITE");
        List<GamePiece> blackPieces = game.getPieces("BLACK");
        
        String color = game.activeColor();
        if (color.equals("WHITE")) {
            graphics.setColor(Color.YELLOW);
        } else {
            graphics.setColor(Color.BLACK);
        }
        

        graphics.drawImage(images.getBoardImage(), 0, 0, null);

        graphics.fillRect(720, 400, 50, 50);
        paintPieces(whitePieces, graphics);
        paintPieces(blackPieces, graphics);
        repaint();
    }
    
    /**
     * Piirtää laudan uudelleen vastaamaan nykyistä tilannetta.
     */
    public void reDraw() {
        repaint();
    }
    

    private int convertPieceColumnToX(GamePiece piece) {

        int column = piece.getLocation().getColumn();
        return 50 + 80 * (column - 1);
    }

    private int convertPieceRowToY(GamePiece piece) {

        int row = piece.getLocation().getRow();
        return 50 + 80 * (8 - row);
    }

    private void paintPieces(List<GamePiece> pieces, Graphics graphics) {
        for (GamePiece piece : pieces) {
            graphics.drawImage(images.getPieceImage(piece.getColor(), piece.getType()),
                    convertPieceColumnToX(piece), convertPieceRowToY(piece), null);
        }

    }
}
