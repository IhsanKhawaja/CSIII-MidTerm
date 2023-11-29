import mayflower.Actor;

public class gravity extends Actor {
    public Vector2D velocity;
    static int fallspeed = 7;
    public boolean grav;
    public gravity(){
        velocity = new Vector2D();
        grav = true;
    }

    public void act() {
        if(grav) {
            velocity.y += 0.1f;
        }

        if(velocity.y > fallspeed) velocity.y = fallspeed;
        if(this.isTouching(Block.class)) velocity.y = 0;
    }
}
