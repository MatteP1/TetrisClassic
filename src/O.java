import java.awt.*;

public class O extends  Tetrimino{

    public O(){
        super(Color.YELLOW);
        pieces.add(new IntPair(4,21));
        pieces.add(new IntPair(4,22));
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
