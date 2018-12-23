import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

public class Game implements KeyListener {
    //private static Game game = new Game();
    private Game game;
    private int timePassed;
    private int score;
    private Timer time;
    private Random random;
    private PlayingField playfield;
    private Tetrimino currentTetrimino;
    private boolean paused;
    private GUI gui;


    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
    }

    // --------------------- GAME CREATION AND TIME HANDLING---------------------
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
     * Initializes the game time.
     */
    private void startGame(){
        paused = false;
        nextPiece();
        startTimer();
    }

    private void stopGame(){
        time.cancel();
        paused = true;
    }

    private void resumeGame(){
        time = new Timer();
        startTimer();
        paused = false;
    }

    public void pauseResume(){
        if(paused){
            resumeGame();
        } else {
            stopGame();
        }
    }

    public void startTimer(){
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
        }, 500, 1000*1);
    }

    /**
     * One ingame timeunit passes.
     */
    public void step(){
        timePassed++;
        fall();
    }

    // --------------------- GAME LOGIC ---------------------

    /**
     * Calculates the next piece to moveDown down.
     */
    public void nextPiece(){
        int nextPiece = game.getRandom().nextInt(7)+1;
        Tetrimino nextTetrimino;
        switch (nextPiece){
            case 1 : nextTetrimino = new I(playfield); break;
//            case 2 : nextTetrimino = new J(playfield); break;
//            case 3 : nextTetrimino = new L(playfield); break;
//            case 4 : nextTetrimino = new O(playfield); break;
//            case 5 : nextTetrimino = new S(playfield); break;
//            case 6 : nextTetrimino = new T(playfield); break;
//            case 7 : nextTetrimino = new Z(playfield); break;

            default: nextTetrimino = new I(playfield);
        }
        currentTetrimino = nextTetrimino;
        playfield.setCurrentTetrimino(currentTetrimino);
    }

    /**
     * Makes the current tetrimino moveDown down 1 row if there is space for it.
     */
    public void fall(){
        if(playfield.calculateEnd()){ // Check if there is a spot under, that is occupied by another tetrimino
            playfield.insertCurrentPieceIntoGrid();

            // After the moveDown, check if any rows have been filled out.
            playfield.removeFullRows();
            boolean lost = playfield.calculateLost();

            if(!lost){
                nextPiece();
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
                for(GridElement g : playfield.getGrid()[20]){
                    System.out.println(g.isOccupied());
                }
                System.out.println("y-value: " + playfield.getGrid()[20][4].y());

            }

        } else {
            //move down the Tetrimino
            currentTetrimino.moveDown();
        }
        gui.updatePlayfield();
    }

    private void moveDown(){
        currentTetrimino.moveDown();
        gui.updatePlayfield();
    }

    private void moveLeft(){
        currentTetrimino.moveLeft();
        gui.updatePlayfield();
    }

    private void moveRight(){
        currentTetrimino.moveRight();
        gui.updatePlayfield();
    }

    private void rotateClockWise(){
        currentTetrimino.rotateClockwise();
        gui.updatePlayfield();
    }

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
                    moveDown();
                }
                break;
            }

            case KeyEvent.VK_SPACE : {
                if (!paused) {
                    //IMPLEMENT DROP
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
                    rotateCounterClockWise();
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
    public void increaseScore(int amount){
        score += amount;
    }

    // --------------------- GEETTERS ---------------------

    /**
     * @return The game object.
     */
    public Game getGame(){
        return game;
    }

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

}
