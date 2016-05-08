package game.logic.move;

import game.logic.components.Board;
import game.logic.components.GamePiece;
import game.logic.components.Square;
import game.logic.rules.MoveLegalityIdentifier;
import game.logic.rules.MovingRule;
import game.logic.rules.MovingRules;
import java.util.ArrayList;
import java.util.List;

/**
 * Luokan selvittää ruudut, joihin shakkinappulan voi siirtää pelisääntöjen
 * mukaisesti.
 */
public class MoveLister {

    private Board board;
    private MoveLegalityIdentifier identifier;
    private MovingRules rules;

    /**
     * Luo nappuloiden mahdolliset siirrot selvittävän olion.
     *
     * @param board Lauta
     * @param identifier Siirron laillisuuden selvittäjä
     * @param rules Liikkumissäännöt
     */
    public MoveLister(Board board, MoveLegalityIdentifier identifier, MovingRules rules) {
        this.board = board;
        this.identifier = identifier;
        this.rules = rules;
    }
    
    /**
     * Metodi listaa ruudut, joihin annetun nappulan voi laillisesti siirtää.
     * @param piece Shakkinappula
     * @return Lista ruutuja, joihin nappulan saa siirtää
     */
    public List<Square> possibleTargetSquares(GamePiece piece) {

        List<Square> targets = new ArrayList<>();

        List<MovingRule> pieceRules = rules.getRules(piece.getType());

        for (MovingRule rule : pieceRules) {

            for (int i = 1; i < 8; i++) {
                Move move = createPossibleMove(i, rule, piece);
                if (identifier.isLegal(move, piece.getColor())) {
                    targets.add(board.getSquare(move.getNextRow(), move.getNextCol()));
                } else {
                    break;
                }
            }
        }

        return targets;
    }

    private Move createPossibleMove(int multiplier, MovingRule rule, GamePiece piece) {

        int prevRow = piece.getLocation().getRow();
        int prevCol = piece.getLocation().getColumn();

        int horizontalBase = rule.getBase().getHorizontalComponent();
        int verticalBase = rule.getBase().getVerticalComponent();

        return new Move(prevRow, prevCol, prevRow + verticalBase * multiplier, prevCol + horizontalBase * multiplier);

    }
}
