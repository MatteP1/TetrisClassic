import java.awt.Color;

/**
 * Represents an element in the playingFieldGrid
 */
public class GridElement {
    private Color color;
    private boolean occupied;

    private int y;
    private int x;


    public GridElement(int y, int x, Color c, boolean o){
        color = c;
        occupied = o;
        this.x = x;
        this.y = y;
    }

    public void repaint(Color c){
        color = c;
    }

    public Color getColor(){
        return color;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public int x(){
        return x;
    }
    public int y(){
        return y;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }


}
