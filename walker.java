public class walker extends Enemy{

    private int collide;
    private int collideTimer;
    private int dir;
    public walker(int hp, Animation anim){
        super(hp);
        velocity = new Vector2D(1,0);
        collide = 0;
        collideTimer = 0;
        anim.setScale(64,54);
        super.setAnimationWalk(anim);
    }
    public void act(){

        if(isTurningW() && collideTimer == 0 && collide<0){
            super.setAnimation(2);
            velocity.x = 1;
        }else if(isTurningW() && collideTimer == 0 && collide<0){
            super.setAnimation(1);
            velocity.x = -1;
        }
        if(collideTimer>0){
            collideTimer -= 1;
        }
        setLocation(getX()+velocity.x, getY());
        super.act();

    }
    public boolean isTurningW(){
        if(isTouchingAtOffset(51,0,Block.class)) {
            collide = 1;
            return true;
        }else if(isTouchingAtOffset(-51,0,Block.class)){
            collide = -1;
            return true;
        }
        return false;
    }
}

