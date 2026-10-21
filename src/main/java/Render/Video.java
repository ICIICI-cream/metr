package Render;

import Input.GameKeyInput;
import Input.GameMouseInput;
import Input.WindowInput;
import Render.Listeners.Game;
import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;

public class Video {
    private static GLWindow window = null;
    private static int window_width = 1920;
    private static int window_height = 1080;

    public static void create() {
        GLProfile.initSingleton();
        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities cap = new GLCapabilities(profile);
        window = GLWindow.create(cap);
        window.setSize(window_width, window_height);
        window.setVisible(true);
        window.setResizable(false);
        window.setTitle("Metr");

        //window.setFullscreen(true);

        window_width = window.getSurfaceWidth();
        window_height = window.getSurfaceHeight();


        window.addWindowListener(new WindowInput());
    }
    public static void render() {
        window.display();
    }
    public static void startGame() {
        window.addGLEventListener(new Game());
        window.addKeyListener(new GameKeyInput());
        window.addMouseListener(new GameMouseInput());
    }

    public static int getWindow_width() {
        return window_width;
    }

    public static int getWindow_height() {
        return window_height;
    }
}
