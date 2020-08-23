/**
 * This interface can be implemented by any class that can move.
 *
 * @author Arman Sidhu
 * @since 2020-07-29
 */
public interface Movable {

    /**
     * Moves the object in a random x and y-direction from 0 up to a maximum of step (not
     * included). There is a 50% chance that the movement is backwards.
     *
     * @param step an int representing the maximum x and y coordinate values for the movement
     */
    public void move(int step);

}