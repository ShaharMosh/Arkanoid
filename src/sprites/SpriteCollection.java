//208001677 Shahar Moshonov

package sprites;
import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;

/**
 * SpriteCollection has Array List of Sprite.
 * methods - constructor, getter, addSprite - to thr list,
 *           notifyAllTimePassed-active timePassed method to spirits in the list,
 *           drawAllOn - active drawAllOn method to all spirits in the list.
 */
public class SpriteCollection {
    private ArrayList<Sprite> spriteList;


    /**
     * constructor - create Sprite collection.
     * @param spriteList - ArrayList of Sprite.
     */
    public SpriteCollection(ArrayList<Sprite> spriteList) {
        this.spriteList = spriteList;
    }

    /**
     * @return the Sprite list.
     */
    public ArrayList<Sprite> getSpriteList() {
        return spriteList;
    }

    /**
     * add sprite to the spriteList.
     * @param s - Sprite.
     */
    public void addSprite(Sprite s) {
        this.spriteList.add(s);
    }

    /**
     * remove sprite to the spriteList.
     * @param s - Sprite.
     */
    public void removeSprite(Sprite s) {
        this.spriteList.remove(s);
    }


    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spriteList = new ArrayList<Sprite>(this.spriteList);
        for (int i = 0; i < spriteList.size(); i++) {
            spriteList.get(i).timePassed();
        }
    }


    /**
     * call drawOn(d) on all sprites.
     * @param d - DrawSurface.
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < getSpriteList().size(); i++) {
            this.spriteList.get(i).drawOn(d);
        }
    }
}
