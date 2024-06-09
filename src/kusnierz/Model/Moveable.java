/**
 * The Moveable interface should be implemented by any class whose instances are intended to be moveable on the game board.
 * The class that implements this interface must define the move method, which handles the logic for moving the object.
 */
package kusnierz.Model;

public interface Moveable {
    /**
     * Moves the object to a new position. The specific movement logic should be defined by the implementing class.
     */
    void move();
}
