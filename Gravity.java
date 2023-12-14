import mayflower.Actor;

public class Gravity extends Actor {
    public Vector2D velocity;
    public Vector2D pos;
    public boolean grav;
    public boolean collision;
    public Gravity(){
        velocity = new Vector2D();
        pos = new Vector2D();
        grav = true;
        collision = true;
    }

    public void act() {
        if(grav) {
            velocity.y += 0.1f;
        }

        int fallSpeed = 7;
        if(velocity.y > fallSpeed) velocity.y = fallSpeed;
        if(this.isTouchingAtOffset(0,getHeight()/2 + (int) velocity.y,Wall.class) && collision) velocity.y = 0;
    }
}
