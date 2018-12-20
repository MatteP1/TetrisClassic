import java.util.*;
import java.awt.Color;
public class PlayingField {
    private Tetrimino currentTetrimino;
    private boolean tetriminoHasFallen;
    private GridElement[][] Grid;
    private Game game;


    public PlayingField(Game game) {
        initializeGrid();
        this.game = game;
    }

    /**
     * Sets up the grid with empty GridElements
     */
    private void initializeGrid(){
        Grid = new GridElement[40][10];

        for (int i = 0; i <= 39; i++) {
            for (int j = 0; j <= 9; j++) {
                Grid[i][j] = new GridElement(i, j, Color.GRAY, false);
            }
        }
    }


    /**
     * Calculates the next piece to fall down.
     */
    public void nextPiece(){
        int nextPiece = game.getRandom().nextInt(6)+1;
        Tetrimino nextTetrimino;
//        switch (nextPiece){
//            case 1 : nextTetrimino = new I(); break;
//            case 2 : nextTetrimino = new J(); break;
//            case 3 : nextTetrimino = new L(); break;
//            case 4 : nextTetrimino = new O(); break;
//            case 5 : nextTetrimino = new S(); break;
//            case 6 : nextTetrimino = new T(); break;
//            case 7 : nextTetrimino = new Z(); break;
//
//            default: nextTetrimino = new I();
//        }
        nextTetrimino = new I();

        currentTetrimino = nextTetrimino;
    }

    /**
     * Makes the current tetrimino fall down 1 row if there is space for it.
     */
    public void fall(){
        if(calculateEnd()){ // Check if there is a spot under, that is occupied by another tetrimino
            insertCurrentPieceIntoGrid();

            // After the fall, check if any rows have been filled out.
            removeFullRows();

            boolean lost = calculateLost();

            if(!lost){
                nextPiece();
                System.out.println("Piece fallen");
                System.out.println("Next piece is: " + currentTetrimino.toString());
                System.out.println("Currently occupied slots:");
                for(GridElement[] G : Grid){
                    for(GridElement g : G){
                        if(g.isOccupied()){
                            System.out.print("(" + g.y() +", "+  g.x() + ") ");
                        }
                    }
                }
                System.out.println();

            } else {
                game.stopGame();
                System.out.println("Game Over!");
            }

        } else {
            //move down the Tetrimino
            currentTetrimino.moveDown();

        }
    }

    private void insertCurrentPieceIntoGrid(){
        ArrayList<GridElement> pieces = currentTetrimino.getPieces();
        for(GridElement g : pieces){
            Grid[g.y()][g.x()] = g;
        }
    }

    private boolean calculateEnd(){
        currentTetrimino.calculateBottomPieces();
        ArrayList<GridElement> bottomPieces = currentTetrimino.getBottomPieces();
        for(GridElement i : bottomPieces){
            if(i.y() == 0 || Grid[i.y()-1][i.x()].isOccupied()){
                tetriminoHasFallen = true;
                return true;
            }
        }
        return false;
    }

    private boolean calculateLost(){
        for(GridElement g : Grid[21]){
            if(g.isOccupied()){
                return true;
            }
        }
        return false;
    }

    private void removeFullRows(){
        ArrayList<Integer> fullRows = checkForFullRows();
        if(!fullRows.isEmpty()){
            removeRows(fullRows);
        }
    }

    /**
     * @return The row indices of the full rows
     */
    private ArrayList<Integer> checkForFullRows(){
        ArrayList<Integer> fullRows = new ArrayList<>();
        for (int i = 0; i < 22; i++) {
            int amountOccupied = 0;

            for (int j = 0; j <= 9; j++) {
                if(Grid[i][j].isOccupied()){
                    amountOccupied++;
                }
            }
            if(amountOccupied == 10){
                fullRows.add(i);
            }
        }
        return fullRows;
    }

    /**
     * Removes the full rows and adds point to the score
     * @param rows The rows to be cleared
     */
    private void removeRows(ArrayList<Integer> rows){
        //Moves all rows above the i'th row, one down.
        for(Integer i : rows){
            for (int j = i; j < 23; j++) {
                Grid[j] = Grid[j+1];
            }
        }
        int calculatedScore = rows.size(); //Change later to include combos.
        game.increaseScore(calculatedScore);
    }

    public Tetrimino getCurrentTetrimino(){
        return currentTetrimino;
    }

    public GridElement[][] getGrid(){
        return Grid;
    }
}
