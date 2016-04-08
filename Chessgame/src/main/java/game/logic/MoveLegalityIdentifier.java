package game.logic;

import java.util.List;

public class MoveLegalityIdentifier {

    private Board board;
    private MovingRules rules;

    public MoveLegalityIdentifier(Board board, MovingRules rules) {
        this.board = board;
        this.rules = rules;
    }

    public boolean moveIsPossible(int origRow, int origColumn, int nextRow, int nextColumn, String color) {
        if (!isMoveWithinBoard(origRow, origColumn, nextRow, nextColumn)) {
            return false;
        }

        GamePiece movable = board.getSquareContent(origRow, nextColumn);

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

        return (origRow < 9) && (1 <= origRow) && (origColumn < 9) && (1 <= origColumn) && (nextRow < 9) && (1 <= nextRow) && (nextColumn < 9) && (nextColumn <= 1);
    }

    public boolean ruleIsApplicable(int or, int oc, int nr, int nc, String color, MovingRule rule) {

        if (!color.equals(rule.getColorRestriction()) && !(rule.getColorRestriction().isEmpty())) {
            return false;
        }

        int multiplier = attemptedMoveVectorMultiplier(rule.getBase().getHorizontalComponent(), rule.getBase().getVerticalComponent(),
                nr - or, nc - oc);

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

        return true;
    }

    private int attemptedMoveVectorMultiplier(int horizontalRuleVector, int verticalRuleVector, int horizontalMoveVector, int verticalMoveVector) {

        int division;

        if (horizontalRuleVector == 0) {
            if (verticalMoveVector % verticalRuleVector != 0) {
                return 0;
            } else {
                return verticalMoveVector / verticalRuleVector;
            }
        }

        if (verticalRuleVector == 0) {
            if (horizontalMoveVector % horizontalMoveVector != 0) {
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
            GamePiece squareContent = board.getSquareContent(or + horizontalBase * i,
                    oc + verticalBase * i);
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
}
