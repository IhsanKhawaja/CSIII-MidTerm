import mayflower.*;
import java.util.ArrayList;
import java.util.HashMap;

public class MyWorld extends World {
    boolean once;
    MyMayflower mayflower;
    AnimatedActor gameOverScreen;
    MyStack<InanimateObject> playerHearts;
    Raindrop drop;
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
    StartButton button;
    StartButton button2;
    boolean isDead;
    boolean game;
    boolean restart;
    Vector2D startPos;
    Wall titleObj;
    Boss boss;
    private InanimateObject clouds;

    int arrNum;
    /*
        Sets enemies to a new ArrayList
        Animates all the enemies walking, shooting, idling, shooting, and the game over screen
        Creates the bullets ArrayList and sets the array number to 0 and sets isDead to false
     */
    public MyWorld(MyMayflower mayflower, boolean restart)
    {
        once = true;
        boss = new Boss(child, this);
        startPos = new Vector2D();
        this.restart = restart;
        this.mayflower = mayflower;
        gameOverScreen = new AnimatedActor();
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
        isDead = false;
        game = false;
        clouds = new InanimateObject(new String[]{"Sprites/Cloudy_Background.png"});
        clouds.getAnimation().setScale(1280, 1280*2);

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
                {"b","b","b","b","-","-","-","b","-","r","-","-","-","-","-","-","-","-","b","b"},
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
                {"b","l","-","-","-","-","-","-","-","-","-","-","-","r","-","b","l","-","-","b"},
                {"b","l","-","s","-","s","-","s","-","-","e1","e1","-","-","e2","b","l","-","-","b"},
        };

        String[][] room4 = new String[][]{
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","d","d"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","e1","e1","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","c","c","c","c","c","c"},
                {"r","e1","e2","-","-","-","-","-","-","-","-","-","-","-","-","c","c","c","c","-"},
                {"c","c","c","c","c","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","c","c","c","-","-","-","-","-","-","e1","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","e1","-","-","c","c","c","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","c","c","c","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","c","c","c","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","e2","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
        };

        String[][] room5 = new String[][]{
                {"d","d","d","d","d","d","c","c","c","c","c","c","c","c","c","c","c","c","c","c"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"c","c","c","c","c","c","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","e3","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","e3","-","-","-","e1","-","-","c","c","c","c","-","-","e2","r","-","-","-"},
                {"-","-","-","-","-","c","c","-","-","-","-","-","-","-","-","c","c","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
        };

        String[][] room6 = new String[][]{
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","e3","-","-","-","-","-","-","-","-","c","c","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","e1","-","-","-","-","c","c","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","c","c","c","c","-","-","c","c","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","c","c","-","-","-","c","c","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","c","c","-","-","-","-","-","-","-","d"},
                {"-","-","-","-","-","-","-","-","-","-","c","c","r","-","-","-","e1","-","e2","d"},
        };

        String[][] room7 = new String[][]{
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","r","-","d","d"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","e3","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","e1","-","e2","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","c","c","c","c","c","c","c","c","c","c","c","c","c","c","c"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","e2","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"c","c","c","c","c","c","c","c","c","c","c","c","c","c","c","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
        };

        String[][] room8 = new String[][]{
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","d","d"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","e3","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","c","c"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","e3","-","-","-","-","c","c","c"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","c","c","c","c"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","c","c","c","c","c"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","c","c","c","c","c","c"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","c","c","c","c","c","c"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","c","c","c","c","c","c","c"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"r","-","-","-","-","-","-","-","e1","e2","-","-","-","-","-","-","-","-","-","-"},
        };

        String[][] room9 = new String[][]{
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","r","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","e3","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","e2","-","-"},
                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","c","c","-","-"},
                {"d","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                {"d","e1","e1","e1","e1","e1","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
        };

        String[][] room10 = new String[][]{
                {"c","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","c"},
                {"c","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","c"},
                {"c","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","c"},
                {"c","-","c","c","c","c","-","-","-","-","-","-","-","-","c","c","c","c","-","c"},
                {"c","-","-","c","c","-","-","-","-","e4","-","-","-","-","-","c","c","-","-","c"},
                {"c","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","c"},
                {"c","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","c"},
                {"c","-","-","-","c","c","c","c","-","-","-","-","c","c","c","c","-","-","-","c"},
                {"c","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","c"},
                {"i","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","c"},
                {"i","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","c"},
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
        button2 = new StartButton();
        titleObj = new Wall("Sprites/Waterlogged.png");

        addObject(titleObj, 1280/2 - 250,720/3);
        showText("Waterlogged", 64,1280/2 - 170,720/3 + 64, Color.BLACK);
        addObject(button2,1280/2 - 144,720/2 - (51/2));
        addObject(mouse, 0, 0);
    }
    /*

        Checks if any enemy in enemies is at 0 or less health and removes them from the world and the list if they are
        Checks if any enemies are able to shoot (Shooter and Flying Shooter enemies, if they are checks if shoot is true for that enemy if it is then adds a bullet to the queue specific to that enemy in the bullets ArrayList
        and adds that bullet to the game
        Checks if the oldest bullet in the Queue for any of the enemies is past its lifetime or touching a wall and then removes it from the game if it is 
        Checks if the players health is less than zero if it is removes the player and their weapon from the game and displays the game over screen with a restart button
     */
    public void act()
    {
        if(Mayflower.isKeyPressed(Keyboard.KEY_B)){
            roomTally = 9;
            removeWorld();
            generateWorld(rooms[roomTally]);
        }

        /*
        handles the start screen and its buttons
         */
        button2.setAnimation(1);
        if (button2 != null && button2.isBlocked() || restart) {
            button2.setAnimation(2);
            if (Mayflower.mouseDown(button2) || restart) {
                LetThereBeFloor();
                addObject(child, 400, 400);
                generateWorld(rooms[0]);
                addObject(child.umbrella, 400, 400);
                removeObject(button2);
                removeObject(titleObj);
                removeText(1280/2 - 170,720/3 + 64);
                game = true;
                restart = false;
            }
        }
        if(game) {
            if (playerHearts.size() < child.getHealth()) {
                for (int i = playerHearts.size(); i < child.getHealth(); i++) {
                    String[] frame = new String[1];
                    frame[0] = "Sprites/Heart.png";
                    playerHearts.push(new InanimateObject(frame));
                    playerHearts.peek().getAnimation().setScale(32, 32);
                    addObject(playerHearts.peek(), i * tileSize + 20, 20);
                }
            } else if (playerHearts.size() > child.getHealth()) {
                removeObject(playerHearts.peek());
                playerHearts.pop();
            }

            if (child.drop()) {
                removeObject(drop);
                child.addScore(5);
                child.setHealth(child.getHealth() + 1);
            }

            if (child.door()) {
                roomTally++;
                removeWorld();
                generateWorld(rooms[roomTally]);
                if (child.getX() > getWidth() / 2 && child.getY() > getHeight() / 2)
                    child.setLocation(64, child.getY());
                if (child.getY() < getHeight() / 2) child.setLocation(child.getX(), getHeight() - child.getHeight() - 30);
                child.velocity = new Vector2D();
                startPos = new Vector2D(child.getX(), child.getY());
            }
            for (int i = 0; i < enemies.size(); i++) {
                if (enemies.get(i).getHealth() <= 0) {
                    child.addScore(1);
                    removeObject(enemies.get(i));
                    enemies.remove(i);
                    if (enemies.isEmpty()) {
                        break;
                    }
                }
            }
            for (int i = 0; i < enemies.size(); i++) {
                if (enemies.get(i).getType() == 2) {
                    if (enemies.get(i).getClass() == Shooter.class) {
                        Shooter temp = (Shooter) enemies.get(i);
                        if (temp.shoot()) {
                            Bullet newB = new Bullet(child.pos, bullet, child, temp.pos);
                            bullets.get(temp.getBulletArrNum()).add(newB);
                            addObject(newB, (int) temp.pos.x, (int) temp.pos.y);

                        }
                    } else if (enemies.get(i).getClass() == FlyingShooter.class) {
                        FlyingShooter temp = (FlyingShooter) enemies.get(i);
                        if (temp.shoot()) {
                            Bullet newB = new Bullet(child.pos, bullet, child, temp.pos);
                            bullets.get(temp.getBulletArrNum()).add(newB);
                            addObject(newB, (int) temp.pos.x, (int) temp.pos.y);

                        }
                    }
                }
            }
            for (int i = 0; i < bullets.size(); i++) {
                if (bullets.get(i).getOldest() != null && (bullets.get(i).getOldest().getLifeTime() < 0 || bullets.get(i).getOldest().isBlocked())) {
                    removeObject(bullets.get(i).getOldest());
                    bullets.get(i).remove();
                }
            }

            if(child.getX() < -child.getWidth() || child.getX() > 1280 || child.getY() > getHeight()){
                child.setLocation(startPos.x, startPos.y);
                showText("Nuh - uh", 100, 1280/3, 720/2, Color.BLACK);
            }

            if (child.getHealth() <= 0 && !isDead) {
                isDead = true;
                removeObject(child);
                removeObject(child.umbrella);
                gameOverScreen.setAnimation(gameOver);
                addObject(gameOverScreen, 0, 0);
                button = new StartButton();
                addObject(button, 496, 540);
            }else if(boss.bossIsDead() && !isDead){
                isDead = true;
                removeWorld();
                gameOverScreen.setAnimation(youWin);
                addObject(gameOverScreen, 0, 0);
                button = new StartButton();
                addObject(button, 496, 540);
            }

            if (button != null && button.isBlocked()) {
                button.setAnimation(2);
                if (Mayflower.mouseClicked(button)) {
                    mayflower.restart();
                }
            } else if (button != null) {
                button.setAnimation(1);
            }
            if(!isDead) {
                showText("Score: " + child.getScore(), 20, tileSize + tileSize / 2, Color.BLACK);
            } else {
                removeText(20, tileSize + tileSize / 2);
                showText("Final Score: " + child.getScore(), button.getX(),  720/2 + 720/3 + 720/10, Color.BLACK);
            }
        }
    }
    /*
    Generates the floor, sets it to bricks if its below level 3
    otherwise it sets it to clouds.
     */
    public void LetThereBeFloor(){
        for(int i = 0; i < floors.length; i++){
            if(roomTally < 4){
                floors[i] = new Wall("Sprites/Brick.png");
            } else {
                floors[i] = new Wall("Sprites/Cloud.png");
            }
            addObject(floors[i], i*tileSize,getHeight()-20);
        }
    }

    public void generateWorld(String[][] room){
        if(roomTally > 0 && roomTally < 3){
            setBackground("Sprites/Brick_Wall.png");
        } else if(roomTally > 2){
            setBackground("Sprites/Cloudy_Background.png");
        } else {
            setBackground("Sprites/City_Background.png");
        }

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
                    currentRoom[i][j] = new Door("Sprites/Door.png");
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
                    enemies.add(new FlyingShooter(5, child,2,nimbus,arrNum));
                    addObject(enemies.get(enemies.size()-1), j*tileSize,i*tileSize);
                    bullets.add(new Queue<Bullet>());
                    arrNum++;
                }else if(room[i][j].equals("e4")) {
                    boss = new Boss(child, this);
                    addObject(boss, j * tileSize - 32, i * tileSize - 100);
                }else if(room[i][j].equals("r")) {
                    String[] frame = new String[1];
                    frame[0] = "Sprites/Raindrop.png";
                    drop = new Raindrop(frame);
                    drop.getAnimation().setScale(32,32);
                    addObject(drop, j * tileSize + tileSize/4, i *tileSize + tileSize/4);
                }
            }
        }
        for(int i = 0; i < floors.length; i++){
            removeObject(floors[i]);
        }
        LetThereBeFloor();
        addObject(child, child.getX(), child.getY());
        addObject(child.umbrella, child.umbrella.getX(), child.umbrella.getY());
        for(int i = 0; i < child.getHealth(); i++){
            removeObject(playerHearts.peek());
            playerHearts.pop();
        }
    }
    /*

    For each enemy that currently exists it removes it from the world
    Makes the enemies array into a new empty ArrayList
     */
    public void removeWorld(){
        removeText(1280/3, 720/2);
        for(int i = 0; i < currentRoom.length; i++){
            for(int j = 0; j < currentRoom[i].length; j++){
                if(currentRoom[i][j] != null){
                    removeObject(child);
                    removeObject(child.umbrella);
                    removeObject(currentRoom[i][j]);
                }
            }
        }
        if(drop != null){
            removeObject(drop);
        }

        for (Enemy enemy:enemies){
            removeObject(enemy);

        }
        enemies=new ArrayList<Enemy>();
        bullets=new ArrayList<Queue<Bullet>>();
        arrNum =0;
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
