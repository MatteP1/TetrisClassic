import java.awt.Color;
import java.util.ArrayList;

public class J extends Tetrimino{

    public J(){
        super(Color.BLUE);
        pieces.add(new IntPair(3,22));
        pieces.add(new IntPair(3,21));
        pieces.add(new IntPair(4,21));
        pieces.add(new IntPair(5,21));
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
