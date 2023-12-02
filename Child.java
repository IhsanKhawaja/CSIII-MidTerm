
import mayflower.*;

public class Child extends AnimatedActor{
    public Weapon umbrella;
    private Animation walk;
    private Animation jump;
    private Animation idle;
    private final int speed;
    private int dashCoolDown;
    private boolean dash;
    private int dashDuration;
    private final MyMouse mouse;
    private boolean attack;
    private int swingTimer;
    private double rotation;

    public Child (MyMouse mouse){
        swingTimer = 0;
        rotation = 0.0;
        attack = false;
        dashCoolDown = 0;
        speed = 3;
        String[] frames = new String[1];
        frames[0] = "Sprites/Player_Temp.png";
        walk = new Animation(50, frames);
        walk.setScale(16*4,16*4);
        setAnimation(walk);
        velocity = new Vector2D();
        umbrella = new Weapon();
        this.mouse = mouse;
    }

    public void act(){
        if(!dash) {
            velocity.x = 0;
        }
        if (dashDuration < 0 && dash){
            dash = false;
            grav = true;
            velocity.y = -.5f;
        } else {
            dashDuration--;
        }

        if(this.isTouching(Block.class) && dashCoolDown > 20){
            dashCoolDown = 20;
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
        if(Mayflower.isKeyDown(Keyboard.KEY_D) && !dash) {
            velocity.x += 1;
        }

        if(Mayflower.isKeyPressed(Keyboard.KEY_SPACE) && dashCoolDown < 0){
            dashCoolDown = 100;
            if(Mayflower.isKeyDown(Keyboard.KEY_W)) velocity.y -= 6f;
            else if(Mayflower.isKeyDown(Keyboard.KEY_S) && !this.isTouchingAtOffset(0,getHeight(),Block.class)) velocity.y += 6f;
            else velocity.y = 0;

            if(Mayflower.isKeyDown(Keyboard.KEY_A)) velocity.x -= 6f;
            if(Mayflower.isKeyDown(Keyboard.KEY_D)) velocity.x += 6f;
            dashDuration = 5;
            dash = true;
            grav = false;
        }

        if(umbrella.touchBlock() && (umbrella.getRotation() < 130 && umbrella.getRotation() > 50) && swingTimer >= 37) velocity.y = -1;

        setLocation(getX()+velocity.x*speed, getY()+ velocity.y*speed);
        super.act();

        if (dashCoolDown >= 0) dashCoolDown--;
        if(swingTimer > 0) swingTimer--;
        else attack = false;

        if(Mayflower.mouseDown(mouse) && !attack){
            rotation = Math.atan2(umbrella.pos.ydiff(mouse.pos()), umbrella.pos.xdiff(mouse.pos()));
            umbrella.setRotation((int) Math.toDegrees(rotation));
            attack = true;
            swingTimer = 45;
            umbrella.swing();
        } else if(swingTimer > 30){
            umbrella.pos = new Vector2D((70*(float)Math.cos(rotation)+getX()-17), (70*(float)Math.sin(rotation)+getY()-17));
        } else {
            umbrella.pos.x = getX()+20;
            umbrella.pos.y = getY()+10;
            umbrella.setRotation(0);
            umbrella.idle();
        }
    }
}
