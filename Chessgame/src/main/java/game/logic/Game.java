package game.logic;

import game.logic.components.Board;
import game.logic.components.GamePiece;
import game.logic.rules.CheckIdentifier;
import game.logic.rules.MoveLegalityIdentifier;
import game.logic.rules.MovingRules;
import game.logic.setup.BasicSetup;
import game.logic.turn.Turn;
import java.util.List;

/**
 * Luokka kuvaa shakkipeliä.
 */
public class Game {

    private Board board;
    private BasicSetup setupper;
    private Turn turn;
    private MovingRules rules;
    private MoveLegalityIdentifier valifier;
    private CheckIdentifier checkIdentifier;

    /**
     * Luo pelin.
     */
    public Game() {
        this.board = new Board();
        this.setupper = new BasicSetup();
        this.turn = new Turn();
        this.rules = new MovingRules();
        this.valifier = new MoveLegalityIdentifier(board, rules);
        this.checkIdentifier = new CheckIdentifier(valifier);
    }
    
    /**
     * Palauttaa vuorossoa olevan värin.
     * 
     * @return Vuorossa oleva väri
     */
    public String activeColor() {
        return turn.activeColor();
    }
    
    /**
     * Tarkistaa, onko yritetty siirto mahdollinen, ja toteuttaa siirron tarvittaessa.
     * 
     * @param prevRow   Siirrettävän nappulan rivi
     * 
     * @param prevCol   Siirrettävän nappulan sarake
     * 
     * @param nextRow   Kohderivi
     * 
     * @param nextCol   Kohderivi
     * 
     * @return Tieto siitä, suoritettiinko siirto
     */
    public boolean move(int prevRow, int prevCol, int nextRow, int nextCol) {
        String color = turn.activeColor();
        boolean isLegal = valifier.isLegal(prevRow, prevCol, nextRow, nextCol, color);

        if (!isLegal) {
            return false;
        } else {
            turn.proceed();
            board.movePiece(prevRow, prevCol, nextRow, nextCol);
            return true;
        }
    }

    /**
     * Listaa tietynväriset nappulat laudalla.
     * 
     * @param color Väri
     * 
     * @return  Lista nappuloita 
     */
    public List<GamePiece> getPieces(String color) {
        return this.board.getPieces(color);
    }
    
    /**
     * Luo normaalin shakin alkutilanteen laudalle.
     */
    public void setup() {
        this.turn.setTurnNumber(1);
        this.setupper.setup(this.board);
    }
}
