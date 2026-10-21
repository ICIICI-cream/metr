package GameObject;

import Engine.Engine;

import java.util.ArrayList;

public class Map extends Level{

    public Map(int x_max, int y_max, int pixel_per_cell) {
        super(x_max, y_max, pixel_per_cell);
    }

    public void reinit(int x_max, int y_max, int pixel_per_cell) {
        this.x_max = x_max;
        this.y_max = y_max;
        this.pixel_per_cell = pixel_per_cell;
    }

    public void setBaseTile(int y_index, int x_index) {
        if ((y_index < construction_matrix.size()) & (y_index > -1) & (x_index < construction_matrix.get(0).size()) & (x_index > -1)) {
            float current_x = construction_matrix.get(y_index).get(x_index).getX();
            float current_y = construction_matrix.get(y_index).get(x_index).getY();

            construction_matrix.get(y_index).set(x_index, new Tile(current_x, current_y, "./Res/tile-base.png"));
        }

        float y_left = 5 - y_index;
        float x_left = 5 - x_index;


       if ((y_left > 0) | (x_left > 0)) {
            //        \/
            for (int i = 0; i < y_left; i++) {
                construction_matrix.add(0, new ArrayList<>());
                Engine.addObjectCoord(0, 0, pixel_per_cell);
                this.y_max += pixel_per_cell;
                for (int j = 0; j < construction_matrix.get(i+1).size(); j++) {
                    construction_matrix.get(0).add(new Tile("./Res/alpha0.png"));
                }
            }
            //        <
            for (int j = 0; j < x_left; j++) {
                for  (int i = 0; i < construction_matrix.size(); i++) {
                    construction_matrix.get(i).add(0, new Tile("./Res/alpha0.png"));
                }
                Engine.addObjectCoord(0, pixel_per_cell, 0);
                this.x_max += pixel_per_cell;
            }
            // update coord
            for (int i = 0; i < construction_matrix.size(); i++) {
                for (int j = 0; j < construction_matrix.get(0).size(); j++) {
                    construction_matrix.get(i).get(j).setCoord(j * pixel_per_cell, i * pixel_per_cell);
                }
            }
        }

        if ((y_index + 5 > construction_matrix.size()) | (x_index + 5 > construction_matrix.get(0).size())) {
            //        ^
            for (int i = construction_matrix.size(); i < y_index + 5; i++) {
                construction_matrix.add(new ArrayList<>());
                this.y_max += pixel_per_cell;
                for (int j = 0; j < construction_matrix.get(0).size(); j++) {
                    construction_matrix.get(i).add(new Tile(j * pixel_per_cell, i * pixel_per_cell, "./Res/alpha0.png"));
                }
            }
            //        >
            for (int j = construction_matrix.get(0).size(); j < x_index + 5; j++) {
                this.x_max += pixel_per_cell;
                for (int i = 0; i < construction_matrix.size(); i++) {
                    construction_matrix.get(i).add(new Tile(j * pixel_per_cell, i * pixel_per_cell, "./Res/alpha0.png"));
                }
            }
        }
    }
    public void setZeroTile(int y_index, int x_index) {
        if ((y_index < construction_matrix.size()) & (y_index > -1) & (x_index < construction_matrix.get(0).size()) & (x_index > -1)) {
            float current_x = construction_matrix.get(y_index).get(x_index).getX();
            float current_y = construction_matrix.get(y_index).get(x_index).getY();

            construction_matrix.get(y_index).set(x_index, new Tile(current_x, current_y, "./Res/alpha0.png"));
        }
    }
}
