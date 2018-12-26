import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

/**
 * The game class containing most of the game handling logic, as well as keybindings.
 * The logic handling the movement of the tetriminos is defined in the abstract Tetrimino class and its sub-classes.
 *
 *
 */
public class Game implements KeyListener {

    // --------------------- FIELD VARIABLES ---------------------
    private Game game;
    private int timePassed;
    private int score;
    private Timer time;
    private Random random;
    private boolean paused;
    private GUI gui;

    /** The grid that the current tetrimino has to be placed in*/
    private PlayingField playfield;

    /** Variable containing the current Tetrimino in the playingfield*/
    private Tetrimino currentTetrimino;

    /** Variable containing the tetrimino available for swapping */
    private Tetrimino savedTetrimino;

    /** Variable telling if the swap feature has been used this round ("round" being the fall of the current Tetrimino) */
    private boolean changedCurrentTetriminoThisRound;

    /** Defines the delay before the game ticks again after the time has been stopped*/
    private static final int delay = 500;

    /** Defines the amount of time that passes between each ingame timetick*/
    private static final int period = 1000*1;


    // --------------------- MAIN METHOD ---------------------
    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
    }

    // --------------------- GAME CREATION AND TIME HANDLING ---------------------
    /**
     * Creates a new game object with all stats set to 0 and a new, empty playingfield.
     */
    private Game() {
        timePassed = 0;
        score = 0;
        time = new Timer();
        playfield = new PlayingField(this);
        random = new Random();
        this.game = this;
        gui = new GUI(game, playfield);
    }

    /**
     * Initializes the game time and requests the first piece
     */
    private void startGame(){
        paused = false;
        nextPiece();
        startTimer(delay, period);
    }

    /**
     * Resets stats and playfield, and start a new game.
     */
    public void newGame(){
        stopGame();
        timePassed = 0;
        score = 0;
        savedTetrimino = null;
        playfield.clearGrid();
        time = new Timer();
        startGame();
        paused = false;
        gui.updatePlayfield();
    }

    /**
     * Stops the time.
     */
    private void stopGame(){
        time.cancel();
        paused = true;
    }

    /**
     * Creates a new timer and starts it.
     */
    private void resumeGame(){
        time = new Timer();
        startTimer(delay, period);
        paused = false;
    }

    /**
     * If game is running, the method pauses the game. Otherwise, it resumes it.
     */
    public void pauseResume(){
        if(paused){
            resumeGame();
        } else {
            stopGame();
        }
    }

    /**
     * Creates a new timer, and schedules it to run with a given delay and period
     *
     * @param delay The time it takes before it executes the first run method
     * @param period The time that goes between the execution of each run
     */
    public void startTimer(int delay, int period){
        paused = false;
        time.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                step();
                System.out.println(timePassed);
                for(GridElement g : currentTetrimino.getPieces()){
                    System.out.print("(" + g.y() +", "+  g.x() + ") ");
                }
                System.out.println();
            }
        }, delay, period);
    }

    /**
     * One ingame timeunit passes.
     */
    public void step(){
        timePassed++;
        computerMoveDown();
    }

    // --------------------- GAME LOGIC ---------------------

    /**
     * Calculates the next tetrimino to move down.
     */
    public void nextPiece(){
        int nextPiece = game.getRandom().nextInt(7)+1;
        Tetrimino nextTetrimino;
        switch (nextPiece){
            case 1 : nextTetrimino = new I(playfield); break;
            case 2 : nextTetrimino = new J(playfield); break;
            case 3 : nextTetrimino = new L(playfield); break;
            case 4 : nextTetrimino = new O(playfield); break;
            case 5 : nextTetrimino = new S(playfield); break;
            case 6 : nextTetrimino = new T(playfield); break;
            case 7 : nextTetrimino = new Z(playfield); break;

            default: nextTetrimino = new I(playfield);
        }
        currentTetrimino = nextTetrimino;
        playfield.setCurrentTetrimino(currentTetrimino);
    }

    /**
     * This method is used to support the swap feature.
     * You can change your current tetrimino with one you have set a side earlier
     * If you haven't set one aside earlier (first time the method is ever called), you set your piece aside, and the next piece is generated.
     */
    private void changeCurrentTetrimino(){
        if(!changedCurrentTetriminoThisRound) {
            if (savedTetrimino == null) {
                savedTetrimino = currentTetrimino;
                savedTetrimino = createNewInstanceOf(savedTetrimino);
                nextPiece();
            } else {
                Tetrimino temp = savedTetrimino;
                savedTetrimino = currentTetrimino;
                savedTetrimino = createNewInstanceOf(savedTetrimino);
                currentTetrimino = temp;
                playfield.setCurrentTetrimino(currentTetrimino);
            }
            changedCurrentTetriminoThisRound = true;
        }

        gui.updatePlayfield();
    }


    /**
     * Helper method for the changeCurrentTetrimino method.
     * It creates a new instance of the piece that the player wants to save. That way the position is resat, so it spawns at the top of the playingfield.
     * @param t The tetrimino of which a new instance of should be created.
     * @return The newly created instance.
     */
    private Tetrimino createNewInstanceOf(Tetrimino t) {
        if(t instanceof I) {
            return new I(playfield);
        } else if(t instanceof J){
            return new J(playfield);
        } else if(t instanceof L){
            return new L(playfield);
        } else if(t instanceof O){
            return new O(playfield);
        } else if(t instanceof S){
            return new S(playfield);
        } else if(t instanceof T){
            return new T(playfield);
        } else if(t instanceof Z){
            return new Z(playfield);
        } else {
            return new I(playfield);
        }
    }

    /**
     * Makes the current tetrimino moveDown down 1 row if there is space for it.
     * If there isn't space for it. Then it needs to be inserted into the playingfield, and a new piece should be created.
     */
    public void computerMoveDown(){
        boolean successful = currentTetrimino.moveDown();
        if(!successful){
            insertIntoGrid();
        }
        gui.updatePlayfield();
    }

    /**
     * Helper method for the computerMoveDownMethod.
     * Inserts the current piece into the grid and checks to see if the game is lost.
     */
    private void insertIntoGrid(){
        playfield.insertCurrentPieceIntoGrid();

        // After the moveDown, check if any rows have been filled out.
        playfield.removeFullRows();
        boolean lost = playfield.calculateLost();
        if(!lost){
            nextPiece();
            changedCurrentTetriminoThisRound = false;
            System.out.println("Piece fallen");
            System.out.println("Next piece is: " + currentTetrimino.toString());
            System.out.println("Currently occupied slots:");
            for(GridElement[] G : playfield.getGrid()){
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
            gui.gameLostScreen();
            gui.updatePlayfield();
        }
    }

    /**
     * Method to drop the current piece to the bottom of the playingfield.
     */
    private void drop(){
        for(int i = 0; i < 20; i++){
            moveDown();
        }
        insertIntoGrid();
        gui.updatePlayfield();
    }

    /**
     * Move down the current tetrimino 1 gridelement
     */
    private void moveDown(){
        currentTetrimino.moveDown();
        gui.updatePlayfield();
    }

    /**
     * Move the tetrimino left
     */
    private void moveLeft(){
        currentTetrimino.moveLeft();
        gui.updatePlayfield();
    }

    /**
     * Move the tetrimino right
     */
    private void moveRight(){
        currentTetrimino.moveRight();
        gui.updatePlayfield();
    }

    /**
     * Rotate the current tetrimino in the clockwise direction
     */
    private void rotateClockWise(){
        currentTetrimino.rotateClockwise();
        gui.updatePlayfield();
    }

    /**
     * Rotate the current tetrimino in the counter-clockwise direction
     */
    private void rotateCounterClockWise(){
        currentTetrimino.rotateCounterClockwise();
        gui.updatePlayfield();
    }


    // --------------------- GAME INPUT ---------------------

    @Override
    public void keyTyped(KeyEvent e) {
        //Won't be implemented
    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch(e.getKeyCode()) {

            case KeyEvent.VK_LEFT : {
                if (!paused) {
                    moveLeft();
                }
                break;
            }

            case KeyEvent.VK_RIGHT : {
                if (!paused) {
                    moveRight();
                }
                break;
            }

            case KeyEvent.VK_CONTROL : {
                if (!paused) {
                    rotateCounterClockWise();
                }
                break;
            }

            case KeyEvent.VK_SPACE : {
                if (!paused) {
                    drop();
                }
            }

            case KeyEvent.VK_UP : {
                if (!paused) {
                    rotateClockWise();
                }
                break;
            }

            case KeyEvent.VK_DOWN : {
                if (!paused) {
                    moveDown();
                }
                break;
            }

            case KeyEvent.VK_C : {
                if(!paused){
                    changeCurrentTetrimino();
                }
                break;
            }

            case KeyEvent.VK_ESCAPE : {
                gui.pauseResume();
                break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //Won't be implemented
    }

    // --------------------- METAINFO HANDLER ---------------------

    /**
     * Increases the current score with the specified value
     * @param amount Amount to be added to the score
     */
    public void increaseScore(int amount){
        score += amount;
    }


    // --------------------- GEETTERS ---------------------

    public Random getRandom(){
        return random;
    }

    public int getTimePassed(){
        return timePassed;
    }

    public int getScore(){
        return score;
    }
    public boolean isPaused(){
        return paused;
    }
    public Tetrimino getSavedTetrimino(){
        return savedTetrimino;
    }

}
