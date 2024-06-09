/**
 * The Fruit class represents a fruit object on the game board. It extends the ObjectOnBoard class
 * and provides methods to get and set the fruit's X and Y coordinates.
 *
 * <p>This class is used to define the position of a fruit that the snake aims to consume in the game.</p>
 *
 * @see ObjectOnBoard
 */
package kusnierz.Model;

public class Fruit extends ObjectOnBoard {

    /**
     * Gets the current X coordinate of the fruit.
     *
     * @return the current X coordinate of the fruit
     */
    public int getFruitX() {
        return x;
    }

    /**
     * Sets the X coordinate of the fruit.
     *
     * @param fruitX the new X coordinate of the fruit
     */
    public void setFruitX(int fruitX) {
        this.x = fruitX;
    }

    /**
     * Gets the current Y coordinate of the fruit.
     *
     * @return the current Y coordinate of the fruit
     */
    public int getFruitY() {
        return y;
    }

    /**
     * Sets the Y coordinate of the fruit.
     *
     * @param fruitY the new Y coordinate of the fruit
     */
    public void setFruitY(int fruitY) {
        this.y = fruitY;
    }
}
