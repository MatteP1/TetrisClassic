import java.awt.*;

public class T extends Tetrimino{

    public T(PlayingField p){
        super(Color.MAGENTA, p);
        pieces.add(new GridElement(3,21, Color.MAGENTA, true));
        pieces.add(new GridElement(4,21, Color.MAGENTA, true));
        pieces.add(new GridElement(4,22, Color.MAGENTA, true));
        pieces.add(new GridElement(5,21, Color.MAGENTA, true));
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
