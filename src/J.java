import java.awt.Color;
import java.util.ArrayList;

public class J extends Tetrimino{

    public J(){
        super(Color.BLUE);
        pieces.add(new GridElement(22,3, Color.BLUE, true));
        pieces.add(new GridElement(21,3, Color.BLUE, true));
        pieces.add(new GridElement(21,4, Color.BLUE, true));
        pieces.add(new GridElement(21,5, Color.BLUE, true));
    }

    @Override
    public void rotateClockwise() {

    }

    @Override
    public void rotateCounterClockwise() {

    }

    @Override
    public void calculateBottomPieces() {

    }
}
