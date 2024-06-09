/**
 * The WelcomeFrame class represents the welcome frame of the Snake game application.
 * It extends the JFrame class and implements the ActionListener interface to handle button clicks.
 *
 * <p>The welcome frame contains buttons to start the game and check results.</p>
 *
 * @see kusnierz.Snake
 * @see kusnierz.Frame.ResultFrame
 */
package kusnierz.Frame;

import kusnierz.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class WelcomeFrame extends JFrame implements ActionListener {
    private JButton checkResultsBox;
    private JButton startGameButton;

    /**
     * Constructs a WelcomeFrame object and initializes the welcome frame.
     */
    public WelcomeFrame() {
        super("Welcome Frame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLayout(new GridLayout(2, 1));
        setLocationRelativeTo(null);

        checkResultsBox.addActionListener(this);
        startGameButton.addActionListener(this);
        add(startGameButton);
        add(checkResultsBox);
        setVisible(true);
    }

    /**
     * Handles button click events.
     *
     * @param e the ActionEvent object representing the button click event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == checkResultsBox) {
            try {
                dispose();
                new ResultFrame().setVisible(true);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        } else if (source == startGameButton) {
            dispose();
            new Snake().setVisible(true);
        }
    }
}
