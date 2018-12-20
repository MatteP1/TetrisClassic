import java.awt.*;

public class T extends Tetrimino{

    public T(){
        super(Color.MAGENTA);
        pieces.add(new IntPair(3,21));
        pieces.add(new IntPair(4,21));
        pieces.add(new IntPair(4,22));
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
