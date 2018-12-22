import java.awt.Color;

public class O extends  Tetrimino{

    public O(PlayingField p){
        super(Color.YELLOW, p);
        //CHANGE ALL Y-X IN ALL PIECES CLASSES
        pieces.add(new GridElement(21,4, Color.YELLOW, true));
        pieces.add(new GridElement(22,4, Color.YELLOW, true));
        pieces.add(new GridElement(21,5, Color.YELLOW, true));
        pieces.add(new GridElement(22,5, Color.YELLOW, true));
        zero = pieces.get(0);
        one = pieces.get(1);
        two = pieces.get(2);
        three = pieces.get(3);
    }

    @Override
    protected void rotateClockwiseCase0() {
        //Not gonna implement these
    }

    @Override
    protected void rotateClockwiseCase1() {
        //Nothing happens anyways
    }

    @Override
    protected void rotateClockwiseCase2() {
        //Lol
    }

    @Override
    protected void rotateClockwiseCase3() {
        // The game is actually faster when i don't implement them
        // so i think it is justified.
    }

    public String toString(){
        return "O-piece";
    }
}
