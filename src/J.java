import java.awt.Color;
import java.util.ArrayList;

public class J extends Tetrimino{

    public J(PlayingField p){
        super(Color.BLUE, p);
        pieces.add(new GridElement(22,3, Color.BLUE, true));
        pieces.add(new GridElement(21,3, Color.BLUE, true));
        pieces.add(new GridElement(21,4, Color.BLUE, true));
        pieces.add(new GridElement(21,5, Color.BLUE, true));
        zero = pieces.get(0);
        one = pieces.get(1);
        two = pieces.get(2);
        three = pieces.get(3);
    }

    @Override
    public void rotateClockwise() {

    }

    @Override
    public void rotateCounterClockwise() {

    }

    @Override
    protected void rotateClockwiseCase0() {

    }

    @Override
    protected void rotateClockwiseCase1() {

    }

    @Override
    protected void rotateClockwiseCase2() {

    }

    @Override
    protected void rotateClockwiseCase3() {

    }
}
