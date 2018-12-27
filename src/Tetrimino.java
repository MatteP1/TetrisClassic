import java.awt.Color;
import java.util.ArrayList;

/**
 * This class represents the generic type: Tetrimino. Sub-classes are actual tetriminos.
 * @author MatRusTy
 */
public abstract class Tetrimino {

    // --------------------- FIELD VARIABLES ---------------------
    private int orientation;
    private Color color;
    protected ArrayList<GridElement> pieces; //position of the 4 pieces
    protected PlayingField playfield;
    protected GridElement[][] grid;

    //----- PIECE NUMBERS -----
    protected GridElement zero;
    protected GridElement one;
    protected GridElement two;
    protected GridElement three;

    //----- NEW COORDS -----
    protected int zeroy=0;
    protected int zerox=0;

    protected int oney=0;
    protected int onex=0;

    protected int twoy=0;
    protected int twox=0;

    protected int threey=0;
    protected int threex=0;

    // --------------------- CONSTRUCTOR ---------------------

    /**
     * Sets the generic values for a tetrimino
     * @param c Color of the tetrimino
     * @param p The playingfield on which the tetrimino will be played.
     */
    public Tetrimino(Color c, PlayingField p){
        orientation = 0;
        color = c;
        pieces = new ArrayList<>();
        playfield = p;
        grid = p.getGrid();
    }

    // --------------------- MOVEMENT LOGIC ---------------------

    /**
     * Calls the overwritten method in the sub-classes, and then they set the NEW COORDS field variables to the right values.
     * The value of these field variables are then applied if possible, that is, if they are legal positions in the current playinfield.
     */
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
     * Rotation Pairs (the number indicates the current orientation of the tetrimino) :
     * cw	ccw
     * 0	3
     * 1	0
     * 2	1
     * 3	2
     * These rotation methods do, computationally, the same, therefore ccw just calls the cw rotations
     * in those specific orientations / cases.
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
     * @return Boolean expresion telling if the move was successful
     */
    public boolean moveDown() {
        zeroy = zero.y()-1;
        zerox = zero.x();

        oney = one.y()-1;
        onex = one.x();

        twoy = two.y()-1;
        twox = two.x();

        threey = three.y()-1;
        threex = three.x();
        return applyNewCoords();
    }


    public void drop(){
        ArrayList<GridElement> newPieces = calculateGhost();

        zeroy = newPieces.get(0).y();
        zerox = newPieces.get(0).x();

        oney = newPieces.get(1).y();
        onex = newPieces.get(1).x();

        twoy = newPieces.get(2).y();
        twox = newPieces.get(2).x();

        threey = newPieces.get(3).y();
        threex = newPieces.get(3).x();

        applyNewCoords();

    }

    /**
     * calculates the values of the ghost piece, that is, the position of the current tetrimino if it is dropped.
     * returns empty playlist if closestToOccupiedSlot is 0
     */
    public ArrayList<GridElement> calculateGhost(){
        int closestToOccupiedSlot = Integer.MAX_VALUE;
        for(GridElement g : pieces){
            closestToOccupiedSlot = Math.min(g.y(), closestToOccupiedSlot);
        }

        for(GridElement g : pieces){
            for (int i = 1; i <= g.y(); i++) {
                if (grid[g.y()-i][g.x()].isOccupied()){
                    closestToOccupiedSlot = Math.min(i-1, closestToOccupiedSlot);
                }
            }
        }

        ArrayList<GridElement> ghostPieces = new ArrayList<>(4);

        for(GridElement g : pieces){
            ghostPieces.add(grid[g.y()-closestToOccupiedSlot][g.x()]);
        }

        return ghostPieces;
    }


    // --------------------- METHODS TO BE OVERRIDDEN ---------------------
    protected abstract void rotateClockwiseCase0();
    protected abstract void rotateClockwiseCase1();
    protected abstract void rotateClockwiseCase2();
    protected abstract void rotateClockwiseCase3();

    // --------------------- HELPER METHODS FOR THE GAME LOGIC ---------------------

    /**
     * Applies the new coordinates if they are legal.
     * @return
     */
    private boolean applyNewCoords(){
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

    /**
     * Tests if the specified coordinates are legal, ie. not occupied.
     * @param zeroy y-coordinate of the 0th piece
     * @param zerox x-coordinate of the 0th piece
     *
     * @param oney y-coordinate of the 1st piece
     * @param onex x-coordinate of the 1st piece
     *
     * @param twoy y-coordinate of the 2nd piece
     * @param twox x-coordinate of the 2nd piece
     *
     * @param threey y-coordinate of the 3rd piece
     * @param threex x-coordinate of the 3rd piece
     *
     * @return a boolean value telling if the coordinates are legal.
     */
    private boolean isLegal(int zeroy, int zerox,int oney, int onex,int twoy, int twox,int threey, int threex){
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

    // --------------------- GETTERS ---------------------

    /**
     * @return The pieces making up the tetrimino
     */
    public ArrayList<GridElement> getPieces() {
        return pieces;
    }
}