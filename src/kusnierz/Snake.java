package kusnierz;
/**
 * The Snake class is the main entry point for the Snake game application. It extends the JFrame class
 * and sets up the main window for the game, including initializing the game board and configuring the window properties.
 *
 * <p>The main method starts the application by displaying the welcome frame.</p>
 *
 * @see com.Frame.GameBoard
 * @see com.Frame.WelcomeFrame
 *
 * Author: Jedrzej Kusnierz
 * Date: 09.06
 */


import kusnierz.Frame.Board;
import kusnierz.Frame.WelcomeFrame;

import javax.swing.*;
import java.awt.*;

public class Snake extends JFrame {

    /**
     * Constructs a Snake object and initializes the game window.
     */
    public Snake() {
        initWindow();
    }

    /**
     * Initializes the game window by adding the game board, setting the window properties,
     * and configuring it to be non-resizable.
     */
    public void initWindow() {
        add(new Board());
        setResizable(false);
        pack();
        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * The main method is the entry point of the application. It schedules a job for the event dispatch thread,
     * creating and showing the welcome frame.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame snake = new WelcomeFrame();
            snake.setVisible(true);
        });
    }
}
