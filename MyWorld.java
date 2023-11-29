import mayflower.*;


public class MyWorld extends World {

    Block[] floors;
    int tileSize;
    Child child;
    public MyWorld()
    {
        tileSize = 64;
        child = new Child();
        addObject(child, 400, 400);
        floors = new Block[getWidth()/tileSize];
        LetThereBeFloor();
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