import java.util.ArrayList;

public class Boss extends AnimatedActor{
    private ArrayList<Bullet> bullets;
    private Animation idle;
    private Animation shoot;
    private Animation dash;
    private Animation zap;
    private Animation hurt;
    private Lightning light;
    private Animation bulletAnim;
    Health health;
    double timer;
    Vector2D glide;
    private Child child;
    private MyWorld world;
    private int iframes;
    private int attackTimer;
    private int attack;
    private boolean attacking;
    private Vector2D oldpos;
    private boolean move;
    private boolean dead;
    public Boss(Child child, MyWorld world){
        dead = false;
        move = true;
        bullets = new ArrayList<Bullet>();
        oldpos = new Vector2D();
        attacking = false;
        iframes = 0;
        this.child = child;
        this.world = world;
        glide = new Vector2D();
        health = new Health(30);
        timer = 0;
        grav = false;
        collision = false;
        attack = 0;
        attackTimer = 0;

        pos.x = 1280/2 - 100;
        pos.y = 720/3 - 100;

        String[] bulletFrame = new String[]{"Sprites/Bullet.png"};
        bulletAnim = new Animation(50, bulletFrame);

        String[] frame = new String[]{"Sprites/BossIdle.png"};
        idle = new Animation(50, frame);
        String[] frames = new String[]{"Sprites/BossShoot.png"};
        shoot = new Animation(50, frames);
        String[] framess = new String[]{"Sprites/BossDash.png"};
        dash = new Animation(50, framess);
        String[] framesss = new String[]{"Sprites/BossLightning1.png"};
        zap = new Animation(50, framesss);
        String[] framessss = new String[]{"Sprites/BossHurt.png"};
        hurt = new Animation(1, framessss);

        idle.setScale(200,200);
        shoot.setScale(200,200);
        dash.setScale(200,200);
        zap.setScale(200,200);
        hurt.setScale(200,200);

        light = new Lightning(world);

        setAnimation(idle);
    }

    public void act() {
        pos.x = getX();
        pos.y = getY();

        if(health.getHealth() <= 0){
            world.removeObject(this);
            dead = true;
        }

        if(isTouching(Weapon.class) && iframes == 0 && child.getAttack()){
            health.takeDamage(1);
            iframes = 35;
            if(move) {
                velocity.x += -pos.xdiff(child.pos) / 3;
                velocity.y += -pos.ydiff(child.pos) / 3;
            }
            setAnimation(hurt);
        } else if(iframes < 1 && !attacking) {
            setAnimation(idle);
        } else if (attacking){
            if(attack == 0) setAnimation(zap);
            if(attack == 2) setAnimation(shoot);
            if(attack == 1) setAnimation(dash);
        }

        if(child.boss()){
            child.takeDamage(1);
        }

        if(attack == 1 && attackTimer < 210){
            velocity.y = 0;
            velocity.x = 0;
        }

        if(attackTimer == 150){
            attack = (int)(Math.random() * 3);
            System.out.println(attack);
            attacking = true;
            oldpos = new Vector2D(child.pos.x, child.pos.y);
            if(attack == 0){
                iframes = 30;
            } else if(attack == 1){
                setLocation(0,Math.random()*620);
                move = false;
            }
            for(Bullet bullet : bullets){
                world.removeObject(bullet);
            }
            bullets = new ArrayList<Bullet>();
        } else if (attackTimer == 195){
            if (attack == 0) world.addObject(light, (int)oldpos.x, (int)oldpos.y - 300 + 64);
            if(attack == 2){
                for(int i = 0; i < 25; i++){
                    bullets.add(new Bullet(child.pos, bulletAnim, child, pos));
                    world.addObject(bullets.get(i), (int)pos.x + 100 + (int)(Math.random()*200) - 50
                            , (int)pos.y + 100 + (int)(Math.random()*100) - 50);
                }
            }
        }else if(attackTimer == 210) {
            if (attack == 0) world.removeObject(light);
            if(attack == 1) velocity.x = 10;
        }else if(attackTimer == 215 && attack != 1) {
            attacking = false;
            attackTimer = 0;
        } else if (attackTimer == 310){
            move = true;
            attacking = false;
            attackTimer = 0;
        }

        if(move) {
            glide.x = pos.xdiff(new Vector2D(1280 / 2 - 100, 720 / 3 - 100)) / 10;
            glide.y = pos.ydiff(new Vector2D(1280 / 2 - 100, 720 / 3 - 100)) / 10;

            velocity.y = (float) Math.cos(timer);
            velocity.x /= 1.2;
        }

        setLocation(getX() + glide.x + velocity.x, getY() + glide.y + velocity.y);
        super.act();

        timer += Math.PI/25;
        if (iframes > 0) iframes--;
        if(attackTimer < 310) attackTimer++;
    }
    public boolean isDead(){
        return dead;
}
