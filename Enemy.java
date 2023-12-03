public class Enemy extends AnimatedActor{
    private Health hp;
    private Animation walkR;
    private Animation walkL;
    private int collide;
    public Enemy(int hp) {
        this.hp = new Health(hp);
        collide = 0;

    }
    public void act(){
        super.act();
    }
    public void setAnimation(int num){
        if(num == 2){
            setAnimation(walkR);
        }else if(num == 1){
            setAnimation(walkL);
        }
    }
    public boolean isTurningW() {
        if (isTouchingAtOffset(getWidth() / 2, 0, Block.class)) {
            collide = 1;
            return true;
        } else if (isTouchingAtOffset(-getWidth() / 2, 0, Block.class)) {
            collide = -1;
            return true;
        }
        return false;
    }
    public int getCollide(){
        return collide;
    }

    public void setAnimationWalk(Animation walk){
        walkR = walk;
        walkL = new Animation(50,walkR.getFrame());
        walkL.setScale(64,54);
        walkL.flipX();
        setAnimation(walkL);

    }
    public int getHealth(){
        return hp.getHealth();
    }
}

