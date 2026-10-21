package Engine;

import GameObject.*;
import Render.Video;

import java.io.*;
import java.util.ArrayList;

public class Engine {
    private static Map map = null;
    private static Level level = null;
    private static ArrayList<GameObject> object_list; //0 - ViewCenter, 1 - Player

    public static void initLevel() {
        loadMapFromBin("test.bin");

        object_list = new ArrayList<>();
        object_list.add(new ViewCenter(Video.getWindow_width()/2 + 200, Video.getWindow_height()/2 + 200));
        object_list.add(new Player(Video.getWindow_width()/2 + 350, Video.getWindow_height()/2 - 200, 30, 70));
        object_list.add(new Item(Video.getWindow_width()/2 + 200, Video.getWindow_height()/2 + 200, 10, 10));
    }

    public static void update() {
        doPlayerMoveX(getPlayer());

        for (GameObject object : object_list) {
            doMoveY(object);
            doMoveX(object);
        }
//        for (int i = 0; i < object_list.size(); i++) {
//            GameObject object = object_list.get(i);
//            doMoveY(object);
//        }

        Video.render();
    }


    public static void doPlayerMoveX (Player player){
        player.setXSpeed(10 * player.getD() - 10 * player.getA());
    }
    private static void doMoveX(GameObject object) {
        if (object.getSpeed_X() != 0) {

            object.addCoord(object.getSpeed_X(), 0);

            int bottom_bound = (int) level.getConstruction_matrix().get((int) ((object.getY() + 1) / getLevelPixel_per_Cell())).get((int) ((object.getX()) / getLevelPixel_per_Cell())).getSoftness();
            int top_bound = (int) level.getConstruction_matrix().get((int) ((object.getY() + object.getY_length() - 1) / getLevelPixel_per_Cell())).get((int) ((object.getX()) / getLevelPixel_per_Cell())).getSoftness();
            int right_bottom_bound = (int) level.getConstruction_matrix().get((int) ((object.getY() + 1) / getLevelPixel_per_Cell())).get((int) ((object.getX() + object.getX_length()) / getLevelPixel_per_Cell())).getSoftness();
            int right_top_bound = (int) level.getConstruction_matrix().get((int) ((object.getY() + object.getY_length() - 1) / getLevelPixel_per_Cell())).get((int) ((object.getX() + object.getX_length()) / getLevelPixel_per_Cell())).getSoftness();
            int tile_soft_left = Math.min(bottom_bound, top_bound);
            int tile_soft_right = Math.min(right_bottom_bound, right_top_bound);

            if (tile_soft_left != 1) {
                object.setXSpeed(0);
                object.addCoord(50 - object.getX() % 50, 0);
            } else if (tile_soft_right != 1) {
                object.setXSpeed(0);
                object.addCoord(-object.getX_length() - object.getX() % getLevelPixel_per_Cell() + getLevelPixel_per_Cell(), 0);
            }
        }
    }
    private static void doMoveY(GameObject object) {
        if (object.getGravity()) {
            int cell = getLevelPixel_per_Cell();

            object.addSpeed(0, -level.getG_force());
            object.addCoord(0, object.getSpeed_Y());

            int left_bound = (int) level.getConstruction_matrix().get((int) (object.getY() / cell)).get((int) (object.getX() / cell)).getSoftness();
            int right_bound = (int) level.getConstruction_matrix().get((int) (object.getY() / cell)).get((int) ((object.getX() + object.getX_length() - 1) / cell)).getSoftness();
            int up_left_bound = (int) level.getConstruction_matrix().get((int) ((object.getY() + object.getY_length()) / cell)).get((int) (object.getX() / cell)).getSoftness();
            int up_right_bound = (int) level.getConstruction_matrix().get((int) ((object.getY() + object.getY_length()) / cell)).get((int) ((object.getX() + object.getX_length() - 1) / cell)).getSoftness();
            int tile_soft_d = Math.min(left_bound, right_bound);
            int tile_soft_u = Math.min(up_right_bound, up_left_bound);

            object.setXSpeed(object.getSpeed_X() - object.getSpeed_X() * 0.1f * (1 - tile_soft_d));

            if (tile_soft_d != 1) {
                object.setYSpeed(0);
                object.addCoord(0, cell - object.getY() % cell);
            } else if (tile_soft_u != 1) {
                object.setYSpeed(0);
                object.addCoord(0, -(object.getY_length() - (cell - object.getY() % cell) - ((int) (object.getY_length() - (cell - object.getY() % cell)) / cell * cell)));
            }
        }
    }
    public static void pickUpItem() {
        Item item;

        for (int i = 2; i < object_list.size(); i++) {
            item = (Item) object_list.get(i);

            if ((item.isTaken() == 0) & (Math.abs(item.getX() - getPlayer().getX()) <= getLevelPixel_per_Cell()) & (Math.abs(item.getY() - getPlayer().getY()) <= getLevelPixel_per_Cell() * 2)) {
                item.setTaken(1);
                item.setGravity(false);
                item.addSpeed(1, 0);
                break;
            }
        }
    }
    public static void throwItem(float mouse_x, float mouse_y) {
        Item item;

        for (int i = 2; i < object_list.size(); i++) {
            item = (Item) object_list.get(i);

            if ((item.isTaken() == 1)) {
                float x = mouse_x - getViewShiftX(Video.getWindow_width(), item.getX());
                float y = mouse_y - getViewShiftY(Video.getWindow_height(), item.getY());
                double vector_module = Math.sqrt(x * x + y * y);

                item.setTaken(0);
                item.setGravity(true);
                item.addSpeed((float) ((x / vector_module) * 30f) + getPlayer().getSpeed_X(), (float) ((y / vector_module) * 30f) + getPlayer().getSpeed_Y());
                break;
            }
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
        object_list.get(0).addSpeed((-3 * (a?1:0)) + (10 * (d?1:0)), (-3 * (s?1:0)) + (3 * (w?1:0)));
    }
    public static void addObjectCoord(int object_id, int x_delta, int y_delta) {
        object_list.get(object_id).addCoord(x_delta, y_delta);
    }
    public static void addRenderObject(float start_x, float start_y, int length_x, int length_y) {
        Item object = new Item(start_x, start_y, length_x, length_y);
        object_list.add(object);
    }

    public static Tile[][] getLevelViewMatrix(int width_view, int height_view) {
        Tile[][] submatrix = new Tile[(height_view/level.getPixel_per_cell())+2][(width_view/level.getPixel_per_cell())+2];
        int view_center_x = (int) object_list.get(0).getX();
        int view_center_y = (int) object_list.get(0).getY();
        for (int i = ((view_center_y - ((height_view - height_view%level.getPixel_per_cell()) / 2)) / level.getPixel_per_cell()); i < ((view_center_y + ((height_view - height_view%level.getPixel_per_cell()) / 2))/level.getPixel_per_cell()) + 2; i++) {
            for (int j = ((view_center_x - ((width_view - width_view%level.getPixel_per_cell()) / 2)) / level.getPixel_per_cell()); j < ((view_center_x + ((width_view - width_view%level.getPixel_per_cell()) / 2)) / level.getPixel_per_cell()) + 2; j++) {
                int cur_i = i - ((view_center_y - ((height_view - height_view%level.getPixel_per_cell()) / 2)) / level.getPixel_per_cell());
                int cur_j = j - ((view_center_x - ((width_view - width_view%level.getPixel_per_cell()) / 2))/level.getPixel_per_cell());

                submatrix[cur_i][cur_j] = level.getConstruction_matrix().get(i).get(j);
            }
        }
        return submatrix;
    }
    public static int getLevelPixel_per_Cell() {
        return level.getPixel_per_cell();
    }
    public static float getViewShiftX(int width_view, float obj_x) {
        return obj_x - (object_list.get(0).getX() - width_view/2);
    }
    public static float getViewShiftY(int height_view, float obj_y) {
        return  obj_y - (object_list.get(0).getY() - height_view/2);
    }
    public static Player getPlayer() {
        return (Player) object_list.get(1);
    }

    /**
     *
     * @param num 0 - ViewCenter, 1 - Player, 2.. - Item
     */
    public static Rendered getRenderedGameObject(int num) {
        return (Rendered) object_list.get(num);
    }
    public static int getGameObjectNum() {
        return object_list.size();
    }
}
