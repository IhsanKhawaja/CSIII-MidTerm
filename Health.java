public class Health {
    private int hp;
    public Health(int hp) {
        this.hp = hp;
    }
    public void takeDamage(int damage) {
        hp -= damage;
    }
    public int getHealth(){
        return hp;
    }
}
