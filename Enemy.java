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
        if(num == 1){
            setAnimation(walkR);
        }else if(num == 2){
            setAnimation(walkL);
        }
    }
    public void setAnimationWalk(Animation walk){
        this.walkR = walk;
        Animation walkAnimL = walk;
        walkAnimL.flipX();
        walkL = walkAnimL;
        setAnimation(walkL);

    }
    public int getHealth(){
        return hp.getHealth();
    }
}

