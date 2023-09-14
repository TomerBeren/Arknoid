// 209625946 Tomer Berenstein
package physics;

import geometry.Point;
/**
 * The CollisionInfo class represents information about a collision
 * between a moving object and a collidable object.
 *
 * @author Tomer Berenstein tomerbrotem@gmail.com
 * @version 19.0.2
 * @since 2023-01-17
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * Constructs a CollisionInfo with the given Collidable object and collision
     * point.
     *
     * @param collisionObject The collidable object involved in the collision
     * @param collisionPoint  The point where the collision occurs
     */
    public CollisionInfo(Collidable collisionObject, Point collisionPoint) {
        this.collisionObject = collisionObject;
        this.collisionPoint = collisionPoint;
    }

    /**
     * Returns the point at which the collision occurs.
     *
     * @return The collision point
     */
    public Point collisionPoint() {
        return this.collisionPoint;

    }

    /**
     * Returns the collidable object involved in the collision.
     *
     * @return The collidable object
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}