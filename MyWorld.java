import mayflower.*;
import java.util.ArrayList;

public class MyWorld extends World {

    Block[] floors;

    ArrayList<Enemy> enemies;
    int tileSize;
    Child child;
    Animation crawlid;
    String[] frames;
    public MyWorld()
    {
        frames = new String[1];
        for(int i = 0;i < frames.length;i++){
            frames[i] = "Sprites/Crawlid.png";
        }
        crawlid = new Animation (50,frames);
        enemies = new ArrayList<>();
        definetlyNotCrawlid crawlid1 = new definetlyNotCrawlid(10, 700, 400, crawlid);
        addObject(crawlid1 ,700,400);
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