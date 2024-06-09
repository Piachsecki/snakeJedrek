/**
 * The ObjectOnBoard class serves as an abstract base class for any object that exists on the game board.
 * It contains protected fields for the X and Y coordinates of the object.
 *
 * <p>Subclasses are expected to extend this class and utilize the X and Y coordinates for positioning on the game board.</p>
 */
package kusnierz.Model;

public abstract class ObjectOnBoard {
    /**
     * The X coordinate of the object on the game board.
     */
    protected int x;

    /**
     * The Y coordinate of the object on the game board.
     */
    protected int y;
}
