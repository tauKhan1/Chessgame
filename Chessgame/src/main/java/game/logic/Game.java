package game.logic;

import game.logic.components.Board;
import game.logic.components.GamePiece;
import game.logic.rules.CheckIdentifier;
import game.logic.rules.MoveLegalityIdentifier;
import game.logic.rules.MovingRules;
import game.logic.setup.BasicSetup;
import game.logic.turn.Turn;
import java.util.List;

public class Game {

    private Board board;
    private BasicSetup setupper;
    private Turn turn;
    private MovingRules rules;
    private MoveLegalityIdentifier valifier;
    private CheckIdentifier checkIdentifier;

    public Game() {
        this.board = new Board();
        this.setupper = new BasicSetup();
        this.turn = new Turn();
        this.rules = new MovingRules();
        this.valifier = new MoveLegalityIdentifier(board, rules);
        this.checkIdentifier = new CheckIdentifier(valifier);
    }

    public String activeColor() {
        return turn.activeColor();
    }

    public boolean move(int prevRow, int prevCol, int nextRow, int nextCol) {
        System.out.println("yritys " + prevRow + " " + prevCol + " " + nextRow + " " + nextCol);
        String color = turn.activeColor();
        boolean isLegal = valifier.isLegal(prevRow, prevCol, nextRow, nextCol, color);

        if (!isLegal) {
            System.out.println("epäonnistui");
            return false;
        } else {
            turn.proceed();
            board.movePiece(prevRow, prevCol, nextRow, nextCol);
            return true;
        }
    }

    public List<GamePiece> getPieces(String color) {
        return this.board.getPieces(color);
    }

    public void setup() {
        this.setupper.setup(this.board);
    }
}
