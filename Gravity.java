import mayflower.Actor;

public class Gravity extends Actor {
    public Vector2D velocity;
    public boolean grav;
    public Gravity(){
        velocity = new Vector2D();
        grav = true;
    }

    public void act() {
        if(grav) {
            velocity.y += 0.1f;
        }

        int fallSpeed = 7;
        if(velocity.y > fallSpeed) velocity.y = fallSpeed;
        if(this.isTouching(Block.class)) velocity.y = 0;
        if(this.isTouchingAtOffset(10,0,Block.class)) velocity.x = 0;
    }
}
