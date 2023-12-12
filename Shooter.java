public class Shooter extends Walker{

    private Animation shootR;
    private Animation shootL;
    private float saveX;
    private int bulletArrNum;
    private int bulletCool;
    /*
    Passes the hp,walk animation, player,width,height, and enemy type to the class above it.
    Declares both the left and right shooting animations with the correct width and height.
    Passes the new shoot animations up to the enemy class to be stored
    Sets the number of the queue that it uses to save its bullets and sets it bullet cooldown to 0
     */
    public Shooter(int hp, Animation walk,Animation shoot,Child child,int type,int n){
        super(hp,walk,child,64,54,type);
        shootL = new Animation(50,shoot.getFrame());
        shootL.flipX();
        shootL.setScale(64,54);
        shootR = new Animation(50,shoot.getFrame());
        shootR.setScale(64,54);
        super.setShoot(shootR,shootL);
        bulletArrNum = n;
        bulletCool = 0;
    }
    /*
    Checks if the distance between this enemy and the player is less than 250 pixels
    If it is within distance and the velocity isn't already 0 it sets saveX to be the velocity and the velocity to 0
    After that it determines which shoot animation to use for the enemy while shooting
    Then it checks if it can shoot and if it can it returns true
    If it isn't within distance it determines which animation it should be using and sets the velocity back to the value it was previously
    Finally if the attack cooldown isn't over it decreases it by 1 frame
    If it didn't already return true it returns false
     */
    public boolean shoot() {
        float distanceP = this.pos.distance(super.getChild().pos);
        if (distanceP < 250 && distanceP != 0.0) {

            if (velocity.x != 0) {
                saveX = velocity.x;
                velocity.x = 0;
            }

            if (getChild().pos.x > this.pos.x) {
                super.setAnimation(shootR);
            } else {
                super.setAnimation(shootL);
            }
            if (bulletCool == 90) {
                bulletCool = 0;
                return true;
            }
        } else {
            if (saveX != 0) {
                velocity.x = saveX;
                saveX = 0;
            }
            if (velocity.x > 0) {
                super.setAnimation(1);
            } else {
                super.setAnimation(2);
            }


        }
        if (bulletCool != 90) {
            bulletCool++;
        }
        return false;
    }
    /*
    Returns the index in the bullets ArrayList in MyWorld that this enemy stores its bullets in.
     */
    public int getBulletArrNum(){
        return bulletArrNum;
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
