public class Shooter extends Walker{

    private Animation shootR;
    private Animation shootL;
    public Shooter(int hp, Animation walk,Animation shoot,Child child){
        super(hp,walk,child,64,54);
        shootR = new Animation(50,shoot.getFrame());
        shootR.flipX();
        shootR.setScale(64,54);
        shootL = new Animation(50,shoot.getFrame());
        shootL.setScale(64,54);
    }
    public void shoot(){
        System.out.println(getChild().velocity.distance(this.velocity));
        if(getChild().velocity.distance(this.velocity) <100 ) {
            if(velocity.x > 0){
                setAnimation(shootR);
            }else{
                setAnimation(shootL);
            }
        }
    }
    public void act(){
        shoot();
        super.act();
    }
}
