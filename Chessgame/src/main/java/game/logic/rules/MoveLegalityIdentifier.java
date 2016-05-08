package game.logic.rules;

import game.logic.components.Board;
import game.logic.components.GamePiece;
import game.logic.move.Move;
import java.util.List;

/**
 * Luokan avulla tarkistetaan onko pyydetty siirto pelisääntöjen mukainen.
 */
public class MoveLegalityIdentifier {

    private Board board;
    private MovingRules rules;
    private SpecialRuleChecker specialRuleChecker;
    private CheckIdentifier checkIdentifier;

    /**
     * Luo liikkumissääntöjen oikeellisuuden tunnistajan.
     *
     * @param board Pelilauta
     * @param rules Säännöt
     * @param c Shakin tarkastaja, joka tarkistaa ettei laudalle synny shakkia
     * siirtojen yhteydessä
     */
    public MoveLegalityIdentifier(Board board, MovingRules rules, CheckIdentifier c) {
        this.board = board;
        this.rules = rules;
        this.specialRuleChecker = new SpecialRuleChecker(c);
        this.checkIdentifier = c;
    }

    /**
     * Kertoo onko siirto sääntöjen mukainen. Toteutus keskeneräinen.
     *
     * @param move yritetty siirto
     * @param color Vuorossa olevan pelaajan väri
     * @return Tulos siitä, onko liike pelin sääntöjen mukainen.
     */
    public boolean isLegal(Move move, String color) {
        if (!moveIsPossible(move, color)) {
            return false;
        }
        return doesNotCauseCheck(move, color);
    }

    /**
     * Kertoo onko siirto liikkumissääntöjen mukainen, ottamatta huomioon
     * siirrosta mahdollisesti aiheutuvaa shakkia, joka voi estää siirron.
     *
     * @param move siirto
     * @param color Vuorossa olevan pelaajan väri
     *
     * @return Tulos siitä, onko liike liikkumissääntöjen mukainen
     */
    public boolean moveIsPossible(Move move, String color) {
        if (!isMoveWithinBoard(move)) {
            return false;

        }

        GamePiece movable = board.getSquareContent(move.getPrevRow(), move.getPrevCol());

        if (movable == null) {
            return false;
        }
        move.setMoved(movable);
        move.setMovedPreviousStatus(movable.getStatus());
        if (!movable.getColor().equals(color)) {
            return false;
        }
        move.setCaptured(board.getSquareContent(move.getNextRow(), move.getNextCol()));

        String type = movable.getType();
        List<MovingRule> pieceRules = this.rules.getRules(type);

        for (MovingRule pieceRule : pieceRules) {
            if (ruleIsApplicable(move, color, pieceRule)) {
                return true;
            }
        }
        return false;
    }

    private boolean isMoveWithinBoard(Move move) {

        return (move.getPrevRow() < 9) && (1 <= move.getPrevRow()) && (move.getPrevCol() < 9) && (1 <= move.getNextRow()) && (move.getNextRow() < 9) && (1 <= move.getNextRow()) && (move.getNextCol() < 9) && (1 <= move.getNextCol());
    }

    /**
     * Metodi kertoo, onko siirto yksittäisen liikkumissäännön mukainen.
     *
     * @param move siirto
     * @param color Siirrettävän nappulan väri
     * @param rule Liikkumissääntö
     *
     * @return Tieto siitä, onko siirto metodille annetun säännön mukainen
     */
    public boolean ruleIsApplicable(Move move, String color, MovingRule rule) {

        if (!color.equals(rule.getColorRestriction()) && !(rule.getColorRestriction().isEmpty())) {
            return false;
        }
        int multiplier = attemptedMoveVectorMultiplier(rule.getBase().getHorizontalComponent(), rule.getBase().getVerticalComponent(),
                move.getHorizontalVector(), move.getVerticalVector());

        if (multiplier < 1) {
            return false;
        }

        if (rule.getPossibleMultiplier() == '1' && multiplier > 1) {
            return false;
        }

        if (!noObstaclesInTheWayOfMove(move.getPrevRow(), move.getPrevCol(), rule.getBase().getHorizontalComponent(),
                rule.getBase().getVerticalComponent(), multiplier, color)) {
            return false;
        }

        if (!checkSpecialRulesAllowMove(move, color, rule)) {
            return false;
        }
        return true;
    }

    private int attemptedMoveVectorMultiplier(int horizontalRuleVector, int verticalRuleVector, int horizontalMoveVector, int verticalMoveVector) {

        int division;

        if (horizontalRuleVector == 0) {
            if (verticalMoveVector % verticalRuleVector != 0 || horizontalMoveVector != 0) {
                return 0;
            } else {
                return verticalMoveVector / verticalRuleVector;
            }
        }

        if (verticalRuleVector == 0) {
            if (horizontalMoveVector % horizontalRuleVector != 0 || verticalMoveVector != 0) {
                return 0;
            } else {
                return horizontalMoveVector / horizontalRuleVector;
            }
        }

        if (horizontalMoveVector % horizontalRuleVector != 0 || verticalMoveVector % verticalRuleVector != 0) {
            return 0;
        }
        division = horizontalMoveVector / horizontalRuleVector;
        if (division != verticalMoveVector / verticalRuleVector || division < 1) {
            return 0;
        }

        return division;
    }

    private boolean noObstaclesInTheWayOfMove(int or, int oc, int horizontalBase, int verticalBase,
            int multiplier, String color) {

        for (int i = 1; i <= multiplier; i++) {
            GamePiece squareContent = board.getSquareContent(or + verticalBase * i,
                    oc + horizontalBase * i);
            if (i < multiplier) {
                if (squareContent != null) {
                    return false;
                }
            } else {
                if (squareContent != null) {
                    if (squareContent.getColor().equals(color)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private boolean checkSpecialRulesAllowMove(Move move, String color, MovingRule rule) {

        String specialRules = rule.getSpecialRules();
        return specialRuleChecker.check(move, color, specialRules, board);
    }

    private boolean doesNotCauseCheck(Move move, String color) {

        return !this.checkIdentifier.isCheckAfterMove(color, board, move);
    }
}
