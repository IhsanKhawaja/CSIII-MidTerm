public class Enemy extends AnimatedActor{
    private Health hp;
    private Animation walkR;
    private Animation walkL;
    private Animation shootR;
    private Animation shootL;
    private Child child;
    private int damageCool;
    private int type;
    private Queue<Bullet> bullets;
    public Enemy(int hp, Child child,int type,boolean is2) {
        this.hp = new Health(hp);
        this.child = child;
        this.type = type;
        if(is2){
            bullets = new Queue<Bullet>();
        }
    }
    public void act(){
        if (child.getAttack() && this.isTouching(Weapon.class) && damageCool==0) {
            hp.takeDamage(5);
            damageCool = 18;
        }
        if(damageCool>0) {
            if(damageCool == 18){
                setTransparency(75);
            }else if(damageCool == 1){
                setTransparency(0);
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
    public void setTransparency(int transparency){
        if(walkL != null){
            walkL.SetTransparency(transparency);
        }
        if(walkR != null){
            walkR.SetTransparency(transparency);
        }
        if(shootR != null){
            shootR.SetTransparency(transparency);
        }
        if(shootL != null){
            shootL.SetTransparency(transparency);
        }
    }
    public int getType(){
        return type;
    }
    public void shouldTurningW() {
        if (isTouchingAtOffset( ((int) velocity.x / 2) * getWidth() / 2, 0, Wall.class) ||
                isTouchingAtOffset(((int) velocity.x / 2) * getWidth() / 2, 0 , Door.class)){
            velocity.x = -velocity.x;
            if(velocity.x > 0) setAnimation(1);
            if(velocity.x < 0) setAnimation(2);
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
    public void setShoot(Animation R,Animation L){
        shootR = R;
        shootL = L;
    }
    public int getHealth(){
        return hp.getHealth();
    }
    public Queue<Bullet> getBullets(){
        return bullets;
    }

}

