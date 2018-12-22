import java.awt.Color;

public class S extends Tetrimino{

    public S(PlayingField p){
        super(Color.GREEN, p);
        pieces.add(new GridElement(21,3, Color.GREEN, true));
        pieces.add(new GridElement(21,4, Color.GREEN, true));
        pieces.add(new GridElement(22,4, Color.GREEN, true));
        pieces.add(new GridElement(22,5, Color.GREEN, true));
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
        twox = two.x()+1;

        threey = three.y()-2;
        threex = three.x();
    }

    @Override
    protected void rotateClockwiseCase1() {
        zeroy = zero.y()-1;
        zerox = zero.x()+1;

        oney = one.y();
        onex = one.x();

        twoy = two.y()-1;
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
        twox = two.x()-1;

        threey = three.y()+2;
        threex = three.x();
    }

    @Override
    protected void rotateClockwiseCase3() {
        zeroy = zero.y()+1;
        zerox = zero.x()-1;

        oney = one.y();
        onex = one.x();

        twoy = two.y()+1;
        twox = two.x()+1;

        threey = three.y();
        threex = three.x()+2;
    }
}
