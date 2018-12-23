import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

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
    private JPanel[][] tiles;
    private JPanel sideInfo;
    private JLabel stats;



    public GUI(Game game, PlayingField playfield) {
        this.game = game;
        this.playfield = playfield;
        Grid = playfield.getGrid();
        createGUI();
        mainFrame.addKeyListener(game);
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
        mainFrame.setFocusable(true);
        mainFrame.setVisible(true);
    }

    public void updatePlayfield(){
        for (int i = 0; i <= 19; i++) {
            for (int j = 0; j <= 9; j++) {
                tiles[i][j].setBackground(Grid[i][j].getBackground());
            }
        }

        ArrayList<GridElement> pieces = playfield.getCurrentTetrimino().getPieces();
        for(GridElement g : pieces){
            if(g.y()<20){
                tiles[g.y()][g.x()].setBackground(g.getBackground());
            }
        }
        stats.setText(generateStatsText());
    }

    private void createContent(){
        //INFO AREA
        createSideInfo();
        contentPane.add(sideInfo, BorderLayout.EAST);

        //GAME AREA
        gameArea = createGameArea();

        contentPane.add(gameArea, BorderLayout.CENTER);

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
        pauseResumeButton.setBackground(Color.DARK_GRAY);
        pauseResumeButton.setForeground(Color.GREEN);
        settings.add(pauseResumeButton);

        JButton newGame = new JButton("New Game");
        newGame.addActionListener(e -> {
            game.newGame();
            mainFrame.requestFocusInWindow();
        });
        newGame.setBackground(Color.DARK_GRAY);
        newGame.setForeground(Color.GREEN);
        settings.add(newGame);

        JButton settingsButton = new JButton("Settings");
        settingsButton.addActionListener(e -> {
            openSettings();
            mainFrame.requestFocusInWindow();
        });
        settingsButton.setBackground(Color.DARK_GRAY);
        settingsButton.setForeground(Color.GREEN);
        settings.add(settingsButton);

        JButton exitButton = new JButton("Exit Game");
        exitButton.addActionListener(e -> System.exit(0));
        exitButton.setBackground(Color.DARK_GRAY);
        exitButton.setForeground(Color.GREEN);
        settings.add(exitButton);

        contentPane.add(optionsArea, BorderLayout.SOUTH);
    }

    public void pauseResume(){
        game.pauseResume();
        paused = game.isPaused();
        if(paused){
            pauseResumeButton.setText("Resume");
        } else {
            pauseResumeButton.setText("Pause");
        }
        mainFrame.requestFocusInWindow();
    }

    private void openSettings(){
        //OPEN SETTINGS
    }

    private JPanel createGameArea(){
        gameArea = new JPanel();
        gameArea.setBackground(Color.BLACK);
        gameArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        gameArea.setLayout(new GridLayout(20,10));
        tiles = new JPanel[20][10];

        for (int i = 19; i >= 0; i--) {
            for (int j = 0; j <= 9; j++) {
                tiles[i][j] = new JPanel();
                tiles[i][j].setBackground(Grid[i][j].getBackground());
                tiles[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            }
        }

        for (int i = 19; i >= 0; i--) {
            for (int j = 0; j <= 9; j++) {
                gameArea.add(tiles[i][j]);
            }
        }
        return gameArea;
    }

    private void createSideInfo(){
        sideInfo = new JPanel();
        sideInfo.setBackground(Color.BLACK);
        sideInfo.setPreferredSize(new Dimension(200, HEIGHT));
        sideInfo.setLayout(new BoxLayout(sideInfo,  BoxLayout.Y_AXIS));

        // --------------- STATS AREA ---------------
        stats = new JLabel(generateStatsText(), SwingConstants.LEFT);
        stats.setBorder(new EmptyBorder(200,25,0,0));
        stats.setForeground(Color.GREEN);

        sideInfo.add(stats);

        // --------------- CONTROLS AREA ---------------

        String controlsText =
                "<html>" +
                        "<h1>Controls</h1> " +
                        "<p>" +
                        "Move Left: ←<br> " +
                        "Move Right: →<br>" +
                        "Move Down: Ctrl<br>" +
                        "Rotate Anticlockwise: ↑<br>" +
                        "Rotate Clockwise: ↓<br>" +
                        "Drop: Space<br>" +
                        "Pause Game: Esc<br>" +
                        "</p>" +
                "</html>";

        JLabel controls = new JLabel(controlsText, SwingConstants.LEFT);
        controls.setForeground(Color.GREEN);
        controls.setBorder(new EmptyBorder(50,25,0,0));

        sideInfo.add(controls);
    }

    private String generateStatsText(){
        return "<html>" +
                        "<h1>Stats</h1> " +
                        "<p>" +
                        "Time : " + game.getTimePassed() +"<br> " +
                        "Score : " + game.getScore() +"<br>" +
                        "</p>" +
                "</html>";
    }

    private void createFile(){
        JMenuBar menuBar = new JMenuBar();
        mainFrame.setJMenuBar(menuBar);

        JMenu test = new JMenu("Test");
        JMenuItem testItem = new JMenuItem("Test");
        test.add(testItem);
        menuBar.add(test);
    }
}
