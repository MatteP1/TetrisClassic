import java.awt.Color;
import javax.swing.*;

/**
 * Represents an element in the playingFieldGrid
 */
public class GridElement extends JPanel{
    private Color color;
    private boolean occupied;

    private int y;
    private int x;


    public GridElement(int y, int x, Color c, boolean o){
        color = c;
        occupied = o;
        this.x = x;
        this.y = y;
        setBackground(color);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public void repaint(Color c){
        color = c;
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

    public void makeOccupied(){
        occupied = true;
    }

    public void setOccupied(boolean b){
        occupied =b;
    }

}
