// 209625946 Tomer Berenstein
package physics;

import biuoop.DrawSurface;
import game.GameLevel;
import listeners.lhelpers.HitListener;
import listeners.lhelpers.HitNotifier;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import sprites.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 * The Block class represents a rectangular block in the game,
 * which implements both Collidable and Sprite interfaces.
 *
 * @author Tomer Berenstein tomerbrotem@gmail.com
 * @version 19.0.2
 * @since 2023-01-17
 */
public class Block implements Collidable, Sprite, HitNotifier {

    private Rectangle block;
    private List<HitListener> hitListeners;

    /**
     * Constructs a Block with the given Rectangle shape.
     *
     * @param block The rectangle shape of the block
     */
    public Block(Rectangle block) {
        this.block = block;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Returns the upper edge of the block as a Line.
     *
     * @return The upper edge of the block
     */
    public Line getUpperLine() {
        double x1 = block.getUpperLeft().getX();
        double y1 = block.getUpperLeft().getY();
        double x2 = x1 + block.getWidth();
        double y2 = y1;
        return new Line(x1, y1, x2, y2);
    }

    /**
     * Returns the bottom edge of the block as a Line.
     *
     * @return The bottom edge of the block
     */
    public Line getBottomLine() {
        double x1 = block.getUpperLeft().getX();
        double y1 = block.getUpperLeft().getY() + block.getHeight();
        double x2 = x1 + block.getWidth();
        double y2 = y1;
        return new Line(x1, y1, x2, y2);
    }

    /**
     * Returns the right edge of the block as a Line.
     *
     * @return The right edge of the block
     */
    public Line getRightLine() {
        double x1 = block.getUpperLeft().getX() + block.getWidth();
        double y1 = block.getUpperLeft().getY();
        double x2 = x1;
        double y2 = y1 + block.getHeight();
        return new Line(x1, y1, x2, y2);
    }

    /**
     * Returns the left edge of the block as a Line.
     *
     * @return The left edge of the block
     */
    public Line getLeftLine() {
        double x1 = block.getUpperLeft().getX();
        double y1 = block.getUpperLeft().getY();
        double x2 = x1;
        double y2 = y1 + block.getHeight();
        return new Line(x1, y1, x2, y2);
    }

    /**
     * Returns the "collision shape" of the object.
     *
     * @return The collision shape as a Rectangle
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.block;
    }

    /**
     * Notifies the object that we collided with it at collisionPoint with
     * a given velocity. The return is the new velocity expected after the hit
     * (based on the force the object inflicted on us).
     *
     * @param collisionPoint  The point of collision
     * @param currentVelocity The current velocity of the ball
     * @return The new velocity after the collision
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();

        double blockUpperLeftX = block.getUpperLeft().getX();
        double blockUpperLeftY = block.getUpperLeft().getY();
        double blockWidth = block.getWidth();
        double blockHeight = block.getHeight();


        Line[] blockLines = {getUpperLine(), getBottomLine(), getLeftLine(), getRightLine()};
        for (Line line : blockLines) {
            if (line.isPointOnLineSegment(collisionPoint, line)) {
                if (line.equals(getUpperLine())) {
                    dy = -Math.abs(dy);
                    hitter.setCenter(new Point(hitter.getCenter().getX(), blockUpperLeftY - hitter.getSize()));
                    this.notifyHit(hitter);
                } else if (line.equals(getBottomLine())) {
                    dy = Math.abs(dy);
                    hitter.setCenter(new Point(hitter.getCenter().getX(), blockUpperLeftY + blockHeight
                            + hitter.getSize()));
                    this.notifyHit(hitter);
                } else if (line.equals(getLeftLine())) {
                    dx = -Math.abs(dx);
                    hitter.setCenter(new Point(blockUpperLeftX - hitter.getSize(), hitter.getCenter().getY()));
                    this.notifyHit(hitter);
                } else if (line.equals(getRightLine())) {
                    dx = Math.abs(dx);
                    hitter.setCenter(new Point(blockUpperLeftX + blockWidth + hitter.getSize(),
                            hitter.getCenter().getY()));
                    this.notifyHit(hitter);
                }
                break;
            }
        }

        return new Velocity(dx, dy);
    }


    /**
     * Adds this block to the game by adding it as a collidable and a sprite.
     *
     * @param g The game to add the block to
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * Draws the block on the given DrawSurface.
     *
     * @param surface The DrawSurface to draw the block on
     */
    @Override
    public void drawOn(DrawSurface surface) {
        this.block.drawRect(surface);
    }

    /**
     * Updates the state of the block as time passes.
     * Currently, this method is empty as the block doesn't change state.
     */
    @Override
    public void timePassed() {

    }

    /**
     * Method to remove the block from the game.
     *
     * @param game The game to remove the block from.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
