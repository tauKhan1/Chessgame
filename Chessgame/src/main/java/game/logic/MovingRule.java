package game.logic;


public class MovingRule {
    
    private MoveBaseVector base;
    private char possibleMultiplier;
    private String colorRestriction;
    private String specialRules;

    public MovingRule(int vert, int hori, char possibleMultiplier) {
        this.base = new MoveBaseVector(vert, hori);
        this.possibleMultiplier = possibleMultiplier;
        this.colorRestriction = "";
        this.specialRules = "";        
    }

    public MoveBaseVector getBase() {
        return base;
    }

    public void setBase(MoveBaseVector base) {
        this.base = base;
    }

    public char getPossibleMultiplier() {
        return possibleMultiplier;
    }

    public void setPossibleMultiplier(char possibleMultiplier) {
        this.possibleMultiplier = possibleMultiplier;
    }

    public String getColorRestriction() {
        return colorRestriction;
    }

    public void setColorRestriction(String colorRestriction) {
        this.colorRestriction = colorRestriction;
    }

    public String getSpecialRules() {
        return specialRules;
    }

    public void setSpecialRules(String specialRules) {
        this.specialRules = specialRules;
    }
    

    
}
