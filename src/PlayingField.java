import java.util.*;
import java.awt.Color;
public class PlayingField {
    private Tetrimino currentTetrimino;
    private GridElement[][] Grid;
    private Game game;


    public PlayingField(Game game) {
        Grid = new GridElement[40][10];
        clearGrid();
        this.game = game;
    }

    /**
     * Sets up the grid with empty GridElements
     */
    public void clearGrid(){
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
                return true;
            }
        }
        return false;
    }

    public boolean calculateLost(){
        for(GridElement g : Grid[20]){
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
        fullRows.sort(Comparator.reverseOrder());

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
        for (int i = 0; i < 21; i++) {
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
     * Copies the information from the row above, down to the row below, for each row above the given index.
     * @param rowIndex The row to be cleared
     */
    private void removeRow(int rowIndex){
        for (int i = rowIndex; i <= 38; i++) {
            for (int j = 0; j <= 9; j++) {
                Grid[i][j].setOccupied(Grid[i+1][j].isOccupied());
                Grid[i][j].setBackground(Grid[i+1][j].getBackground());
            }
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
