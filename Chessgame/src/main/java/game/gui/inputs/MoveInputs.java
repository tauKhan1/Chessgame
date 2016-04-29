package game.gui.inputs;

import game.gui.graphics.BoardStateGraphics;
import game.logic.Game;

/**
 * Luokka ottaa vastaan siirtokäskyt ja yrittää toteuttaa ne pelissä.
 */
public class MoveInputs {

    private boolean receiveInputs = true;
    private Game game;
    private BoardStateGraphics graphics;
    private int prevRow;
    private int prevCol;
    private int nextRow;
    private int nextCol;
    
    /**
     * Luo ilmentymän oliosta.
     * 
     * @param game Peli
     * 
     * @param graphics Pelitilanteen piirtäjä
     */
    public MoveInputs(Game game, BoardStateGraphics graphics) {
        this.prevRow = 0;
        this.prevCol = 0;
        this.nextRow = 0;
        this.nextCol = 0;

        this.game = game;
        this.graphics = graphics;
    }

    public int getPrevRow() {
        return prevRow;
    }

    public void setPrevRow(int prevRow) {
        this.prevRow = prevRow;
    }

    public int getPrevCol() {
        return prevCol;
    }

    public void setPrevCol(int prevCol) {
        this.prevCol = prevCol;
    }

    public int getNextRow() {
        return nextRow;
    }

    public void setNextRow(int nextRow) {
        this.nextRow = nextRow;
    }

    public int getNextCol() {
        return nextCol;
    }

    public void setNextCol(int nextCol) {
        this.nextCol = nextCol;
    }
    
    /**
     * Lisää hiirellä annetun ruutusyötteen, jos pelitilannetta ohjelma ei
     * käsittele pelitilannetta sillä hetkellä.
     * 
     * @param x syötteen rivi
     * 
     * @param y syötteen sarake
     */
    public void addInput(int x, int y) {
        if (receiveInputs) {
            permitInput(x, y);
        }
    }

    private void permitInput(int x, int y) {
        if (this.nextCol > 0 || this.prevCol == 0) {
            this.clear();
            this.prevCol = x;
            this.prevRow = y;
        } else {
            this.nextCol = x;
            this.nextRow = y;

            receiveInputs = false;
            if (attemptMove(prevRow, prevCol, nextRow, nextCol)) {
                this.graphics.reDraw();
            }
            this.clear();
            receiveInputs = true;
        }

    }

    private boolean attemptMove(int prevRow, int prevCol, int nextRow, int nextCol) {
        return this.game.move(prevRow, prevCol, nextRow, nextCol);
    }

    private void clear() {
        this.prevRow = 0;
        this.prevCol = 0;
        this.nextRow = 0;
        this.nextCol = 0;
    }

}
