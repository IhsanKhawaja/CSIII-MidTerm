import mayflower.*;

public class MyMayflower extends Mayflower
{
    //Constructor
    public MyMayflower()
    {
        //Create a window with 800x600 resolution
        super("Traumatized Child", 1280, 720);
    }

    public void init()
    {
        //Change this to true to run this program in fullscreen mode
        Mayflower.setFullScreen(false);
        World w =  new MyWorld();
        Mayflower.setWorld(w);

    }
}
