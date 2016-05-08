package game.logic.move;

import game.logic.components.GamePiece;

/**
 * Luokka kuvaa yksittäistä siirtoa laudalla.
 */
public class Move {

    private int prevRow;
    private int prevCol;
    private int nextRow;
    private int nextCol;
    private GamePiece moved = null;
    private GamePiece captured = null;
    private String movedPreviousStatus = null;
    private String capturedPreviousStatus = null;
    private String specialAction = null;
    private Move specialMove = null;
    private int turnNum = 1;

    /**
     * Luo siirron.
     *
     * @param prevRow Siirron lähtöruudun rivi
     * @param prevCol Siirron lähtöruudun sarake
     * @param nextRow Siirron kohderuudun rivi
     * @param nextCol Siirron kohderuudun sarake
     */
    public Move(int prevRow, int prevCol, int nextRow, int nextCol) {
        this.prevRow = prevRow;
        this.prevCol = prevCol;
        this.nextRow = nextRow;
        this.nextCol = nextCol;
    }

    public int getTurnNum() {
        return turnNum;
    }

    public void setTurnNum(int turnNum) {
        this.turnNum = turnNum;
    }
    
    public String getSpecialAction() {
        return specialAction;
    }

    public void setSpecialAction(String specialAction) {
        this.specialAction = specialAction;
    }

    public Move getSpecialMove() {
        return specialMove;
    }

    public void setSpecialMove(Move specialMove) {
        this.specialMove = specialMove;
    }

    public int getPrevRow() {
        return prevRow;
    }

    public int getPrevCol() {
        return prevCol;
    }

    public int getNextRow() {
        return nextRow;
    }

    public int getNextCol() {
        return nextCol;
    }

    public GamePiece getMoved() {
        return moved;
    }

    public void setMoved(GamePiece moved) {
        this.moved = moved;
    }

    public GamePiece getCaptured() {
        return captured;
    }

    public void setCaptured(GamePiece captured) {
        this.captured = captured;
    }

    public String getMovedPreviousStatus() {
        return movedPreviousStatus;
    }

    public void setMovedPreviousStatus(String movedPreviousStatus) {
        this.movedPreviousStatus = movedPreviousStatus;
    }

    public String getCapturedPreviousStatus() {
        return capturedPreviousStatus;
    }

    public void setCapturedPreviousStatus(String capturedPreviousStatus) {
        this.capturedPreviousStatus = capturedPreviousStatus;
    }

    /**
     * Metodi palauttaa siirron sivuttaissuuntaisen liikkumisen määrän.
     *
     * @return Sivuttaissuuntainen liike
     */
    public int getHorizontalVector() {
        return this.nextCol - this.prevCol;
    }

    /**
     * Metodi palauttaa siirron pystysuuntaisen liikkeen määrän.
     *
     * @return Pystysuuntainen liike
     */
    public int getVerticalVector() {
        return this.nextRow - this.prevRow;
    }

}
