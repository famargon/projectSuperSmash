/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nanoplatformer.input;

/**
 *
 * @author Fabian
 */
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyboardKeyListener implements KeyListener {
	
	
	private transient boolean[] keys = new boolean[65536];
	public transient boolean up, down,left, right, w,s,a,d ;

	public void update() {
		
                up   = keys[KeyEvent.VK_UP];
		down = keys[KeyEvent.VK_DOWN];
		left = keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_RIGHT];
		
		w  = keys[KeyEvent.VK_W];
		s = keys[KeyEvent.VK_S];
		a = keys[KeyEvent.VK_A];
		d = keys[KeyEvent.VK_D];
               
		
	}
	
        public boolean[] getValues(int id){
           
            boolean[] pressedKeys = new boolean[4];
            if(id==1){
                pressedKeys[0] = w;
                pressedKeys[1] = s;
                pressedKeys[2] = a ;      
                pressedKeys[3] = d;}
            if(id==2){
                pressedKeys[0] = up;
                pressedKeys[1] = down;
                pressedKeys[2] = right;   
                pressedKeys[3] = left;}
        
            return pressedKeys;
        }
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent arg0) {
		
	}
        
}
