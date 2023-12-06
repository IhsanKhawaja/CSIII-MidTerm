
import mayflower.*;

public class Child extends AnimatedActor{
    public Weapon umbrella;
    private final Animation walk;
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
    private Health health;
    private int iframes;

    public Child (MyMouse mouse){
        iframes = 0;
        swingTimer = 0;
        rotation = 0.0;
        health = new Health(10);
        attack = false;
        dashCoolDown = 0;
        speed = 3;
        String[] frames = new String[1];
        frames[0] = "Sprites/Player_Temp.png";
        walk = new Animation(50, frames);
        System.out.println("child");
        walk.setScale(16*4,16*4);
        setAnimation(walk);
        umbrella = new Weapon();
        this.mouse = mouse;
    }

    public void act(){
        if(isTouchingAtOffset(0,0,Ladder.class)) velocity.y = 0;
        if(isTouchingAtOffset(0,0,Ladder.class) && Mayflower.isKeyDown(Keyboard.KEY_W)) velocity.y -= 1;
        if(isTouchingAtOffset(0,0,Ladder.class) && Mayflower.isKeyDown(Keyboard.KEY_S)) velocity.y += 1;

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

        if((this.isTouchingAtOffset(0,getHeight()/2,Wall.class) || isTouchingAtOffset(0,0,Ladder.class)) && dashCoolDown > 30){
            dashCoolDown = 20;
        }

        if(Mayflower.isKeyDown(Keyboard.KEY_W) && this.isTouchingAtOffset(0,getHeight()/2,Wall.class) && !dash){
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
            dashCoolDown = 1000;
            if(Mayflower.isKeyDown(Keyboard.KEY_W)) velocity.y -= 6f;
            else if(Mayflower.isKeyDown(Keyboard.KEY_S) && !this.isTouchingAtOffset(0,getHeight(),Wall.class)) velocity.y += 6f;
            else velocity.y = 0;

            if(Mayflower.isKeyDown(Keyboard.KEY_A)) velocity.x -= 6f;
            if(Mayflower.isKeyDown(Keyboard.KEY_D)) velocity.x += 6f;
            dashDuration = 5;
            dash = true;
            grav = false;
        }

        if(umbrella.touchBlock() && (umbrella.getRotation() < 130 && umbrella.getRotation() > 50) && swingTimer == 37) velocity.y = -1;

        if(this.isTouchingAtOffset(getWidth()/2+1 + (int) velocity.x,0,Wall.class) && velocity.x > 0) velocity.x = 0;
        if(this.isTouchingAtOffset(-getWidth()/2-2 + (int) velocity.x,0,Wall.class) && velocity.x < 0) velocity.x = 0;
        if(this.isTouchingAtOffset(0,-getHeight()/2 + (int) velocity.y,Wall.class)){
            setLocation(getX(),getY()+1);
            velocity.y = 0;
        }
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

        if(isTouching(Enemy.class) && iframes == 0){
            health.takeDamage(1);
            iframes = 150;
            velocity.y = -3;
        }
        if(iframes > 0){
            iframes--;
            System.out.println(iframes);
        }
    }

    public int getHealth() {
        return health.getHealth();
    }

    public boolean door(){
        return isTouchingAtOffset(0,getHeight()/4,Door.class);
    }

    public boolean getAttack(){
        return attack;
    }
}
