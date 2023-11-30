import mayflower.*;
import java.util.ArrayList;

public class MyWorld extends World {

    Block[] floors;

    ArrayList<Enemy> enemies;
    int tileSize;
    Child child;
    public MyWorld()
    {
        enemies = new ArrayList<Enemy>();
        tileSize = 64;
        child = new Child();
        addObject(child, 400, 400);
        floors = new Block[getWidth()/tileSize];
        LetThereBeFloor();
    }

    public void act()
    {
            for(int i = 0;i<enemies.size();i++) {
                if ((enemies.get(i)).getHealth() <= 0) {
                    removeObject(enemies.get(i));
                    enemies.remove(i);
                }
            }
    }

    public void LetThereBeFloor(){
        for(int i = 0; i < floors.length; i++){
            floors[i] = new Block("Sprites/Brick.png");
            addObject(floors[i], i*tileSize,getHeight()-tileSize);
        }
    }

}