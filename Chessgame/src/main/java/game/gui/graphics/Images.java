package game.gui.graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

public class Images {

    private final String imageLocation = "src/main/resources/images/";
    private Map<String, BufferedImage> blackPieces;
    private Map<String, BufferedImage> whitePieces;
    private BufferedImage board;

    public Images() {
        this.blackPieces = new HashMap<>();
        this.whitePieces = new HashMap<>();

        try {
            board = ImageIO.read(new File(imageLocation + "board.png"));

            addPiecesOfColor("White");
            addPiecesOfColor("Black");

        } catch (IOException e) {
            System.out.println("Image(s) missing!");
        }

    }

    private void addPieceImage(String color, String type) throws IOException {

        BufferedImage pieceImage = ImageIO.read(new File(imageLocation + type + color + ".png"));

        if (color.equals("Black")) {
            this.blackPieces.put(type.toUpperCase(), pieceImage);
        } else {
            this.whitePieces.put(type.toUpperCase(), pieceImage);
        }
    }

    private void addPiecesOfColor(String color) throws IOException {
        addPieceImage(color, "Pawn");
        addPieceImage(color, "Rook");
        addPieceImage(color, "Bishop");
        addPieceImage(color, "Knight");
        addPieceImage(color, "Queen");
        addPieceImage(color, "King");
    }

    public BufferedImage getPieceImage(String color, String type) {

        if (color.equals("BLACK")) {
            return this.blackPieces.get(type);
        } else {
            return this.whitePieces.get(type);
        }
    }

    public BufferedImage getBoardImage() {
        return this.board;
    }
}
