//208001677 Shahar Moshonov

import biuoop.GUI;
import game.GameLevel;
import game.AnimationRunner;
import game.LevelInformation;
import game.Level1;
import game.Level2;
import game.Level3;
import game.Level4;
import game.GameFlow;
import java.util.ArrayList;
import java.util.List;

/**
 * Ass6Game class- create a LevelInformation, pass it to GameLevel,
 * and then run the GameLevel.
 */
public class Ass6Game {

    private GameLevel game;
    static final int WIDTH = 800;
    static final int HEIGHT = 600;

    /**
     * set the GAME class.
     * @param game - Game.
     */
    public void setGame(GameLevel game) {
        this.game = game;
    }



    /**
     * create collidable List and sprite List, GUI and game.
     * active initialize and run methods.
     * @param args .
     */
    public static void main(String[] args) {
        GUI gui = new GUI("game", WIDTH, HEIGHT);
        AnimationRunner animationRunner = new AnimationRunner(gui);
        List<LevelInformation> levels = new ArrayList<>();
        LevelInformation level1 = new Level1();
        LevelInformation level2 = new Level2();
        LevelInformation level3 = new Level3();
        LevelInformation level4 = new Level4();
        if (args.length == 0) {
            levels.add(level1);
            levels.add(level2);
            levels.add(level3);
            levels.add(level4);
        }

        for (String str : args) {
        switch (str) {
            case "1":
                levels.add(level1);
                break;
            case "2":
                levels.add(level2);
                break;
            case "3":
                levels.add(level3);
                break;
            case "4":
                levels.add(level4);
                break;
            default:
        }
        }
            GameFlow gameFlow = new GameFlow(gui, animationRunner);
            gameFlow.runLevels(levels);
    }
}
