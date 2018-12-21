import java.util.*;

public class Game {
    //private static Game game = new Game();
    private Game game;
    private int timePassed;
    private int score;
    private Timer time;
    private Random random;
    private PlayingField playfield;
    private Tetrimino currentTetrimino;
    private boolean paused;


    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
    }

    // --------------------- GAME CREATION AND TIME ---------------------
    private Game() {
        timePassed = 0;
        score = 0;
        time = new Timer();
        playfield = new PlayingField(this);
        random = new Random();
        this.game = this;
        GUI.createGameGUI(game, playfield);
    }

    /**
     * Initializes the game time.
     * 3 seconds pass between each tetrimino fall.
     */
    private void startGame(){
        playfield.nextPiece();
        startTimer();
    }

    public void stopGame(){
        time.cancel();
        paused = true;
    }

    public void resumeGame(){
        time = new Timer();
        startTimer();
    }

    public void startTimer(){
        paused = false;
        time.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                step();
                System.out.println(timePassed);
            }
        }, 500, 1000*1);
    }

    /**
     * @return The game object.
     */
    public Game getGame(){
        return game;
    }

    /**
     * One ingame timeunit passes.
     */
    public void step(){
        timePassed++;
        playfield.fall();

    }

    // --------------------- GAME LOGIC ---------------------




    // --------------------- GAME INPUT ---------------------





    // --------------------- METAINFO HANDLER ---------------------
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

}
