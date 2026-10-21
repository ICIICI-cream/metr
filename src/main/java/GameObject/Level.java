package GameObject;

import java.util.ArrayList;

public class Level {

    protected int x_max = 0;
    protected int y_max = 0;
    protected int pixel_per_cell = 0;
    protected int g_force = 2;
    protected ArrayList<ArrayList<Tile>> construction_matrix =  new ArrayList<>();

    public Level(int x_max, int y_max, int pixel_per_cell) {
        this.x_max = x_max;
        this.y_max = y_max;
        this.pixel_per_cell = pixel_per_cell;

        for (int i=0; i<=y_max/pixel_per_cell; i++) {
            ArrayList<Tile> inner = new ArrayList<>();
            for (int j=0; j<=x_max/pixel_per_cell; j++) {
                inner.add(new Tile(j*pixel_per_cell, i*pixel_per_cell, "./Res/alpha0.png"));
            }
            construction_matrix.add(inner);
        }
    }
    public Level() {}

    public ArrayList<ArrayList<Tile>> getConstruction_matrix() {
        return construction_matrix;
    }
    public int getG_force() {
        return g_force;
    }
    public int getPixel_per_cell() {
        return pixel_per_cell;
    }
    public int getX_max() {
        return x_max;
    }
    public int getY_max() {
        return y_max;
    }

}