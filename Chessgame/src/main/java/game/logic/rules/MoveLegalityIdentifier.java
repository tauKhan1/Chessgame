package game.logic.rules;

import game.logic.components.Board;
import game.logic.components.GamePiece;
import java.util.List;

public class MoveLegalityIdentifier {

    private Board board;
    private MovingRules rules;
    private SpecialRuleChecker specialRuleChecker;

    public MoveLegalityIdentifier(Board board, MovingRules rules) {
        this.board = board;
        this.rules = rules;
        this.specialRuleChecker = new SpecialRuleChecker();
    }
    
    public boolean isLegal(int origRow, int origColumn, int nextRow, int nextColumn, String color) {
        if (!moveIsPossible(origRow, origColumn, nextRow, nextColumn, color)) {
            System.out.println("isLegal epäonnistui");
            return false;
        }
        
        return doesNotCauseCheck(origRow, origColumn, nextRow, nextColumn, color);
    }
      
    public boolean moveIsPossible(int origRow, int origColumn, int nextRow, int nextColumn, String color) {
        if (!isMoveWithinBoard(origRow, origColumn, nextRow, nextColumn)) {
            return false;
            
        }

        GamePiece movable = board.getSquareContent(origRow, origColumn);

        if (movable == null) {
            System.out.println("ei nappulaa");
            return false;
        }

        if (!movable.getColor().equals(color)) {
            System.out.println("väärä väri");
            return false;
        }

        String type = movable.getType();
        List<MovingRule> pieceRules = this.rules.getRules(type);

        for (MovingRule pieceRule : pieceRules) {
            if (ruleIsApplicable(origRow, origColumn, nextRow, nextColumn, color, pieceRule)) {
                return true;
            }
        }
        System.out.println("ei löydetty sääntöä");
        return false;
    }

    private boolean isMoveWithinBoard(int origRow, int origColumn, int nextRow, int nextColumn) {

        return (origRow < 9) && (1 <= origRow) && (origColumn < 9) && (1 <= origColumn) && (nextRow < 9) && (1 <= nextRow) && (nextColumn < 9) && (1 <= nextColumn);
    }

    public boolean ruleIsApplicable(int or, int oc, int nr, int nc, String color, MovingRule rule) {

        if (!color.equals(rule.getColorRestriction()) && !(rule.getColorRestriction().isEmpty())) {
            System.out.println("väri väärin");
            return false;
        }

        int multiplier = attemptedMoveVectorMultiplier(rule.getBase().getHorizontalComponent(), rule.getBase().getVerticalComponent(),
                nc - oc, nr - or);

        if (multiplier < 1) {
            System.out.println("kerroin < 1");
            return false;
        }

        if (rule.getPossibleMultiplier() == '1' && multiplier > 1) {
            System.out.println("kerroin liian iso");
            return false;
        }

        if (!noObstaclesInTheWayOfMove(or, oc, rule.getBase().getHorizontalComponent(),
                rule.getBase().getVerticalComponent(), multiplier, color)) {
            System.out.println("löytyi este");
            return false;
        }

        if (!checkSpecialRulesAllowMove(or, oc, nr, nc, color, rule)) {
            System.out.println("eriokoissääntö hylkäsi");
            return false;
        }
        System.out.println("sääntö löytyi");
        return true;
    }

    private int attemptedMoveVectorMultiplier(int horizontalRuleVector, int verticalRuleVector, int horizontalMoveVector, int verticalMoveVector) {

        int division;

        if (horizontalRuleVector == 0) {
            if (verticalMoveVector % verticalRuleVector != 0 || horizontalMoveVector != 0) {
                System.out.println("vertikaali jako ei mene tasan");
                return 0;
            } else {
                System.out.println(verticalMoveVector);
                System.out.println(verticalRuleVector);
                return verticalMoveVector / verticalRuleVector;
            }
        }

        if (verticalRuleVector == 0) {
            if (horizontalMoveVector % horizontalRuleVector != 0 || verticalMoveVector != 0) {
                System.out.println("horizontaali jako ei mene tasan");
                return 0;
            } else {
                System.out.println("täs kustiin");
                return horizontalMoveVector / horizontalRuleVector;
            }
        }

        if (horizontalMoveVector % horizontalRuleVector != 0 || verticalMoveVector % verticalRuleVector != 0) {
            System.out.println("päin persittä");
            return 0;
        }
        division = horizontalMoveVector / horizontalRuleVector;
        if (division != verticalMoveVector / verticalRuleVector || division < 1) {
            System.out.println("viel huonommi");
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
        System.out.println(specialRules);
        return specialRuleChecker.check(origRow, origCol, nextRow,
                nextCol, color, specialRules, board);
    }
    
    private boolean doesNotCauseCheck(int origRow, int origCol, int nextRow,
            int nextCol, String color) {
        
        return true;
    }
}
