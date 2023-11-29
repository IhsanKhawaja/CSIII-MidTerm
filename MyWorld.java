import mayflower.*;


public class MyWorld extends World {

    Child child;
    public MyWorld()
    {
        child = new Child();
        addObject(child, 400, 400);
    }

    public void act()
    {
    }

}