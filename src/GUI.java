import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class GUI implements KeyListener {
    private JFrame mainFrame;
    private Container contentPane;
    private JPanel gameArea;
    private PlayingField playfield;
    private GridElement[][] Grid;
    private Game game;

    public GUI(Game game, PlayingField playfield) {
        this.game = game;
        this.playfield = playfield;
        createGUI();
        Grid = playfield.getGrid();
    }

    private void createGUI(){
        mainFrame = new JFrame("Tetris");
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //createFile();
        contentPane = mainFrame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        createContent();

        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    private void createContent(){

        //GAME AREA
        gameArea = setupGameArea();
        gameArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        contentPane.add(gameArea, BorderLayout.CENTER);
        addGameInputFunctionality();


        //OPTIONS AREA
        JPanel optionsArea = new JPanel();
        optionsArea.setLayout(new BorderLayout());

        // Label
        JLabel optionsLabel = new JLabel("Options",SwingConstants.CENTER);
        optionsArea.add(optionsLabel,BorderLayout.NORTH);

        //Settings
        JPanel settings = new JPanel();
        settings.setLayout(new GridLayout(1,4));
        optionsArea.add(settings);

        JButton pauseResume = new JButton("Pause/Resume");
        //ADD PAUSE FUNCTIONALITY HERE
        settings.add(pauseResume);

        JButton newGame = new JButton("New Game");
        //ADD NEW GAME FUNCTIONALITY HERE
        settings.add(newGame);

        JButton settingsButton = new JButton("Settings");
        //ADD SETTINGS FUNCTIONALITY HERE
        settings.add(settingsButton);

        JButton exitButton = new JButton("Exit Game");
        exitButton.addActionListener(e -> System.exit(0)); //ADD POPUP ASKING IF THEY ARE SURE THEY WANT TO EXIT
        settings.add(exitButton);

        contentPane.add(optionsArea, BorderLayout.SOUTH);

    }

    private void addGameInputFunctionality(){

    }

    private JPanel setupGameArea(){
        //NEEDS IMPLEMENTING
        gameArea = new JPanel();
        gameArea.setLayout(new GridLayout(10,21));
        for (int i = 21; i >= 0; i++) {
            for(GridElement g : Grid[i]){
                JPanel box = new JPanel();
                box.setBackground(g.getColor());
                box.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                gameArea.add(box);
            }
        }

        return gameArea;
    }

    private void createFile(){
        JMenuBar menuBar = new JMenuBar();
        mainFrame.setJMenuBar(menuBar);

        JMenu test = new JMenu("Test");
        JMenuItem testItem = new JMenuItem("Test");
        test.add(testItem);
        menuBar.add(test);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //Won't be implemented
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){

            case KeyEvent.VK_UP : {
                //ROTATE
            }
            case KeyEvent.VK_DOWN : {
                //ROTATE
            }
            case KeyEvent.VK_LEFT : {
                playfield.getCurrentTetrimino().moveLeft();
            }
            case KeyEvent.VK_RIGHT : {
                playfield.getCurrentTetrimino().moveRight();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //Won't be implemented
    }

    public static void createGameGUI(Game game, PlayingField playfield){
        new GUI(game, playfield);
    }

}
