public class Bullet extends AnimatedActor{

    private int lifeTime;
    private Animation bullet;
    private Child child;
    public Bullet(Vector2D target,Animation bullet,Child child, Vector2D spawn) {
        pos.x = spawn.x;
        pos.y = spawn.y;
        velocity.y = pos.ydiff(target);
        velocity.x = pos.xdiff(target);
        velocity.normalize();
        this.child = child;
        velocity.x *= 4;
        velocity.y *= 4;
        velocity.y += .5f;
        grav = false;
        lifeTime = 300;
        bullet.setScale(32,20);
        if(velocity.x<0){
            bullet.flipX();
        }
        setAnimation(bullet);
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
    public int getLifeTime(){
        return lifeTime;
    }
}
