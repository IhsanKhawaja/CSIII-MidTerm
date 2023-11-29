import mayflower.*;

public class AnimatedActor extends gravity
{
    private Animation animation;
    private Timer animationTimer;
    public AnimatedActor(){

    }
    public void setAnimation(Animation a){
        animation = a;
        animationTimer = new Timer(a.getFrameRate());
    }
    public void act(){
        if(animationTimer.isDone() && animation != null){
            MayflowerImage img = new MayflowerImage(animation.getNextFrame());
            setImage(img);
            animationTimer.reset();
        }
        super.act();
    }
}
