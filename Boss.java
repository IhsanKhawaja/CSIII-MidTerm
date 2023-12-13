import jaco.mp3.player.A;

public class Boss extends AnimatedActor{
    private Animation idle;
    private Animation shoot;
    private Animation dash;
    public Boss(){
        String[] frame = new String[]{"Sprites/Nimbus.png"};
        idle = new Animation(50, frame);
        String[] frames = new String[]{"Sprites/BossShoot.png"};
        shoot = new Animation(50, frames);
        String[] framess = new String[]{"Sprites/BossDash.png"};
        dash = new Animation(50, framess);
    }

    public void act() {
        super.act();
    }
}
