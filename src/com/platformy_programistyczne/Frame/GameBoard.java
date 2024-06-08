package com.platformy_programistyczne.Frame;

import com.platformy_programistyczne.Frame.SetResultFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

/**
 * It is basically a main class of the application, because
 * it contains gameboard and models of an object that are
 * take part in the game - player (snake), fruitGenerator, frog
 */
public class GameBoard extends JPanel implements ActionListener {

    /**
     * There are gameboard params such as
     * width, height size of the one point
     * amounts of the points and rand_pos paramether
     * to make a random location of an object
     */
    private final int FIELD_WIDTH = 300;
    private final int FIELD_HEIGHT = 300;
    private final int POINT_SIZE = 10;
    private final int POINT_AMOUNTS = 900;
    private final int RAND_POS = 29;
    private final int DELAY = 140;

    /**
     * There are x,y arrays to store integers
     */
    private final int x[] = new int[POINT_AMOUNTS];
    private final int y[] = new int[POINT_AMOUNTS];

    /**
     * Parameters to specify fruit and frog location
     */
    private int dots;
    private int fruitX, fruitY;
    private int frogX, frogY;



    /**
     * Parameters to specify move direction and if
     * game is ended or continued
     */
    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame = true;

    private Timer timer;
    private Image ball, fruit, head, frog;

    public GameBoard() {
        buildBoard();
    }

    /**
     * Method to build board - set JPanel propetries and
     * set keyListener to use inication from keyboard
     */
    public void buildBoard() {
        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);

        setPreferredSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));
        loadImages();
        gameInitialize();
    }

    /**
     * Method to load images from the file .png
     */
    public void loadImages() {
        ImageIcon _ball = new ImageIcon("src/images/ball.png");
        ball = _ball.getImage();

        ImageIcon _fruit = new ImageIcon("src/images/fruit.png");
        fruit = _fruit.getImage();

        ImageIcon _head = new ImageIcon("src/images/head.png");
        head = _head.getImage();

        ImageIcon _frog = new ImageIcon("src/images/frog.png");
        frog = _frog.getImage();
    }

    /**
     * Method to initialize the game - fulfill x,y arrays
     * and locate fruit and frog on the gameboard, at the
     * end start the timer
     */
    public void gameInitialize() {
        dots = 3;
        for (int i = 0; i < dots; ++i) {
            x[i] = 50 - i * 10;
            y[i] = 50;
        }

        locateFruit();
        locateFrog();

        timer = new Timer(DELAY, this);
        timer.start();
    }



    /**
     * Method to paint components using Graphics object
     * @param g - instance of Graphics class
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    /**
     * Method to draw Images if game is still countinued
     * @param g - instance of Graphics class
     */
    public void doDrawing(Graphics g)  {
        if (inGame) {
            g.drawImage(fruit, fruitX, fruitY, this);
            g.drawImage(frog, frogX, frogY, this);

            for (int i = 0; i < dots; ++i) {
                if (i == 0) {
                    g.drawImage(head, x[i], y[i], this);
                } else {
                    g.drawImage(ball, x[i], y[i], this);
                }
            }
            Toolkit.getDefaultToolkit().sync();
        } else {
            gameOver(g);
        }
    }

    /**
     * Method to draw finish message after the end of the game
     * and create a new SetResultFrame to write and save result
     * @param g - instance of Graphics class
     */
    public void gameOver(Graphics g)  {
        final String message = "Game is Over";
        Font messageFont = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(messageFont);

        g.setColor(Color.white);
        g.setFont(messageFont);
        g.drawString(message, (FIELD_WIDTH - metr.stringWidth((message)))/ 2, FIELD_HEIGHT / 2);

        SetResultFrame addResult = new SetResultFrame(dots - 3);
        addResult.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addResult.setVisible(true);
    }

    /**
     * Method to check if snake ate a fruit
     * it means the value of the head of the snake
     * is the same as fruit's x,y
     */
    public void checkFruit() {
        if ((x[0] == fruitX) && (y[0] == fruitY)) {
            ++dots;
            locateFruit();
        }
    }

    /**
     * Method to locate fruit randomnly
     */
    public void locateFruit() {
        int r = (int) (Math.random() * RAND_POS);
        fruitX = r * POINT_SIZE;
        r = (int) (Math.random() * RAND_POS);
        fruitY = r * POINT_SIZE;
    }

    /**
     * Method to locate frog randomnly
     */
    public void locateFrog() {
        int r = (int) (Math.random() * RAND_POS);
        frogX = r * POINT_SIZE;
        r = (int) (Math.random() * RAND_POS);
        frogY = r * POINT_SIZE;
    }

    /**
     * Same as checkFruit - method to check if
     * snake ate frog
     */
    public void checkFrog() {
        if ((x[0] == frogX) && (y[0] == frogY)) {
            ++dots;
            locateFrog();
        }
    }

    /**
     * method to make a move by frog
     */
    public void moveFrog() {
        int r = 1 - (int) (Math.random() * 3);
        int moveX = r * POINT_SIZE;

        if ((frogX + moveX) < FIELD_WIDTH && (frogX + moveX) > 0) {
            frogX += moveX;
        }

        r = 1 - (int) (Math.random() * 3);
        int moveY = r * POINT_SIZE;
        if ((frogY + moveY) < FIELD_HEIGHT && (frogY + moveY) > 0) {
            frogY += moveY;
        }
    }

    /**
     * Class to extends KeyAdapter to use
     * inication from the keyboard to make
     * snake's move by player
     */
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
                leftDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
                rightDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_UP) && (!downDirection)) {
                upDirection = true;
                rightDirection = false;
                leftDirection = false;
            }

            if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
                downDirection = true;
                rightDirection = false;
                leftDirection = false;
            }
        }
    }

    /**
     * Main method make an events service - if game is not
     * ended then it is making a 3 threads for snake, frog and
     * fruit that all of this object can run simultaneously
     * @param e - instance of an class ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            Thread fruitThread = new Thread(this::checkFruit);
            Thread playerThreat = new Thread(() -> {
                checkIfCollision();
                makeMove();
            });
            Thread frogThread = new Thread(() -> {
                checkFrog();
                moveFrog();
            });
            fruitThread.start();
            playerThreat.start();
            frogThread.start();
            try {
                fruitThread.join();
                playerThreat.join();
                frogThread.join();
            } catch (InterruptedException interruptedException) {
                System.out.println(interruptedException.getMessage());
            }
        }
        repaint();
    }

    /**
     * Method to check if collision advanced
     */
    private void checkIfCollision() {
        for (int i = dots; i > 0; --i) {
            if ((i > 4 ) && (x[0] == x[i]) && (y[0] == y[i])) {
                inGame = false;
            }
        }
        if ((y[0] >= FIELD_HEIGHT) || (y[0] < 0) || (x[0] >= FIELD_WIDTH) || (x[0] < 0)) {
            inGame = false;
        }
        if (!inGame) {
            timer.stop();
        }
    }

    /**
     * Method to make a move by player (snake)
     */
    public void makeMove() {
        for (int i = dots; i > 0; --i) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        if (leftDirection) x[0] -= POINT_SIZE;
        if (rightDirection) x[0] += POINT_SIZE;
        if (upDirection) y[0] -= POINT_SIZE;
        if (downDirection) y[0] += POINT_SIZE;
    }
}
