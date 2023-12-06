public class Bullet extends AnimatedActor{

    private int lifeTime;
    public Bullet(Vector2D target) {
        velocity.y = pos.ydiff(target);
        velocity.x = pos.xdiff(target);
        velocity.normalize();
        velocity.y *= 2;
        velocity.x *= 2;
        lifeTime = 300;
    }
    public void act(){
        lifeTime -=1;
        super.act();
    }
    public int getLifeTime(){
        return lifeTime;
    }
}
