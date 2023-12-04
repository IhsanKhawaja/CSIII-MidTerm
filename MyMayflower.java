import mayflower.*;

import javax.swing.*;
import java.awt.*;

public class MyMayflower extends Mayflower
{

    //Constructor
    public MyMayflower()
    {
        //Create a window with 800x600 resolution
        super("Traumatized Child", 1280, 720);
    }

    @Override
    public void initGUI(String title) {
        super.initGUI(title);
    }

    public void init()
    {
        //Change this to true to run this program in fullscreen mode
        Mayflower.showBounds(true);
        Mayflower.setFullScreen(false);
        Mayflower.showCursor(false);
        World w =  new MyWorld();
        Mayflower.setWorld(w);
    }
}
