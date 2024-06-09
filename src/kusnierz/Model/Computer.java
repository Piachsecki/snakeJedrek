package kusnierz.Model;

import static kusnierz.Frame.Constants.POINT_SIZE;

public class Computer extends DefaultSnake {
    private boolean stopped;

    /**
     * Constructs a Computer object and initializes the snake's starting position and direction.
     */
    public Computer() {
        init();
    }

    /**
     * Initializes the snake with a default length of 3 segments, positioned horizontally on the game board.
     * The initial direction is set to move right.
     */
    public void init() {
        dots = 3;
        for (int i = 0; i < dots; i++) {
            x[i] = 200 - i * POINT_SIZE;
            y[i] = 10;
        }
        leftDirection = false;
        rightDirection = true;
        upDirection = false;
        downDirection = false;
        stopped = false;
    }

    /**
     * Gets the X coordinates of the snake's segments.
     *
     * @return an array of X coordinates of the snake's segments
     */
    public int[] getX() {
        return x;
    }

    /**
     * Gets the Y coordinates of the snake's segments.
     *
     * @return an array of Y coordinates of the snake's segments
     */
    public int[] getY() {
        return y;
    }

    /**
     * Moves the snake by updating the position of its segments based on the current direction.
     * The movement is halted if the snake is stopped.
     */
    public void move() {
        if (!stopped) {
            for (int i = dots; i > 0; i--) {
                x[i] = x[i - 1];
                y[i] = y[i - 1];
            }

            if (leftDirection) {
                x[0] -= POINT_SIZE;
            }

            if (rightDirection) {
                x[0] += POINT_SIZE;
            }

            if (upDirection) {
                y[0] -= POINT_SIZE;
            }

            if (downDirection) {
                y[0] += POINT_SIZE;
            }
        }
    }

    /**
     * Updates the snake's direction to move towards the given fruit coordinates.
     *
     * @param fruitX the X coordinate of the fruit
     * @param fruitY the Y coordinate of the fruit
     */
    public void updateDirection(int fruitX, int fruitY) {
        if (x[0] < fruitX) {
            rightDirection = true;
            leftDirection = false;
            upDirection = false;
            downDirection = false;
        } else if (x[0] > fruitX) {
            leftDirection = true;
            rightDirection = false;
            upDirection = false;
            downDirection = false;
        } else if (y[0] < fruitY) {
            downDirection = true;
            upDirection = false;
            leftDirection = false;
            rightDirection = false;
        } else if (y[0] > fruitY) {
            upDirection = true;
            downDirection = false;
            leftDirection = false;
            rightDirection = false;
        }
    }

    /**
     * Reverses the snake's current direction.
     */
    public void reverseDirection() {
        if (leftDirection) {
            leftDirection = false;
            rightDirection = true;
        } else if (rightDirection) {
            rightDirection = false;
            leftDirection = true;
        } else if (upDirection) {
            upDirection = false;
            downDirection = true;
        } else if (downDirection) {
            downDirection = false;
            upDirection = true;
        }
    }

    /**
     * Checks if the snake is stopped.
     *
     * @return true if the snake is stopped, false otherwise
     */
    public boolean isStopped() {
        return stopped;
    }

    /**
     * Sets the snake's stopped state.
     *
     * @param stopped the new stopped state
     */
    public void setStopped(boolean stopped) {
        this.stopped = stopped;
    }
}
