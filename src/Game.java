import java.util.*;

public class Game {
    private static Game game = new Game();
    private int timePassed;
    private int score;
    private Timer time;
    private Random random;
    private PlayingField playfield;


    public static void main(String[] args) {
        game.startGame();
    }

    private Game() {
        timePassed = 0;
        score = 0;
        time = new Timer();
        playfield = PlayingField.getInstance();
    }

    /**
     * Initializes the game time.
     * 3 seconds pass between each tetrimino fall.
     */
    private void startGame(){
        while(timePassed<50){
            time.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    step();
                }
            },0, 1000*3);
        }
    }

    /**
     * @return The game object.
     */
    public static Game getGame(){
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
