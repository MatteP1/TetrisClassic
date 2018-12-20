import java.util.*;

public class Game {
    //private static Game game = new Game();
    private Game game;
    private int timePassed;
    private int score;
    private Timer time;
    private Random random;
    private PlayingField playfield;


    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
    }

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
        time.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                step();
                System.out.println(timePassed);
            }
        }, 0, 1000*1);
    }

    public void stopGame(){
        time.cancel();
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

    public void increaseScore(int amount){
        score += amount;
    }

    public Random getRandom(){
        return random;
    }

}
