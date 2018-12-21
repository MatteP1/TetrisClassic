import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI {

    private JFrame mainFrame;
    private Container contentPane;
    private JPanel gameArea;
    private PlayingField playfield;
    private GridElement[][] Grid;
    private Game game;
    private boolean paused = false;
    private JButton pauseResumeButton;
    private static final int WIDTH = 600, HEIGHT = 800;

    public GUI(Game game, PlayingField playfield) {
        this.game = game;
        this.playfield = playfield;
        Grid = playfield.getGrid();
        createGUI();
    }

    private void createGUI(){
        mainFrame = new JFrame("Tetris");
        mainFrame.setSize(WIDTH,HEIGHT);
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //createFile();
        contentPane = mainFrame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        createContent();

        mainFrame.setVisible(true);
    }

    private void createContent(){
        //INFO AREA
        JPanel infoArea = new SideInfo(game, HEIGHT);
        contentPane.add(infoArea,BorderLayout.EAST);

        //GAME AREA
        gameArea = setupGameArea();
        gameArea.setBackground(Color.BLACK);
        gameArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        contentPane.add(gameArea, BorderLayout.CENTER);
        addGameInputFunctionality();


        //OPTIONS AREA
        JPanel optionsArea = new JPanel();
        optionsArea.setLayout(new BorderLayout());
        optionsArea.setBackground(Color.DARK_GRAY);

        // ------Label------
        JLabel optionsLabel = new JLabel("Options",SwingConstants.CENTER);
        optionsLabel.setForeground(Color.GREEN);
        optionsArea.add(optionsLabel,BorderLayout.NORTH);

        // -------Settings------
        JPanel settings = new JPanel();
        settings.setLayout(new GridLayout(1,4));
        optionsArea.add(settings);

        pauseResumeButton = new JButton("Pause");
        pauseResumeButton.addActionListener(e -> pauseResume());
        //ADD PAUSE FUNCTIONALITY HERE
        pauseResumeButton.setBackground(Color.DARK_GRAY);
        pauseResumeButton.setForeground(Color.GREEN);
        settings.add(pauseResumeButton);

        JButton newGame = new JButton("New Game");
        //ADD NEW GAME FUNCTIONALITY HERE
        newGame.setBackground(Color.DARK_GRAY);
        newGame.setForeground(Color.GREEN);
        settings.add(newGame);

        JButton settingsButton = new JButton("Settings");
        //ADD SETTINGS FUNCTIONALITY HERE
        settingsButton.setBackground(Color.DARK_GRAY);
        settingsButton.setForeground(Color.GREEN);
        settings.add(settingsButton);

        JButton exitButton = new JButton("Exit Game");
        exitButton.addActionListener(e -> System.exit(0)); //ADD POPUP ASKING IF THEY ARE SURE THEY WANT TO EXIT
        exitButton.setBackground(Color.DARK_GRAY);
        exitButton.setForeground(Color.GREEN);
        settings.add(exitButton);

        contentPane.add(optionsArea, BorderLayout.SOUTH);

    }

    public void pauseResume(){
        paused = !paused;
        if(paused){
            game.stopGame();
            pauseResumeButton.setText("Resume");

        } else {
            game.resumeGame();
            pauseResumeButton.setText("Pause");
        }
    }

    private void addGameInputFunctionality(){

    }

    private JPanel setupGameArea(){
        gameArea = new JPanel();
        gameArea.setLayout(new GridLayout(20,10));
        for (int i = 0; i <= 19; i++) {
            for(GridElement g : Grid[i]){
                gameArea.add(g);
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



    public static void createGameGUI(Game game, PlayingField playfield){
        new GUI(game, playfield);
    }

}
