// 209625946 Tomer Berenstein

package game;

import biuoop.DrawSurface;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import physics.Collidable;
import physics.CollisionInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing the environment in which game objects can collide.
 *
 * @author Tomer Berenstein tomerbrotem@gmail.com
 * @version 19.0.2
 * @since 2023-01-17
 */
public class GameEnvironment {
    private List<Collidable> collidables;

    /**
     * Constructs an empty GameEnvironment.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * Returns the list of collidables in the environment.
     *
     * @return a list of collidables
     */
    public List<Collidable> getCollidables() {
        return collidables;
    }

    /**
     * Adds the given collidable to the environment.
     *
     * @param c the collidable to be added
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * Draws all collidables on the given DrawSurface.
     *
     * @param d the DrawSurface to draw the collidables on
     */
    public void drawAllCollidable(DrawSurface d) {
        for (Collidable c : this.collidables) {
            c.getCollisionRectangle().drawRect(d);
        }
    }

    /**
     * Returns information about the closest collision that will occur
     * along the given trajectory, if there is one.
     *
     * @param trajectory the line representing the object's trajectory
     * @return a CollisionInfo object containing the closest collision
     * information, or null if no collision will occur
     */

    public CollisionInfo getClosestCollision(Line trajectory) {
        Point closestPoint = null;
        Collidable closestCollidable = null;

        for (Collidable collidable : this.getCollidables()) {
            Rectangle rect = collidable.getCollisionRectangle();

            // Get intersection point of trajectory and collidable rectangle
            Point intersectionPoint =
                    trajectory.closestIntersectionToStartOfLine(rect);

            if (intersectionPoint != null) {
                if (closestPoint == null) {
                    // First collidable intersection found
                    closestPoint = intersectionPoint;
                    closestCollidable = collidable;
                } else {
                    // Check if new intersection point is closer than current
                    // closest point
                    double currentDistance =
                            trajectory.start().distance(closestPoint);
                    double newDistance =
                            trajectory.start().distance(intersectionPoint);

                    if (newDistance < currentDistance) {
                        closestPoint = intersectionPoint;
                        closestCollidable = collidable;
                    }
                }
            }
        }

        // No intersection found
        if (closestPoint == null) {
            return null;
        } else {
            return new CollisionInfo(closestCollidable, closestPoint);
        }

    }

    /**
     * Method to remove a Collidable from the Collidable Collection.
     *
     * @param c The Collidable to remove.
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }
}