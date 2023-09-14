// 209625946 Tomer Berenstein
package physics;

import geometry.Point;
/**
 * The Velocity class defines the change in position of an object in a 2D space.
 * The velocity is represented by its dx and dy values, indicating the amount
 * of movement in the x and y-axis, respectively.
 *
 * @author Tomer Berenstein tomerbrotem@gmail.com
 * @version 19.0.2
 * @since 2023-01-17
 */
public class Velocity {
    private static final int DEGREE = 360;
    private static final int RIGHT_ANGLE = 90;
    private double dx;
    private double dy;


    /**
     * Constructs a new Velocity object with the given dx and dy values.
     *
     * @param dx the change in the x-axis.
     * @param dy the change in the y-axis.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Getter for the change in the y-axis.
     *
     * @return the dy value.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Getter for the change in the x-axis.
     *
     * @return the dx value.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Calculates a Velocity object based on an angle and speed values.
     *
     * @param angle the angle in degrees to move towards.
     * @param speed the speed to move at.
     * @return a new Velocity object calculated from the angle and speed values.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        // Convert angle to radians
        double radians = Math.toRadians(angle);
        // Calculate the dx and dy based on the angle and speed
        double dx = speed * Math.sin(radians);
        double dy = -speed * Math.cos(radians);
        return new Velocity(dx, dy);
    }

    /**
     * Applies the Velocity object to a Point object, returning a new Point
     * object with the new position.
     *
     * @param p the Point object to apply the Velocity to.
     * @return a new Point object with the new position.
     */
    public Point applyToPoint(Point p) {
        double x = p.getX();
        double y = p.getY();
        x = x + dx;
        y = y + dy;
        return new Point(x, y);
    }

    /**
     * Returns the speed of the object with this velocity.
     *
     * @return The speed, calculated as the square root of the sum of the
     * squares of the horizontal and vertical components of the velocity
     */
    public double getSpeed() {
        return Math.sqrt(dx * dx + dy * dy);
    }


    /**
     * Returns the angle between the velocity vector and the x-axis.
     *
     * @return The angle, calculated using the arctangent function and converted
     * to degrees. The angle is positive and lies in the range [0, 360).
     */
    public double getAngle() {
        double angle =
                (RIGHT_ANGLE + (Math.toDegrees(Math.atan2(this.dy, this.dx))))
                        % DEGREE;

        while (0 > angle) {
            angle += DEGREE;
        }
        return angle;
    }
}