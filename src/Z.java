import java.awt.*;

public class Z extends Tetrimino{

    public Z(PlayingField p){
        super(Color.RED, p);
        pieces.add(new GridElement(3,22, Color.RED, true));
        pieces.add(new GridElement(4,22, Color.RED, true));
        pieces.add(new GridElement(4,21, Color.RED, true));
        pieces.add(new GridElement(5,21, Color.RED, true));
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
