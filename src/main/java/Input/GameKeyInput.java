package Input;

import Engine.Engine;
import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;

import java.util.HashMap;

public class GameKeyInput implements KeyListener {
    private HashMap<Short, Runnable> key_action;
    private HashMap<Short, Boolean> key_bool;

    public GameK    eyInput() {
        key_action = new HashMap<>();
        key_bool = new HashMap<>();

        key_action.put(KeyEvent.VK_SPACE, () -> {
            //Engine.getPlayer().addCoord(0, 0);
            Engine.getPlayer().addSpeed(0, 30);
        });
        key_action.put(KeyEvent.VK_F, () ->
                Engine.pickUpItem()
        );

        key_bool.put(KeyEvent.VK_D, false);
        key_bool.put(KeyEvent.VK_A, false);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.isAutoRepeat()) {
            return;
        }

        key_bool.put(e.getKeyCode(), true);
        Engine.getPlayer().setAD(key_bool.get(KeyEvent.VK_A), key_bool.get(KeyEvent.VK_D));

        Runnable action = key_action.get(e.getKeyCode());
        if (action != null) {
            action.run();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.isAutoRepeat()) {
            return;
        }

        key_bool.put(e.getKeyCode(), false);
        Engine.getPlayer().setAD(key_bool.get(KeyEvent.VK_A), key_bool.get(KeyEvent.VK_D));
    }
}
