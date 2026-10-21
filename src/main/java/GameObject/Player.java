package GameObject;

import Resources.Resource;
import com.jogamp.opengl.util.texture.Texture;

public class Player extends GameObject {
    private int A = 0;
    private int D = 0;
    private Resource tex = new Resource("./Res/pl-base.png");

    public Player(int start_x, int start_y, int x_lenght, int y_length) {
        super(start_x, start_y, x_lenght, y_length);
        this.gravity = 1;
    }

    public void setAD(boolean a, boolean d) {
        this.A = a ? 1 : 0;
        this.D = d ? 1 : 0;
    }

    public Texture getTexture() {
        return tex.getTexture();
    }
    public int getA() {
        return A;
    }
    public int getD() {
        return D;
    }
}
