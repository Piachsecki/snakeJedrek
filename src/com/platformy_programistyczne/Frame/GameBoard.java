package com.platformy_programistyczne.Frame;

import com.platformy_programistyczne.Model.Computer;
import com.platformy_programistyczne.Model.Frog;
import com.platformy_programistyczne.Model.Fruit;
import com.platformy_programistyczne.Model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GameBoard extends JPanel implements ActionListener {
    public static final int FIELD_WIDTH = 300;
    public static final int FIELD_HEIGHT = 300;
    public static final int POINT_SIZE = 10;
    public static final int POINT_AMOUNTS = 900;
    public static final int RAND_POS = 29;
    public final int DELAY = 140;
    public boolean inGame = true;
    private Player player;
    private Fruit fruitS;
    private Frog frogS;
    private ArrayList<Computer> computers;
    private int obstacleX[] = new int[4]; // Array to store X positions of each rectangle
    private int obstacleY[] = new int[4]; // Array to store Y positions of each rectangle
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
        this.player = new Player();

        this.computers = new ArrayList<>();
        computers.add(new Computer());


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

            for (int i = 0; i < player.dots; ++i) {
                if (i == 0) {
                    g.drawImage(head, player.getX()[i], player.getY()[i], this);
                } else {
                    g.drawImage(ball, player.getX()[i], player.getY()[i], this);
                }
            }

            for (Computer computer : computers) {
                for (int i = 0; i < computer.dots; ++i) {
                    if (i == 0) {
                        g.drawImage(head, computer.getX()[i], computer.getY()[i], this);
                    } else {
                        g.drawImage(ball, computer.getX()[i], computer.getY()[i], this);
                    }
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

        SetResultFrame addResult = new SetResultFrame(player.dots - 3);
        addResult.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addResult.setVisible(true);
    }

    public void checkFruit() {
        if ((player.getX()[0] == fruitS.getFruitX()) && (player.getY()[0] == fruitS.getFruitY())) {
            ++player.dots;
            locateFruit();
        }

        for (Computer computer : computers) {
            if ((computer.getX()[0] == frogS.getFrogX()) && (computer.getY()[0] == frogS.getFrogY())) {
                ++computer.dots;
                locateFrog();
            }
        }
    }

    public void checkFrog() {
        if ((player.getX()[0] == frogS.getFrogX()) && (player.getY()[0] == frogS.getFrogY())) {
            ++player.dots;
            locateFrog();
        }
        for (Computer computer : computers) {
            if ((computer.getX()[0] == frogS.getFrogX()) && (computer.getY()[0] == frogS.getFrogY())) {
                ++computer.dots;
                locateFrog();
            }
        }


    }

    public void moveFrog() {
        int r = 1 - (int) (Math.random() * 3);
        int moveX = r * POINT_SIZE;

        if ((frogS.getFrogX() + moveX) < FIELD_WIDTH && (frogS.getFrogX() + moveX) > 0) {
            frogS.setFrogX(frogS.getFrogX() + moveX);
        }

        r = 1 - (int) (Math.random() * 3);
        int moveY = r * POINT_SIZE;
        if ((frogS.getFrogY() + moveY) < FIELD_HEIGHT && (frogS.getFrogY() + moveY) > 0) {
            frogS.setFrogY(frogS.getFrogY() + moveY);
        }
    }

    private void checkIfCollision() {
        for (int i = player.dots; i > 0; --i) {
            if ((i > 4) && (player.getX()[0] == player.getX()[i]) && (player.getY()[0] == player.getY()[i])) {
                inGame = false;
            }
        }
        if ((player.getY()[0] >= FIELD_HEIGHT) || (player.getY()[0] < 0) || (player.getX()[0] >= FIELD_WIDTH) || (player.getX()[0] < 0)) {
            inGame = false;
        }
        for (int i = 0; i < 4; i++) {
            if ((player.getX()[0] == obstacleX[i]) && (player.getY()[0] == obstacleY[i])) { // Check if the snake hits any part of the obstacle line
                inGame = false;
                break; // Exit loop early if collision detected
            }
        }
        // Check collision between Player and Computer
        for (Computer computer : computers) {
            for (int i = computer.dots; i > 0; --i) {
                if ((computer.getX()[0] == player.getX()[0]) && (computer.getY()[0] == player.getY()[0])) {
                    inGame = false;
                }
            }
        }

        if (!inGame) {
            timer.stop();
        }

    }

    private void checkComputerCollision() {
        // Check collision between Computers (optional)
        for (int i = 0; i < computers.size(); ++i) {
            Computer c1 = computers.get(i);
            for (int j = i + 1; j < computers.size(); ++j) {
                Computer c2 = computers.get(j);
                if ((c1.getX()[0] == c2.getX()[0]) && (c1.getY()[0] == c2.getY()[0])) {
                    c1.setStopped(true);
                    c2.setStopped(true);
                }
            }
        }

        // Check collision with walls for Computers
        for (Computer computer : computers) {
            if ((computer.getY()[0] == FIELD_HEIGHT - 1) || (computer.getY()[0] < 0) || (computer.getX()[0] == FIELD_WIDTH - 1) || (computer.getX()[0] < 0)) {
                computer.reverseDirection();
            }
        }

        // Check collision with obstacles for Computers
        for (Computer computer : computers) {
            for (int i = 0; i < 4; i++) {
                if ((computer.getX()[0] == obstacleX[i]) && (computer.getY()[0] == obstacleY[i])) {
                    computer.setStopped(true);
                    break;
                }
            }
        }
    }


    public void makeMove() {
        for (int i = player.dots; i > 0; --i) {
            player.getX()[i] = player.getX()[i - 1];
            player.getY()[i] = player.getY()[i - 1];
        }

        if (player.leftDirection) player.getX()[0] -= POINT_SIZE;
        if (player.rightDirection) player.getX()[0] += POINT_SIZE;
        if (player.upDirection) player.getY()[0] -= POINT_SIZE;
        if (player.downDirection) player.getY()[0] += POINT_SIZE;
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
            Thread computerThread = new Thread(() -> {
                checkComputerCollision();
                for (Computer computer : computers) {
                    computer.updateDirection(fruitS.getFruitX(), fruitS.getFruitY());
                    computer.move();
                }
            });

            fruitThread.start();
            playerThread.start();
            frogThread.start();
            computerThread.start();
            try {
                fruitThread.join();
                playerThread.join();
                frogThread.join();
                computerThread.join();

            } catch (InterruptedException interruptedException) {
                System.out.println(interruptedException.getMessage());
            }
        }
        repaint();
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!player.rightDirection)) {
                player.leftDirection = true;
                player.upDirection = false;
                player.downDirection = false;
            }

            if ((key == KeyEvent.VK_RIGHT) && (!player.leftDirection)) {
                player.rightDirection = true;
                player.upDirection = false;
                player.downDirection = false;
            }

            if ((key == KeyEvent.VK_UP) && (!player.downDirection)) {
                player.upDirection = true;
                player.rightDirection = false;
                player.leftDirection = false;
            }

            if ((key == KeyEvent.VK_DOWN) && (!player.upDirection)) {
                player.downDirection = true;
                player.rightDirection = false;
                player.leftDirection = false;
            }
        }
    }
}
