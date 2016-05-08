package game.logic.components;

import game.logic.move.Move;
import java.util.ArrayList;
import java.util.List;

/**
 * Luokka sisältää shakkinappuloiden sijainnit ja niitä siirretän sen kautta.
 */
public class Board {

    private final List<Square> squares;
    private final List<GamePiece> pieces;
    
    /**
     * Luo pelilaudan ja ruudut sille.
     */
    public Board() {
        this.squares = new ArrayList<>();
        this.pieces = new ArrayList<>();

        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                Square newSquare = new Square(i, j);
                this.squares.add(newSquare);
            }

        }
    }
    
    /**
     * Metodi palauttaa kysytyssä ruudussa (mahdollisesti) sijaitsevan nappulan.
     * 
     * @param row   Ruudun rivi
     * 
     * @param column    Ruudun sarake
     * 
     * @return Nappula tai tyhjä viite, jos ruudussa ei ole nappulaa
     */
    public GamePiece getSquareContent(int row, int column) {

        return this.squares.get(convertPairToSquare(row, column)).getPiece();
    }
    
    /**
     * Metodi palauttaa kysytyssä sijainnissa olevan ruudun.
     * 
     * @param row Ruudun rivi
     * @param column Ruudun sarake
     * @return Ruutu
     */
    public Square getSquare(int row, int column) {
        return this.squares.get(convertPairToSquare(row, column));
    }
    
    /**
     * Metodi palauttaa listan tietynvärisistä laudalla olevista nappuloista.
     * 
     * @param color Haettujen nappuloiden väri
     * @return Lista nappuloita
     */
    public List<GamePiece> getPieces(String color) {
        List<GamePiece> piecesOfSameColor = new ArrayList<>();

        for (GamePiece piece : pieces) {
            if (piece.getColor().equals(color) && !piece.getStatus().equals("CAPTURED")) {

                piecesOfSameColor.add(piece);
            }
        }

        return piecesOfSameColor;
    }
    
    /**
     * Metodi sijoittaa annettuun ruutuun sille pyydetyn nappulan.
     * 
     * @param row   Sijoitusruudun rivi
     * 
     * @param column    Sijoitusruudun sarake
     * 
     * @param piece Sijoitettava nappula
     */
    public void insertPiece(int row, int column, GamePiece piece) {
        if (isSquare(row, column)) {
            Square square = this.squares.get(convertPairToSquare(row, column));

            square.setPiece(piece);
            piece.setLocation(square);
            pieces.add(piece);

        }

    }

    /**
     * Metodi poistaa nappulan laudalta.
     * 
     * @param piece Nappula, joka halutaan poistaa.
     */
    public void removePiece(GamePiece piece) {
        piece.getLocation().setPiece(null);
        pieces.remove(piece);
    }
    
    /**
     * Metodi liikuttaa nappulaa laudalla.
     * 
     * @param move Suoritettava siirto
     */
    public void movePiece(Move move) {
        GamePiece moving = move.getMoved();
        GamePiece captured = move.getCaptured();
        Square targetSquare = this.squares.get(convertPairToSquare(move.getNextRow(), move.getNextCol()));
        if (captured != null) {
            capture(captured);
        }

        moving.getLocation().setPiece(null);
        targetSquare.setPiece(moving);
        moving.setLocation(targetSquare);
        moving.setStatus("MOVED");

    }
    
    /**
     * Metodilla peruutetaan siirto eli asetetaan lauta siirtoa edeltäneeseen tilaan.
     * @param move Peruutettava siirto
     */
    public void revertMove(Move move) {
        Square prevSquare = this.squares.get(convertPairToSquare(move.getPrevRow(), move.getPrevCol()));
        Square current = this.squares.get(convertPairToSquare(move.getNextRow(), move.getNextCol()));
        GamePiece moved = move.getMoved();
        prevSquare.setPiece(moved);
        GamePiece captured = move.getCaptured();
        
        moved.setLocation(prevSquare);
        moved.setStatus(move.getMovedPreviousStatus());
        
        current.setPiece(captured);
        if (captured != null) {
            captured.setStatus(move.getCapturedPreviousStatus());
        }
    }

    private int convertPairToSquare(int row, int column) {
        return (row - 1) * 8 + column - 1;
    }
    
    /**
     * Metodilla syödään nappula laudalta.
     * @param piece Syötävä nappula
     */
    public void capture(GamePiece piece) {
        piece.getLocation().setPiece(null);
        piece.setStatus("CAPTURED");
    }

    private boolean isSquare(int row, int column) {
        return 0 < row && 0 < column && row < 9 && column < 9;
    }
    
    /**
     * Metodi palauttaa kysytyn värisen kuninkaan sijainnin.
     * 
     * @param color Haluttu väri.
     * @return Ruutu, jolla kuningas sijaitsee
     */
    public Square getKingLocation(String color) {
        for (GamePiece piece: this.pieces) {
            if (piece.getColor().equals(color) && piece.getType().equals("KING")) {
                return piece.getLocation();
            }
        }
        return null;
    }
}
