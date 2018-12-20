import java.awt.Color;
import java.util.ArrayList;

public class J extends Tetrimino{

    public J(){
        super(Color.BLUE);
        pieces.add(new GridElement(3,22, Color.BLUE, true));
        pieces.add(new GridElement(3,21, Color.BLUE, true));
        pieces.add(new GridElement(4,21, Color.BLUE, true));
        pieces.add(new GridElement(5,21, Color.BLUE, true));
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
