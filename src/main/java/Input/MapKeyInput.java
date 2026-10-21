package Input;

import Engine.Engine;
import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;

import java.util.HashMap;

public class MapKeyInput implements KeyListener {
    private HashMap<Short, Boolean> key_bool;
    private HashMap<Short, Runnable> action_key;

    public MapKeyInput() {
        key_bool = new HashMap<>();
        action_key = new HashMap<>();

        key_bool.put(KeyEvent.VK_D, false);
        key_bool.put(KeyEvent.VK_A, false);
        key_bool.put(KeyEvent.VK_W, false);
        key_bool.put(KeyEvent.VK_S, false);

        action_key.put(KeyEvent.VK_ENTER, () -> Engine.saveMapToBin("Current_saved_level"));
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.isAutoRepeat()) {return;}

        key_bool.put(e.getKeyCode(), true);
        Engine.addViewCenterSpeed(key_bool.get(KeyEvent.VK_W), key_bool.get(KeyEvent.VK_A), key_bool.get(KeyEvent.VK_S), key_bool.get(KeyEvent.VK_D));

        Runnable action = action_key.get(e.getKeyCode());
        if (action != null) {
            action.run();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.isAutoRepeat()) {return;}

        key_bool.put(e.getKeyCode(), false);
        Engine.getViewCenter().setYSpeed(0);
        Engine.getViewCenter().setXSpeed(0);
    }
}
