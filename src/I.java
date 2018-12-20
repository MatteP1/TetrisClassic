import java.awt.Color;
import java.util.ArrayList;

public class I extends Tetrimino{
    public I(){
        super(Color.CYAN);
        pieces.add(new IntPair(3,21));
        pieces.add(new IntPair(4,21));
        pieces.add(new IntPair(5,21));
        pieces.add(new IntPair(6,21));


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
        ArrayList<IntPair> newBottom = new ArrayList<>();
        switch (orientation){
            case 0 : case 2 : {
                newBottom = pieces;
                break;

            }
            case 1 : case 3 : {
                int yValue = Math.min(Math.min(pieces.get(0).y(),pieces.get(1).y()), Math.min(pieces.get(2).y(),pieces.get(3).y()));
                newBottom.add(new IntPair(pieces.get(0).x(),yValue));
                break;
            }

        }
        bottomPieces = newBottom;
    }
}
