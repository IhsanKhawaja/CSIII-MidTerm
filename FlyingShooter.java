public class FlyingShooter extends Enemy{

    private Animation flyingShooter;
    private int bulletArrNum;
    private int bulletCool;
    /*
        Passes the health, player, and type to the enemy class
        Declares its own animation and scales it properly
        Sets the number of the queue that it uses to save its bullets and sets it bullet cooldown to 0
        Sets its animation to its stationary animation
     */
    public FlyingShooter(int health,Child child,int type,Animation flyingShooter, int n){
        super(health,child,type);
        this.flyingShooter = new Animation(50,flyingShooter.getFrame());
        this.flyingShooter.setScale(64,54);
        bulletArrNum = n;
        bulletCool = 0;
        setAnimation(this.flyingShooter);
    }
    /*
        Returns the index in the bullets ArrayList in MyWorld that this enemy stores its bullets in.
     */
    public int getBulletArrNum(){
        return bulletArrNum;
    }
    /*
        Checks if the distance between it and the player is 500 pixels
        Then it checks if it can shoot and if it can it returns true
        Finally if the attack cooldown isn't over it decreases it by 1 frame
        If it didn't already return true it returns false
     */
    public boolean shoot() {
        float distanceP = this.pos.distance(super.getChild().pos);
        if (distanceP < 500 && distanceP != 0.0) {
            if (bulletCool == 90) {
                bulletCool = 0;
                return true;
            }
        }
        if (bulletCool != 90) {
            bulletCool++;
        }
        return false;
    }
    /*
        Updates the position and calls super.act()
     */
    public void act(){
        pos.x = getX();
        pos.y = getY();
        super.act();
    }
}
