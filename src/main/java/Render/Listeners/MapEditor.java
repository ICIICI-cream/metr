package Render.Listeners;

import Render.Graphic;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;

import static com.jogamp.opengl.GL.*;

public class MapEditor implements GLEventListener {

    private GL2 gl = null;

    @Override
    public void init(GLAutoDrawable drawable) {
        gl = drawable.getGL().getGL2();
        Graphic.initGL(gl);
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {

    }

    @Override
    public void display(GLAutoDrawable drawable) {
        gl = drawable.getGL().getGL2();

        gl.glClear(GL_COLOR_BUFFER_BIT);

        Graphic.setResolutionCoord(gl);
        Graphic.renderConstrictionNet(gl);
        Graphic.renderPlatformMatrix(gl);

    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {

    }
}
