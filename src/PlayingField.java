import java.util.*;
public class PlayingField {
    private static PlayingField playfield = new PlayingField();
    private Tetrimino currentTetrimino;
    private boolean tetriminoHasFallen;
    private GridElement[][] Grid;
    private Game game;


    private PlayingField() {
        initializeGrid();
        game = Game.getGame();
    }

    /**
     * Sets up the grid with empty GridElements
     */
    private void initializeGrid(){
        Grid = new GridElement[39][9];

        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 10; j++) {
                Grid[i][j] = new GridElement();
            }
        }
    }


    /**
     * Calculates the next piece to fall down.
     */
    public void nextPiece(){
        int nextPiece = game.getRandom().nextInt(6)+1;
        Tetrimino nextTetrimino;
        switch (nextPiece){
            case 1 : nextTetrimino = new I(); break;
            case 2 : nextTetrimino = new J(); break;
            case 3 : nextTetrimino = new L(); break;
            case 4 : nextTetrimino = new O(); break;
            case 5 : nextTetrimino = new S(); break;
            case 6 : nextTetrimino = new T(); break;
            case 7 : nextTetrimino = new Z(); break;

            default: nextTetrimino = new I();
        }
        currentTetrimino = nextTetrimino;
    }

    /**
     * Makes the current tetrimino fall down 1 row if there is space for it.
     */
    public void fall(){
        if(calculateEnd()){ // Check if there is a spot under, that is occupied by another tetrimino
            // After the fall, check if any rows have been filled out.
            ArrayList<Integer> fullRows = checkForFullRows();
            if(!fullRows.isEmpty()){
                removeFullRows(fullRows);
            }

            nextPiece();

        } else {
            //move down the Tetrimino
            currentTetrimino.moveDown();
        }
    }

    private boolean calculateEnd(){
        currentTetrimino.calculateBottomPieces();
        ArrayList<IntPair> bottomPieces = currentTetrimino.getBottomPieces();
        for(IntPair i : bottomPieces){
            if(Grid[i.y()-1][i.x()].isOccupied()){
                return true;
            }
        }
        return false;
    }

    /**
     * @return The row indices of the full rows
     */
    private ArrayList<Integer> checkForFullRows(){
        ArrayList<Integer> fullRows = new ArrayList<>();
        for (int i = 0; i < 23; i++) {
            int amountOccupied = 0;

            for (int j = 0; j < 10; j++) {
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
    private void removeFullRows(ArrayList<Integer> rows){
        //Moves all rows above the i'th row, one down.
        for(Integer i : rows){
            for (int j = i; j < 23; j++) {
                Grid[j] = Grid[j+1];
            }
        }
        int calculatedScore = rows.size(); //Change later to include combos.
        game.increaseScore(calculatedScore);
    }



    /**
     * @return The playingField object.
     */
    public static PlayingField getInstance() {
        return playfield;
    }
}
