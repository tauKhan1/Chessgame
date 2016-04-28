package game.logic.turn;

public class Turn {

    private int turnNumber;

    public Turn() {
        this.turnNumber = 1;
    }

    public int getTurnNumber() {
        return turnNumber;
    }

    public void setTurnNumber(int turnNumber) {
        this.turnNumber = turnNumber;
    }

    public void proceed() {
        this.turnNumber++;
    }

    public String activeColor() {
        if ((this.turnNumber % 2) == 1) {
            return "WHITE";
        } else {
            return "BLACK";
        }
    }
}
