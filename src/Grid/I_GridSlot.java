package Grid;

import java.awt.geom.Point2D;

/**
 * Created by jacks on 2017-12-16.
 */

//An interface for an individual slot of each grid. Contains three public abstract methods.
public interface I_GridSlot {

    Point2D getCoord();

    double getSize();

    boolean isMouseOnTop();

}
