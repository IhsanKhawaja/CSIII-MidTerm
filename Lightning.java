public class Lightning extends AnimatedActor {
    private Animation lightning;
    private MyWorld world;

    public Lightning(MyWorld world) {
        this.world = world;
        String[] frame = new String[]{"Sprites/Lightning.png"};
        lightning = new Animation(1,frame);
        lightning.setScale(45,300);
        setAnimation(lightning);
    }
}
