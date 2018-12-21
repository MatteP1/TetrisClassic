import java.awt.*;

public class Z extends Tetrimino{

    public Z(PlayingField p){
        super(Color.RED, p);
        pieces.add(new GridElement(3,22, Color.RED, true));
        pieces.add(new GridElement(4,22, Color.RED, true));
        pieces.add(new GridElement(4,21, Color.RED, true));
        pieces.add(new GridElement(5,21, Color.RED, true));
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
