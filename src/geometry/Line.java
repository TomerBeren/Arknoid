// 209625946 Tomer Berenstein

package geometry;

import java.util.List;
/**
 * A class representing a line segment between two points in a 2D plane.
 *
 * @author Tomer Berenstein tomerbrotem@gmail.com
 * @version 19.0.2
 * @since 2023-01-17
 */
public class Line {
    private static final double EPSILON = 1e-15;
    private Point start;
    private Point end;

    /**
     * Constructs a new Line object with the given start and end points.
     *
     * @param start the starting point of the line
     * @param end   the ending point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs a new Line object with the given x and y coordinates for the
     * start and end points.
     *
     * @param x1 the x-coordinate of the start point
     * @param y1 the y-coordinate of the start point
     * @param x2 the x-coordinate of the end point
     * @param y2 the y-coordinate of the end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }


    /**
     * Calculates the distance between start to end.
     *
     * @return the length of the line.
     */
    public double length() {
        return this.start.distance(this.end);

    }

    /**
     * Calculates the middle point of the line.
     *
     * @return the middle point of the line.
     */
    public Point middle() {
        double x = Math.abs(this.start.getX() + this.end.getX()) / 2;
        double y = Math.abs(this.start.getY() + this.end.getY()) / 2;
        return new Point(x, y);
    }

    /**
     * Getter for the start point of the line segment.
     *
     * @return the start point of the line segment.
     */
    public Point start() {
        return this.start;
    }

    /**
     * Getter for the ending point of the line segment.
     *
     * @return the ending point of the line segment.
     */
    public Point end() {
        return this.end;
    }


    /**
     * Determines if this line segment intersects with another line segment.
     *
     * @param other the other line segment to check for intersection.
     * @return true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        double x1 = start.getX();
        double y1 = start.getY();
        double x2 = end.getX();
        double y2 = end.getY();
        double x3 = other.start().getX();
        double y3 = other.start().getY();
        double x4 = other.end().getX();
        double y4 = other.end().getY();

        // Calculate the denominator
        double denominator = (y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1);

        // Check if the lines are parallel
        if (denominator == 0) {
            // Check if the lines are on the same line
            if ((y1 - y3) * (x2 - x1) == (y2 - y1) * (x1 - x3)) {
                // Check if the lines overlap
                if (Math.max(x1, x2) >= Math.min(x3, x4)
                        && Math.max(x3, x4) >= Math.min(x1, x2)
                        && Math.max(y1, y2) >= Math.min(y3, y4)
                        && Math.max(y3, y4) >= Math.min(y1, y2)) {
                    return true;
                }
            }
            return false;
        }

        // Calculate the two numerators
        double numerator1 = (x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3);
        double numerator2 = (x2 - x1) * (y1 - y3) - (y2 - y1) * (x1 - x3);

        // Calculate the two parameters
        double parameter1 = numerator1 / denominator;
        double parameter2 = numerator2 / denominator;

        // Check if the intersection point is on both lines
        if (parameter1 >= 0 && parameter1 <= 1 && parameter2 >= 0
                && parameter2 <= 1) {
            return true;
        }

        return false;
    }

    /**
     * If this line does not intersect with the rectangle, returns null.
     * Otherwise, returns the closest intersection point to the start of the
     * line.
     *
     * @param rect The rectangle to find the intersection point with
     * @return The closest intersection point to the start of the line, or null
     * if no intersection
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        // Get the list of intersection points between the line and rectangle
        List<Point> intersectionPoints = rect.intersectionPoints(this);

        if (intersectionPoints.isEmpty()) {
            // No intersection points found
            return null;
        }
        // Set the first intersection point as the closest initially
        Point closest = intersectionPoints.get(0);

        // Iterate through the intersection points to find the closest one
        for (Point current : intersectionPoints) {
            double currentDistance = this.start.distance(current);
            double closestDistance = this.start.distance(closest);

            // If the current point is closer, update the closest point
            if (currentDistance < closestDistance) {
                closest = current;
            }
        }

        return closest;
    }


    /**
     * Calculates the intersection point between two line segments.
     * Return null if the two line have infinite intersection points.
     *
     * @param other the other line segment to intersect with
     * @return the intersection point if it exists, null otherwise
     */
    public Point intersectionWith(Line other) {
        // Create points for both lines
        Point pointA = new Point(this.start().getX(), this.start().getY());
        Point pointB = new Point(this.end().getX(), this.end().getY());
        Point pointC = new Point(other.start().getX(), other.start().getY());
        Point pointD = new Point(other.end().getX(), other.end().getY());

        // Calculate slopes for both lines
        double slopeThis = (pointB.getY() - pointA.getY())
                / (pointB.getX() - pointA.getX());
        double slopeOther = (pointD.getY() - pointC.getY())
                / (pointD.getX() - pointC.getX());

        // Check if the lines are the same
        if (this.equals(other)) {
            return null; // Lines are the same
        }

        // Check for fully contained lines
        if (isPointOnLineSegment(pointA, other)
                && isPointOnLineSegment(pointB, other)) {
            return null; // This line is fully contained in the other
        } else if (isPointOnLineSegment(pointC, this)
                && isPointOnLineSegment(pointD, this)) {
            return null; // The other line is fully contained in this one
        }

        // Check for lines overlapping partially
        if (((isPointOnLineSegment(pointA, other)
                && !pointA.equals(pointC) && !pointA.equals(pointD))
                || (isPointOnLineSegment(pointB, other)
                && !pointB.equals(pointC) && !pointB.equals(pointD)))
                && slopeThis == slopeOther) {
            return null; // Lines overlapping partially
        }

        // Check for shared start or end points
        if (pointA.equals(pointC) || pointA.equals(pointD)) {
            return pointA;
        } else if (pointB.equals(pointC) || pointB.equals(pointD)) {
            return pointB;
        }

        // Check for shared end-start or start-end points
        if (pointA.equals(pointD)) {
            return pointA;
        } else if (pointB.equals(pointC)) {
            return pointB;
        } else if (pointA.equals(other.end())) {
            return pointA;
        } else if (pointB.equals(other.start())) {
            return pointB;
        }

        // Calculate intersection point
        double a1 = pointB.getY() - pointA.getY();
        double b1 = pointA.getX() - pointB.getX();
        double c1 = a1 * (pointA.getX()) + b1 * (pointA.getY());
        double a2 = pointD.getY() - pointC.getY();
        double b2 = pointC.getX() - pointD.getX();
        double c2 = a2 * (pointC.getX()) + b2 * (pointC.getY());
        double determinant = a1 * b2 - a2 * b1;

        // Check if the lines trully instersect
        if (!isIntersecting(other)) {
            return null;
        } else {
            double x = (b2 * c1 - b1 * c2) / determinant;
            double y = (a1 * c2 - a2 * c1) / determinant;
            Point intersectionPoint = new Point(x, y);
            return intersectionPoint;
        }
    }

    /**
     * Determines if a given point lies on this line segment within a certain
     * tolerance.
     *
     * @param p     the point to check if it lies on the line segment.
     * @param other the line segment to check if the point lies on it.
     * @return true if the point lies on the line segment, false otherwise.
     */
    public boolean isPointOnLineSegment(Point p, Line other) {
        double dist1 = Math.abs(p.distance(other.start()));
        double dist2 = Math.abs(p.distance(other.end()));
        double length = Math.abs(other.start().distance(other.end()));

        // Check if the distance between the point and each endpoint
        // is within the tolerance value of the line length
        return Math.abs(dist1 + dist2 - length) < EPSILON;
    }


    /**
     * Determines if this line segment is equal to another line segment.
     *
     * @param other the other line segment to check for equality.
     * @return true if the line segments are equal, false otherwise.
     */
    public boolean equals(Line other) {
        Point otherStart = new Point(other.start.getX(), other.start.getY());
        Point otherEnd = new Point(other.end.getX(), other.end.getY());
        return (start.equals(otherStart) && end.equals(otherEnd))
                || (start.equals(otherEnd) && end.equals(otherStart));
    }

}
