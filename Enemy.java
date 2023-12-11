public class Enemy extends AnimatedActor{
    private Health hp;
    private Animation walkR;
    private Animation walkL;
    private Animation shootR;
    private Animation shootL;
    private Child child;
    private int damageCool;
    private int type;
    /*
        Makes the health object for the enemy
        Makes the child object for the enemy equal to the player
        Sets the type of the enemy
     */
    public Enemy(int hp, Child child,int type) {
        this.hp = new Health(hp);
        this.child = child;
        this.type = type;
    }
    /*
        If the child is attacking, and it is touching the weapon, and it is not immune it will take five damage and the immunity period will start
        If the immunity period is active, and it is the first frame it is active it sets the transparency of the animations to 75%
        If it is the last frame of the immunity period it resets the transparency
        If the immunity period is active it will decrease the remaining length by 1 frame.
        Calls super.act()
     */
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
    /*
        If the inputted number is 2 it sets the animation to the right walk if it is 1 the animation is set to the left walk.
     */
    public void setAnimation(int num){
        if(num == 2){
            setAnimation(walkR);
        }else if(num == 1){
            setAnimation(walkL);
        }
    }
    /*
        Calls the setAnimation of Animated Actor with the animation that is passed in
     */
    public void setAnimation(Animation anim){
            super.setAnimation(anim);
    }
    /*
        Returns the player object previously declared
     */
    public Child getChild(){
        return child;
    }
    /*
        Sets the transparency of each animation to the inputted value if the animation exists
     */
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
    /*
        Returns the type of the enemy
     */
    public int getType(){
        return type;
    }
    /*
        If it is touching a wall or door directly in front of it or there is not a wall on the next tile over for it to walk on the enemy turns around
        If it turns the enemy will also change its walking animation accordingly
     */
    public void shouldTurningW() {
        if (isTouchingAtOffset( ((int) velocity.x / 2) * getWidth() / 2, 0, Wall.class) ||
                isTouchingAtOffset(((int) velocity.x / 2) * getWidth() / 2, 0 , Door.class)||
                !isTouchingAtOffset(((int) velocity.x/2)*getWidth()/2,getHeight(),Wall.class)){
            velocity.x = -velocity.x;
            if(velocity.x > 0) setAnimation(walkL);
            if(velocity.x < 0) setAnimation(walkR);
        }
    }
    /*
        Using the inputted animation it sets the left and right walk animations and scales them for the enemy
     */
    public void setAnimationWalk(Animation walk){
        walkR = walk;
        walkR = new Animation(50,walk.getFrame());
        walkR.setScale(64,54);
        walkL = walk;
        walkL = new Animation(49,walk.getFrame());
        walkL.setScale(64,54);
        walkL.flipX();
        setAnimation(walkR);

    }
    /*
        Sets the shoot animations for the enemy if it needs to shoot
     */
    public void setShoot(Animation R,Animation L){
        shootR = R;
        shootL = L;
    }
    /*
        Returns the health object assigned to this enemy
     */
    public int getHealth(){
        return hp.getHealth();
    }

}

