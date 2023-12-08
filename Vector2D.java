import java.math.MathContext;

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
        float mag = (float) (Math.sqrt(x*x + y*y));
        if(mag > 0) {
            x /= mag;
            y /= mag;
        }
        return this;
    }

    public float distance(Vector2D target){
        return (float)Math.sqrt(Math.pow(xdiff(target), 2) + Math.pow(ydiff(target), 2));
    }

    public float xdiff(Vector2D target){
        return (float) target.x - this.x;
    }

    public float ydiff(Vector2D target){
        return (float) target.y - this.y;
    }
}
