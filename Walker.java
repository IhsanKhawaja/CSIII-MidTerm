public class Walker extends Enemy{
    public Walker(int hp, Animation anim,Child child){
        super(hp,child);
        velocity = new Vector2D(2,0);
        anim.setScale(64,54);
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


