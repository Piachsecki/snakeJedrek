package com.platformy_programistyczne;

import com.platformy_programistyczne.Frame.GameBoard;
import com.platformy_programistyczne.Frame.WelcomeFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Main frame of the game - frame with gameBoard
 */
public class Snake extends JFrame {

    public Snake() {
        initWindow();
    }

    /**
     * Method to set SnakeFrame properties
     */
    public void initWindow() {
        add(new GameBoard());
        setResizable(false);
        pack();
        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame snake = new WelcomeFrame();
            snake.setVisible(true);
        });
    }
}
