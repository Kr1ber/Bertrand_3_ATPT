/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slickexample;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author Kriber
 */
public class Orb {

    private int width, height;
    private float x, y;
    private int dmg, hitboxX, hitboxY, hitboxWidth, hitboxHeight, direction = 0;
    private boolean isVisible;
    Image orbpic;
    Shape hitbox;

    public Orb(float a, float b) throws SlickException {
        this.x = a;
        this.y = b;
        this.isVisible = false;
        this.orbpic = new Image("res/orbs/Ninja_21.png");
        this.hitbox = new Rectangle(a, b, 32, 32);
    }
    /*
     **
     * Getters and setters are a common concept in Java.
     * a design guideline in Java, and object oriented
     * programming in general, is to encapsulate/isolate
     * values as much as possible.
     * Getters - are methods used to query the value of
     * instance variables.
     * this.getLocationX();
     * Setters - methods that set values for instance
     * variables.
     */

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public void setHitboxX(int hitboxX) {
        this.hitboxX = hitboxX;
    }

    public void setHitboxY(int hitboxY) {
        this.hitboxY = hitboxY;
    }

    public void setHitboxWidth(int hitboxWidth) {
        this.hitboxWidth = hitboxWidth;
    }

    public void setHitboxHeight(int hitboxHeight) {
        this.hitboxHeight = hitboxHeight;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public void setOrbpic(Image orbpic) {
        this.orbpic = orbpic;
    }

//    void setIsVisible(boolean b) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    boolean isIsVisible() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    void setX(int i) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    void setY(int i) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    float getX() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    float getY() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    public void setHitbox(Shape hitbox) {
        this.hitbox = hitbox;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getDmg() {
        return dmg;
    }

    public int getHitboxX() {
        return hitboxX;
    }

    public int getHitboxY() {
        return hitboxY;
    }

    public int getHitboxWidth() {
        return hitboxWidth;
    }

    public int getHitboxHeight() {
        return hitboxHeight;
    }

    public boolean isIsVisible() {
        return isVisible;
    }

    public Image getOrbpic() {
        return orbpic;
    }

    public Shape getHitbox() {
        return hitbox;
    }

    int isIsvisible() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }

    void settimeExists(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
