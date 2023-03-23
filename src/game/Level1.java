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
 * create Level1 that implements LevelInformation.
 */
public class Level1 implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        Velocity velocity = new Velocity(0, -3);
        list.add(velocity);
        return list;
    }

    @Override
    public List<Ball> balls() {
        List<Ball> list = new ArrayList<>();
        Ball ball = new Ball(400, 500, 5, Color.black);
        list.add(ball);
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 80;
    }

    @Override
    public Rectangle rectPaddle() {
        return new Rectangle(new Point(360, 530), paddleWidth(), 15);
    }

    @Override
    public String levelName() {
        return "Level1";
    }

    @Override
    public Sprite getBackground() {
        Rectangle back = new Rectangle(new Point(25, 25), 750, 600);
        return new Block(back, new Color(154, 200, 50));
    }

    @Override
    public List<Block> blocks() {
        List<Block> list = new ArrayList<>();
        Block block = new Block(new Rectangle(new Point(375, 300), 50, 10), Color.cyan);
        list.add(block);
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
