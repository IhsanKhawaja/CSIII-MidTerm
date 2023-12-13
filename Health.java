public class Health {
    private int hp;
    /*
        Sets the hp to the inputted integer
     */
    public Health(int hp) {
        this.hp = hp;
    }
    /*
        Reduces hp by inputted int
     */
    public void takeDamage(int damage) {
        hp -= damage;
    }
    public void setHealth(int health) {
        hp = health;
    }

    /*
        Returns hp
     */
    public int getHealth(){
        return hp;
    }
}
