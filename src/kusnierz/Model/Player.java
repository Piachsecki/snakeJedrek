/**
 * The Player class represents a player-controlled snake in the game. It extends the DefaultSnake class
 * and implements the Moveable interface, providing specific implementations for initializing and moving the snake.
 *
 * <p>This class initializes the snake with a default length of 3 segments and positions it at a starting location.
 * The move method updates the snake's position based on the current direction.</p>
 *
 * @see kusnierz.Frame.Board
 * @see DefaultSnake
 * @see Moveable
 */
package kusnierz.Model;

import static kusnierz.Frame.Constants.POINT_SIZE;

public class Player extends DefaultSnake implements Moveable {

    /**
     * Constructs a Player object and initializes the snake's starting position and direction.
     */
    public Player() {
        this.dots = 3;
        for (int i = 0; i < dots; ++i) {
            x[i] = 50 - i * 10;
            y[i] = 50;
        }
    }

    /**
     * Moves the snake by updating the position of its segments based on the current direction.
     * The position of each segment is updated to follow the one before it.
     */
    @Override
    public void move() {
        for (int i = this.dots; i > 0; --i) {
            this.getX()[i] = this.getX()[i - 1];
            this.getY()[i] = this.getY()[i - 1];
        }

        if (this.leftDirection) this.getX()[0] -= POINT_SIZE;
        if (this.rightDirection) this.getX()[0] += POINT_SIZE;
        if (this.upDirection) this.getY()[0] -= POINT_SIZE;
        if (this.downDirection) this.getY()[0] += POINT_SIZE;
    }
}
