import java.awt.Color;

public class T extends Tetrimino{

    public T(PlayingField p){
        super(Color.MAGENTA, p);
        pieces.add(new GridElement(3,21, Color.MAGENTA, true));
        pieces.add(new GridElement(4,21, Color.MAGENTA, true));
        pieces.add(new GridElement(4,22, Color.MAGENTA, true));
        pieces.add(new GridElement(5,21, Color.MAGENTA, true));
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
