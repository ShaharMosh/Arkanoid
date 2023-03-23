//208001677 Shahar Moshonov
package game;
import java.util.ArrayList;

import collision.Collidable;
import collision.CollisionInfo;
import geometry.Point;
import geometry.Line;

/**
 * GameEnvironment has Array List of Collidable.
 * methods -constructor, getters, getClosestCollision.
 */
public class GameEnvironment {
    private ArrayList<Collidable> collidableList;

    /**
     * constructor - create Collidable collection.
     * @param collidableList - ArrayList of Collidable.
     */
    public GameEnvironment(ArrayList<Collidable> collidableList) {
        this.collidableList = collidableList;
    }

    /**
     * add the given collidable to the environment.
     * @param c Collidable.
     */
    public void addCollidable(Collidable c) {
        this.collidableList.add(c);
    }

    /**
     * remove the given collidable to the environment.
     * @param c Collidable.
     */
    public void removeCollidable(Collidable c) {
        collidableList.remove(c);
    }

    /**
     * @return the collidable list.
     */
    public ArrayList<Collidable> getCollidableList() {
        return collidableList;
    }



    /**
     * check if an object move on a trajectory will collide with one of the
     * collidables in this collection.
     * @param trajectory - track of the object.
     * @return - if not collide- null.
     *           Else, return the information about the closest collision
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        ArrayList<Collidable> collisionColidable = new ArrayList<>();
        int place = 0;

        /*
        check if the object collide with one of the collidables list,
        and save the points in a list.
         */
        for (int i = 0; i < getCollidableList().size(); i++) {
            Point point = trajectory.closestIntersectionToStartOfLine(
                    getCollidableList().get(i).getCollisionRectangle());
            if (point != null) {
                collisionColidable.add(getCollidableList().get(i));
            }
        }

        //if this object dont collide with any of the collidables - return null.
        if (collisionColidable.size() == 0) {
            return null;
        }
        //find the closest collision that is going to occur
        //(from the array of points we save before)
        Point point = trajectory.closestIntersectionToStartOfLine(
                collisionColidable.get(0).getCollisionRectangle());
        double distanceDef = point.distance(trajectory.start());
        for (int i = 0; i < collisionColidable.size(); i++) {
            Point p = trajectory.closestIntersectionToStartOfLine(
                    collisionColidable.get(i).getCollisionRectangle());
            if (p.distance(trajectory.start()) < distanceDef) {
                place = i;
                distanceDef = p.distance(trajectory.start());
            }
        }
        return new CollisionInfo(trajectory.closestIntersectionToStartOfLine(
                collisionColidable.get(place).getCollisionRectangle()),
                collisionColidable.get(place));
    }
    }

