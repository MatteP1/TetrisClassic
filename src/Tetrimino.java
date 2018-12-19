import java.awt.Color;
public abstract class Tetrimino {
    private int orientation;
    private Color color;

    public Tetrimino(Color c){
        orientation = 0;
        color = c;

    }
    public abstract void rotateClockwise();

    public abstract void rotateCounterClockwise();


}