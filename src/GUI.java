import java.awt.*;
import javax.swing.*;

public class GUI {
    private JFrame mainFrame;

    public GUI(){
        createGUI();
    }

    private void createGUI(){
        mainFrame = new JFrame("Tetris");


        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    public static void createGameGUI(){
        new GUI();
    }

}
