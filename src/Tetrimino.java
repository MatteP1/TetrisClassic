import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public abstract class Tetrimino {
    protected int orientation;
    private Color color;
    protected ArrayList<IntPair> pieces; //position of the 4 pieces
    protected ArrayList<IntPair> bottomPieces;


    public Tetrimino(Color c){
        orientation = 0;
        color = c;
        pieces = new ArrayList<>();
    }

    public abstract void rotateClockwise();

    public abstract void rotateCounterClockwise();

    public abstract void calculateBottomPieces();

    public void moveDown(){
        for(IntPair i : pieces){
            i.setY(i.y()-1);
        }
    }

    public ArrayList<IntPair> getBottomPieces(){
        return bottomPieces;
    }


}