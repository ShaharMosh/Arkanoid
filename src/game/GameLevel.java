//208001677 Shahar Moshonov
package game;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import collision.Collidable;
import collisionSpirite.Block;
import collisionSpirite.Paddle;
import listeners.BlockRemover;
import listeners.Counter;
import listeners.ScoreIndicator;
import listeners.ScoreTrackingListener;
import listeners.BallRemover;
import sprites.Ball;
import sprites.SpriteCollection;
import sprites.Sprite;
import geometry.Rectangle;
import geometry.Point;
import java.awt.Color;
import java.util.List;

/**
 * Game has - Sprite Collection, Game Environment, GUI
 * methods - constructor, get methods, add Sprite and Collidable,
 *           initialize the game, run method.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    static final int WIDTH = 800;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter score;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation levelInfo;
    private ScoreIndicator scoreIndicator;
    private ScoreTrackingListener scoreListener;
    /**
     * constructor - create a new Game with game environment and sprites.
     * @param environment - GameEnvironment.
     * @param sprites - SpriteCollection.
     * @param gui - Gui.
     * @param levelInfo - LevelInformation.
     * @param animationRunner - AnimationRunner.
     * @param scoreIndicator - ScoreIndicator.
     * @param scoreListener - ScoreTrackingListener.
     */
    public GameLevel(GameEnvironment environment, SpriteCollection sprites,
                     GUI gui, AnimationRunner animationRunner, LevelInformation levelInfo,
                     ScoreIndicator scoreIndicator, ScoreTrackingListener scoreListener) {
        this.environment = environment;
        this.sprites = sprites;
        this.gui = gui;
        this.remainingBlocks = new Counter(0);
        this.remainingBalls = new Counter(0);
        this.score = new Counter(0);
        this.runner = animationRunner;
        this.running = false;
        this.keyboard = gui.getKeyboardSensor();
        this.levelInfo = levelInfo;
        this.scoreIndicator = scoreIndicator;
        this.scoreListener = scoreListener;
    }

    /**
     * @return the Sprite Collection.
     */
    public SpriteCollection getSprites() {
        return sprites;
    }

    /**
     * add Collidable to the environment.
     * @param c - Collidable.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }



    /**
     * add Sprite to the Sprite Collection.
     * @param s - Sprite.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * get the balls of the game from the level info -  add them to the game and
     * them to the game environment, set their velocity.
     */
    private void createBalls() {
        List<Ball> list = levelInfo.balls();
        //  create balls and add them to the game.
        if (levelInfo.numberOfBalls() == 1) {
            list.get(0).setVelocity(levelInfo.initialBallVelocities().get(0));
            list.get(0).addToGame(this);
            list.get(0).setGameEnvironment(this.environment);
        }
        if (levelInfo.numberOfBalls() == 2 || levelInfo.numberOfBalls() > 3) {
            for (int i = 0; i < levelInfo.numberOfBalls() / 2; i++) {
                list.get(i).setVelocity(levelInfo.initialBallVelocities().get(0));
                list.get(i).addToGame(this);
                list.get(i).setGameEnvironment(this.environment);
            }
            for (int i = levelInfo.numberOfBalls() / 2; i < levelInfo.numberOfBalls(); i++) {
                list.get(i).setVelocity(levelInfo.initialBallVelocities().get(1));
                list.get(i).addToGame(this);
                list.get(i).setGameEnvironment(this.environment);
            }
        }
        if (levelInfo.numberOfBalls() == 3) {
            for (int i = 0; i < levelInfo.numberOfBalls() / 3; i++) {
                list.get(i).setVelocity(levelInfo.initialBallVelocities().get(0));
                list.get(i).addToGame(this);
                list.get(i).setGameEnvironment(this.environment);
            }
            for (int i = levelInfo.numberOfBalls() / 3; i < 2 * levelInfo.numberOfBalls() / 3; i++) {
                list.get(i).setVelocity(levelInfo.initialBallVelocities().get(1));
                list.get(i).addToGame(this);
                list.get(i).setGameEnvironment(this.environment);
            }
            for (int i = 2 * levelInfo.numberOfBalls() / 3; i < levelInfo.numberOfBalls(); i++) {
                list.get(i).setVelocity(levelInfo.initialBallVelocities().get(2));
                list.get(i).addToGame(this);
                list.get(i).setGameEnvironment(this.environment);
            }
        }
    }



    /**
     * initialize a new game: create blocks, Ball and Paddle.
     *     and add them to the game.
     */
    public void initialize() {
        levelInfo.balls();
        remainingBalls.increase(levelInfo.numberOfBalls());

        //create background to the game.
        addSprite(levelInfo.getBackground());

        // add scoreIndicator to spirite.
        addSprite(this.scoreIndicator);

        //create BlockRemover and ballRemover.
        BlockRemover blockRemover = new BlockRemover(this, remainingBlocks);
        BallRemover ballRemover = new BallRemover(this, remainingBalls);


        //create paddle and add it to the game.
        biuoop.KeyboardSensor keyboard = gui.getKeyboardSensor();
        Rectangle padl = new Rectangle(new Point(360, 530),
                levelInfo.paddleWidth(), 8);
        Paddle paddle = new Paddle(keyboard, levelInfo.rectPaddle(), Color.cyan, gui,
                WIDTH, 0, levelInfo.paddleSpeed());
        paddle.addToGame(this);

        //create blocks in a pattern,add them to the game,add them has listeners.
        List<Block> list = levelInfo.blocks();
        for (int i = 0; i < levelInfo.numberOfBlocksToRemove(); i++) {
           list.get(i).addToGame(this);
            remainingBlocks.increase(1);
            list.get(i).addHitListener(blockRemover);
            //list.get(i).addHitListener(scoreTrack);
            list.get(i).addHitListener(scoreListener);
        }

        //add blocks at the borders of the screen, and add them to the game.
        Rectangle upX = new Rectangle(new Point(0, 25), 800, 25);
        Block upperX = new Block(upX, Color.gray);
        upperX.addToGame(this);

        Rectangle doX = new Rectangle(new Point(0, 620), 800, 2);
        Block downX = new Block(doX, Color.gray);
        downX.addToGame(this);
        downX.addHitListener(ballRemover);

        Rectangle leY = new Rectangle(new Point(0, 50), 25, 750);
        Block leftY = new Block(leY, Color.gray);
        leftY.addToGame(this);

        Rectangle riY = new Rectangle(new Point(775, 50), 25, 600);
        Block rightY = new Block(riY, Color.gray);
        rightY.addToGame(this);
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    @Override
    public void doOneFrame(DrawSurface d) {


        this.sprites.drawAllOn(d);
        d.drawText(650, 20, "Level Name: " + levelInfo.levelName(), 14);
        this.sprites.notifyAllTimePassed();

        //active the PauseScreen animation.
        if (this.keyboard.isPressed("p")) {
            KeyPressStoppableAnimation keyPress =
                    new KeyPressStoppableAnimation(keyboard, "p",
                            new PauseScreen(keyboard));
            this.runner.run(keyPress);
        }

        //check if all the blocks are removed.
        if (remainingBlocks.getValue() == 0) {
            this.sprites.drawAllOn(d);
            this.running = false;
        }

        //check if there are no balls.
        if (remainingBalls.getValue() == 0) {
            this.running = false;
        }
    }

    /**
     * @return the number of remaining Balls.
     */
    public Counter getRemainingBalls() {
        return remainingBalls;
    }

    /**
     * @return the number of remaining Blocks.
     */
    public Counter getRemainingBlocks() {
        return remainingBlocks;
    }

    /**
     * run the game and start the animation loop.
     */
    public void run() {
        //create the balls of the game.
        this.createBalls();
        // countdown before turn starts.
        this.runner.run(new CountdownAnimation(3, 3, sprites));
        this.running = true;
        // use runner to run the game animation.
        this.runner.run(this);
    }

    /**
     * remove Collidable to the environment.
     * @param c - Collidable.
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * remove Sprite to the Sprite Collection.
     * @param s - Sprite.
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }
}
