package kusnierz.Model;

import static kusnierz.Frame.Constants.POINT_AMOUNTS;


public abstract class DefaultSnake {
    /**
     * Indicates if the snake is moving to the left.
     */
    public boolean leftDirection = false;

    /**
     * Indicates if the snake is moving to the right.
     */
    public boolean rightDirection = true;

    /**
     * Indicates if the snake is moving upwards.
     */
    public boolean upDirection = false;

    /**
     * Indicates if the snake is moving downwards.
     */
    public boolean downDirection = false;

    /**
     * The X coordinates of the snake's segments.
     */
    public final int[] x = new int[POINT_AMOUNTS];

    /**
     * The Y coordinates of the snake's segments.
     */
    public final int[] y = new int[POINT_AMOUNTS];

    /**
     * The number of segments (dots) that make up the snake.
     */
    public int dots;

    /**
     * Checks if the snake is moving to the left.
     *
     * @return true if the snake is moving to the left, false otherwise
     */
    public boolean isLeftDirection() {
        return leftDirection;
    }

    /**
     * Sets the snake's left movement direction.
     *
     * @param leftDirection the new left movement direction
     */
    public void setLeftDirection(boolean leftDirection) {
        this.leftDirection = leftDirection;
    }

    /**
     * Checks if the snake is moving to the right.
     *
     * @return true if the snake is moving to the right, false otherwise
     */
    public boolean isRightDirection() {
        return rightDirection;
    }

    /**
     * Sets the snake's right movement direction.
     *
     * @param rightDirection the new right movement direction
     */
    public void setRightDirection(boolean rightDirection) {
        this.rightDirection = rightDirection;
    }

    /**
     * Checks if the snake is moving upwards.
     *
     * @return true if the snake is moving upwards, false otherwise
     */
    public boolean isUpDirection() {
        return upDirection;
    }

    /**
     * Sets the snake's upward movement direction.
     *
     * @param upDirection the new upward movement direction
     */
    public void setUpDirection(boolean upDirection) {
        this.upDirection = upDirection;
    }

    /**
     * Checks if the snake is moving downwards.
     *
     * @return true if the snake is moving downwards, false otherwise
     */
    public boolean isDownDirection() {
        return downDirection;
    }

    /**
     * Sets the snake's downward movement direction.
     *
     * @param downDirection the new downward movement direction
     */
    public void setDownDirection(boolean downDirection) {
        this.downDirection = downDirection;
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
     * Gets the number of segments (dots) that make up the snake.
     *
     * @return the number of segments (dots)
     */
    public int getDots() {
        return dots;
    }

    /**
     * Sets the number of segments (dots) that make up the snake.
     *
     * @param dots the new number of segments (dots)
     */
    public void setDots(int dots) {
        this.dots = dots;
    }
}
