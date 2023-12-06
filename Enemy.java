public class Enemy extends AnimatedActor{
    private Health hp;
    private Animation walkR;
    private Animation walkL;
    private Child child;
    private int damageCool;
    public Enemy(int hp, Child child) {
        this.hp = new Health(hp);
        this.child = child;
    }
    public void act(){
        if (child.getAttack() && this.isTouching(Weapon.class) && damageCool==0) {
            hp.takeDamage(5);
            damageCool = 18;
        }
        if(damageCool>0) {
            if(damageCool == 18){
                walkR.SetTransparency(75);
                walkL.SetTransparency(75);
            }else if(damageCool == 1){
                walkR.SetTransparency(0);
                walkL.SetTransparency(0);
            }
            damageCool -= 1;
        }
        super.act();
    }
    public void setAnimation(int num){
        if(num == 2){
            setAnimation(walkR);
        }else if(num == 1){
            setAnimation(walkL);
        }
    }
    public void setAnimation(Animation anim){
            super.setAnimation(anim);
    }
    public Child getChild(){
        return child;
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
        walkR = new Animation(50,walk.getFrame());
        walkR.setScale(64,54);
        walkL = walk;
        walkL = new Animation(49,walk.getFrame());
        walkL.setScale(64,54);
        walkL.flipX();
        setAnimation(walkL);

    }
    public int getFrameRate(){
        return walkL.getFrameRate();
    }

    public int getHealth(){
        return hp.getHealth();
    }
}

