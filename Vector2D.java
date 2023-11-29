public class Vector2D {
    public float x;
    public float y;

    public Vector2D(){
        x = 0;
        y = 0;
    }
    public Vector2D(float xpos, float ypos){
        x = xpos;
        y = ypos;
    }

    public Vector2D normalize(){
        float mag = (float) Math.abs(Math.sqrt(x*x + y*y));
        if(mag > 0) {
            x /= mag;
            y /= mag;
        }
        return this;
    }
}
