public class Bullet extends AnimatedActor{

    private int lifeTime;
    private Animation bullet;

    public Bullet(Vector2D target,Animation bullet,Child child, Vector2D spawn) {
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
    public void act(){
        pos.x = getX();
        pos.y = getY();
        lifeTime -=1;
        setLocation(getX() + velocity.x,getY() + velocity.y);
        if(isTouchingAtOffset(0,0,Child.class)){

        }
        super.act();
    }
    public boolean isBlocked(){
        return isTouching(Wall.class);
    }
    public int getLifeTime(){
        return lifeTime;
    }
}
