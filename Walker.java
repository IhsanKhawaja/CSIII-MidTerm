public class Walker extends Enemy{
    public Walker(int hp, Animation anim){
        super(hp);
        velocity = new Vector2D(2,0);
        anim.setScale(64,54);
        super.setAnimationWalk(anim);
    }
    public void act(){

        if(super.isTurningW() && super.getCollide()>0){
            super.setAnimation(2);
            velocity.x = -2;
        }else if(super.isTurningW()  && super.getCollide()<0){
            super.setAnimation(1);
            velocity.x = 2;
        }
        setLocation(getX()+velocity.x, getY());
        super.act();

    }

}


