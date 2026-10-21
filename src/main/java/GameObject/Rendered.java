package GameObject;

import Resources.Resource;
import com.jogamp.opengl.util.texture.Texture;

public class Rendered extends GameObject {
    protected Resource tex = null;

    public Rendered(float start_x, float start_y, int x_length, int y_length, String file_name) {
        super(start_x, start_y, x_length, y_length);
        this.tex = new Resource(file_name);
    }
    public Rendered(float start_x, float start_y, String file_name) {
        super(start_x, start_y);
        tex = new Resource(file_name);
    }
    public Rendered(String file_name) {
        super();
        tex = new Resource(file_name);
    }

    public Texture getTexture() {
        return tex.getTexture();
    }
}
