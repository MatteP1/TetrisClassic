import java.awt.*;

public class L extends Tetrimino{

    public L(){
        super(Color.ORANGE);
        pieces.add(new IntPair(3,21));
        pieces.add(new IntPair(4,21));
        pieces.add(new IntPair(5,21));
        pieces.add(new IntPair(5,22));
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
