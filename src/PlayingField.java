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

    public void insertCurrentPieceIntoGrid(){
        ArrayList<GridElement> pieces = currentTetrimino.getPieces();
        for(GridElement g : pieces){
            g.makeOccupied();
            Grid[g.y()][g.x()] = g;
        }
    }

    public boolean calculateEnd(){
        ArrayList<GridElement> pieces = currentTetrimino.getPieces();
        for(GridElement i : pieces){
            if(i.y() == 0 || Grid[i.y()-1][i.x()].isOccupied()){
                tetriminoHasFallen = true;
                return true;
            }
        }
        return false;
    }

    public boolean calculateLost(){
        for(GridElement g : Grid[21]){
            if(g.isOccupied()){
                return true;
            }
        }
        return false;
    }

    /**
     * Removes all full rows in the playing-field
     */
    public void removeFullRows(){
        ArrayList<Integer> fullRows = checkForFullRows();
        fullRows.sort(Collections.reverseOrder());

        if(!fullRows.isEmpty()){
            for(Integer i : fullRows){
                removeRow(i);
            }
        }
        int calculatedScore = fullRows.size(); //Change later to include combos.
        game.increaseScore(calculatedScore);
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
     * Removes the row and adds a new, empty row to the top
     * @param rowIndex The row to be cleared
     */
    private void removeRow(int rowIndex){
        for (int j = rowIndex; j <= 38; j++) {
            Grid[j] = Grid[j+1];
        }

        for (int j = 0; j <= 9; j++) {
            Grid[39][j] = new GridElement(39, j, Color.GRAY, false);
        }
    }

    public Tetrimino getCurrentTetrimino(){
        return currentTetrimino;
    }

    public GridElement[][] getGrid(){
        return Grid;
    }

    public void setCurrentTetrimino(Tetrimino t){
        currentTetrimino = t;
    }
}
