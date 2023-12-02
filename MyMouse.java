import jaco.mp3.player.A;
import mayflower.Actor;
import mayflower.Mayflower;

public class MyMouse extends AnimatedActor {
    private Animation mouse;
    public MyMouse(){
        String[] frame = new String[1];
        frame[0] = "Sprites/Brick.png";
        mouse = new Animation(10, frame);
        mouse.setScale(100,100);
        mouse.SetTransparency(100);
        setAnimation(mouse);
    }
    public void act(){
        setLocation(Mayflower.getMouseInfo().getX()-getWidth()/2, Mayflower.getMouseInfo().getY()-getHeight()/2);
        super.act();
    }

    public Vector2D pos(){
        return new Vector2D(Mayflower.getMouseInfo().getX(), Mayflower.getMouseInfo().getY());
    }
}
