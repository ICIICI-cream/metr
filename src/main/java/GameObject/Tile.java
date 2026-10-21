package GameObject;

import Resources.Resource;
import com.jogamp.opengl.util.texture.Texture;

public class Tile extends GameObject{
    private float softness = 1;
    private Resource tex = null;

    public Tile(float start_x, float start_y, String file_name, int soft) {
        super(start_x, start_y);
        this.tex = new Resource(file_name);
        this.softness = soft;
    }
    public Tile(float start_x, float start_y, String file_name) {
        super(start_x, start_y);
        tex = new Resource(file_name);

    }
    public Tile(String file_name) {
        super();
        tex = new Resource(file_name);
    }

    public float getSoftness() {
        return softness;
    }
    public Texture getTexture() {
        return tex.getTexture();
    }
    public String getFileName() {
        return tex.getFileName();
    }
}