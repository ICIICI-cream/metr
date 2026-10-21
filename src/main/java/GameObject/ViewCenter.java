package GameObject;

public class ViewCenter extends GameObject {

    public ViewCenter(int start_x, int start_y) {
        super(start_x, start_y);
    }

    @Override
    public void addSpeed(float delta_speed_x, float delta_speed_y) {
        this.speed_x += delta_speed_x;
        this.speed_y += delta_speed_y;

//        if (this.x < Video.getWindow_width()/2) {
//            this.x = Video.getWindow_width()/2 + 1;
//        }
//        if (this.x >= Engine.getMap().getX_max() - Video.getWindow_width()/2 - 22) {
//            this.x = Engine.getMap().getX_max() - Video.getWindow_width()/2 - 23;
//        }
//        if (this.y < Video.getWindow_height()/2) {
//            this.y = Video.getWindow_height()/2 + 1;
//        }
//        if (this.y > Engine.getMap().getY_max() - Video.getWindow_height()/2 - 24) {
//            this.y = Engine.getMap().getY_max() - Video.getWindow_height()/2 - 25;
//        }
    }
}
