// 209625946 Tomer Berenstein
package geometry;

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The Rectangle class represents a rectangle with x and y coordinates,
 * width and height.
 *
 * @author Tomer Berenstein tomerbrotem@gmail.com
 * @version 19.0.2
 * @since 2023-01-17
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    private java.awt.Color color;


    /**
     * Constructs a new Rectangle object with the specified location, width,
     * height, and color.
     *
     * @param upperLeft the upper left corner point of the rectangle
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     * @param color     the color of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height,
                     Color color) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.color = color;

    }

    /**
     * Returns a list of intersection points between the specified line and
     * the rectangle.
     *
     * @param line the line to check for intersection points
     * @return a list of intersection points, which can be empty if no
     * intersection is found
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> intersections = new ArrayList<>();
        double x1 = upperLeft.getX();
        double y1 = upperLeft.getY();
        double w = getWidth();
        double h = getHeight();

        // Calculate the four points of the rectangle
        Point p1 = new Point(x1, y1);
        Point p2 = new Point(x1 + w, y1);
        Point p3 = new Point(x1 + w, y1 + h);
        Point p4 = new Point(x1, y1 + h);

        // Check for intersection with each of the four sides
        // upper side
        Line l1 = new Line(p1, p2);
        Point intersection1 = l1.intersectionWith(line);
        if (intersection1 != null) {
            intersections.add(intersection1);
        }

        // right side
        Line l2 = new Line(p2, p3);
        Point intersection2 = l2.intersectionWith(line);
        if (intersection2 != null) {
            intersections.add(intersection2);
        }

        // lower side
        Line l3 = new Line(p3, p4);
        Point intersection3 = l3.intersectionWith(line);
        if (intersection3 != null) {
            intersections.add(intersection3);
        }

        // left side
        Line l4 = new Line(p4, p1);
        Point intersection4 = l4.intersectionWith(line);
        if (intersection4 != null) {
            intersections.add(intersection4);
        }


        return intersections;
    }


    /**
     * Draws the rectangle on the provided DrawSurface with the rectangle's
     * color. A black border is also drawn around the rectangle.
     *
     * @param d the DrawSurface on which to draw the rectangle
     */
    public void drawRect(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) upperLeft.getX(), (int) upperLeft.getY(),
                (int) this.width, (int) this.height);
        d.setColor(color.BLACK);
        d.drawRectangle((int) upperLeft.getX(), (int) upperLeft.getY(),
                (int) this.width, (int) this.height);
    }


    /**
     * Getter for the x coordinate of the top-left corner of the rectangle.
     *
     * @return the x coordinate of the top-left corner of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }


    /**
     * Getter for the width of the rectangle.
     *
     * @return the width of the rectangle
     */
    public double getWidth() {
        return width;
    }

    /**
     * Getter for the height of the rectangle.
     *
     * @return the height of the rectangle
     */
    public double getHeight() {
        return height;
    }

    /**
     * Getter for the color of the rectangle.
     *
     * @return the color of the rectangle
     */
    public Color getColor() {
        return color;
    }
}
