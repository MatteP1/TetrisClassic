import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class GUI {
    private JFrame mainFrame;
    private Container contentPane;
    private JPanel gameArea;

    public GUI(){
        createGUI();
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
        return new JPanel();
    }

    private void createFile(){
        JMenuBar menuBar = new JMenuBar();
        mainFrame.setJMenuBar(menuBar);

        JMenu test = new JMenu("Test");
        JMenuItem testItem = new JMenuItem("Test");
        test.add(testItem);
        menuBar.add(test);
    }


    public static void createGameGUI(){
        new GUI();
    }

}
