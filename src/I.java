
import java.awt.Color;
import java.util.ArrayList;

public class I extends Tetrimino{
    public I(PlayingField p){
        super(Color.CYAN, p);
        pieces.add(new GridElement(21,3, Color.CYAN, false));
        pieces.add(new GridElement(21,4, Color.CYAN, false));
        pieces.add(new GridElement(21,5, Color.CYAN, false));
        pieces.add(new GridElement(21,6, Color.CYAN, false));
        zero = pieces.get(0);
        one = pieces.get(1);
        two = pieces.get(2);
        three = pieces.get(3);
    }

    protected void rotateClockwiseCase0(){
        zeroy = zero.y()+1;
        zerox = zero.x()+2;

        oney = one.y();
        onex = one.x()+1;

        twoy = two.y()-1;
        twox = two.x();

        threey = three.y()-2;
        threex = three.x()-1;
    }

    protected void rotateClockwiseCase1(){
        zeroy = zero.y()-2;
        zerox = zero.x()+1;

        oney = one.y()-1;
        onex = one.x();

        twoy = two.y();
        twox = two.x()-1;

        threey = three.y()+1;
        threex = three.x()-2;
    }

    protected void rotateClockwiseCase2(){
        zeroy = zero.y()-1;
        zerox = zero.x()-2;

        oney = one.y();
        onex = one.x()-1;

        twoy = two.y()+1;
        twox = two.x();

        threey = three.y()+2;
        threex = three.x()+1;
    }

    protected void rotateClockwiseCase3(){
        zeroy = zero.y()+2;
        zerox = zero.x()-1;

        oney = one.y()+1;
        onex = one.x();

        twoy = two.y();
        twox = two.x()+1;

        threey = three.y()-1;
        threex = three.x()+2;
    }






//    @Override
//    public void rotateClockwise() {
//        switch (orientation) {
//            case 0 : {
//                zeroy = zero.y()+1;
//                zerox = zero.x()+2;
//
//                oney = one.y();
//                onex = one.x()+1;
//
//                twoy = two.y()-1;
//                twox = two.x();
//
//                threey = three.y()-2;
//                threex = three.x()-1;
//                break;
//            }
//            case 1 : {
//                zeroy = zero.y()-2;
//                zerox = zero.x()+1;
//
//                oney = one.y()-1;
//                onex = one.x();
//
//                twoy = two.y();
//                twox = two.x()-1;
//
//                threey = three.y()+1;
//                threex = three.x()-2;
//                break;
//            }
//            case 2 : {
//                zeroy = zero.y()-1;
//                zerox = zero.x()-2;
//
//                oney = one.y();
//                onex = one.x()-1;
//
//                twoy = two.y()+1;
//                twox = two.x();
//
//                threey = three.y()+2;
//                threex = three.x()+1;
//                break;
//            }
//            case 3 : {
//                zeroy = zero.y()+2;
//                zerox = zero.x()-1;
//
//                oney = one.y()+1;
//                onex = one.x();
//
//                twoy = two.y();
//                twox = two.x()+1;
//
//                threey = three.y()-1;
//                threex = three.x()+2;
//                break;
//            }
//        }
//        if(applyNewCoords()){
//            if(orientation == 3){
//                orientation = 0;
//            } else {
//                orientation++;
//            }
//        }
//        for(GridElement g : pieces){
//            System.out.print("(" + g.y() +", "+  g.x() + ") ");
//        }
//        System.out.println();
//        System.out.println("now in orientation: " + orientation);
//        System.out.println();
//    }



//    @Override
//    public void rotateCounterClockwise() {
//        switch (orientation) {
//            case 0 : {
//                zeroy = zero.y()-2;
//                zerox = zero.x()+1;
//
//                oney = one.y()-1;
//                onex = one.x();
//
//                twoy = two.y();
//                twox = two.x()-1;
//
//                threey = three.y()+1;
//                threex = three.x()-2;
//                break;
//            }
//            case 1 : {
//                zeroy = zero.y()-1;
//                zerox = zero.x()-2;
//
//                oney = one.y();
//                onex = one.x()-1;
//
//                twoy = two.y()+1;
//                twox = two.x();
//
//                threey = three.y()+2;
//                threex = three.x()+1;
//                break;
//            }
//            case 2 : {
//                zeroy = zero.y()+2;
//                zerox = zero.x()-1;
//
//                oney = one.y()+1;
//                onex = one.x();
//
//                twoy = two.y();
//                twox = two.x()+1;
//
//                threey = three.y()-1;
//                threex = three.x()+2;
//                break;
//            }
//            case 3 : {
//                zeroy = zero.y()+1;
//                zerox = zero.x()+2;
//
//                oney = one.y();
//                onex = one.x()+1;
//
//                twoy = two.y()-1;
//                twox = two.x();
//
//                threey = three.y()-2;
//                threex = three.x()-1;
//                break;
//            }
//        }
//        if(applyNewCoords()){
//            if(orientation == 0){
//                orientation = 3;
//            } else {
//                orientation--;
//            }
//        }
//
//        for(GridElement g : pieces){
//            System.out.print("(" + g.y() +", "+  g.x() + ") ");
//        }
//        System.out.println();
//        System.out.println("now in orientation: " + orientation);
//        System.out.println();
//    }

    public String toString(){
        return "I-piece";
    }
}
