import Engine.Engine;
import Render.Video;

public class Main {
    public static void main(String[] args) {
        Video.create();

        Engine.initLevel();
        Video.startGame();

        Loop.start();
    }
}
