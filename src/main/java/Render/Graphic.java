package Render;

import Engine.Engine;
import GameObject.Tile;

import com.jogamp.opengl.GL2;

import static com.jogamp.opengl.GL.*;
import static com.jogamp.opengl.GL.GL_ONE_MINUS_SRC_ALPHA;

public class Graphic {
    public static void initGL(GL2 gl) {
        gl.glClearColor(0.15f,0.15f,0.15f,1);

        gl.glEnable(GL_TEXTURE_2D);
        gl.glEnable(GL_BLEND);
        gl.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    }
    public static void setResolutionCoord(GL2 gl) {
        gl.glViewport(0, 0, Video.getWindow_width(), Video.getWindow_height());
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(0, Video.getWindow_width(), 0, Video.getWindow_height(), -1, 1);
    }
    public static void renderConstrictionNet(GL2 gl) {
        int cell = Engine.getMapPixel_per_Cell();
        int view_matrix_X_size = Engine.getViewMatrix(Video.getWindow_width(), Video.getWindow_height())[0].length;
        int view_matrix_Y_size = Engine.getViewMatrix(Video.getWindow_width(), Video.getWindow_height()).length;

        for (int i=0; i<=view_matrix_Y_size; i++) {
            float y = Engine.getViewShiftY(Video.getWindow_height(),i * cell);

            gl.glBegin(gl.GL_LINES);
            gl.glVertex2f(0, y);
            gl.glVertex2f(Video.getWindow_width(), y);
            gl.glEnd();
        }
        for (int j=0; j<=view_matrix_X_size; j++) {
            float x = Engine.getViewShiftX(Video.getWindow_width(),j * cell);

            gl.glBegin(gl.GL_LINES);
            gl.glVertex2f(x, 0);
            gl.glVertex2f(x, Video.getWindow_height());
            gl.glEnd();
        }
        gl.glFlush();
    }
    public static void renderPlatformMatrix(GL2 gl) {
        int cell = Engine.getMapPixel_per_Cell();

        for (Tile[] line : Engine.getViewMatrix(Video.getWindow_width(), Video.getWindow_height())) {
            for (Tile tile : line) {
                if (tile != null){
                    float tile_x = Engine.getViewShiftX(Video.getWindow_width(), tile.getX());
                    float tile_y = Engine.getViewShiftY(Video.getWindow_height(), tile.getY());

                    gl.glBindTexture(GL_TEXTURE_2D, tile.getTexture().getTextureObject());

                    gl.glBegin((GL2.GL_QUADS));
                    gl.glTexCoord2f(0, 0);
                    gl.glVertex2f(tile_x, tile_y);
                    gl.glTexCoord2f(1, 0);
                    gl.glVertex2f(tile_x + cell, tile_y);
                    gl.glTexCoord2f(1, 1);
                    gl.glVertex2f(tile_x + cell, tile_y + cell);
                    gl.glTexCoord2f(0, 1);
                    gl.glVertex2f(tile_x, tile_y + cell);
                    gl.glEnd();
                }
            }
        }
        gl.glFlush();
        gl.glBindTexture(GL_TEXTURE_2D, 0);
    }
//    public static void renderLevel(GL2 gl) {
//        int cell = Engine.getLevelPixel_per_Cell();
//
//        for (Tile[] line : Engine.getLevelViewMatrix(Video.getWindow_width(), Video.getWindow_height())) {
//            for (Tile tile : line) {
//                if (tile != null){
//                    float tile_x = Engine.getViewShiftX(Video.getWindow_width(), tile.getX());
//                    float tile_y = Engine.getViewShiftY(Video.getWindow_height(), tile.getY());
//
//                    gl.glBindTexture(GL_TEXTURE_2D, tile.getTexture().getTextureObject());
//                    gl.glBegin((GL2.GL_QUADS));
//                    gl.glTexCoord2f(0, 0);
//                    gl.glVertex2f(tile_x, tile_y);
//                    gl.glTexCoord2f(1, 0);
//                    gl.glVertex2f(tile_x + cell, tile_y);
//                    gl.glTexCoord2f(1, 1);
//                    gl.glVertex2f(tile_x + cell, tile_y + cell);
//                    gl.glTexCoord2f(0, 1);
//                    gl.glVertex2f(tile_x, tile_y + cell);
//                    gl.glEnd();
//                }
//            }
//        }
//        gl.glFlush();
//        gl.glBindTexture(GL_TEXTURE_2D, 0);
//    }
//    public static void renderPlayer(GL2 gl) {
//        float x = Engine.getViewShiftX(Video.getWindow_width(), Engine.getPlayer().getX());
//        float y = Engine.getViewShiftY(Video.getWindow_height(), Engine.getPlayer().getY());
//        int cell_x = Engine.getPlayer().getX_length();
//        int cell_y = Engine.getPlayer().getY_length();
//
//        gl.glBindTexture(GL_TEXTURE_2D, Engine.getPlayer().getTexture().getTextureObject());
//
//        gl.glBegin((GL2.GL_QUADS));
//        gl.glTexCoord2f(0, 0);
//        gl.glVertex2f(x, y);
//        gl.glTexCoord2f(1, 0);
//        gl.glVertex2f(x + cell_x, y);
//        gl.glTexCoord2f(1, 1);
//        gl.glVertex2f(x + cell_x, y + cell_y);
//        gl.glTexCoord2f(0, 1);
//        gl.glVertex2f(x, y + cell_y);
//        gl.glEnd();
//
//        gl.glFlush();
//        gl.glBindTexture(GL_TEXTURE_2D, 0);
//    }
}