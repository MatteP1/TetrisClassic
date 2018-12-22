import java.awt.Color;

public class L extends Tetrimino{

    public L(PlayingField p){
        super(Color.ORANGE, p);
        pieces.add(new GridElement(21,3, Color.ORANGE, true));
        pieces.add(new GridElement(21,4, Color.ORANGE, true));
        pieces.add(new GridElement(21,5, Color.ORANGE, true));
        pieces.add(new GridElement(22,5, Color.ORANGE, true));
        zero = pieces.get(0);
        one = pieces.get(1);
        two = pieces.get(2);
        three = pieces.get(3);
    }

    @Override
    protected void rotateClockwiseCase0() {
        zeroy = zero.y()+1;
        zerox = zero.x()+1;

        oney = one.y();
        onex = one.x();

        twoy = two.y()-1;
        twox = two.x()-1;

        threey = three.y()-2;
        threex = three.x();
    }

    @Override
    protected void rotateClockwiseCase1() {
        zeroy = zero.y()-1;
        zerox = zero.x()+1;

        oney = one.y();
        onex = one.x();

        twoy = two.y()+1;
        twox = two.x()-1;

        threey = three.y();
        threex = three.x()-2;
    }

    @Override
    protected void rotateClockwiseCase2() {
        zeroy = zero.y()-1;
        zerox = zero.x()-1;

        oney = one.y();
        onex = one.x();

        twoy = two.y()+1;
        twox = two.x()+1;

        threey = three.y()+2;
        threex = three.x();
    }

    @Override
    protected void rotateClockwiseCase3() {
        zeroy = zero.y()+1;
        zerox = zero.x()-1;

        oney = one.y();
        onex = one.x();

        twoy = two.y()-1;
        twox = two.x()+1;

        threey = three.y();
        threex = three.x()+2;
    }
}
