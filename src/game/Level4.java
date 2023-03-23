//208001677 Shahar Moshonov
package game;

import collisionSpirite.Block;
import collisionSpirite.Velocity;
import geometry.Point;
import geometry.Rectangle;
import sprites.Ball;
import sprites.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * create Level3 that implements LevelInformation.
 */
public class Level4 implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        Velocity velocity = Velocity.fromAngleAndSpeed(180, 3.5);
        list.add(velocity);
        Velocity velocity1 = Velocity.fromAngleAndSpeed(60, 3);
        list.add(velocity1);
        Velocity velocity2 = Velocity.fromAngleAndSpeed(-50, 3);
        list.add(velocity2);
        return list;
    }

    @Override
    public List<Ball> balls() {
        List<Ball> list = new ArrayList<>();
        Ball ball1 = new Ball(400, 400, 5, Color.black);
        list.add(ball1);
        Ball ball2 = new Ball(400, 400, 5, Color.black);
        list.add(ball2);
        Ball ball3 = new Ball(400, 400, 5, Color.black);
        list.add(ball3);
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 70;
    }

    @Override
    public Rectangle rectPaddle() {
        return new Rectangle(new Point(365, 530), paddleWidth(), 8);
    }

    @Override
    public String levelName() {
        return "Level4";
    }

    @Override
    public Sprite getBackground() {
        Rectangle back = new Rectangle(new Point(25, 25), 750, 600);
        return new Block(back, new Color(154, 200, 50));
    }

    @Override
    public List<Block> blocks() {
        List<Block> list = new ArrayList<>();
        for (int i = 25; i < 775; i += 50) {
            Point p = new Point(i, 100.0);
            Rectangle rectangle = new Rectangle(p, 50, 20);
            Block block = new Block(rectangle, Color.gray);
            list.add(block);
        }
        for (int i = 25; i < 775; i += 50) {
            Point p = new Point(i, 120.0);
            Rectangle rectangle = new Rectangle(p, 50, 20);
            Block block = new Block(rectangle, Color.red);
            list.add(block);
        }
        for (int i = 25; i < 775; i += 50) {
            Point p = new Point(i, 140.0);
            Rectangle rectangle = new Rectangle(p, 50, 20);
            Block block = new Block(rectangle, Color.yellow);
            list.add(block);
        }
        for (int i = 25; i < 775; i += 50) {
            Point p = new Point(i, 160.0);
            Rectangle rectangle = new Rectangle(p, 50, 20);
            Block block = new Block(rectangle, Color.green);
            list.add(block);
        }
        for (int i = 25; i < 775; i += 50) {
            Point p = new Point(i, 180.0);
            Rectangle rectangle = new Rectangle(p, 50, 20);
            Block block = new Block(rectangle, Color.white);
            list.add(block);
        }
        for (int i = 25; i < 775; i += 50) {
            Point p = new Point(i, 200);
            Rectangle rectangle = new Rectangle(p, 50, 20);
            Block block = new Block(rectangle, Color.pink);
            list.add(block);
        }
        for (int i = 25; i < 775; i += 50) {
            Point p = new Point(i, 220);
            Rectangle rectangle = new Rectangle(p, 50, 20);
            Block block = new Block(rectangle, Color.cyan);
            list.add(block);
        }
        return list;
    }
    @Override
    public int numberOfBlocksToRemove() {
        return 105;
    }
}

