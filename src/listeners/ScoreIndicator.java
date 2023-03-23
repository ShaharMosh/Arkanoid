//208001677 Shahar Moshonov
package listeners;
import game.GameLevel;
import sprites.Sprite;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * ScoreIndicator class- implements Sprite in charge of displaying the current score.
 * hold a reference to the scores counter, and will be added to
 * the game as a sprite positioned at the top of the screen.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;

    /**
     * constructor with configurables - Counter.
     * @param score - Counter.
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.lightGray);
        d.fillRectangle(0, 0, 800, 25);
        d.setColor(Color.black);
        String s = Integer.toString(score.getValue());
        d.drawText(365, 20, "score:" + s, 17);

    }

    @Override
    public void timePassed() { }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
