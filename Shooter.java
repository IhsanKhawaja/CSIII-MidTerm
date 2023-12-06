import java.util.Collection;
import java.util.Iterator;
import java.util.PriorityQueue;
public class Shooter extends Walker{

    private Animation shootR;
    private Animation shootL;
    private float saveX;
    private Queue<Bullet> bullets;
    private int bulletCool;

    public Shooter(int hp, Animation walk,Animation shoot,Child child){
        super(hp,walk,child,64,54);
        shootL = new Animation(50,shoot.getFrame());
        shootL.flipX();
        shootL.setScale(64,54);
        shootR = new Animation(50,shoot.getFrame());
        shootR.setScale(64,54);
        bullets = new Queue<Bullet>();
        bulletCool = 60;
    }
    public void shoot(){
        float distanceP = this.pos.distance(getChild().pos);
        if( distanceP <150 ) {
            if(velocity.x != 0) {
                saveX = velocity.x;
            }
            velocity.x = 0;
            if(getChild().getX()>this.getX()){
                super.setAnimation(shootR);
            }else{
                super.setAnimation(shootL);
            }
            if(bulletCool == 60){
                bulletCool = 0;

            }
        }else{
            if(velocity.x>0){
                super.setAnimation(1);
            }else{
                super.setAnimation(2);
            }
            if(saveX !=0){
                velocity.x = saveX;
            }
        }
    }
    public void act(){
        super.act();
        pos.x = getX();
        pos.y = getY();
        shoot();

    }
}
