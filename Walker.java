public class Walker extends Enemy{
    public Walker(int hp, Animation anim,Child child,int w, int h,int type,boolean is2){
        super(hp,child,type,is2);
        velocity = new Vector2D(2,0);
        anim.setScale(w,h);
        super.setAnimationWalk(anim);
    }
    public void act(){
        if(velocity.x > 0) super.setAnimation(1);
        if(velocity.x < 0) super.setAnimation(2);
        shouldTurningW();
        setLocation(getX()+velocity.x, getY()+ velocity.y);
        super.act();

    }

}


