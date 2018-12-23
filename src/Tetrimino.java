import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public abstract class Tetrimino {
    protected int orientation;
    private Color color;
    protected ArrayList<GridElement> pieces; //position of the 4 pieces
    protected PlayingField playfield;
    protected GridElement[][] grid;

    //----- BLOCK NUMBERS -----
    GridElement zero;
    GridElement one;
    GridElement two;
    GridElement three;
    //----- BLOCK NUMBERS -----

    //----- NEW COORDS -----
    protected int zeroy=0;
    protected int zerox=0;

    protected int oney=0;
    protected int onex=0;

    protected int twoy=0;
    protected int twox=0;

    protected int threey=0;
    protected int threex=0;
    //----- NEW COORDS -----


    public Tetrimino(Color c, PlayingField p){
        orientation = 0;
        color = c;
        pieces = new ArrayList<>();
        playfield = p;
        grid = p.getGrid();
    }

    public void rotateClockwise(){
        switch (orientation){
            case 0 : rotateClockwiseCase0(); break;
            case 1 : rotateClockwiseCase1(); break;
            case 2 : rotateClockwiseCase2(); break;
            case 3 : rotateClockwiseCase3(); break;
        }
        if(applyNewCoords()){
            if(orientation == 3){
                orientation = 0;
            } else {
                orientation++;
            }
        }

        for(GridElement g : pieces){
            System.out.print("(" + g.y() +", "+  g.x() + ") ");
        }
        System.out.println();
        System.out.println("now in orientation: " + orientation);
        System.out.println();

    }

    /**
     * This method uses the fact that there is a connection between the rotations and orientations of the cw and ccw rotations
     * Rotation Pairs:
     * cw	ccw
     * 0	3
     * 1	0
     * 2	1
     * 3	2
     * Those rotation methods do, computationally, the same, therefore ccw just calls the cw rotations
     * with those specific orientations / cases.
     */
    public void rotateCounterClockwise(){
        switch (orientation){
            case 3 : rotateClockwiseCase0(); break;
            case 0 : rotateClockwiseCase1(); break;
            case 1 : rotateClockwiseCase2(); break;
            case 2 : rotateClockwiseCase3(); break;
        }
        if(applyNewCoords()){
            if(orientation == 0){
                orientation = 3;
            } else {
                orientation--;
            }
        }

        for(GridElement g : pieces){
            System.out.print("(" + g.y() +", "+  g.x() + ") ");
        }
        System.out.println();
        System.out.println("now in orientation: " + orientation);
        System.out.println();
    }

    protected abstract void rotateClockwiseCase0();
    protected abstract void rotateClockwiseCase1();
    protected abstract void rotateClockwiseCase2();
    protected abstract void rotateClockwiseCase3();


    protected boolean applyNewCoords(){
        if(isLegal(zeroy, zerox, oney, onex,twoy, twox, threey, threex)) {
            zero.setY(zeroy);
            zero.setX(zerox);

            one.setY(oney);
            one.setX(onex);

            two.setY(twoy);
            two.setX(twox);

            three.setY(threey);
            three.setX(threex);
            return true;
        }
        return false;
    }

    protected boolean isLegal(int zeroy, int zerox,int oney, int onex,int twoy, int twox,int threey, int threex){
        if(zerox < 0 || zerox > 9 || onex < 0 || onex > 9 || twox < 0 || twox > 9 || threex < 0 || threex > 9){
            return false;
        } else if(zeroy < 0 || oney < 0 || twoy < 0 || threey < 0) {
            return false;
        } else if (grid[zeroy][zerox].isOccupied() || grid[oney][onex].isOccupied() || grid[twoy][twox].isOccupied() || grid[threey][threex].isOccupied()) {
            return false;
        } else {
            return true;
        }
    }

//    public abstract void calculateBottomPieces();

    /**
     * Moves the tetrimino one unit to the left
     */
    public void moveLeft(){
        zeroy = zero.y();
        zerox = zero.x()-1;

        oney = one.y();
        onex = one.x()-1;

        twoy = two.y();
        twox = two.x()-1;

        threey = three.y();
        threex = three.x()-1;
        applyNewCoords();
    }

    /**
     * Moves the tetrimino one unit to the right
     */
    public void moveRight(){
        zeroy = zero.y();
        zerox = zero.x()+1;

        oney = one.y();
        onex = one.x()+1;

        twoy = two.y();
        twox = two.x()+1;

        threey = three.y();
        threex = three.x()+1;
        applyNewCoords();
    }


    /**
     * Makes the current tetrimino moveDown down 1 row if there is space for it.
     */
    public void moveDown() {
        if (!playfield.calculateEnd()) { // Check if there is a spot under, that is occupied by another tetrimino
            for (GridElement i : pieces) {
                i.setY(i.y() - 1);

            }
        }
    }


    public ArrayList<GridElement> getPieces() {
        return pieces;
    }
}