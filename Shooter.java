import java.util.Collection;
import java.util.Iterator;
import java.util.PriorityQueue;
public class Shooter extends Walker{

    private Animation shootR;
    private Animation shootL;
    private float saveX;
    private int bulletArrNum;
    private int bulletCool;

    public Shooter(int hp, Animation walk,Animation shoot,Child child,int type,boolean is2,int n){
        super(hp,walk,child,64,54,type,is2);
        shootL = new Animation(50,shoot.getFrame());
        shootL.flipX();
        shootL.setScale(64,54);
        shootR = new Animation(50,shoot.getFrame());
        shootR.setScale(64,54);
        super.setShoot(shootR,shootL);
        bulletArrNum = n;
        bulletCool = 0;
    }
    public boolean shoot() {
        float distanceP = this.pos.distance(getChild().pos);
        if (distanceP < 250 && distanceP != 0.0) {

            if (velocity.x != 0) {
                saveX = velocity.x;
            }
            velocity.x = 0;
            if (getChild().getX() > this.getX()) {
                super.setAnimation(shootR);
            } else {
                super.setAnimation(shootL);
            }
            if (bulletCool == 60) {
                bulletCool = 0;
                return true;
            }
        } else {
            if (velocity.x > 0) {
                super.setAnimation(1);
            } else {
                super.setAnimation(2);
            }
            if (saveX != 0) {
                velocity.x = saveX;
                saveX = 0;
            }

        }
        if (bulletCool != 60) {
            bulletCool++;
        }
        return false;
    }
    public int getBulletArrNum(){
        return bulletArrNum;
    }
    public void act(){
        super.act();
        pos.x = getX();
        pos.y = getY();

    }
}
