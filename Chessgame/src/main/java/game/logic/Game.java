package game.logic;

import game.logic.components.Board;
import game.logic.components.GamePiece;
import game.logic.events.SpecialEventHandler;
import game.logic.move.Move;
import game.logic.move.MoveLister;
import game.logic.rules.CheckIdentifier;
import game.logic.rules.MoveLegalityIdentifier;
import game.logic.rules.MovingRules;
import game.logic.setup.BasicSetup;
import game.logic.state.GameState;
import game.logic.state.StateResolver;
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
    private SpecialEventHandler specialEvents;
    private MoveLister lister;
    private GameState state;
    private StateResolver stateResolver;

    /**
     * Luo pelin.
     */
    public Game() {
        this.board = new Board();
        this.setupper = new BasicSetup();
        this.turn = new Turn();
        this.rules = new MovingRules();
        this.checkIdentifier = new CheckIdentifier();
        this.valifier = new MoveLegalityIdentifier(board, rules, checkIdentifier);
        this.checkIdentifier.setMoveLegalityIdentifier(valifier);
        this.lister = new MoveLister(board, valifier, rules);
        this.specialEvents = new SpecialEventHandler();
        this.state = new GameState();
        this.stateResolver = new StateResolver(turn, state, checkIdentifier, lister);
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
     * Tarkistaa, onko yritetty siirto mahdollinen, ja toteuttaa siirron
     * tarvittaessa.
     *
     * @param prevRow Siirrettävän nappulan rivi
     * @param prevCol Siirrettävän nappulan sarake
     * @param nextRow Kohderivi
     * @param nextCol Kohderivi
     * @return Tieto siitä, suoritettiinko siirto
     */
    public boolean move(int prevRow, int prevCol, int nextRow, int nextCol) {
        String color = turn.activeColor();
        Move attemptedMove = new Move(prevRow, prevCol, nextRow, nextCol);
        attemptedMove.setTurnNum(turn.getTurnNumber());
        boolean isLegal = valifier.isLegal(attemptedMove, color);

        if (!isLegal) {
            System.out.println("väärin");
            return false;
        } else {
            board.movePiece(attemptedMove);
            specialEvents.executeSpecialEvents(attemptedMove, board, turn);
            turn.proceed();
            stateResolver.resolve(board);
            return true;
        }
    }

    /**
     * Listaa tietynväriset nappulat laudalla.
     *
     * @param color Väri
     *
     * @return Lista nappuloita
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
    
    public GameState getGameState() {
        return this.state;
    }
}
