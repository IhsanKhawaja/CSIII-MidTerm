import mayflower.*;

public class Animation
{
    private MayflowerImage[] frames;
    private String[] frame;
    private int framerate;
    private int currentFrame;

    public Animation(int fRate, String[] names){
        framerate = fRate;
        System.out.println(framerate);
        frame = new String[names.length];
        for(int i = 0;i<frame.length;i++){
            frame[i] = names[i];
        }
        frames = new MayflowerImage[names.length];
        for(int i = 0; i < names.length; i++){
            frames[i] = new MayflowerImage(names[i]);
        }
    }

    public int getFrameRate(){
        return framerate;
    }

    public MayflowerImage getNextFrame(){
        if(currentFrame < frames.length-1){
            currentFrame++;
        } else {
            currentFrame = 0;
        }
        return frames[currentFrame];
    }

    public void setScale(int w, int h){
        for(int j = 0; j < frames.length; j++){
            frames[j].scale(w,h);
        }
    }
    public void SetTransparency(int percent){
        for(int j = 0; j < frames.length; j++){
            frames[j].setTransparency(percent);
        }
    }

    public void flipX(){
        for(int i = 0; i < frames.length; i++){
            frames[i].mirrorHorizontally();
        }
    }

    public String[] getFrame(){
        return frame;
    }
}
