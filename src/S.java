import java.awt.*;

public class S extends Tetrimino{

    public S(PlayingField p){
        super(Color.GREEN, p);
        pieces.add(new GridElement(3,21, Color.GREEN, true));
        pieces.add(new GridElement(4,21, Color.GREEN, true));
        pieces.add(new GridElement(4,22, Color.GREEN, true));
        pieces.add(new GridElement(5,22, Color.GREEN, true));
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
