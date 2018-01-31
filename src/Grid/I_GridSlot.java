package Grid;

import java.awt.geom.Point2D;

/**
 * Created by jacks on 2017-12-16.
 */

//An interface for an individual slot of each grid. Contains three public abstract methods.
public interface I_GridSlot {

    /**
     * @return the coordinate of the top-left corner of the cell
     */
    Point2D getCoord();

    /**
     * @return the length of the grid
     */
    double getSize();

    /**
     *
     * @return the state of if the cursor is hovered above a gridslot
     */
    boolean isMouseOnTop();

}
