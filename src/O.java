import java.awt.*;

public class O extends  Tetrimino{

    public O(){
        super(Color.YELLOW);
        //CHANGE ALL Y-X IN ALL PIECES CLASSES
        pieces.add(new GridElement(4,21, Color.YELLOW, true));
        pieces.add(new GridElement(4,22, Color.YELLOW, true));
        pieces.add(new GridElement(5,21, Color.YELLOW, true));
        pieces.add(new GridElement(5,22, Color.YELLOW, true));
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
