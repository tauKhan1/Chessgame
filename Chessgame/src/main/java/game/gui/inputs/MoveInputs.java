package game.gui.inputs;

import game.gui.graphics.BoardStateGraphics;
import game.logic.Game;

public class MoveInputs {

    private boolean receiveInputs = true;
    private Game game;
    private BoardStateGraphics graphics;
    private int prevRow;
    private int prevCol;
    private int nextRow;
    private int nextCol;

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

    public void addInput(int x, int y) {
        if (receiveInputs) {
            permitInput(x, y);
        }
    }

    public void permitInput(int x, int y) {
        if (this.nextCol > 0 || this.prevCol == 0) {
            this.clear();
            this.prevCol = x;
            this.prevRow = y;
        } else {
            this.nextCol = x;
            this.nextRow = y;

            receiveInputs = false;
            if (attemptMove(prevRow, prevCol, nextRow, nextCol)) {
                System.out.println("true");
                this.graphics.reDraw();
            }
            receiveInputs = true;
            this.clear();
        }

    }

    public boolean attemptMove(int prevRow, int prevCol, int nextRow, int nextCol) {
        return this.game.move(prevRow, prevCol, nextRow, nextCol);
    }

    public void clear() {
        this.prevRow = 0;
        this.prevCol = 0;
        this.nextRow = 0;
        this.nextCol = 0;
    }

}
