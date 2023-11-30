public class Enemy extends AnimatedActor{
    private Health hp;
    private Vector2D pos;
    private Animation walkR;
    private Animation walkL;
    public Enemy(int hp, int xpos, int ypos, Animation walkAnim) {
        this.hp = new Health(hp);
        pos = new Vector2D(xpos,ypos);
        walkR = walkAnim;
        Animation walkAnimL = walkAnim;
        walkAnimL.flipX();
        walkL = walkAnimL;
        setAnimation(walkL);
    }
    public int getHealth(){
        return hp.getHealth();
    }
}

