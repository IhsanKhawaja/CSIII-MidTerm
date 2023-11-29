
import mayflower.*;

import java.security.Key;

public class Child extends AnimatedActor{
    private Animation walk;
    private Animation jump;
    private Animation idle;
    private int speed;
    private int dashCooldown;
    private boolean dash;
    private int dashDuration;
    public Child (){
        dashCooldown = 0;
        speed = 3;
        String[] frames = new String[1];
        frames[0] = "Sprites/Player_Temp.png";
        walk = new Animation(50, frames);
        walk.setScale(16*4,16*4);
        setAnimation(walk);
        velocity = new Vector2D();
    }

    public void act(){
        if(!dash) {
            velocity.x = 0;
        }
        if (dashDuration < 0 && dash){
            dash = false;
            grav = true;
            velocity.y = 0;
        } else {
            dashDuration--;
        }

        if(this.isTouching(Block.class) && dashCooldown > 20){
            dashCooldown = 20;
        }

        if(Mayflower.isKeyDown(Keyboard.KEY_W) && this.isTouching(Block.class) && !dash){
            velocity.y = -2.2f;
        } else if(Mayflower.isKeyDown(Keyboard.KEY_W) && !dash){
            velocity.y -= 0.045f;
        }
        if(Mayflower.isKeyDown(Keyboard.KEY_A) && !dash){
            velocity.x -= 1;
        }
        if(Mayflower.isKeyDown(Keyboard.KEY_S) && !dash){
            velocity.y += 0.1f;
        }
        if(Mayflower.isKeyDown(Keyboard.KEY_D) && !dash){
            velocity.x += 1;
        }

        if(Mayflower.isKeyPressed(Keyboard.KEY_SPACE) && dashCooldown < 0){
            dashCooldown = 100;
            if(Mayflower.isKeyDown(Keyboard.KEY_W)) velocity.y -= 6f;
            if(Mayflower.isKeyDown(Keyboard.KEY_A)) velocity.x -= 6f;
            if(Mayflower.isKeyDown(Keyboard.KEY_S)) velocity.y += 6f;
            if(Mayflower.isKeyDown(Keyboard.KEY_D)) velocity.x += 6f;
            dashDuration = 5;
            dash = true;
            grav = false;
        }

        setLocation(getX()+velocity.x*speed, getY()+ velocity.y*speed);
        super.act();
        if (dashCooldown >= 0){
            dashCooldown--;
        }
    }
}
