package game.logic.rules;

/**
 * Luokka kuvaa yhtä mahdollista nappulatyyppiin liittyvää liikkumissääntöä.
 */
public class MovingRule {

    private MoveBaseVector base;
    private char possibleMultiplier;
    private String colorRestriction;
    private String specialRules;

    /**
     * Luo liikkumissäännön.
     *
     * @param vert Pystykomponentti
     *
     * @param hori Vaakakomponentti
     *
     * @param possibleMultiplier Kerroin, joka kuvaa voiko liikkumisvektorin
     * mukaiseen suuntaan liikkua useampia askelia
     */
    public MovingRule(int hori, int vert, char possibleMultiplier) {
        this.base = new MoveBaseVector(hori, vert);
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
