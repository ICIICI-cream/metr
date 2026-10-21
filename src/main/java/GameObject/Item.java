package GameObject;

import Engine.Engine;

public class Item extends Rendered {
    //private int portable = 1;
    private int taken = 0;

    public  Item(float start_x, float start_y, int x_length, int y_length) {
        super(start_x, start_y, x_length, y_length, "./Res/item-base.png");
        this.gravity = true;
    }

//    public int isPortable() {
//        return portable;
//    }
    public int isTaken() {
        return taken;
    }
//    public void setPortable(int portable) {
//        this.portable = portable;
//    }
    public void setTaken(int taken) {
        this.taken = taken;
    }

    @Override
    public void addCoord(float delta_x, float delta_y) {
        delta_x = (this.x + delta_x) * (1 - this.taken) + (Engine.getPlayer().getX()) * this.taken;
        delta_y = (this.y + delta_y) * (1 - this.taken) + (Engine.getPlayer().getY() + 40) * this.taken;

        super.setCoord(delta_x, delta_y);
    }
}
