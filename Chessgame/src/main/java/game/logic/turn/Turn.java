package game.logic.turn;

/**
 * Luokka kuvaa pelivuoroa, ja se kertoo millä värillä on vuoro liikkua.
 */
public class Turn {

    private int turnNumber;

    /**
     * Luo vuorot.
     */
    public Turn() {
        this.turnNumber = 1;
    }

    public int getTurnNumber() {
        return turnNumber;
    }

    public void setTurnNumber(int turnNumber) {
        this.turnNumber = turnNumber;
    }
    
    /**
     * Metodi aloittaa seuraavan vuoron.
     */
    public void proceed() {
        this.turnNumber++;
    }
    
    /**
     * Kertoo, kumman värisellä pelaajalla on vuoro.
     * 
     * @return Vuorossa oleva väri
     */
    public String activeColor() {
        if ((this.turnNumber % 2) == 1) {
            return "WHITE";
        } else {
            return "BLACK";
        }
    }
}
