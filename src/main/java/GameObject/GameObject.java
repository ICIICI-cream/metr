package GameObject;

public class GameObject {
    protected float x = 0;
    protected float y = 0;
    private int x_length = 0;
    private int y_length = 0;
    protected float speed_x = 0;
    protected float speed_y = 0;

    protected boolean gravity = false;

    public GameObject(float start_x, float start_y, int x_length, int y_length) {
        this.x = start_x;
        this.y = start_y;
        this.x_length = x_length;
        this.y_length = y_length;
    }
    public GameObject(float start_x, float start_y) {
        this.x = start_x;
        this.y = start_y;
    }
    public GameObject() {}

    public void addCoord(float delta_x, float delta_y) {
        this.x += delta_x;
        this.y += delta_y;
        
    }
    public void addSpeed(float delta_speed_x, float delta_speed_y) {
        this.speed_x += delta_speed_x;
        this.speed_y += delta_speed_y;
    }

    public void setXSpeed(float delta_speed_x) {
        this.speed_x = delta_speed_x;
    }
    public void setYSpeed(float delta_speed_y) {
        this.speed_y = delta_speed_y;
    }
    public void setCoord(float x_coord, float y_coord) {
        this.x = x_coord;
        this.y = y_coord;
    }
    public void setGravity(boolean gravity) {
        this.gravity = gravity;
    }


    public boolean getGravity() {
        return gravity;
    }
    public float getSpeed_X() {
        return speed_x;
    }
    public float getSpeed_Y() {
        return speed_y;
    }
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public int getX_length() {
        return x_length;
    }
    public int getY_length() {
        return y_length;
    }
}
