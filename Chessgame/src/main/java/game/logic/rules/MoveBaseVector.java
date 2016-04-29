package game.logic.rules;

/**
 * Luokka kuvaa säännön mahdollistamaa liikkumissuuntaa nappulalle.
 */
public class MoveBaseVector {

    private int verticalComponent;
    private int horizontalComponent;
    
    /**
     * Luo liikkumisyksikön annettujen komponenttien mukaisesti.
     * 
     * @param horizontalComponent   Sivuttaiskomponentti
     * 
     * @param verticalComponent     Pystykomponentti
     */
    public MoveBaseVector(int horizontalComponent, int verticalComponent) {
        this.verticalComponent = verticalComponent;
        this.horizontalComponent = horizontalComponent;
    }

    public int getVerticalComponent() {
        return verticalComponent;
    }

    public void setVerticalComponent(int verticalComponent) {
        this.verticalComponent = verticalComponent;
    }

    public int getHorizontalComponent() {
        return horizontalComponent;
    }

    public void setHorizontalComponent(int horizontalComponent) {
        this.horizontalComponent = horizontalComponent;
    }

}
