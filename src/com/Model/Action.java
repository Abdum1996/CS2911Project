package com.Model;

import java.awt.event.KeyEvent;

public enum Action {
	MOVE_UP, MOVE_DOWN, MOVE_LEFT, MOVE_RIGHT;
	
	public static Action readKeyEvent(KeyEvent e) {
		int kc = e.getKeyCode();
		if (kc == KeyEvent.VK_D || kc == KeyEvent.VK_RIGHT) {
            return MOVE_RIGHT;
        } else if (kc == KeyEvent.VK_A || kc == KeyEvent.VK_LEFT) {
            return MOVE_LEFT;
        } else if (kc == KeyEvent.VK_W || kc ==KeyEvent.VK_UP) {
            return MOVE_UP;
        } else if ((kc == KeyEvent.VK_S || kc ==KeyEvent.VK_DOWN)) {
            return MOVE_DOWN;
        }
		
		return null;
	}
}
