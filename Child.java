
import mayflower.*;

import java.util.Vector;

public class Child extends AnimatedActor{
    private Animation walk;
    private Animation jump;
    private Animation idle;
    private Vector2D velocity;
    private int speed;
    public Child (){
        speed = 5;
        String[] frames = new String[1];
        frames[0] = "Sprites/Player_Temp.png";
        walk = new Animation(50, frames);
        walk.setScale(16*4,16*4);
        setAnimation(walk);
        velocity = new Vector2D();
    }

    public void act(){
        velocity.x = 0;
        velocity.y = 0;

        if(Mayflower.isKeyDown(Keyboard.KEY_W)){
            velocity.y -= 1;
        }
        if(Mayflower.isKeyDown(Keyboard.KEY_A)){
            velocity.x -= 1;
        }
        if(Mayflower.isKeyDown(Keyboard.KEY_S)){
            velocity.y += 1;
        }
        if(Mayflower.isKeyDown(Keyboard.KEY_D)){
            velocity.x += 1;
        }

        //velocity.normalize();

        setLocation(getX()+velocity.x*speed, getY()+ velocity.y*speed);
        super.act();
    }
}
