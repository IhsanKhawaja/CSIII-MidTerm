public class Enemy extends AnimatedActor{
    private Health hp;
    private Animation walkR;
    private Animation walkL;
    public Enemy(int hp) {
        this.hp = new Health(hp);
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
    public void shouldTurningW() {
        if (isTouchingAtOffset( ((int) velocity.x / 2) * getWidth() / 2, 0, Wall.class) ||
                isTouchingAtOffset(((int) velocity.x / 2) * getWidth() / 2, 0 , Door.class) ||
                !isTouchingAtOffset(((int) velocity.x / 2) * getWidth() / 2, getHeight() , Wall.class)){
            velocity.x = -velocity.x;
        }
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

