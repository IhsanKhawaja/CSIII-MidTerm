import jaco.mp3.player.A;
import mayflower.Actor;
import mayflower.Mayflower;
import mayflower.Mouse;

public class Weapon extends AnimatedActor {
    private Animation weapon;
    private Animation swing;
    public Vector2D pos;
    private boolean flipped;

    public Weapon(){
        pos = new Vector2D();
        flipped = false;
        String[] frames = new String[1];
        frames[0] = "Sprites/umbrella.png";
        weapon = new Animation(50,frames);
        weapon.setScale(16*3,16*3);
        String[] swingFrames = new String[1];
        swingFrames[0] = "Sprites/Swing.png";
        swing = new Animation(50, swingFrames);
        swing.setScale(100,100);
        setAnimation(weapon);
    }
    public void act(){
        setLocation(pos.x, pos.y);
        super.act();
    }

    public void swing(){
        setAnimation(swing);
    }

    public void idle(){
        setAnimation(weapon);
    }

    public boolean touchBlock(){
        return this.isTouching(Wall.class);
    }
}
