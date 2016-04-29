package game.logic.rules;

import game.logic.components.Board;
import game.logic.components.GamePiece;
import java.util.List;

/**
 * Luokan avulla tarkistetaan onko pyydetty siirto pelisääntöjen mukainen.
 */
public class MoveLegalityIdentifier {

    private Board board;
    private MovingRules rules;
    private SpecialRuleChecker specialRuleChecker;
    
    /**
     * Luo liikkumissääntöjen oikeellisuuden tunnistajan.
     * 
     * @param board Pelilauta
     * @param rules Säännöt
     */
    public MoveLegalityIdentifier(Board board, MovingRules rules) {
        this.board = board;
        this.rules = rules;
        this.specialRuleChecker = new SpecialRuleChecker();
    }
    
    /**
     * Kertoo onko siirto sääntöjen mukainen. Toteutus keskeneräinen.
     * 
     * @param origRow   Liikutettavan nappulan rivi
     * @param origColumn    Liikutettavan nappulan sarake
     * @param nextRow   Rivi, jolle nappula yritetään siirtää
     * @param nextColumn    Sarake, jolle nappula yritetään siirtää 
     * @param color Vuorossa olevan pelaajan väri
     * @return Tulos siitä, onko liike pelin sääntöjen mukainen.
     */
    public boolean isLegal(int origRow, int origColumn, int nextRow, int nextColumn, String color) {
        if (!moveIsPossible(origRow, origColumn, nextRow, nextColumn, color)) {
            return false;
        }
        
        return doesNotCauseCheck(origRow, origColumn, nextRow, nextColumn, color);
    }

    /**
     * Kertoo onko siirto liikkumissääntöjen mukainen, ottamatta huomioon siirrosta mahdollisesti aiheutuvaa
     * shakkia, joka voi estää siirron.
     * 
     * @param origRow   Liikutettavan nappulan rivi
     * @param origColumn    Liikutettavan nappulan sarake
     * @param nextRow   Rivi, jolle nappula yritetään siirtää
     * @param nextColumn    Sarake, jolle nappula yritetään siirtä
     * @param color Vuorossa olevan pelaajan väri
     * 
     * @return Tulos siitä, onko liike liikkumissääntöjen mukainen
     */    
    public boolean moveIsPossible(int origRow, int origColumn, int nextRow, int nextColumn, String color) {
        if (!isMoveWithinBoard(origRow, origColumn, nextRow, nextColumn)) {
            return false;
            
        }

        GamePiece movable = board.getSquareContent(origRow, origColumn);

        if (movable == null) {
            return false;
        }

        if (!movable.getColor().equals(color)) {
            return false;
        }

        String type = movable.getType();
        List<MovingRule> pieceRules = this.rules.getRules(type);

        for (MovingRule pieceRule : pieceRules) {
            if (ruleIsApplicable(origRow, origColumn, nextRow, nextColumn, color, pieceRule)) {
                return true;
            }
        }
        return false;
    }

    private boolean isMoveWithinBoard(int origRow, int origColumn, int nextRow, int nextColumn) {

        return (origRow < 9) && (1 <= origRow) && (origColumn < 9) && (1 <= origColumn) && (nextRow < 9) && (1 <= nextRow) && (nextColumn < 9) && (1 <= nextColumn);
    }
    
    /**
     * Metodi kertoo, onko siirto yksittäisen liikkumissäännön mukainen.
     * 
     * @param or    Siirrettävän nappulan rivi
     * @param oc    Siirrettävän nappulan sarake
     * @param nr    Kohderivi
     * @param nc    Kohdesarake
     * @param color Siirrettävän nappulan väri
     * @param rule  Liikkumissääntö
     * 
     * @return Tieto siitä, onko siirto metodille annetun säännön mukainen
     */
    public boolean ruleIsApplicable(int or, int oc, int nr, int nc, String color, MovingRule rule) {

        if (!color.equals(rule.getColorRestriction()) && !(rule.getColorRestriction().isEmpty())) {
            return false;
        }

        int multiplier = attemptedMoveVectorMultiplier(rule.getBase().getHorizontalComponent(), rule.getBase().getVerticalComponent(),
                nc - oc, nr - or);

        if (multiplier < 1) {
            return false;
        }

        if (rule.getPossibleMultiplier() == '1' && multiplier > 1) {
            return false;
        }

        if (!noObstaclesInTheWayOfMove(or, oc, rule.getBase().getHorizontalComponent(),
                rule.getBase().getVerticalComponent(), multiplier, color)) {
            return false;
        }

        if (!checkSpecialRulesAllowMove(or, oc, nr, nc, color, rule)) {
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

    private boolean checkSpecialRulesAllowMove(int origRow, int origCol, int nextRow,
            int nextCol, String color, MovingRule rule) {
        
        String specialRules = rule.getSpecialRules();
        return specialRuleChecker.check(origRow, origCol, nextRow,
                nextCol, color, specialRules, board);
    }
    
    private boolean doesNotCauseCheck(int origRow, int origCol, int nextRow,
            int nextCol, String color) {
        
        return true;
    }
}
