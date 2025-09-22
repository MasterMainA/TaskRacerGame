import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    boolean WPressed;
    boolean SPressed;
    boolean APressed;
    boolean DPressed;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            WPressed = true;
            System.out.println("WPressedKey");
        }
        if (code == KeyEvent.VK_S) {
            SPressed = true;
            System.out.println("SPressedKey");
        }
        if (code == KeyEvent.VK_A) {
            APressed = true;
            System.out.println("APressedKey");
        }
        if (code == KeyEvent.VK_D) {
            DPressed = true;
            System.out.println("DPressedKey");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            WPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            SPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            APressed = false;
        }
        if (code == KeyEvent.VK_D) {
            DPressed = false;
        }
    }
}
