import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class GUI {

    // --------------------- FIELD VARIABLES ---------------------
    private JFrame mainFrame;
    private Container contentPane;
    private JPanel gameArea, sideInfo, savedTetrimino;
    private PlayingField playfield;
    private GridElement[][] Grid;
    private Game game;
    private boolean paused = false;
    private JButton pauseResumeButton, newGame, themeButton, exitButton;
    private static final int WIDTH = 600, HEIGHT = 800;
    private JPanel[][] tiles, savedTiles;
    private JLabel stats, optionsLabel, savedTetriminoLabel, controls;
    private Color themeColor;


    // ------------------------------------------ GUI CREATION ------------------------------------------
    /**
     * Creates a new GUI
     * @param game The main game object, that controls the game.
     * @param playfield The playingfield that is being played on.
     */
    public GUI(Game game, PlayingField playfield) {
        this.game = game;
        this.playfield = playfield;
        Grid = playfield.getGrid();
        themeColor = Color.GREEN;
        createGUI();
        mainFrame.addKeyListener(game);
    }

    /**
     * Creates the mainFrame
     */
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

    /**
     * Creates all containers in the contentPane, handles layout, design, etc.
     */
    private void createContent(){
        //INFO AREA
        createSideInfo();
        contentPane.add(sideInfo, BorderLayout.EAST);

        //------ GAME AREA ------
        gameArea = createGameArea();

        contentPane.add(gameArea, BorderLayout.CENTER);

        //------ OPTIONS AREA ------
        JPanel optionsArea = new JPanel();
        optionsArea.setLayout(new BorderLayout());
        optionsArea.setBackground(Color.DARK_GRAY);

        // ------Label------
        optionsLabel = new JLabel("Options",SwingConstants.CENTER);
        optionsLabel.setForeground(themeColor);
        optionsArea.add(optionsLabel,BorderLayout.NORTH);

        // -------Settings------
        JPanel settings = new JPanel();
        settings.setLayout(new GridLayout(1,4));
        optionsArea.add(settings);

        pauseResumeButton = new JButton("Pause");
        pauseResumeButton.addActionListener(e -> pauseResume());
        pauseResumeButton.setBackground(Color.DARK_GRAY);
        pauseResumeButton.setForeground(themeColor);
        settings.add(pauseResumeButton);

        newGame = new JButton("New Game");
        newGame.addActionListener(e -> {
            game.newGame();
            mainFrame.requestFocusInWindow();
        });
        newGame.setBackground(Color.DARK_GRAY);
        newGame.setForeground(themeColor);
        settings.add(newGame);

        themeButton = new JButton("Theme");
        themeButton.addActionListener(e -> {
            changeTheme();
            mainFrame.requestFocusInWindow();
        });
        themeButton.setBackground(Color.DARK_GRAY);
        themeButton.setForeground(themeColor);
        settings.add(themeButton);

        exitButton = new JButton("Exit Game");
        exitButton.addActionListener(e -> System.exit(0));
        exitButton.setBackground(Color.DARK_GRAY);
        exitButton.setForeground(themeColor);
        settings.add(exitButton);

        contentPane.add(optionsArea, BorderLayout.SOUTH);
    }

    /**
     * Creates the GUI for the entire playingfield.
     * @return A JPanel containing the the playingfield grid.
     */
    private JPanel createGameArea(){
        gameArea = new JPanel();
        gameArea.setBackground(Color.BLACK);
        gameArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        gameArea.setLayout(new GridLayout(20,10));
        tiles = newEmptyGrid(19,9, Color.GRAY);

        for (int i = 19; i >= 0; i--) {
            for (int j = 0; j <= 9; j++) {
                gameArea.add(tiles[i][j]);
            }
        }
        return gameArea;
    }

    /**
     * Creates the side-part of the GUI containing the saved tetrimino, the stats, and the controls.
     */
    private void createSideInfo(){
        sideInfo = new JPanel();
        sideInfo.setBackground(Color.BLACK);
        sideInfo.setPreferredSize(new Dimension(200, HEIGHT));
        sideInfo.setLayout(new BorderLayout());
        sideInfo.setBorder(new EmptyBorder(15,15,15,15));


        // --------------- SAVED PIECE AREA ---------------
        JPanel savedTetriminoArea = new JPanel();
        savedTetriminoArea.setLayout(new BorderLayout());
        savedTetriminoArea.setBackground(Color.BLACK);

        String savedPieceString = "<html><h1>Saved piece</h1></html>";
        savedTetriminoLabel = new JLabel(savedPieceString, SwingConstants.LEFT);
        savedTetriminoLabel.setForeground(themeColor);
        savedTetriminoArea.add(savedTetriminoLabel, BorderLayout.NORTH);

        savedTetrimino = new JPanel();
        savedTetrimino.setBackground(Color.BLACK);
        savedTetrimino.setLayout(new GridLayout(4,4));
        savedTetrimino.setPreferredSize(new Dimension(170,170));
        savedTetrimino.setBorder(BorderFactory.createLineBorder(themeColor));

        savedTiles = newEmptyGrid(3,3, Color.DARK_GRAY.darker());
        for (int i = 3; i >= 0; i--) {
            for (int j = 0; j <= 3; j++) {
                savedTetrimino.add(savedTiles[i][j]);
            }
        }

        savedTetriminoArea.add(savedTetrimino, BorderLayout.CENTER);

        sideInfo.add(savedTetriminoArea, BorderLayout.NORTH);

        // ------------------------------ TEXT AREA ------------------------------
        JPanel textArea = new JPanel();
        textArea.setBackground(Color.BLACK);
        textArea.setLayout(new BoxLayout(textArea,BoxLayout.Y_AXIS));

        // --------------- STATS AREA ---------------
        stats = new JLabel(generateStatsText(), SwingConstants.LEFT);
        stats.setBorder(new EmptyBorder(25,0,0,0));
        stats.setForeground(themeColor);
        textArea.add(stats);

        // --------------- CONTROLS AREA ---------------

        String controlsText =
                "<html>" +
                        "<h1>Controls</h1> " +
                        "<p>" +
                        "Move Left: ←<br> " +
                        "Move Right: →<br>" +
                        "Move Down: ↑<br>" +
                        "Rotate Counterclockwise: Ctrl<br>" +
                        "Rotate Clockwise: ↓<br>" +
                        "Drop: Space<br>" +
                        "Pause Game: Esc<br>" +
                        "</p>" +
                        "</html>";

        controls = new JLabel(controlsText, SwingConstants.LEFT);
        controls.setForeground(themeColor);
        controls.setBorder(new EmptyBorder(50,0,0,0));

        textArea.add(controls);
        sideInfo.add(textArea,BorderLayout.CENTER);
    }


    // ------------------------------------------ GUI UPDATING AND CHANGES ------------------------------------------

    /**
     * Method reevaluating all changeable components in the GUI.
     * This method is called everytime a change happens in the playingfield, the current tetrimino, and the stats.
     */
    public void updatePlayfield(){
        //Display the grid
        for (int i = 0; i <= 19; i++) {
            for (int j = 0; j <= 9; j++) {
                tiles[i][j].setBackground(Grid[i][j].getBackground());
            }
        }

        //Display the saved tetrimino grid
        for (int i = 0; i <= 3; i++) {
            for (int j = 0; j <= 3; j++) {
                savedTiles[i][j].setBackground(Color.DARK_GRAY.darker());
            }
        }

        //Display the saved tetrimino
        if(game.getSavedTetrimino() != null) {
            for (GridElement g : game.getSavedTetrimino().getPieces()) {
                savedTiles[g.y() - 19][g.x() - 3].setBackground(g.getBackground());
            }
        }

        //Display ghost pieces of current tetrimino
        for(GridElement g : playfield.calculateGhost()){
            if(g.y() < 20) {
                tiles[g.y()][g.x()].setBackground(Color.GRAY.darker());
            }
        }

        //Display current tetrimino
        ArrayList<GridElement> pieces = playfield.getCurrentTetrimino().getPieces();
        for(GridElement g : pieces){
            if(g.y()<20){
                tiles[g.y()][g.x()].setBackground(g.getBackground());
            }
        }

        //Display the stats text
        stats.setText(generateStatsText());
    }

    /**
     * Method to pause or resume the game.
     */
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

    /**
     * Opens a color changer dialog which allows the user to change the theme color.
     */
    private void changeTheme(){
        if(!paused){
            pauseResume();
        }

        Color newColor = JColorChooser.showDialog(mainFrame, "Theme Color Chooser", Color.GREEN);
        if(newColor != null){
            themeColor = newColor;
        }

        optionsLabel.setForeground(themeColor);
        pauseResumeButton.setForeground(themeColor);
        newGame.setForeground(themeColor);
        themeButton.setForeground(themeColor);
        exitButton.setForeground(themeColor);
        savedTetriminoLabel.setForeground(themeColor);
        savedTetrimino.setBorder(BorderFactory.createLineBorder(themeColor));
        stats.setForeground(themeColor);
        controls.setForeground(themeColor);
        
    }


    /**
     * Makes the playingfield dark.
     * This method is called when the game is lost.
     */
    public void gameLostScreen(){
        for (int i = 0; i <= 19; i++) {
            for (int j = 0; j <= 9; j++) {
                Grid[i][j].setBackground(Grid[i][j].getBackground().darker().darker().darker());
            }
        }
    }

    // ------------------------------------------ HELPER METHODS ------------------------------------------

    /**
     * Helper method to create the stats text in the side area.
     * @return The string containing the updated stats.
     */
    private String generateStatsText(){
        return "<html>" +
                "<h1>Stats</h1> " +
                "<p>" +
                "Time : " + game.getTimePassed() +"<br> " +
                "Score : " + game.getScore() +"<br>" +
                "</p>" +
                "</html>";
    }

    /**
     * Helper method to create a new empty Grid.
     * @param rows The amount of rows in the grid.
     * @param cols The amount of columns in the grid.
     * @param background The color of the GridElements.
     * @return The Grid with the specified values.
     */
    private JPanel[][] newEmptyGrid(int rows, int cols, Color background){
        JPanel[][] newGrid = new JPanel[rows+1][cols+1];
        for (int i = rows; i >= 0; i--) {
            for (int j = 0; j <= cols; j++) {
                newGrid[i][j] = new JPanel();
                newGrid[i][j].setBackground(background);
                newGrid[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            }
        }
        return newGrid;
    }

}
