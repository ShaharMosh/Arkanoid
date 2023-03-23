//208001677 Shahar Moshonov
package collisionSpirite;

import biuoop.DrawSurface;
import biuoop.GUI;
import collision.Collidable;
import game.GameLevel;
import geometry.Rectangle;
import geometry.Point;
import sprites.Ball;
import sprites.Sprite;

import java.awt.Color;

/**
 * Paddle has - biuoop.KeyboardSensor, Rectangle, color, GUI,
 *              rightBorder and leftBorder of the screen.
 * methods- constructor, get methods, move right anf left thr paddle,
 *          implements of Collidable, Sprite.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rectanglePaddle;
    private java.awt.Color color;
    private GUI gui;
    private double rightBorder;
    private double leftBorder;
    private int speed;


    /**
     * constructor - create a paddle as rectangle with color,KeyboardSensor,gui.
     * @param keyboard - biuoop.KeyboardSensor.
     * @param rectanglePaddle - Rectangle-  shape of the paddle.
     * @param color - of the paddle.
     * @param gui - GUI.
     * @param rightBorder - the right border of the screen.
     * @param leftBorder - the left border of the screen.
     * @param speed - speed of the paddle.
     */
    public Paddle(biuoop.KeyboardSensor keyboard, Rectangle rectanglePaddle,
                  java.awt.Color color, GUI gui,
                  double rightBorder, double leftBorder, int speed) {
        this.keyboard = keyboard;
        this.rectanglePaddle = rectanglePaddle;
        this.color = color;
        this.gui = gui;
        this.rightBorder = rightBorder;
        this.leftBorder = leftBorder;
        this.speed = speed;
    }

    /**
     * @return the color of the paddle.
     */
    public java.awt.Color getColor() {
        return color;
    }


    /**
     * change the location of the paddle when the player press on the LEFT_KEY,
     * and make sure the paddle stay in the screen borders.
     */
    public void moveLeft() {
        if (this.rectanglePaddle.getLeftX() - speed >= this.leftBorder + 20) {
            rectanglePaddle = new Rectangle(new Point(
                    getCollisionRectangle().getUpperLeft().getX() - speed,
                    getCollisionRectangle().getUpperLeft().getY()),
                    getCollisionRectangle().getWidth(),
                    getCollisionRectangle().getHeight());
        }
    }

    /**
     * change the location of the paddle when the player press on the RIGHT_KEY,
     * and make sure the paddle stay in the screen borders.
     */
    public void moveRight() {
        if (this.rectanglePaddle.getRightX() + speed <= this.rightBorder - 20) {
            rectanglePaddle = new Rectangle(new Point(
                    getCollisionRectangle().getUpperLeft().getX() + speed,
                    getCollisionRectangle().getUpperLeft().getY()),
                    getCollisionRectangle().getWidth(),
                    getCollisionRectangle().getHeight());
        }
    }

    // Sprite
    @Override
    public void timePassed() {
        //biuoop.GUI gui = new biuoop.GUI("paddle", 100, 100);
        biuoop.KeyboardSensor keyboard = gui.getKeyboardSensor();
        if (keyboard.isPressed(keyboard.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(keyboard.RIGHT_KEY)) {
            moveRight();
        }
    }

    @Override
    /**
     * draw the paddle on the given DrawSurface.
     * @param d - a DrawSurface.
     */
    public void drawOn(DrawSurface d) {
        java.awt.Color colorBall = this.getColor();
        d.setColor(colorBall);
        d.fillRectangle((int) getCollisionRectangle().getUpperLeft().getX(),
                (int) getCollisionRectangle().getUpperLeft().getY(),
                (int) getCollisionRectangle().getWidth(),
                (int) getCollisionRectangle().getHeight());
        d.setColor(Color.darkGray);
        d.drawRectangle((int) getCollisionRectangle().getUpperLeft().getX(),
                (int) getCollisionRectangle().getUpperLeft().getY(),
                (int) getCollisionRectangle().getWidth(),
                (int) getCollisionRectangle().getHeight());
    }

    // Collidable
    @Override
    public Rectangle getCollisionRectangle() {
        return rectanglePaddle;
    }

    /**
     * check where the ball will collide in the paddle,
     * and change the velocity accordingly.
     * @param collisionPoint of object that we collided.
     * @param currentVelocity object that we collided.
     * @param hitter -Ball.
     * @return the new velocity.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double x = getCollisionRectangle().getWidth() / 5; //5 regions of paddle.
        Velocity v = currentVelocity;
        double speed = v.getSpeed();
        double xLeft = getCollisionRectangle().getUpperLeft().getX();

        if (collisionPoint.equalsX(getCollisionRectangle().getUpperLeft())
                && collisionPoint.getY() > getCollisionRectangle().getUpperY()
                && collisionPoint.getY()
                < getCollisionRectangle().getDownRight().getY()) {
            v = new Velocity(currentVelocity.getVelocityX() * -1,
                    (currentVelocity.getVelocityY()));
            return v;
        }

        if (collisionPoint.equalsX(getCollisionRectangle().getDownRight())
                && collisionPoint.getY() > getCollisionRectangle().getUpperY()
                && collisionPoint.getY()
                < getCollisionRectangle().getDownRight().getY()) {
            v = new Velocity(currentVelocity.getVelocityX() * -1,
                    (currentVelocity.getVelocityY()));
            return v;
        }
        //if we hit region 1, the ball bounce back with an angle of 300 degrees.
        if (collisionPoint.getX() <= x + xLeft) {
            v =  Velocity.fromAngleAndSpeed(300, speed);
        }

        //if we hit region 2, the ball bounce back with an angle of 330 degrees.
        if (collisionPoint.getX() >= x + xLeft
                && collisionPoint.getX() <= 2 * x + xLeft) {
            v = Velocity.fromAngleAndSpeed(330, speed);
        }

        //If the ball hits the middle region,  change its vertical direction.
        if (collisionPoint.getX() <= (3 * x) + xLeft
                && collisionPoint.getX() > (2 * x) + xLeft) {
            v = new Velocity(currentVelocity.getVelocityX(),
                    (currentVelocity.getVelocityY()) * -1);
        }

        //if we hit region 4, the ball bounce back with an angle of 30 degrees.
        if (collisionPoint.getX() > 3 * x + xLeft
                && collisionPoint.getX() <= 4 * x + xLeft) {
            v = Velocity.fromAngleAndSpeed(30, speed);
        }

        //if we hit region 5, the ball bounce back with an angle of 60 degrees.
        if (collisionPoint.getX() > 4 * x + xLeft
                && collisionPoint.getX() <= 5 * x + xLeft) {
            v = Velocity.fromAngleAndSpeed(60, speed);
        }
        return v;
    }


    /**
     * Add this paddle to the game (as Collidable and Sprite).
     * @param g - Game.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}
