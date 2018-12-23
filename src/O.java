import java.awt.Color;

public class O extends  Tetrimino{

    public O(PlayingField p){
        super(Color.YELLOW, p);
        //CHANGE ALL Y-X IN ALL PIECES CLASSES
        pieces.add(new GridElement(20,4, Color.YELLOW, true));
        pieces.add(new GridElement(21,4, Color.YELLOW, true));
        pieces.add(new GridElement(20,5, Color.YELLOW, true));
        pieces.add(new GridElement(21,5, Color.YELLOW, true));
        zero = pieces.get(0);
        one = pieces.get(1);
        two = pieces.get(2);
        three = pieces.get(3);
    }

    @Override
    protected void rotateClockwiseCase0() {
        zeroy = zero.y();
        zerox = zero.x();

        oney = one.y();
        onex = one.x();

        twoy = two.y();
        twox = two.x();

        threey = three.y();
        threex = three.x();
    }

    @Override
    protected void rotateClockwiseCase1() {
        zeroy = zero.y();
        zerox = zero.x();

        oney = one.y();
        onex = one.x();

        twoy = two.y();
        twox = two.x();

        threey = three.y();
        threex = three.x();
    }

    @Override
    protected void rotateClockwiseCase2() {
        zeroy = zero.y();
        zerox = zero.x();

        oney = one.y();
        onex = one.x();

        twoy = two.y();
        twox = two.x();

        threey = three.y();
        threex = three.x();
    }

    @Override
    protected void rotateClockwiseCase3() {
        zeroy = zero.y();
        zerox = zero.x();

        oney = one.y();
        onex = one.x();

        twoy = two.y();
        twox = two.x();

        threey = three.y();
        threex = three.x();
    }

    public String toString(){
        return "O-piece";
    }
}
