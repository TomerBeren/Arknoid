// 209625946 Tomer Berenstein
package physics;

import game.GameLevel;
import game.GameEnvironment;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import sprites.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;


/**
 * The Ball class represents a ball object with a center point, a radius,
 * a color, and a velocity. It provides methods for accessing and modifying
 * the ball's properties, as well as moving and drawing the ball.
 *
 * @author Tomer Berenstein tomerbrotem@gmail.com
 * @version 19.0.2
 * @since 2023-01-17
 */
public class Ball implements Sprite {
    private Rectangle rec;
    private Velocity v;
    private Point center;
    private int r;
    private java.awt.Color color;
    private GameEnvironment environment;

    /**
     * Constructs a new Ball object with the given center point, radius,
     * and color. The velocity of the ball is initialized to zero.
     *
     * @param center the center point of the ball
     * @param r      the radius of the ball
     * @param color  the color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.v = new Velocity(0, 0);

    }

    /**
     * Constructs a new Ball with specified center coordinates, radius, and
     * color. Velocity is set to zero.
     *
     * @param x     the x coordinate of the center point of the ball
     * @param y     the y coordinate of the center point of the ball
     * @param r     the radius of the ball
     * @param color the color of the ball
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.v = new Velocity(0, 0);

    }

    /**
     * Sets the center point of the ball to the specified point.
     *
     * @param center The new center point for the ball
     */

    public void setCenter(Point center) {
        this.center = center;
    }

    /**
     * Sets the boundary surface for the ball to bounce off of. This method is
     * specifically for a DrawSurface.
     *
     * @param rec the Rectangle object
     */
    public void setSurface(Rectangle rec) {
        this.rec = rec;
    }

    /**
     * Getter for the x coordinate of the center point of the ball.
     *
     * @return the x coordinate of the center point of the ball
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Getter for the y coordinate of the center point of the ball.
     *
     * @return the y coordinate of the center point of the ball
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Returns the radius of the ball.
     *
     * @return the radius of the ball
     */
    public int getSize() {
        return this.r;
    }


    /**
     * Draws the ball on the given DrawSurface.
     *
     * @param surface the DrawSurface on which to draw the ball
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
        surface.setColor(Color.BLACK);
        surface.drawCircle(this.getX(), this.getY(), this.getSize());
    }

    /**
     * Sets the velocity of the ball to the given velocity.
     *
     * @param v the velocity to set
     */
    public void setVelocity(Velocity v) {
        this.v = v;
    }

    /**
     * Sets the velocity of the ball to the given dx and dy values.
     *
     * @param dx the x component of the velocity to set
     * @param dy the y component of the velocity to set
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * Returns the center point of the ball.
     *
     * @return The current center point of the ball
     */
    public Point getCenter() {
        return center;
    }

    /**
     * Getter for the current velocity of the ball.
     *
     * @return the velocity of the ball as a Velocity object.
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * Updates the position of the ball based on its current velocity. If the
     * ball collides with a Collidable object, the velocity is updated according
     * to the collision. If the ball collides with the edges of the DrawSurface,
     * the velocity is adjusted to keep the ball within the boundaries.
     */
    public void moveOneStep() {
        CollisionInfo info =
                environment.getClosestCollision(this.getTrajectory());

        // If no collision, move the ball to the end of the trajectory
        if (info == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
        } else {
            // Otherwise, handle the collision
            Collidable collidable = info.collisionObject();
            Point collisionPoint = info.collisionPoint();

            //Get the new velocity using the hit method
            Velocity newVelocity =
                    collidable.hit(this, collisionPoint, this.getVelocity());


            // Update the ball's velocity
            this.setVelocity(newVelocity);
        }
    }

    /**
     * Adds this ball to the specified game by adding it as a Sprite.
     *
     * @param g The game to which the ball should be added
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }


    /**
     * Calculates and returns the trajectory of the ball based on its current
     * position and velocity. The trajectory is represented as a Line object.
     *
     * @return The trajectory of the ball as a Line object
     */
    public Line getTrajectory() {
        double radius = this.getSize();
        Velocity v = Velocity.fromAngleAndSpeed(this.getVelocity().getAngle(),
                2 * Math.max(this.getVelocity().getSpeed(), radius));
        Point endOfTrajectory = v.applyToPoint(this.center);
        return new Line(this.center, endOfTrajectory);
    }

    /**
     * Sets the game environment for the ball, which contains information about
     * collidable objects in the game.
     *
     * @param environment The game environment to be set for the ball
     */
    public void setGameEnvironment(GameEnvironment environment) {
        this.environment = environment;
    }

    /**
     * Updates the ball's position based on its current velocity and the
     * game environment's collidable objects. This method is called on each
     * time step in the game loop.
     */
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * Removes the ball from the game given in the argument.
     *
     * @param g The game to remove the ball from.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}