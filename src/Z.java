import java.awt.Color;

public class Z extends Tetrimino{

    public Z(PlayingField p){
        super(Color.RED, p);
        pieces.add(new GridElement(21,3, Color.RED, true));
        pieces.add(new GridElement(21,4, Color.RED, true));
        pieces.add(new GridElement(20,4, Color.RED, true));
        pieces.add(new GridElement(20,5, Color.RED, true));
        zero = pieces.get(0);
        one = pieces.get(1);
        two = pieces.get(2);
        three = pieces.get(3);
    }

    @Override
    protected void rotateClockwiseCase0() {
        zeroy = zero.y();
        zerox = zero.x()+2;

        oney = one.y()-1;
        onex = one.x()+1;

        twoy = two.y();
        twox = two.x();

        threey = three.y()-1;
        threex = three.x()-1;
    }

    @Override
    protected void rotateClockwiseCase1() {
        zeroy = zero.y()-2;
        zerox = zero.x();

        oney = one.y()-1;
        onex = one.x()-1;

        twoy = two.y();
        twox = two.x();

        threey = three.y()+1;
        threex = three.x()-1;
    }

    @Override
    protected void rotateClockwiseCase2() {
        zeroy = zero.y();
        zerox = zero.x()-2;

        oney = one.y()+1;
        onex = one.x()-1;

        twoy = two.y();
        twox = two.x();

        threey = three.y()+1;
        threex = three.x()+1;
    }

    @Override
    protected void rotateClockwiseCase3() {
        zeroy = zero.y()+2;
        zerox = zero.x();

        oney = one.y()+1;
        onex = one.x()+1;

        twoy = two.y();
        twox = two.x();

        threey = three.y()-1;
        threex = three.x()+1;
    }

    public String toString(){
        return "Z-piece";
    }
}
