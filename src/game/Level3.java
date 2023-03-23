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
public class Level3 implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        Velocity velocity = new Velocity(-2.3, -2.3);
        list.add(velocity);
        Velocity velocity1 = new Velocity(2.3, -2.3);
        list.add(velocity1);
        return list;
    }

    @Override
    public List<Ball> balls() {
        List<Ball> list = new ArrayList<>();
        Ball ball1 = new Ball(400, 400, 5, Color.black);
        list.add(ball1);
        Ball ball2 = new Ball(400, 400, 5, Color.black);
        list.add(ball2);
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public Rectangle rectPaddle() {
        return new Rectangle(new Point(40, 530), paddleWidth(), 8);
    }

    @Override
    public String levelName() {
        return "Level3";
    }

    @Override
    public Sprite getBackground() {
        Rectangle back = new Rectangle(new Point(25, 25), 750, 600);
        return new Block(back, new Color(154, 200, 50));
    }

    @Override
    public List<Block> blocks() {
        List<Block> list = new ArrayList<>();
        for (int i = 0; i < 500; i += 50) {
            Point p = new Point(375.0 + i, 100.0);
            Rectangle rectangle = new Rectangle(p, 50, 20);
            Block block = new Block(rectangle, Color.darkGray);
            list.add(block);
        }
        for (int i = 0; i < 450; i += 50) {
            Point p = new Point(425.0 + i, 120.0);
            Rectangle rectangle = new Rectangle(p, 50, 20);
            Block block = new Block(rectangle, Color.red);
            list.add(block);
        }
        for (int i = 0; i < 400; i += 50) {
            Point p = new Point(475 + i, 140.0);
            Rectangle rectangle = new Rectangle(p, 50, 20);
            Block block = new Block(rectangle, Color.yellow);
            list.add(block);
        }
        for (int i = 0; i < 350; i += 50) {
            Point p = new Point(525 + i, 160.0);
            Rectangle rectangle = new Rectangle(p, 50, 20);
            Block block = new Block(rectangle, Color.blue);
            list.add(block);
        }
        for (int i = 0; i < 300; i += 50) {
            Point p = new Point(575 + i, 180.0);
            Rectangle rectangle = new Rectangle(p, 50, 20);
            Block block = new Block(rectangle, Color.white);
            list.add(block);
        }
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 40;
    }
}
