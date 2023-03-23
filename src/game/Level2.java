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
 * create Level2 that implements LevelInformation.
 */
public class Level2 implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 14;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        Velocity velocity = new Velocity(-1.8, -2.1);
        list.add(velocity);
        Velocity velocity1 = new Velocity(2, -1.8);
        list.add(velocity1);
        return list;
    }

    @Override
    public List<Ball> balls() {
        List<Ball> list = new ArrayList<>();
        for (int i = 0; i < 140; i += 20) {
            Ball ball = new Ball(280 + i, 500 - i, 6, Color.black);
            list.add(ball);
        }
        for (int i = 0; i < 140; i += 20) {
            Ball ball = new Ball(400 + i, 380 + i, 6, Color.black);
            list.add(ball);
        }
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 2;
    }

    @Override
    public int paddleWidth() {
        return 600;
    }

    @Override
    public Rectangle rectPaddle() {
        return new Rectangle(new Point(100, 530), paddleWidth(), 8);
    }

    @Override
    public String levelName() {
        return "Level2";
    }

    @Override
    public Sprite getBackground() {
        Rectangle back = new Rectangle(new Point(25, 25), 750, 600);
        return new Block(back, new Color(154, 200, 50));
    }

    @Override
    public List<Block> blocks() {
        List<Block> list = new ArrayList<>();
        for (int i = 25; i < 125; i += 50) {
            Point p = new Point(i, 300.0);
            Rectangle rectangle = new Rectangle(p, 50, 30);
            Block block = new Block(rectangle, Color.red);
            list.add(block);
        }
        for (int i = 125; i < 225; i += 50) {
            Point p = new Point(i, 300.0);
            Rectangle rectangle = new Rectangle(p, 50, 30);
            Block block = new Block(rectangle, Color.orange);
            list.add(block);
        }
        for (int i = 225; i < 325; i += 50) {
            Point p = new Point(i, 300.0);
            Rectangle rectangle = new Rectangle(p, 50, 30);
            Block block = new Block(rectangle, Color.yellow);
            list.add(block);
        }
        for (int i = 325; i < 475; i += 50) {
            Point p = new Point(i, 300.0);
            Rectangle rectangle = new Rectangle(p, 50, 30);
            Block block = new Block(rectangle, Color.green);
            list.add(block);
        }
        for (int i = 475; i < 575; i += 50) {
            Point p = new Point(i, 300.0);
            Rectangle rectangle = new Rectangle(p, 50, 30);
            Block block = new Block(rectangle, Color.blue);
            list.add(block);
        }
        for (int i = 575; i < 675; i += 50) {
            Point p = new Point(i, 300.0);
            Rectangle rectangle = new Rectangle(p, 50, 30);
            Block block = new Block(rectangle, Color.pink);
            list.add(block);
        }
        for (int i = 675; i < 775; i += 50) {
            Point p = new Point(i, 300.0);
            Rectangle rectangle = new Rectangle(p, 50, 30);
            Block block = new Block(rectangle, Color.cyan);
            list.add(block);
        }
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }
}
