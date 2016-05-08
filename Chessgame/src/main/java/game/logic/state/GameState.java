package game.logic.state;

/**
 * Luokka pitää kirjaa pelin tilasta.
 */
public class GameState {

    private boolean over;
    private String turnStatus;
    private String winner;

    /**
     * Luo pelin tilasta kirjaa pitävän olion.
     */
    public GameState() {
        this.turnStatus = "normal";
        over = false;
    }

    /**
     * Metodi selvittää onko peli loppu.
     *
     * @return Onko peli loppu
     */
    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public String getTurnStatus() {
        return turnStatus;
    }

    public void setTurnStatus(String turnStatus) {
        this.turnStatus = turnStatus;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

}
