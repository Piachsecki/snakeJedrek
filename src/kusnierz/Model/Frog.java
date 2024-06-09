package kusnierz.Model;

import static kusnierz.Frame.Constants.*;

public class Frog extends ObjectOnBoard implements Moveable {

    /**
     * Gets the current X coordinate of the frog.
     *
     * @return the current X coordinate of the frog
     */
    public int getFrogX() {
        return x;
    }
    /**
     * Sets the X coordinate of the frog.
     *
     * @param frogX the new X coordinate of the frog
     */
    public void setFrogX(int frogX) {
        this.x = frogX;
    }
    /**
     * Gets the current Y coordinate of the frog.
     *
     * @return the current Y coordinate of the frog
     */
    public int getFrogY() {
        return y;
    }

    /**
     * Sets the Y coordinate of the frog.
     *
     * @param frogY the new Y coordinate of the frog
     */
    public void setFrogY(int frogY) {
        this.y = frogY;
    }
    /**
     * Moves the frog to a new random position on the game board. The frog moves in both X and Y directions
     * by a random amount, constrained by the game board's width and height. The frog's new position is
     * calculated based on random values, ensuring it stays within the board boundaries.
     */
    @Override
    public void move() {
        int r = 1 - (int) (Math.random() * 3);
        int moveX = r * POINT_SIZE;

        if ((this.getFrogX() + moveX) < FIELD_WIDTH && (this.getFrogX() + moveX) > 0) {
            this.setFrogX(this.getFrogX() + moveX);
        }

        r = 1 - (int) (Math.random() * 3);
        int moveY = r * POINT_SIZE;
        if ((this.getFrogY() + moveY) < FIELD_HEIGHT && (this.getFrogY() + moveY) > 0) {
            this.setFrogY(this.getFrogY() + moveY);
        }
    }
}
