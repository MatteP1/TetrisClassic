import java.awt.Color;
import java.util.ArrayList;

public class I extends Tetrimino{
    public I(PlayingField p){
        super(Color.CYAN, p);
        pieces.add(new GridElement(21,3, Color.CYAN, true));
        pieces.add(new GridElement(21,4, Color.CYAN, true));
        pieces.add(new GridElement(21,5, Color.CYAN, true));
        pieces.add(new GridElement(21,6, Color.CYAN, true));


    }
    @Override
    public void rotateClockwise() {
        GridElement zero,one,two,three;
        zero = pieces.get(0);
        one = pieces.get(1);
        two = pieces.get(2);
        three = pieces.get(3);

        int zeroy=0;
        int zerox=0;

        int oney=0;
        int onex=0;

        int twoy=0;
        int twox=0;

        int threey=0;
        int threex=0;


        switch (orientation) {
            case 0 : {

                zeroy = zero.y()+1;
                zerox = zero.x()+2;

                oney = one.y();
                onex = one.x()+1;

                twoy = two.y()-1;
                twox = two.x();

                threey = three.y()-2;
                threex = three.x()-1;
                break;
            }
            case 1 : {
                zeroy = zero.y()-2;
                zerox = zero.x()+1;

                oney = one.y()-1;
                onex = one.x();

                twoy = two.y();
                twox = two.x()-1;

                threey = three.y()+1;
                threex = three.x()-2;
                break;
            }
            case 2 : {
                zeroy = zero.y()-1;
                zerox = zero.x()-2;

                oney = one.y();
                onex = one.x()-1;

                twoy = two.y()+1;
                twox = two.x();

                threey = three.y()+2;
                threex = three.x()+1;
                break;
            }
            case 3 : {
                zeroy = zero.y()-2;
                zerox = zero.x()+1;

                oney = one.y()-1;
                onex = one.x();

                twoy = two.y();
                twox = two.x()-1;

                threey = three.y()+1;
                threex = three.x()-2;
                break;
            }

        }
        if(isLegal(zeroy, zerox, oney, onex,twoy, twox, threey, threex)) {
            zero.setY(zeroy);
            zero.setX(zerox);

            one.setY(oney);
            one.setX(onex);

            two.setY(twoy);
            two.setX(twox);

            three.setY(threey);
            three.setX(threex);
            orientation = (orientation+1 == 4) ? 0 : orientation++;
        }

    }



    @Override
    public void rotateCounterClockwise() {

    }

    @Override
    public void calculateBottomPieces() {
        ArrayList<GridElement> newBottom = new ArrayList<>();
        switch (orientation){
            case 0 : case 2 : {
                newBottom = pieces;
                break;

            }
            case 1 : case 3 : {
                int yValue = Math.min(Math.min(pieces.get(0).y(),pieces.get(1).y()), Math.min(pieces.get(2).y(),pieces.get(3).y()));
                newBottom.add(new GridElement(pieces.get(0).x(),yValue, Color.CYAN, true));
                break;
            }

        }
        bottomPieces = newBottom;
    }

    public String toString(){
        return "I-piece";
    }
}
