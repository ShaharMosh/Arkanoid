//208001677 Shahar Moshonov
package collisionSpirite;

import biuoop.DrawSurface;
import game.GameLevel;
import listeners.HitListener;
import listeners.HitNotifier;
import sprites.Ball;
import sprites.Sprite;
import collision.Collidable;
import geometry.Rectangle;
import geometry.Point;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Block  - has Rectangle and color.
 *          methods- constructor, get methods, implements of Collidable, Sprite.
 */
public class Block implements Collidable, Sprite, HitNotifier {


    private Rectangle collisionRectangle;
    private java.awt.Color color;
    private List<HitListener> hitListeners;

    /**
     *  constructor - create a new rectangle with color.
     * @param collisionRectangle - a rectangle.
     * @param color - of the rectangle.
     */
    public Block(Rectangle collisionRectangle, java.awt.Color color) {
        this.collisionRectangle = collisionRectangle;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * constructor - create a new rectangle with color and hitListeners.
     * @param collisionRectangle - a rectangle.
     * @param color - of the rectangle.
     * @param hitListeners - list of hitListeners.
     */
    public Block(Rectangle collisionRectangle, java.awt.Color color, List<HitListener> hitListeners) {
        this.collisionRectangle = collisionRectangle;
        this.color = color;
        this.hitListeners = hitListeners;
    }

    /**
     * @return color of the block.
     */
    public java.awt.Color getColor() {
        return color;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return collisionRectangle;
    }

    @Override
    /**
     * check where is the collision point and change the velocity accordingly.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double newVelocityX = currentVelocity.getVelocityX();
        double newVelocityY = currentVelocity.getVelocityY();
        if (collisionPoint.equalsX(getCollisionRectangle().getDownRight())
                || collisionPoint.equalsX(
                        getCollisionRectangle().getUpperLeft())) {
            newVelocityX = newVelocityX * -1;
            this.notifyHit(hitter);
        }
        if (collisionPoint.equalsY(getCollisionRectangle().getDownRight())
                || collisionPoint.equalsY(
                        getCollisionRectangle().getUpperLeft())) {
            newVelocityY = newVelocityY * -1;
            this.notifyHit(hitter);
        }
        return new Velocity(newVelocityX, newVelocityY);
    }


    @Override
    /**
     * draw the block on the given DrawSurface.
     * @param surface - DrawSurface.
     */
    public void drawOn(DrawSurface surface) {
        java.awt.Color colorBlock = this.getColor();
        surface.setColor(colorBlock);
        surface.fillRectangle(
                (int) getCollisionRectangle().getUpperLeft().getX(),
                (int) getCollisionRectangle().getUpperLeft().getY(),
                (int) getCollisionRectangle().getWidth(),
                (int) getCollisionRectangle().getHeight());
        surface.setColor(Color.darkGray);
        surface.drawRectangle(
                (int) getCollisionRectangle().getUpperLeft().getX(),
                (int) getCollisionRectangle().getUpperLeft().getY(),
                (int) getCollisionRectangle().getWidth(),
                (int) getCollisionRectangle().getHeight());
    }

    /**
     * notify the sprite that time has passed.
     */
    public void timePassed() {
    }


    /**
     * Add this block to the game (as Collidable and Sprite).
     * @param g - Game.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * Remove this block to the game (as Collidable and Sprite).
     * @param game - Game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * @return hitListeners List.
     */
    public List<HitListener> getHitListeners() {
        return hitListeners;
    }

    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);

    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}
