public class Walker extends Enemy{
    /*
    Passes the hp, player, and enemy type to the enemy class
    Sets the initial velocity of the enemy and sets the walk animations of the enemy
     */
    public Walker(int hp, Animation anim,Child child,int w, int h,int type){
        super(hp,child,type);
        velocity = new Vector2D(2,0);
        super.setAnimationWalk(anim);
    }
    /*
        Checks if the enemy is colliding with a wall and if it should turn around
        Changes the location of the enemy according to the velocity vector
        Calls super.act()
     */
    public void act(){
        shouldTurningW();
        setLocation(getX()+velocity.x, getY()+ velocity.y);
        super.act();

    }

}


