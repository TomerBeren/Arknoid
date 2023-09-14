// 209625946 Tomer Berenstein
package geometry;

/**
 * The class Point represents a location in 2D space.
 *
 * @author Tomer Berenstein tomerbrotem@gmail.com
 * @version 19.0.2
 * @since 2023-01-17
 */
public class Point {
    private static final double EPSILON = 1e-15;

    private double x;
    private double y;

    /**
     * Constructs a new Point object with the specified x and y coordinates.
     *
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Compares two double values for equality within a certain tolerance.
     *
     * @param a the first value to compare
     * @param b the second value to compare
     * @return true if the difference is less than EPSILON, false otherwise
     */
    public static boolean doubleEquals(double a, double b) {
        return Math.abs(a - b) < EPSILON;
    }

    /**
     * Calculates the distance between this point and another point.
     *
     * @param other the other point
     * @return the distance between this point and the other point
     */
    public double distance(Point other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        double distanceSquared = dx * dx + dy * dy;
        if (Math.abs(distanceSquared - 0) <= EPSILON) {
            return 0;
        }
        return Math.sqrt(distanceSquared);
    }

    /**
     * Compares this point to another point for equality.
     *
     * @param other the other point to compare
     * @return true if two points are equal , false otherwise
     */
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }
        return doubleEquals(this.x, other.getX())
                && doubleEquals(this.y, other.getY());
    }


    /**
     * Getter for the x-coordinate of this point.
     *
     * @return the x-coordinate of this point
     */
    public double getX() {
        return this.x;
    }

    /**
     * Getter for the y-coordinate of this point.
     *
     * @return the y-coordinate of this point
     */
    public double getY() {
        return this.y;
    }
}