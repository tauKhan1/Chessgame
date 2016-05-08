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

    private MouseInputTextLabel textFrom;
    private MouseInputTextLabel textTo;

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

    public MouseInputTextLabel getTextFrom() {
        return textFrom;
    }

    public void setTextFrom(MouseInputTextLabel textFrom) {
        this.textFrom = textFrom;
    }

    public MouseInputTextLabel getTextTo() {
        return textTo;
    }

    public void setTextTo(MouseInputTextLabel textTo) {
        this.textTo = textTo;
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
        if (this.prevCol == 0) {
            this.prevCol = x;
            this.prevRow = y;
            this.getTextFrom().update(y, x);
        } else {
            if (this.nextCol == 0) {
                this.nextCol = x;
                this.nextRow = y;
                this.textTo.update(y, x);
            }    
        }    
    }

    /**
     * Suorittaa siirron laudalla.
     *
     * @return Oliko siirto laillinen
     */
    public boolean perform() {
        boolean result = this.game.move(prevRow, prevCol, nextRow, nextCol);
        this.clear();
        return result;
    }

    /**
     * Tyhjentää syötekentät ja näytöt.
     */
    public void clear() {
        this.prevRow = 0;
        this.prevCol = 0;
        this.nextRow = 0;
        this.nextCol = 0;

        this.textFrom.clear();
        this.textTo.clear();
    }

}
