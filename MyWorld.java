import mayflower.*;

import javax.swing.*;
import java.awt.*;

public class MyWorld extends World {
    Block[] floors;
    String[][] rooms;
    int tileSize;
    Child child;
    MyMouse mouse;
    public MyWorld()
    {
        tileSize = 64;
        rooms = new String[10][12];

        //rooms[0] = ["","","","","","","","","","","",""];

        mouse = new MyMouse();
        child = new Child(mouse);
        floors = new Block[getWidth()/tileSize];
        addObject(child, 400, 400);
        LetThereBeFloor();
        addObject(child.umbrella, 400, 400);


        addObject(mouse, 0, 0);
    }

    public void act()
    {
    }

    public void LetThereBeFloor(){
        for(int i = 0; i < floors.length; i++){
            floors[i] = new Block("Sprites/Brick.png");
            addObject(floors[i], i*tileSize,getHeight()-tileSize);
        }
    }

}