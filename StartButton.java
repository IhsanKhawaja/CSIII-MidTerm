public class StartButton extends AnimatedActor{

    private String[] frames;
    private Animation idle;
    private Animation hovered;
    public StartButton(){
        frames = new String[1];
        frames[0] = "Sprites/Start Unselected.png";
        idle = new Animation(50,frames);
        frames[0] = "Sprites/Start Selected.png";
        hovered = new Animation(50,frames);
        idle.setScale(288,51);
        hovered.setScale(288,51);
        setAnimation(1);
    }
    public boolean isBlocked(){
        return this.isTouching(MyMouse.class);
    }
    public void setAnimation(int anim){
        if(anim == 1){
            super.setAnimation(idle);
        }else if(anim == 2){
            super.setAnimation(hovered);
        }

    }
    public void act(){
        super.act();
    }

}
