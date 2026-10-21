package GameObject;

public class Player extends Rendered {
    private int A = 0;
    private int D = 0;

    public Player(float start_x, float start_y, int x_length, int y_length) {
        super(start_x, start_y, x_length, y_length, "./Res/pl-base.png");
        this.gravity = true;
    }

    public void setAD(boolean a, boolean d) {
        this.A = a ? 1 : 0;
        this.D = d ? 1 : 0;
    }

    public int getA() {
        return A;
    }
    public int getD() {
        return D;
    }
}
