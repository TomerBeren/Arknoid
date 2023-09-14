// 209625946 Tomer Berenstein
package physics;

import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import sprites.Sprite;

import java.awt.Color;

/**
 * This class represents a Paddle which implements Sprite and Collidable
 * interfaces.
 *
 * @author Tomer Berenstein tomerbrotem@gmail.com
 * @version 19.0.2
 * @since 2023-01-17
 */
public class Paddle implements Sprite, Collidable {
    private static final int MAX_ANGLE = 60;
    private double startRange;
    private double endRange;
    private biuoop.KeyboardSensor keyboard;
    private Rectangle shape;
    private Color color;
    private int speed;

    /**
     * Paddle constructor.
     *
     * @param shape    The shape of the paddle as a Rectangle
     * @param speed    The speed of the paddle
     * @param keyboard The keyboard sensor for paddle control
     */
    public Paddle(Rectangle shape, int speed,
                  biuoop.KeyboardSensor keyboard) {
        this.shape = shape;
        this.color = shape.getColor();
        this.speed = speed;
        this.keyboard = keyboard;
    }


    /**
     * Sets the range of movement for the paddle.
     *
     * @param startRange The starting position of the paddle movement range
     * @param endRange   The ending position of the paddle movement range
     */
    public void setRange(double startRange, double endRange) {
        this.startRange = startRange;
        this.endRange = endRange;
    }

    /**
     * Returns the upper line of the paddle.
     *
     * @return The upper line of the paddle
     */
    public Line getUpperLine() {
        double x1 = shape.getUpperLeft().getX();
        double y1 = shape.getUpperLeft().getY();
        double x2 = x1 + shape.getWidth();
        double y2 = y1;
        return new Line(x1, y1, x2, y2);
    }
    /**
     * Returns the bottom line of the paddle.
     *
     * @return The bottom line of the paddle
     */
    public Line getBottomLine() {
        double x1 = shape.getUpperLeft().getX();
        double y1 = shape.getUpperLeft().getY() + shape.getHeight();
        double x2 = x1 + shape.getWidth();
        double y2 = y1;
        return new Line(x1, y1, x2, y2);
    }
    /**
     * Returns the right line of the paddle.
     *
     * @return The right line of the paddle
     */
    public Line getRightLine() {
        double x1 = shape.getUpperLeft().getX() + shape.getWidth();
        double y1 = shape.getUpperLeft().getY();
        double x2 = x1;
        double y2 = y1 + shape.getHeight();
        return new Line(x1, y1, x2, y2);
    }
    /**
     * Returns the left line of the paddle.
     *
     * @return The left line of the paddle
     */
    public Line getLeftLine() {
        double x1 = shape.getUpperLeft().getX();
        double y1 = shape.getUpperLeft().getY();
        double x2 = x1;
        double y2 = y1 + shape.getHeight();
        return new Line(x1, y1, x2, y2);
    }


    /**
     * Moves the paddle to the left.
     */
    public void moveLeft() {
        double potentialNewX = shape.getUpperLeft().getX() - speed;
        double newX = Math.max(potentialNewX, startRange);
        updateX(newX);
    }

    /**
     * Moves the paddle to the right.
     */
    public void moveRight() {
        double potentialNewX = shape.getUpperLeft().getX() + speed;
        double rightBoundary = endRange - shape.getWidth();
        double newX = Math.min(potentialNewX, rightBoundary);
        updateX(newX);
    }

    /**
     * Updates the X-coordinate of the paddle's position.
     *
     * @param newX The new X-coordinate.
     */
    private void updateX(double newX) {
        Point upperLeft = new Point(newX, shape.getUpperLeft().getY());
        shape = new Rectangle(upperLeft, shape.getWidth(), shape.getHeight(), Color.ORANGE);
    }

    /**
     * Updates the paddle's position according to the keyboard input.
     */
    public void timePassed() {
        if (keyboard.isPressed(keyboard.LEFT_KEY)) {
            moveLeft();
        } else if (keyboard.isPressed(keyboard.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Draws the paddle on the given DrawSurface.
     *
     * @param d The DrawSurface to draw the paddle on
     */
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        d.fillRectangle((int) shape.getUpperLeft().getX(), (int) shape.getUpperLeft().getY(), (int) shape.getWidth(),
                (int) shape.getHeight());
    }

    /**
     * Returns the collision rectangle of the paddle.
     *
     * @return The collision rectangle of the paddle
     */
    public Rectangle getCollisionRectangle() {
        return shape;
    }

    /**
     * Determines the new velocity of the ball after hitting the paddle.
     *
     * @param collisionPoint  The point of collision between the paddle and the
     *                        ball
     * @param currentVelocity The current velocity of the ball
     * @param hitter          The specific ball that hit the paddle
     * @return The new velocity of the ball after the collision
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        double ballSpeed = currentVelocity.getSpeed();

        // Calculate the angle between the collision point and the center of the
        // paddle
        double relativeIntersectX = collisionPoint.getX() - shape.getUpperLeft().getX() - shape.getWidth() / 2;
        double normalizedRelativeIntersectionX = relativeIntersectX / (shape.getWidth() / 2);

        // maximum bounce angle from the center of the paddle
        double maxBounceAngle = Math.toRadians(MAX_ANGLE);
        double bounceAngle = normalizedRelativeIntersectionX * maxBounceAngle;

        // Calculate the new velocity based on the collision angle and speed
        double newAngle = (collisionPoint.getY() >= shape.getUpperLeft().getY() + shape.getHeight())
                ? -bounceAngle : bounceAngle;

        if (this.getUpperLine().isPointOnLineSegment(collisionPoint, this.getUpperLine())) {
            //making the ball look like its actually touching the upper-line.
            hitter.setCenter(new Point(hitter.getCenter().getX(), shape.getUpperLeft().getY() - hitter.getSize()));
        } else if (this.getLeftLine().isPointOnLineSegment(collisionPoint, this.getLeftLine())) {
            // Handle the collision with the left side of the paddle
            hitter.setCenter(new Point(shape.getUpperLeft().getX() - hitter.getSize(), hitter.getCenter().getY()));
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        } else if (this.getRightLine().isPointOnLineSegment(collisionPoint, this.getRightLine())) {
            // Handle the collision with the right side of the paddle
            hitter.setCenter(new Point(shape.getUpperLeft().getX() + shape.getWidth() + hitter.getSize(),
                    hitter.getCenter().getY()));
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        } else if (this.getBottomLine().isPointOnLineSegment(collisionPoint, this.getBottomLine())) {
            // Handle the collision with the bottom of the paddle
            hitter.setCenter(new Point(hitter.getCenter().getX(), shape.getUpperLeft().getY() + shape.getHeight()
                    + hitter.getSize()));
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }

        return Velocity.fromAngleAndSpeed(Math.toDegrees(newAngle), ballSpeed);
    }


    /**
     * Adds this paddle to the game.
     *
     * @param g The game to add the paddle to
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * Getter for x of UpperLeft point in shape.
     *
     * @return the x coordinate.
     */
    public double getX() {
        return this.shape.getUpperLeft().getX();
    }

    /**
     * Getter for y of UpperLeft point in shape.
     *
     * @return the y coordinate.
     */
    public double getY() {
        return this.shape.getUpperLeft().getY();
    }

}