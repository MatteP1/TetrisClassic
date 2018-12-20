import java.awt.*;

public class L extends Tetrimino{

    public L(){
        super(Color.ORANGE);
        pieces.add(new GridElement(3,21, Color.ORANGE, true));
        pieces.add(new GridElement(4,21, Color.ORANGE, true));
        pieces.add(new GridElement(5,21, Color.ORANGE, true));
        pieces.add(new GridElement(5,22, Color.ORANGE, true));
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
