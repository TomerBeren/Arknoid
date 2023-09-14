// 209625946 Tomer Berenstein
package physics;

import geometry.Point;
import geometry.Rectangle;

/**
 * The Collidable interface represents objects that can be collided with.
 *
 * @author Tomer Berenstein tomerbrotem@gmail.com
 * @version 19.0.2
 * @since 2023-01-17
 */
public interface Collidable {
    /**
     * Returns the "collision shape" of the object.
     *
     * @return The collision shape represented as a Rectangle object
     */
    Rectangle getCollisionRectangle();

    /**
     * Notifies the object that a collision occurred at the specified
     * collision Point with a given velocity. The returned velocity is the new
     * velocity expected after the hit, based on the force the object inflicted
     * on the colliding object.
     *
     * @param collisionPoint  The point of collision between the collidable
     *                        object and another object
     * @param currentVelocity The current velocity of the colliding object
     * @param hitter          The specific ball the hits the Collidable
     * @return The new velocity expected after the hit
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}