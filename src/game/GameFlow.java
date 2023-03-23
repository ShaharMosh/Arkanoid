//208001677 Shahar Moshonov
package game;

import biuoop.GUI;
import biuoop.KeyboardSensor;
import collision.Collidable;
import listeners.Counter;
import listeners.ScoreIndicator;
import listeners.ScoreTrackingListener;
import sprites.Sprite;
import sprites.SpriteCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * GameFlow class - in charge of creating the different levels, and moving from
 * one level to the next. check if the player win or lose in the game.
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private GUI gui;
    private boolean stop;
    private boolean finish;

    /**
     * constructor with configurables - GUI, AnimationRunner.
     * @param gui - GUI.
     * @param ar - AnimationRunner.
     */
    public GameFlow(GUI gui, AnimationRunner ar) {
        this.gui = gui;
        this.animationRunner = ar;
        this.stop = false;
    }

    /**
     * run the different levels, and moving from one level to the next,
     * and check if the player win or lose in the game.
     * @param levels - List of LevelInformation.
     */
    public void runLevels(List<LevelInformation> levels) {
        Counter counter = new Counter(0);
        ScoreIndicator scoreIndicator = new ScoreIndicator(counter);
        ScoreTrackingListener scoreListener = new ScoreTrackingListener(counter);
        KeyboardSensor keyboardSensor = gui.getKeyboardSensor();

        //go over the different levels.
        for (LevelInformation levelInfo : levels) {
            ArrayList<Collidable> collidableList = new ArrayList<>();
            GameEnvironment gameEnvironment = new GameEnvironment(collidableList);
            ArrayList<Sprite> spriteArrayList = new ArrayList<>();
            SpriteCollection spriteCollection = new SpriteCollection(spriteArrayList);
            GameLevel level = new GameLevel(gameEnvironment,
                    spriteCollection, this.gui, this.animationRunner,
                    levelInfo, scoreIndicator, scoreListener);
            level.initialize();

            while (level.getRemainingBalls().getValue() != 0
                    && level.getRemainingBlocks().getValue() != 0) {
                level.run();
            }

            //add 100 points to score and tell that the player win.
            if (level.getRemainingBlocks().getValue() == 0) {
                counter.increase(100);
                finish = true;
            }

            //check if there are no balls ---> the player lose the game.
            if (level.getRemainingBalls().getValue() == 0) {
                finish = false;
            }

            //active the GameOver animation.
            if (!finish) {
                KeyPressStoppableAnimation keyPress =
                        new KeyPressStoppableAnimation(keyboardSensor, "space",
                                new GameOver(keyboardSensor, counter.getValue()));
                animationRunner.run(keyPress);
                gui.close();
            }
        }



        //active the YouWin animation.
        if (finish) {
            KeyPressStoppableAnimation keyPress =
                    new KeyPressStoppableAnimation(keyboardSensor, "space",
                            new YouWin(keyboardSensor, counter.getValue()));
            animationRunner.run(keyPress);
        }

        gui.close();
    }
}

