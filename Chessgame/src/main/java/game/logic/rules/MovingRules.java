package game.logic.rules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovingRules {

    private final Map<String, List<MovingRule>> rules;

    public MovingRules() {
        this.rules = new HashMap<>();

        List<MovingRule> pawnRules = new ArrayList<>();
        this.rules.put("PAWN", pawnRules);

        createPawnRules("WHITE");
        createPawnRules("BLACK");
        createKnightRules();
        createBishopRules();
        createRookRules();
        createQueenRules();
        createKingRules();
    }

    public List<MovingRule> getRules(String pieceType) {
        return this.rules.get(pieceType);
    }

    private void createRookRules() {
        List<MovingRule> rookRules = new ArrayList<>();

        MovingRule up = new MovingRule(0, 1, 'x');
        MovingRule down = new MovingRule(0, -1, 'x');
        MovingRule left = new MovingRule(-1, 0, 'x');
        MovingRule right = new MovingRule(1, 0, 'x');

        rookRules.add(up);
        rookRules.add(down);
        rookRules.add(left);
        rookRules.add(right);

        this.rules.put("ROOK", rookRules);

    }

    private void createQueenRules() {
        List<MovingRule> queenRules = new ArrayList<>();

        MovingRule up = new MovingRule(0, 1, 'x');
        MovingRule down = new MovingRule(0, -1, 'x');
        MovingRule left = new MovingRule(-1, 0, 'x');
        MovingRule right = new MovingRule(1, 0, 'x');
        MovingRule upRight = new MovingRule(1, 1, 'x');
        MovingRule upLeft = new MovingRule(-1, 1, 'x');
        MovingRule downRight = new MovingRule(1, -1, 'x');
        MovingRule downLeft = new MovingRule(-1, -1, 'x');

        queenRules.add(up);
        queenRules.add(down);
        queenRules.add(left);
        queenRules.add(right);
        queenRules.add(upLeft);
        queenRules.add(upRight);
        queenRules.add(downLeft);
        queenRules.add(downRight);

        this.rules.put("QUEEN", queenRules);
    }

    private void createKingRules() {
        List<MovingRule> kingRules = new ArrayList<>();

        MovingRule up = new MovingRule(0, 1, '1');
        MovingRule down = new MovingRule(0, -1, '1');
        MovingRule left = new MovingRule(-1, 0, '1');
        MovingRule right = new MovingRule(1, 0, '1');
        MovingRule upRight = new MovingRule(1, 1, '1');
        MovingRule upLeft = new MovingRule(-1, 1, '1');
        MovingRule downRight = new MovingRule(1, -1, '1');
        MovingRule downLeft = new MovingRule(-1, -1, '1');

        kingRules.add(up);
        kingRules.add(down);
        kingRules.add(left);
        kingRules.add(right);
        kingRules.add(upLeft);
        kingRules.add(upRight);
        kingRules.add(downLeft);
        kingRules.add(downRight);

        this.rules.put("KING", kingRules);
    }

    private void createKnightRules() {
        List<MovingRule> knightRules = new ArrayList<>();

        MovingRule upRight = new MovingRule(1, 2, '1');
        MovingRule upLeft = new MovingRule(-1, 2, '1');
        MovingRule rightUp = new MovingRule(2, 1, '1');
        MovingRule rightDown = new MovingRule(2, -1, '1');
        MovingRule downRight = new MovingRule(1, -2, '1');
        MovingRule downLeft = new MovingRule(-1, -2, '1');
        MovingRule leftDown = new MovingRule(-2, -1, '1');
        MovingRule leftUp = new MovingRule(-2, 1, '1');

        knightRules.add(upRight);
        knightRules.add(upLeft);
        knightRules.add(rightUp);
        knightRules.add(rightDown);
        knightRules.add(downRight);
        knightRules.add(downLeft);
        knightRules.add(leftDown);
        knightRules.add(leftUp);

        this.rules.put("KNIGHT", knightRules);
    }

    private void createBishopRules() {

        List<MovingRule> bishopRules = new ArrayList<>();

        MovingRule upRight = new MovingRule(1, 1, 'x');
        MovingRule upLeft = new MovingRule(-1, 1, 'x');
        MovingRule downRight = new MovingRule(1, -1, 'x');
        MovingRule downLeft = new MovingRule(-1, -1, 'x');

        bishopRules.add(upRight);
        bishopRules.add(upLeft);
        bishopRules.add(downRight);
        bishopRules.add(downLeft);

        this.rules.put("BISHOP", bishopRules);
    }

    private void createPawnRules(String color) {
        int i;
        if (color.equals("WHITE")) {
            i = 1;
        } else {
            i = -1;
        }

        MovingRule moveNormal = new MovingRule(0, i, '1');
        moveNormal.setColorRestriction(color);
        moveNormal.setSpecialRules("pawnMoveForward");

        MovingRule captureLeft = new MovingRule(-1, i, '1');
        captureLeft.setColorRestriction(color);
        captureLeft.setSpecialRules("pawnCapture");

        MovingRule captureRight = new MovingRule(1, i, '1');
        captureRight.setColorRestriction(color);
        captureRight.setSpecialRules("pawnCapture");

        MovingRule moveDouble = new MovingRule(0, i, 'x');
        moveDouble.setColorRestriction(color);
        moveDouble.setSpecialRules("pawnDoubleForward");

        List<MovingRule> pawnRules = this.rules.get("PAWN");

        pawnRules.add(moveNormal);
        pawnRules.add(captureLeft);
        pawnRules.add(captureRight);
        pawnRules.add(moveDouble);
    }
}
