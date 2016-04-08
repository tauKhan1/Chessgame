
package game.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MovingRules {
    
    private final Map<String, List<MovingRule>> rules;
    
    public MovingRules() {
        this.rules = new HashMap<>();
        
        createBishopRules();
    }
    
    public List<MovingRule> getRules(String pieceType) {
        return this.rules.get(pieceType);
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
}
