import java.awt.*;

public class S extends Tetrimino{

    public S(){
        super(Color.GREEN);
        pieces.add(new IntPair(3,21));
        pieces.add(new IntPair(4,21));
        pieces.add(new IntPair(4,22));
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
