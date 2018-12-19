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
    }

    /**
     * Initializes the game.
     */
    private void startGame(){
        playfield = PlayingField.getInstance();
        playfield.nextPiece();

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
