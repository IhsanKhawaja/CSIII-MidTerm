public class Bullet extends AnimatedActor{

    private int lifeTime;
    private Animation bullet;
    private Child child;
    /*
        Sets the child variable to be equal to the player
        Sets the position of the bullet to be the location of the enemy that is shooting it
        Sets the velocity of the bullet to go towards the position of the player
        Sets the gravity to false and the lifetime to 300 frames
        Sets the animation of the bullet and the scale and if it is going to the left it flips the animation
        Sets the bullets animation to the new animation
     */
    public Bullet(Vector2D target,Animation bullet,Child child, Vector2D spawn) {
        this.child = child;
        pos.x = spawn.x;
        pos.y = spawn.y;
        velocity.y = pos.ydiff(target);
        velocity.x = pos.xdiff(target);
        velocity.normalize();
        velocity.x *= 4;
        velocity.y *= 4;
        velocity.y += .5f;
        grav = false;
        lifeTime = 300;
        this.bullet = new Animation(50,bullet.getFrame());
        this.bullet.setScale(32,20);
        if(velocity.x<0){
            this.bullet.flipX();
        }
        setAnimation(this.bullet);
    }
    /*
        Updates the position of the bullet and decreases the lifetime by 1
        Changes the position of the bullet and checks if it is touching the child
        If it is touching the child makes the child take 1 damage
        Calls super.act()
     */
    public void act(){
        pos.x = getX();
        pos.y = getY();
        lifeTime -=1;
        setLocation(getX() + velocity.x,getY() + velocity.y);
        if(isTouchingAtOffset(0,0,Child.class)){
            child.takeDamage(1);
        }
        super.act();
    }
    /*
        Returns true if the bullet is touching a block and false otherwise
     */
    public boolean isBlocked(){
        return isTouching(Block.class);
    }
    /*
        Returns the remaining lifetime of the bullet
     */
    public int getLifeTime(){
        return lifeTime;
    }
}
