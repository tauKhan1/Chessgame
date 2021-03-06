package game.logic.components;

/**
 * Luokka kuvaa ruutua shakkilaudalla.
 */
public class Square {
    
    private int row;
    private int column;
    private GamePiece piece;
    
    /**
     * Luo ruudun.
     * 
     * @param row   Rivi
     * @param column Sarake
     */
    public Square(int row, int column) {
        this.row = row;
        this.column = column;
    }
    
    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public GamePiece getPiece() {
        return piece;
    }

    public void setPiece(GamePiece piece) {
        this.piece = piece;
    }
    
    
    
}
