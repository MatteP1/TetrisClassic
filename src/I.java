import java.awt.Color;

/**
 * Class representing the I-piece
 * @author MatRusTy
 */
public class I extends Tetrimino{

    public I(PlayingField p){
        super(Color.CYAN, p);
        pieces.add(new GridElement(20,3, Color.CYAN, false));
        pieces.add(new GridElement(20,4, Color.CYAN, false));
        pieces.add(new GridElement(20,5, Color.CYAN, false));
        pieces.add(new GridElement(20,6, Color.CYAN, false));
        zero = pieces.get(0);
        one = pieces.get(1);
        two = pieces.get(2);
        three = pieces.get(3);
    }

    @Override
    protected void rotateClockwiseCase0(){
        zeroy = zero.y()+1;
        zerox = zero.x()+2;

        oney = one.y();
        onex = one.x()+1;

        twoy = two.y()-1;
        twox = two.x();

        threey = three.y()-2;
        threex = three.x()-1;
    }

    @Override
    protected void rotateClockwiseCase1(){
        zeroy = zero.y()-2;
        zerox = zero.x()+1;

        oney = one.y()-1;
        onex = one.x();

        twoy = two.y();
        twox = two.x()-1;

        threey = three.y()+1;
        threex = three.x()-2;
    }

    @Override
    protected void rotateClockwiseCase2(){
        zeroy = zero.y()-1;
        zerox = zero.x()-2;

        oney = one.y();
        onex = one.x()-1;

        twoy = two.y()+1;
        twox = two.x();

        threey = three.y()+2;
        threex = three.x()+1;
    }

    @Override
    protected void rotateClockwiseCase3(){
        zeroy = zero.y()+2;
        zerox = zero.x()-1;

        oney = one.y()+1;
        onex = one.x();

        twoy = two.y();
        twox = two.x()+1;

        threey = three.y()-1;
        threex = three.x()+2;
    }

    public String toString(){
        return "I-piece";
    }
}
