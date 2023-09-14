// 209625946 Tomer Berenstein
package sprites;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * The SpriteCollection class represents a collection of Sprite objects.
 * It provides methods to add sprites, notify all sprites that time has passed,
 * and draw all sprites on a given DrawSurface.
 *
 * @author Tomer Berenstein tomerbrotem@gmail.com
 * @version 19.0.2
 * @since 2023-01-17
 */
public class SpriteCollection {
    private List<Sprite> sprites;

    /**
     * Constructs a new SpriteCollection, initializing the list of sprites.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    /**
     * Adds a sprite to the collection.
     *
     * @param s The Sprite object to be added
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * Calls timePassed() on all sprites in the collection, notifying them that
     * time has passed.
     */
    public void notifyAllTimePassed() {
        List<Sprite> tempLst = new ArrayList<>(this.sprites);
        for (Sprite s : tempLst) {
            s.timePassed();
        }
    }

    /**
     * Calls drawOn(d) on all sprites in the collection, drawing them on the
     * given DrawSurface.
     *
     * @param d The DrawSurface to draw the sprites on
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }

    /**
     * Method to remove the Sprite from the SpriteCollection.
     *
     * @param s The sprite to remove.
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }
}