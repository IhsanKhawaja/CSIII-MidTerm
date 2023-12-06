public class Walker extends Enemy{
    public Walker(int hp, Animation anim,Child child,int w, int h){
        super(hp,child);
        velocity = new Vector2D(2,0);
        anim.setScale(w,h);
        super.setAnimationWalk(anim);
    }
    public void act(){

        shouldTurningW();
        setLocation(getX()+velocity.x, getY()+ velocity.y);
        super.act();

    }

}


