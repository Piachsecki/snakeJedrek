package com.platformy_programistyczne.Frame;

import com.platformy_programistyczne.Frame.SetResultFrame;
import com.platformy_programistyczne.Model.Frog;
import com.platformy_programistyczne.Model.Fruit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class GameBoard extends JPanel implements ActionListener {

    public static final int FIELD_WIDTH = 300;
    public static final int FIELD_HEIGHT = 300;
    public static final int POINT_SIZE = 10;
    public static final int POINT_AMOUNTS = 900;
    public static final int RAND_POS = 29;
    public final int DELAY = 140;

    private final int x[] = new int[POINT_AMOUNTS];
    private final int y[] = new int[POINT_AMOUNTS];

    private int dots;
    private Fruit fruitS;
    private Frog frogS;
    private int obstacleX[] = new int[4]; // Array to store X positions of each rectangle
    private int obstacleY[] = new int[4]; // Array to store Y positions of each rectangle


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

    public void buildBoard() {
        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);

        setPreferredSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));
        loadImages();
        gameInitialize();
    }

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

    public void gameInitialize() {
        dots = 3;
        for (int i = 0; i < dots; ++i) {
            x[i] = 50 - i * 10;
            y[i] = 50;
        }

        locateFruit();
        locateFrog();
        locateObstacle(); // Locate the obstacle

        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void locateFruit() {
        this.fruitS = new Fruit();

        int r = (int) (Math.random() * RAND_POS);
        this.fruitS.setFruitX(r * POINT_SIZE);
        r = (int) (Math.random() * RAND_POS);
        this.fruitS.setFruitY(r * POINT_SIZE);

    }

    public void locateFrog() {
        this.frogS = new Frog();
        int r = (int) (Math.random() * RAND_POS);
        this.frogS.setFrogX(r * POINT_SIZE);

        r = (int) (Math.random() * RAND_POS);
        this.frogS.setFrogY(r * POINT_SIZE);
    }

    public void locateObstacle() {
        int r = (int) (Math.random() * RAND_POS);
        obstacleX[0] = r * POINT_SIZE;
        obstacleY[0] = r * POINT_SIZE;

        for (int i = 1; i < 4; i++) {
            obstacleX[i] = obstacleX[i - 1] + POINT_SIZE; // Place each rectangle next to the previous one
            obstacleY[i] = obstacleY[0]; // Align all rectangles at the same Y position
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    public void doDrawing(Graphics g) {
        if (inGame) {
            g.drawImage(fruit, fruitS.getFruitX(), fruitS.getFruitY(), this);
            g.drawImage(frog, frogS.getFrogX(), frogS.getFrogY(), this);
            g.setColor(Color.red); // Use a different color or image for the obstacle
            for (int i = 0; i < 4; i++) {
                g.fillRect(obstacleX[i], obstacleY[i], POINT_SIZE, POINT_SIZE); // Draw each rectangle
            }

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

    public void gameOver(Graphics g) {
        final String message = "Game is Over";
        Font messageFont = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(messageFont);

        g.setColor(Color.white);
        g.setFont(messageFont);
        g.drawString(message, (FIELD_WIDTH - metr.stringWidth((message))) / 2, FIELD_HEIGHT / 2);

        SetResultFrame addResult = new SetResultFrame(dots - 3);
        addResult.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addResult.setVisible(true);
    }

    public void checkFruit() {
        if ((x[0] == fruitS.getFruitX()) && (y[0] == fruitS.getFruitY())) {
            ++dots;
            locateFruit();
        }
    }

    public void checkFrog() {
        if ((x[0] == frogS.getFrogX()) && (y[0] == frogS.getFrogY())) {
            ++dots;
            locateFrog();
        }
    }

    public void moveFrog() {
        int r = 1 - (int) (Math.random() * 3);
        int moveX = r * POINT_SIZE;

        if ((frogS.getFrogX() + moveX) < FIELD_WIDTH && (frogS.getFrogX() + moveX) > 0) {
            frogS.setFrogX(frogS.getFrogX()+moveX);
        }

        r = 1 - (int) (Math.random() * 3);
        int moveY = r * POINT_SIZE;
        if ((frogS.getFrogY() + moveY) < FIELD_HEIGHT && (frogS.getFrogY() + moveY) > 0) {
            frogS.setFrogY(frogS.getFrogY()+moveY);
        }
    }

    private void checkIfCollision() {
        for (int i = dots; i > 0; --i) {
            if ((i > 4) && (x[0] == x[i]) && (y[0] == y[i])) {
                inGame = false;
            }
        }
        if ((y[0] >= FIELD_HEIGHT) || (y[0] < 0) || (x[0] >= FIELD_WIDTH) || (x[0] < 0)) {
            inGame = false;
        }
        for (int i = 0; i < 4; i++) {
            if ((x[0] == obstacleX[i]) && (y[0] == obstacleY[i])) { // Check if the snake hits any part of the obstacle line
                inGame = false;
                break; // Exit loop early if collision detected
            }
        }
        if (!inGame) {
            timer.stop();
        }
    }

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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            Thread fruitThread = new Thread(this::checkFruit);
            Thread playerThread = new Thread(() -> {
                checkIfCollision();
                makeMove();
            });
            Thread frogThread = new Thread(() -> {
                checkFrog();
                moveFrog();
            });
            fruitThread.start();
            playerThread.start();
            frogThread.start();
            try {
                fruitThread.join();
                playerThread.join();
                frogThread.join();
            } catch (InterruptedException interruptedException) {
                System.out.println(interruptedException.getMessage());
            }
        }
        repaint();
    }
}
