import java.awt.Color;
import javax.swing.*;

/**
 * Represents an element in the playingFieldGrid
 * @author MatRusTy
 */
public class GridElement extends JPanel{

    // --------------------- FIELD VARIABLES ---------------------
    private boolean occupied;
    private int y;
    private int x;

    /**
     * Creates a new GridElement with the specified parameters.
     * @param y The y coordinate of the gridElement.
     * @param x The x coordinate of the gridElement.
     * @param color The color of the gridElement.
     * @param o Is the gridElement occupied (can a tetrimino be in this spot).
     */
    public GridElement(int y, int x, Color color, boolean o){
        occupied = o;
        this.x = x;
        this.y = y;
        setBackground(color);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    // --------------------- GETTERS AND SETTERS ---------------------
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
    public void makeOccupied(){
        occupied = true;
    }
    public void setOccupied(boolean b){
        occupied =b;
    }
}
