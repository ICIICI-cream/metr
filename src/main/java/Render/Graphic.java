package Render;

import Engine.Engine;
import GameObject.Rendered;
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
    public static void renderLevel(GL2 gl) {
        int cell = Engine.getLevelPixel_per_Cell();

        for (Tile[] line : Engine.getLevelViewMatrix(Video.getWindow_width(), Video.getWindow_height())) {
            for (Tile tile : line) {
                if (tile != null){
                    float tile_x = Engine.getViewShiftX(Video.getWindow_width(), tile.getX());
                    float tile_y = Engine.getViewShiftY(Video.getWindow_height(), tile.getY());

                    renderObject(gl, tile, tile_x, tile_y, cell, cell);
//                    gl.glBindTexture(GL_TEXTURE_2D, tile.getTexture().getTextureObject());
//
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
                }
            }
        }
        gl.glFlush();
        gl.glBindTexture(GL_TEXTURE_2D, 0);
    }
    public static void renderGameObjects(GL2 gl) {
        Rendered object;

        for (int i = 1; i < Engine.getGameObjectNum(); i++) {
            object = Engine.getRenderedGameObject(i);

            float x = Engine.getViewShiftX(Video.getWindow_width(), object.getX());
            float y = Engine.getViewShiftY(Video.getWindow_height(), object.getY());
            int cell_x = object.getX_length();
            int cell_y = object.getY_length();

            renderObject(gl, object, x, y, cell_x, cell_y);

            gl.glFlush();
            gl.glBindTexture(GL_TEXTURE_2D, 0);
        }
    }
    private static void renderObject(GL2 gl, Rendered object,float x, float y, float cell_x, float cell_y) {

        gl.glBindTexture(GL_TEXTURE_2D, object.getTexture().getTextureObject());

        gl.glBegin((GL2.GL_QUADS));
        gl.glTexCoord2f(0, 0);
        gl.glVertex2f(x, y);
        gl.glTexCoord2f(1, 0);
        gl.glVertex2f(x + cell_x, y);
        gl.glTexCoord2f(1, 1);
        gl.glVertex2f(x + cell_x, y + cell_y);
        gl.glTexCoord2f(0, 1);
        gl.glVertex2f(x, y + cell_y);
        gl.glEnd();
    }
}