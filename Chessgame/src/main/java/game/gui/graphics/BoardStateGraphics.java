package game.gui.graphics;

import game.logic.Game;
import game.logic.components.GamePiece;
import java.awt.Color;
import java.awt.Font;
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
     *
     * @param game Peli
     */
    public BoardStateGraphics(Game game) {
        super();

        this.images = new Images();
        this.game = game;

    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        
        List<GamePiece> whitePieces = game.getPieces("WHITE");
        List<GamePiece> blackPieces = game.getPieces("BLACK");

        graphics.setFont(new Font("serif", Font.PLAIN, 40));
        paintColumnLabels(graphics);
        paintRowLabels(graphics);
        paintSquares(graphics);
        
        paintPieces(whitePieces, graphics);
        paintPieces(blackPieces, graphics);
    }

    private int convertPieceColumnToX(GamePiece piece) {

        int column = piece.getLocation().getColumn();
        return 50 + 60 * (column - 1);
    }

    private int convertPieceRowToY(GamePiece piece) {

        int row = piece.getLocation().getRow();
        return 50 + 60 * (8 - row);
    }

    private void paintPieces(List<GamePiece> pieces, Graphics graphics) {
        for (GamePiece piece : pieces) {
            graphics.drawImage(images.getPieceImage(piece.getColor(), piece.getType()),
                    convertPieceColumnToX(piece), convertPieceRowToY(piece), null);
        }

    }

    private void paintColumnLabels(Graphics g) {
        String paintable = "ABCDEFGH";

        for (int i = 0; i <= 7; i++) {
            g.drawString(paintable.substring(i, i + 1), 65 + 60 * i, 40);
        }
    }

    private void paintRowLabels(Graphics g) {
        String rowLabels = "87654321";

        for (int i = 0; i <= 7; i++) {
            g.drawString(rowLabels.substring(i, i + 1), 20, 90 + 60 * i);
        }
    }

    private void paintSquares(Graphics g) {
        for (int j = 0; j <= 7; j++) {
            for (int i = 0; i <= 7; i++) {
                g.setColor(Color.GRAY);
                if ((i + j) % 2 == 0) {
                    g.setColor(Color.WHITE);
                }
                g.fillRect(50 + i * 60, 50 + j * 60, 60, 60);
            }
        }
    }
}
