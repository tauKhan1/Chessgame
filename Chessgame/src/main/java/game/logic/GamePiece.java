package game.logic;

public class GamePiece {
    
    private String type;
    private String color;
    private Square location;
    private String status;

    public GamePiece(String type, String color, String status) {
        this.type = type;
        this.color = color;
        this.status = status;
    }
    
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Square getLocation() {
        return location;
    }

    public void setLocation(Square location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
