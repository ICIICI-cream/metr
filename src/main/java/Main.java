import Engine.Engine;
import Render.Video;

public class Main {
    public static void main(String[] args) {
        Video.create();
        if (args.length != 0) {
            if (args[0].equals("me")) {
                Engine.initMap();
                Video.startMapEditor();
            }
        } else {
            Engine.initLevel();
        }
        Loop.start();
    }
}
