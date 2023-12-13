import mayflower.Actor;
import mayflower.MayflowerImage;
import mayflower.Timer;

public class InanimateObject extends Actor {
    private Animation animation;
    private Timer animationTimer;

    public InanimateObject(String[] frame){
        setAnimation(new Animation(50, frame));
    }

    public Animation getAnimation() {
        return animation;
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
    }
}
