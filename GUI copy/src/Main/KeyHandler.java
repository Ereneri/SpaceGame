package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    // boolean for if a key is pressed to render an sprite in the game
    public boolean upPressed, downPressed, leftPressed, rightPressed, shotKeyPressed;

    // TBH I don't know what this does
    @Override
    public void keyTyped(KeyEvent e) {
    }

    // takes the boolean values from the key pressed and sets them to true
    @Override
    public void keyPressed(KeyEvent e) {
        //System.out.println("keyPressed="+KeyEvent.getKeyText(e.getKeyCode()));
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_SPACE) {
            shotKeyPressed = true;
        }
    }

    // takes the boolean values from the key released and sets them to false
    @Override
    public void keyReleased(KeyEvent e) {
        //System.out.println("keyReleased="+KeyEvent.getKeyText(e.getKeyCode()));
        
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_SPACE) {
            shotKeyPressed = false;
        }
    }
}
