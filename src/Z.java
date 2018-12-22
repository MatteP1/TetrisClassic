import java.awt.Color;

public class Z extends Tetrimino{

    public Z(PlayingField p){
        super(Color.RED, p);
        pieces.add(new GridElement(22,3, Color.RED, true));
        pieces.add(new GridElement(22,4, Color.RED, true));
        pieces.add(new GridElement(21,4, Color.RED, true));
        pieces.add(new GridElement(21,5, Color.RED, true));
        zero = pieces.get(0);
        one = pieces.get(1);
        two = pieces.get(2);
        three = pieces.get(3);
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
