public class FlyingShooter extends Enemy{

    private Animation flyingShooter;
    private float saveX;
    private int bulletArrNum;
    private int bulletCool;
    public FlyingShooter(int health,Child child,int type,Animation flyingShooter, int n){
        super(health,child,type);
        this.flyingShooter = new Animation(50,flyingShooter.getFrame());
        bulletArrNum = n;
        bulletCool = 0;
        this.flyingShooter.setScale(64,54);
        setAnimation(this.flyingShooter);
    }
    public int getBulletArrNum(){
        return bulletArrNum;
    }
    public boolean shoot() {
        float distanceP = this.pos.distance(super.getChild().pos);
        System.out.println(distanceP + " - Distance");
        if (distanceP < 500 && distanceP != 0.0) {

            if (velocity.x != 0) {
                saveX = velocity.x;
            }
            velocity.x = 0;

            if (bulletCool == 60) {
                bulletCool = 0;
                return true;
            }
        } else {
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
    public void act(){
        pos.x = getX();
        pos.y = getY();
        super.act();
    }
}
