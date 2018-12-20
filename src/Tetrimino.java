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

    /**
     * Moves the tetrimino one unit to the left
     */
    public void moveLeft(){
        int indexOfRightermostBlock = Math.min(Math.min(pieces.get(0).x(),pieces.get(1).x()),Math.min(pieces.get(2).x(),pieces.get(3).x()));
        if(indexOfRightermostBlock != 0){
            for(IntPair i : pieces){
                i.setX(i.x()-1);
            }
        }
    }

    /**
     * Moves the tetrimino one unit to the right
     */
    public void moveRight(){
        int indexOfRightermostBlock = Math.max(Math.max(pieces.get(0).x(),pieces.get(1).x()),Math.max(pieces.get(2).x(),pieces.get(3).x()));
        if(indexOfRightermostBlock != 9){
            for(IntPair i : pieces){
                i.setX(i.x()+1);
            }
        }
    }


    public void moveDown(){
        for(IntPair i : pieces){
            i.setY(i.y()+1);
        }
    }

    public ArrayList<IntPair> getBottomPieces(){
        return bottomPieces;
    }


}