package game.logic.rules;

public class MoveBaseVector {

    private int verticalComponent;
    private int horizontalComponent;

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
