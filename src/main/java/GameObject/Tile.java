package GameObject;

import Resources.Resource;

public class Tile extends Rendered{
    private float softness = 1;
    private Resource tex = null;

    public Tile(float start_x, float start_y, String file_name, int soft) {
        super(start_x, start_y, file_name);
        this.softness = soft;
    }
    public Tile(float start_x, float start_y, String file_name) {
        super(start_x, start_y, file_name);
    }
    public Tile(String file_name) {
        super(file_name);
    }

    public float getSoftness() {
        return softness;
    }
}