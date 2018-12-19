import java.awt.Color;

/**
 * Represents an element in the playingFieldGrid
 */
public class GridElement {
    private Color color;
    private boolean occupied;

    public GridElement(){
        color = Color.BLACK;
        occupied = false;
    }

    public void repaint(Color c){
        color = c;
    }

    public boolean isOccupied() {
        return occupied;
    }


}
