import java.awt.*;

public class Z extends Tetrimino{

    public Z(){
        super(Color.RED);
        pieces.add(new IntPair(3,22));
        pieces.add(new IntPair(4,22));
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
