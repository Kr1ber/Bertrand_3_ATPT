package slickexample;

import org.newdawn.slick.state.*;

import java.io.IOException;

import java.util.ArrayList;

import java.util.Iterator;

import java.util.logging.Level;

import java.util.logging.Logger;
import static jdk.nashorn.tools.ShellFunctions.input;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;

import org.newdawn.slick.AppGameContainer;

import org.newdawn.slick.BasicGame;

import org.newdawn.slick.Font;

import org.newdawn.slick.GameContainer;

import org.newdawn.slick.Graphics;

import org.newdawn.slick.Image;

import org.newdawn.slick.Input;

import org.newdawn.slick.SlickException;

import org.newdawn.slick.SpriteSheet;

import org.newdawn.slick.TrueTypeFont;

import org.newdawn.slick.geom.Rectangle;

import org.newdawn.slick.geom.Shape;

import org.newdawn.slick.state.BasicGameState;

import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import org.newdawn.slick.tiled.TiledMap;
import org.w3c.dom.css.Rect;
import static slickexample.Player.down;
import static slickexample.Player.left;
import static slickexample.Player.right;
import static slickexample.Player.sprite;
import static slickexample.Player.up;

class blocked {

    public static boolean[][] blocked;

    public static boolean[][] getblocked() {

        return blocked;

    }

};

class item1 {

    public int x;
    public int y;
    public boolean isvisible = true;
    Image currentImage;
    Shape hitbox;
    Image healthpotion = new Image("res/speed_potion.png");

    item1(int a, int b) throws SlickException {
        this.x = a;
        this.y = b;
        this.hitbox = new Rectangle(a, b, 32, 32);// 64 is the width of the item
        this.currentImage = healthpotion;

    }

}

class itemwin {

    public int x;
    public int y;
    public static boolean isvisible = true;
    Image currentImage;
    Shape hitbox;
    Image antidote = new Image("res/antidote.png");

    itemwin(int a, int b) throws SlickException {
        this.x = a;
        this.y = b;
        this.hitbox = new Rectangle(a, b, 32, 32);// 64 is the width of the item
        this.currentImage = antidote;

    }

}

public class ATPT extends BasicGameState {

    public Player player;
    public Item healthpotion, healthpotion1;
    public item1 speedpotion, speedpotion1;
    public itemwin antidote;
    public Enemy Aldo;
    public Orb orb1;
    public ArrayList<Enemy> enemies = new ArrayList();
    public ArrayList<Item> stuff = new ArrayList();
    public ArrayList<item1> stuff1 = new ArrayList();
    public ArrayList<itemwin> stuffwin = new ArrayList();
    private boolean[][] hostiles;
    private static TiledMap grassMap;
    private static AppGameContainer app;
    private static Camera camera;
    public static int counter = 0;
    private static final int SIZE = 32;

    private static final int SCREEN_WIDTH = 1000;

    private static final int SCREEN_HEIGHT = 750;

    public ATPT(int xSize, int ySize) {

    }

    public void init(GameContainer gc, StateBasedGame sbg)
            throws SlickException {

        gc.setTargetFrameRate(700);

        gc.setShowFPS(false);

        grassMap = new TiledMap("res/Map.tmx");

        // Ongoing checks are useful
        System.out.println("Tile map is this wide: " + grassMap.getWidth());

        camera = new Camera(gc, grassMap);
        player = new Player();

        blocked.blocked = new boolean[grassMap.getWidth()][grassMap.getHeight()];

        System.out.println("The grassmap is " + grassMap.getWidth() + "by "
                + grassMap.getHeight());

        for (int xAxis = 0; xAxis < grassMap.getWidth(); xAxis++) {

            for (int yAxis = 0; yAxis < grassMap.getHeight(); yAxis++) {

                int tileID = grassMap.getTileId(xAxis, yAxis, 1);

                String value = grassMap.getTileProperty(tileID,
                        "blocked", "false");

                if ("true".equals(value)) {

                    System.out.println("The tile at x " + xAxis + " andy axis "
                            + yAxis + " is blocked.");

                    blocked.blocked[xAxis][yAxis] = true;

                }

            }

        }

        System.out.println("Array length" + blocked.blocked[0].length);

        // A remarkably similar process for finding hostiles
        hostiles = new boolean[grassMap.getWidth()][grassMap.getHeight()];

        for (int xAxis = 0; xAxis < grassMap.getWidth(); xAxis++) {
            for (int yAxis = 0; yAxis < grassMap.getHeight(); yAxis++) {
                int xBlock = (int) xAxis;
                int yBlock = (int) yAxis;
                if (!blocked.blocked[xBlock][yBlock]) {
                    if (yBlock % 7 == 0 && xBlock % 15 == 0) {
                        Item i = new Item(xAxis * SIZE, yAxis * SIZE);
                        stuff.add(i);
                        //stuff1.add(h);
                        hostiles[xAxis][yAxis] = true;
                    }
                }
            }
        }

        for (int xAxis = 0; xAxis < grassMap.getWidth(); xAxis++) {
            for (int yAxis = 0; yAxis < grassMap.getHeight(); yAxis++) {
                int xBlock = (int) xAxis;
                int yBlock = (int) yAxis;
                if (!blocked.blocked[xBlock][yBlock]) {
                    if (xBlock % 9 == 0 && yBlock % 25 == 0) {
                        item1 h = new item1(xAxis * SIZE, yAxis * SIZE);
                        //	stuff.add(i);
                        stuff1.add(h);
                        hostiles[xAxis][yAxis] = true;
                    }
                }
            }
        }

        healthpotion = new Item(100, 100);
        healthpotion1 = new Item(450, 400);
        stuff.add(healthpotion);
        stuff.add(healthpotion1);
        orb1 = new Orb(player.x, player.y);
        Aldo = new Enemy(60, 60);

        speedpotion = new item1(100, 150);
        speedpotion1 = new item1(450, 100);
        stuff1.add(speedpotion);
        stuff1.add(speedpotion1);

        antidote = new itemwin(3004, 92);
        stuffwin.add(antidote);
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
            throws SlickException {

        camera.centerOn((int) Player.x, (int) Player.y);

        camera.drawMap();

        camera.translateGraphics();

        // it helps to add status reports to see what's going on
        // but it gets old quickly
        // System.out.println("Current X: " +player.x + " \n Current Y: "+ y);
        player.sprite.draw((int) Player.x, (int) Player.y);

        //g.drawString("x: " + (int)player.x + "y: " +(int)player.y , player.x, player.y - 10);
        g.drawString("Health: " + Player.health / 1000, camera.cameraX + 10,
                camera.cameraY + 10);

        g.drawString("speed: " + (int) (Player.speed * 10), camera.cameraX + 10,
                camera.cameraY + 25);

        //g.draw(player.rect);
        g.drawString("time passed: " + counter / 1000, camera.cameraX + 600, camera.cameraY);
        // moveenemies();
        if (orb1.isIsVisible()) {
            orb1.orbpic.draw(orb1.getX(), orb1.getY());
        }

        for (Item i : stuff) {
            if (i.isvisible) {
                i.currentImage.draw(i.x, i.y);
                // draw the hitbox
                //g.draw(i.hitbox);

            }
        }
        for (item1 h : stuff1) {
            if (h.isvisible) {
                h.currentImage.draw(h.x, h.y);
                // draw the hitbox
                //g.draw(h.hitbox);

            }
        }

        for (itemwin w : stuffwin) {
            if (w.isvisible) {
                w.currentImage.draw(w.x, w.y);
                // draw the hitbox
                //g.draw(w.hitbox);

            }
        }
        for (Enemy e : enemies) {
            if (orb1.hitbox.intersects(e.rect)) {
                e.isVisible = true;
                if (e.isVisible) {
                    e.currentanime.draw(e.Bx, e.By);
                    // draw the hitbox
                    //g.draw(w.hitbox);

                }
            }
        }
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta)
            throws SlickException {

        counter += delta;

        Input input = gc.getInput();

        float fdelta = delta * Player.speed;

        Player.setpdelta(fdelta);

        double rightlimit = (grassMap.getWidth() * SIZE) - (SIZE * 0.75);

        // System.out.println("Right limit: " + rightlimit);
        float projectedright = Player.x + fdelta + SIZE;

        boolean cangoright = projectedright < rightlimit;

        for (Enemy e : enemies) {

            // e.setdirection();
            e.move();
        }

        if (input.isKeyPressed(Input.KEY_S)) {
//            orb1.setIsVisible(true);
            
            orb1.setX((int) player.x);
            orb1.setY((int) player.y);
            orb1.hitbox.setX(orb1.getX());
            orb1.hitbox.setY(orb1.getY());
            orb1.setIsVisible(!orb1.isIsVisible());
            if (orb1.isIsVisible()) {
            orb1.setDirection(player.getDirection());
            }
            // there are two types of fixes. A kludge and a hack. This is a kludge.
        }
        if (input.isKeyDown(Input.KEY_UP)) {

            player.sprite = up;
            player.setDirection(0);
            float fdsc = (float) (fdelta - (SIZE * .15));

            if (!(isBlocked(Player.x, Player.y - fdelta) || isBlocked((float) (Player.x + SIZE + 1.5), Player.y - fdelta))) {

                player.sprite.update(delta);
                Player.y -= fdelta;
            }// The lower the delta the slower the sprite will animate

        } else if (input.isKeyDown(Input.KEY_DOWN)) {

            player.sprite = down;
            player.setDirection(2);
            if (!isBlocked(Player.x, Player.y + SIZE + fdelta)
                    || !isBlocked(Player.x + SIZE - 1, Player.y + SIZE + fdelta)) {

                sprite.update(delta);

                Player.y += fdelta;

            }

        } else if (input.isKeyDown(Input.KEY_LEFT)) {

            player.sprite = left;
            player.setDirection(3);
            if (!(isBlocked(Player.x - fdelta, Player.y) || isBlocked(Player.x
                    - fdelta, Player.y + SIZE - 1))) {

                player.sprite.update(delta);

                Player.x -= fdelta;

            }

        } else if (input.isKeyDown(Input.KEY_RIGHT)) {

            player.sprite = right;
            player.setDirection(1);
            // the boolean-kludge-implementation
            if (cangoright
                    && (!(isBlocked(Player.x + SIZE + fdelta,
                            Player.y) || isBlocked(Player.x + SIZE + fdelta, Player.y
                            + SIZE - 1)))) {

                player.sprite.update(delta);

                Player.x += fdelta;

            } // else { System.out.println("Right limit reached: " +
            // rightlimit);}

        }
        if (orb1.isIsVisible() && orb1.getDirection() == 0) {
            orb1.setY(orb1.getY() - 1);
        } else if (orb1.isIsVisible() && orb1.getDirection() == 1) {
            orb1.setX(orb1.getX() + 1);
        } else if (orb1.isIsVisible() && orb1.getDirection() == 2) {
            orb1.setY(orb1.getY() + 1);
        } else if (orb1.isIsVisible() && orb1.getDirection() == 3) {
            orb1.setX(orb1.getX() - 1);
        }
        Player.rect.setLocation(Player.getplayershitboxX(),
                Player.getplayershitboxY());

        for (Item i : stuff) {

            if (Player.rect.intersects(i.hitbox)) {
                //System.out.println("yay");
                if (i.isvisible) {

                    Player.health += 1000;
                    i.isvisible = false;
                }

            }
        }
        for (Enemy e : enemies) {

            if (Player.rect.intersects(e.rect)) {
                //System.out.println("yay");
                if (e.isVisible) {

                    Player.health -= 100;
                    e.isVisible = true;
                }

            }
        }

        for (item1 h : stuff1) {

            if (Player.rect.intersects(h.hitbox)) {
                //System.out.println("yay");
                if (h.isvisible) {

                    Player.speed += .1f;
                    h.isvisible = false;
                }

            }
        }

        for (itemwin w : stuffwin) {

            if (Player.rect.intersects(w.hitbox)) {
                //System.out.println("yay");
                if (w.isvisible) {
                    w.isvisible = false;
                    makevisible();
                    sbg.enterState(3, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));

                }

            }
            if (orb1.isIsVisible()) {
                orb1.orbpic.draw(orb1.getX(), orb1.getY());
            }
        }

        Player.health -= counter / 10000;
        if (Player.health <= 0) {
            makevisible();
            sbg.enterState(2, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
        }

    }

    public int getID() {

        return 1;

    }

    public void makevisible() {
        for (item1 h : stuff1) {

            h.isvisible = true;
        }

        for (Item i : stuff) {

            i.isvisible = true;
        }
    }

    private boolean isBlocked(float tx, float ty) {

        int xBlock = (int) tx / SIZE;

        int yBlock = (int) ty / SIZE;

        return blocked.blocked[xBlock][yBlock];

        // this could make a better kludge
    }

}
