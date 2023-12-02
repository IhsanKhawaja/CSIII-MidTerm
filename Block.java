import mayflower.Actor;
import mayflower.MayflowerImage;

public class Block extends Actor {
    private Animation block;
    private boolean ladder;
    public Block(String img){
        String[] frames = new String[1];
        ladder = false;
        frames[0] = img;
        block = new Animation(0, frames);
        block.setScale(16*4,16*4);
        MayflowerImage imgs = new MayflowerImage(block.getNextFrame());
        setImage(imgs);
    }

    public void act(){

    }

    public void isLadder(boolean ladder) {
        this.ladder = ladder;
    }

    public boolean getLadder(){
        return ladder;
    }
}
