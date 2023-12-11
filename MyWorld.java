import mayflower.*;
import java.util.ArrayList;
import java.util.HashMap;

public class MyWorld extends World {
    MyStack<InanimateObject> playerHearts;
    Wall[] floors;
    Actor[][] currentRoom;
    String[][][] rooms;
    int roomTally;
    int tileSize;
    ArrayList<Enemy> enemies;
    Animation crawlid;
    Animation shooterWalk;
    Animation shooterShoot;
    Animation bullet;
    Animation nimbus;
    Animation gameOver;
    String[]frames;
    Child child;
    MyMouse mouse;
    ArrayList<Queue<Bullet>> bullets;
    int arrNum;
    /*
        Sets enemies to a new ArrayList
        Animates all the enemies walking, shooting, idling, shooting, and the game over screen
        Creates the bullets ArrayList and sets the array number to 0
     */
    public MyWorld()
    {
        playerHearts = new MyStack<InanimateObject>();
        enemies = new ArrayList<>();
        frames = new String[1];
        animate(1,"Crawlid",frames,false);
        crawlid = new Animation (50,frames);
        animate(1,"Hornet",frames,false);
        shooterWalk = new Animation (50,frames);
        animate(1,"HornetAttack",frames,false);
        shooterShoot = new Animation(50,frames);
        animate(1,"Bullet",frames,false);
        bullet = new Animation(50,frames);
        animate(1,"Nimbus",frames,false);
        nimbus = new Animation(50,frames);
        animate(1,"GameOver",frames,false);
        gameOver = new Animation(50,frames);
        gameOver.setScale(1280,720);
        roomTally = 0;
        tileSize = 64;
        rooms = new String[10][12][20];
        currentRoom = new Block[12][20];
        bullets = new ArrayList<Queue<Bullet>>();
        arrNum = 0;

        String[][] room1 = new String[][]{
                {"b","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","b"},
                {"b","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","b"},
                {"b","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","b"},
                {"b","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","b"},
                {"b","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","b"},
                {"b","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","b"},
                {"b","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","b"},
                {"b","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","b"},  
                {"b","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","b"},
                {"b","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","d"},
                {"b","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","e1","-","-","d"},
        };

        String[][] room2 = new String[][]{
                {"b","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","d","d","b"},
                {"b","-","-","-","-","-","-","-","-","-","-","-","-","e1","-","e1","-","-","-","b"},
                {"b","-","e1","-","-","-","b","b","b","b","b","b","b","b","b","b","b","b","b","b"},
                {"b","b","b","b","-","-","-","b","-","-","-","-","-","-","-","-","-","-","b","b"},
                {"b","-","-","-","-","-","-","b","e1","e1","e1","e2","-","-","-","-","-","-","l","b"},
                {"b","-","-","-","l","b","b","b","b","b","b","b","b","b","b","b","b","b","l","b"},
                {"b","-","-","-","l","-","-","-","-","-","b","b","b","-","-","-","-","b","l","b"},
                {"b","-","-","-","l","-","-","-","-","-","-","b","-","-","-","-","-","b","l","b"},
                {"b","-","-","-","l","-","-","-","-","-","-","b","-","-","-","-","-","b","l","b"},
                {"i","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","l","b"},
                {"i","-","-","-","-","-","-","-","-","-","-","e1","-","-","-","-","-","-","l","b"},
        };

        String[][] room3 = new String[][]{
                {"b","d","d","b","b","b","b","b","b","b","b","b","b","b","b","b","b","b","b","b"},
                {"b","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","b"},
                {"b","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","b"},
                {"b","-","-","b","b","b","-","-","-","-","-","-","-","-","-","-","-","-","-","b"},
                {"b","-","-","-","b","-","-","-","-","-","-","-","-","-","-","-","-","-","-","b"},
                {"b","-","-","-","b","-","-","-","-","-","-","-","-","-","-","-","-","-","-","b"},
                {"b","l","b","b","b","b","b","b","b","b","b","b","b","b","b","b","l","-","-","b"},
                {"b","l","-","-","s","-","s","-","s","-","-","-","-","-","-","b","l","-","-","b"},
                {"b","l","-","-","-","-","-","-","-","-","-","-","-","-","-","b","l","-","-","b"},
                {"b","l","-","-","-","-","-","-","-","-","-","-","-","-","-","b","l","-","-","b"},
                {"b","l","-","s","-","s","-","s","-","-","e1","e1","-","-","e2","b","l","-","-","b"},
        };

        String[][] room4 = new String[][]{
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","d","d"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","e1","e1","e2","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","c","c","c","c","c","c"},
                {"-","e1","e2","-","-","-","-","-","-","-","-","-","-","-","-","c","c","c","c","-"},
                {"c","c","c","c","c","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","c","c","c","-","-","-","-","-","-","e1","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","e1","-","-","c","c","c","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","c","c","c","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","c","c","c","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
        };

        String[][] room5 = new String[][]{
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
        };

        String[][] room6 = new String[][]{
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
        };

        String[][] room7 = new String[][]{
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
        };

        String[][] room8 = new String[][]{
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
        };

        String[][] room9 = new String[][]{
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
        };

        String[][] room10 = new String[][]{
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
        };

        rooms[0] = room1;
        rooms[1] = room2;
        rooms[2] = room3;
        rooms[3] = room4;
        rooms[4] = room5;
        rooms[5] = room6;
        rooms[6] = room7;
        rooms[7] = room8;
        rooms[8] = room9;
        rooms[9] = room10;



        mouse = new MyMouse();
        child = new Child(mouse);
        floors = new Wall[getWidth()/tileSize];

        LetThereBeFloor();
        addObject(child, 400, 400);
        generateWorld(rooms[0]);
        addObject(child.umbrella, 400, 400);
        addObject(mouse, 0, 0);
    }
    /*

        Checks if any enemie in enemies is at 0 or less health and removes them from the world and the list if they are
        Checks if any enemies are able to shoot (Shooter and Flying Shooter enemies, if they are checks if shoot is true for that enemy if it is then adds a bullet to the queue specific to that enemy in the bullets ArrayList
        and adds that bullet to the game
        Checks if the oldest bullet in the Queue for any of the enemies is past its lifetime or touching a wall and then removes it from the game if it is 
        Checks if the players health is less than zero if it is removes the player and their weapon from the game and displays the game over screen
     */
    public void act()
    {
        for(int i = 0; i < playerHearts.size(); i++){

        }
        if(child.door()){
            roomTally++;
            removeWorld();
            generateWorld(rooms[roomTally]);
            addObject(child, child.getX(),child.getY());
            addObject(child.umbrella, child.umbrella.getX(),child.umbrella.getY());
            if(child.getX() > getWidth()/2 && child.getY() > getHeight()/2) child.setLocation(64, child.getY());
            if(child.getY() < getHeight()/2) child.setLocation(child.getX(), getHeight()-child.getHeight());
        }
        for(int i = 0; i<enemies.size();i++) {
            if (enemies.get(i).getHealth() <= 0) {
                removeObject(enemies.get(i));
                enemies.remove(i);
                if (enemies.size() == 0) {
                    break;
                }
            }
        }
        for(int i = 0; i<enemies.size();i++){
            if(enemies.get(i).getType()==2){
                if(enemies.get(i).getClass() == Shooter.class){
                    Shooter temp = (Shooter) enemies.get(i);
                    if(temp.shoot()){
                        Bullet newB = new Bullet(child.pos,bullet,child,temp.pos);
                        bullets.get(temp.getBulletArrNum()).add(newB);
                        addObject(newB,(int) temp.pos.x,(int) temp.pos.y);

                    }
                }else if(enemies.get(i).getClass() == FlyingShooter.class){
                    FlyingShooter temp = (FlyingShooter) enemies.get(i);
                    if(temp.shoot()){
                        Bullet newB = new Bullet(child.pos,bullet,child,temp.pos);
                        bullets.get(temp.getBulletArrNum()).add(newB);
                        addObject(newB,(int) temp.pos.x,(int) temp.pos.y);

                    }
                }
            }
        }
        for(int i = 0; i < bullets.size();i++){
            if(bullets.get(i).getOldest() != null && (bullets.get(i).getOldest().getLifeTime()<0 || bullets.get(i).getOldest().isBlocked())) {
                removeObject(bullets.get(i).getOldest());
                bullets.get(i).remove();
            }
        }
        if(child.getHealth() <= 0){
            removeObject(child);
            removeObject(child.umbrella);
            AnimatedActor gameOverScreen = new AnimatedActor();
            gameOverScreen.setAnimation(gameOver);
            addObject(gameOverScreen,0,0);
        }
    }

    public void LetThereBeFloor(){
        for(int i = 0; i < floors.length; i++){
            floors[i] = new Wall("Sprites/Brick.png");
            addObject(floors[i], i*tileSize,getHeight()-20);
        }
    }

    public void generateWorld(String[][] room){
        for(int i = 0; i < room.length; i++){
            for(int j = 0; j < room[i].length; j++){
                if(room[i][j].equals("b")){
                    currentRoom[i][j] = new Wall("Sprites/Brick.png");
                    addObject(currentRoom[i][j], j*tileSize,i*tileSize);
                }
                else if(room[i][j].equals("c")){
                    currentRoom[i][j] = new Wall("Sprites/Cloud.png");
                    addObject(currentRoom[i][j], j*tileSize,i*tileSize);
                }
                else if(room[i][j].equals("i")){
                    currentRoom[i][j] = new Wall("Sprites/Brick.png", 100);
                    addObject(currentRoom[i][j], j*tileSize,i*tileSize);
                }
                else if(room[i][j].equals("l")){
                    currentRoom[i][j] = new Ladder("Sprites/Ladder.png");
                    addObject(currentRoom[i][j], j*tileSize,i*tileSize);
                }
                else if(room[i][j].equals("d")){
                    currentRoom[i][j] = new Door("Sprites/Brick.png");
                    addObject(currentRoom[i][j], j*tileSize,i*tileSize);
                }
                else if(room[i][j].equals("e1")){
                    enemies.add(new Walker(10, crawlid, child,64,54,1));
                    addObject(enemies.get(enemies.size()-1), j*tileSize,i*tileSize);
                }
                else if(room[i][j].equals("e2")){
                    enemies.add(new Shooter(10, shooterWalk, shooterShoot, child,2,arrNum));
                    addObject(enemies.get(enemies.size()-1), j*tileSize,i*tileSize);
                    bullets.add(new Queue<Bullet>());
                    arrNum++;
                }else if(room[i][j].equals("e3")){
                    enemies.add(new FlyingShooter(10, child,2,nimbus,arrNum));
                    addObject(enemies.get(enemies.size()-1), j*tileSize,i*tileSize);
                    bullets.add(new Queue<Bullet>());
                    arrNum++;
                }
            }
        }
    }
    /*

    For each enemy that currently exists it removes it from the world
    Makes the enemies array into a new empty ArrayList
     */
    public void removeWorld(){
        for(int i = 0; i < currentRoom.length; i++){
            for(int j = 0; j < currentRoom[i].length; j++){
                if(currentRoom[i][j] != null){
                    removeObject(child);
                    removeObject(child.umbrella);
                    removeObject(currentRoom[i][j]);
                }
            }
        }

        for (Enemy enemy:enemies){
            removeObject(enemy);

        }
        enemies=new ArrayList<Enemy>();
    }
    /*
        If more1 is true sets each index of frames to the png
        If more1 is false it sets 1 index of frames to the png
     */
    public void animate(int nFrame, String fName, String[] frames, boolean more1){
        if(more1){
            for(int i = 0;i < nFrame;i++){
                frames[i] = "Sprites/" + fName + i + ".png";
            }
        }else{
            for(int i = 0;i < nFrame;i++){
                frames[i] = "Sprites/" + fName + ".png";
            }
        }
    }
}
