package Engine;

import GameObject.*;
import Render.Video;

import java.io.*;
import java.util.ArrayList;

public class Engine {
    private static Map map = null;
    private static Level level = null;
    private static ArrayList<GameObject> object_list; //0 - ViewCenter, 1 - Player

    public static void initMap() {
        map = new Map(Video.getWindow_width()+200, Video.getWindow_height()+200, (byte) 50);
        object_list = new ArrayList<>();
        object_list.add(new ViewCenter(Video.getWindow_width()/2, Video.getWindow_height()/2));
    }
    public static void initLevel() {
        loadMapFromBin("test.bin");

        object_list = new ArrayList<>();
        object_list.add(new ViewCenter(Video.getWindow_width()/2 + 200, Video.getWindow_height()/2 + 200));
        object_list.add(new Player(Video.getWindow_width()/2 + 350, Video.getWindow_height()/2 - 200, 30, 70));
    }

    public static void update() {
        for (GameObject object : object_list) {
            object.addCoord(object.getSpeed_X(), object.getSpeed_Y());

        }
        Video.render();
    }

    public static void saveMapToBin(String name) {

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("./" + name + ".bin"))) {
            int y_size = map.getConstruction_matrix().size();
            int x_size = map.getConstruction_matrix().get(0).size();
            int pixel_per_cell = map.getPixel_per_cell();

            dos.write(pixel_per_cell);
            dos.write(y_size);
            dos.write(x_size);

            for (int i = 0; i < y_size; i++) {
                for (int j = 0; j < x_size; j++) {
                    if (map.getConstruction_matrix().get(i).get(j).getFileName().equals("alpha0.png")) {
                        dos.write(0);
                    } else if (map.getConstruction_matrix().get(i).get(j).getFileName().equals("tile-base.png")) {
                        dos.write(1);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void loadMapFromBin(String name) {
        try (DataInputStream dis = new DataInputStream(new FileInputStream("./Res/" + name))) {
            int cell = dis.read();
            int y_size = dis.read();
            int x_size = dis.read();


            String _0 = "./Res/alpha0.png";
            String _1 = "./Res/tile-base.png";

            level = new Level(x_size * cell, y_size * cell, cell);
            for (int i = 0; i < y_size; i++) {
                level.getConstruction_matrix().add(new ArrayList<>());
                for (int j = 0; j < x_size; j++) {
                    int path = dis.read();
                    if (path == 0) {
                        level.getConstruction_matrix().get(i).set(j, new Tile(j * cell, i * cell, _0));
                    } else if (path == 1){
                        level.getConstruction_matrix().get(i).add(j, new Tile(j * cell, i * cell, _1, 0));
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addViewCenterSpeed(boolean w, boolean a, boolean s, boolean d) {
        object_list.get(0).addSpeed((-7 * (a?1:0)) + (7 * (d?1:0)), (-7 * (s?1:0)) + (7 * (w?1:0)));
    }
    public static void addObjectCoord(int object_id, int x_delta, int y_delta) {
        object_list.get(object_id).addCoord(x_delta, y_delta);
    }
    public static void addTile(int mouse_x, int mouse_y, short mouse_button) {
        if (mouse_button == 1) {
            map.setBaseTile((int) ((object_list.get(0).getY() - Video.getWindow_height() / 2 + mouse_y) / map.getPixel_per_cell()), (int) ((object_list.get(0).getX() - Video.getWindow_width() / 2 + mouse_x) / map.getPixel_per_cell()));
        }
        if (mouse_button == 3) {
            map.setZeroTile((int) ((object_list.get(0).getY() - Video.getWindow_height() / 2 + mouse_y) / map.getPixel_per_cell()), (int) ((object_list.get(0).getX() - Video.getWindow_width() / 2 + mouse_x) / map.getPixel_per_cell()));
        }
    }

    public static Tile[][] getViewMatrix(int width_view, int height_view) {
        Tile[][] submatrix = new Tile[(height_view/map.getPixel_per_cell())+2][(width_view/map.getPixel_per_cell())+2];
        int view_center_x = (int) object_list.get(0).getX();
        int view_center_y = (int) object_list.get(0).getY();
        for (int i = ((view_center_y - ((height_view - height_view%map.getPixel_per_cell()) / 2)) / map.getPixel_per_cell()); i < ((view_center_y + ((height_view - height_view%map.getPixel_per_cell()) / 2))/map.getPixel_per_cell()) + 2; i++) {
            for (int j = ((view_center_x - ((width_view - width_view%map.getPixel_per_cell()) / 2)) / map.getPixel_per_cell()); j < ((view_center_x + ((width_view - width_view%map.getPixel_per_cell()) / 2)) / map.getPixel_per_cell()) + 2; j++) {
                int cur_i = i - ((view_center_y - ((height_view - height_view%map.getPixel_per_cell()) / 2)) / map.getPixel_per_cell());
                int cur_j = j - ((view_center_x - ((width_view - width_view%map.getPixel_per_cell()) / 2))/map.getPixel_per_cell());
                submatrix[cur_i][cur_j] = map.getConstruction_matrix().get(i).get(j);
            }
        }
        return submatrix;
    }
    public static int getMapPixel_per_Cell() {
        return map.getPixel_per_cell();
    }
    public static float getViewShiftX(int width_view, float obj_x) {
        return obj_x - (object_list.get(0).getX() - width_view/2);
    }
    public static float getViewShiftY(int height_view, float obj_y) {
        return  obj_y - (object_list.get(0).getY() - height_view/2);
    }
    public static ViewCenter getViewCenter() {
        return (ViewCenter) object_list.get(0);
    }
}
