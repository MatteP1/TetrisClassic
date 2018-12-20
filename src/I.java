import java.awt.Color;
import java.util.ArrayList;

public class I extends Tetrimino{
    public I(){
        super(Color.CYAN);
        pieces.add(new GridElement(21,3, Color.CYAN, true));
        pieces.add(new GridElement(21,4, Color.CYAN, true));
        pieces.add(new GridElement(21,5, Color.CYAN, true));
        pieces.add(new GridElement(21,6, Color.CYAN, true));


    }
    @Override
    public void rotateClockwise() {
        switch (orientation) {
            case 0 : {

            }
            case 1 : {

            }
            case 2 : {

            }
            case 3 : {

            }
        }
    }

    @Override
    public void rotateCounterClockwise() {

    }

    @Override
    public void calculateBottomPieces() {
        ArrayList<GridElement> newBottom = new ArrayList<>();
        switch (orientation){
            case 0 : case 2 : {
                newBottom = pieces;
                break;

            }
            case 1 : case 3 : {
                int yValue = Math.min(Math.min(pieces.get(0).y(),pieces.get(1).y()), Math.min(pieces.get(2).y(),pieces.get(3).y()));
                newBottom.add(new GridElement(pieces.get(0).x(),yValue, Color.CYAN, true));
                break;
            }

        }
        bottomPieces = newBottom;
    }

    public String toString(){
        return "I-piece";
    }
}
