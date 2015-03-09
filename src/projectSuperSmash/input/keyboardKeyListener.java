/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectSuperSmash.input;

/**
 *
 * @author Fabian
 */
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class keyboardKeyListener extends KeyAdapter  {
	
	public static boolean up, down,left, right,p, w,s,a,d ,r ;
        private int pressCount = 1;
        
        
        
        
        public boolean[] getValues(int id){
           
            boolean[] pressedKeys = new boolean[5];
            if(id==1){
                pressedKeys[0] = w;
                pressedKeys[1] = s;
                pressedKeys[2] = a ;      
                pressedKeys[3] = d;
                pressedKeys[4] = r;}
            if(id==2){
                pressedKeys[0] = up;
                pressedKeys[1] = down;
                pressedKeys[2] = left;   
                pressedKeys[3] = right;
                pressedKeys[4] = p;}
        
            return pressedKeys;
        }
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_W) w=true;
                if(e.getKeyCode()==KeyEvent.VK_S) s=true;
                if(e.getKeyCode()==KeyEvent.VK_A) a=true;
                if(e.getKeyCode()==KeyEvent.VK_D) d=true;
                if(e.getKeyCode()==KeyEvent.VK_R) r=true;
                
                if(e.getKeyCode()==KeyEvent.VK_UP) up=true;
                if(e.getKeyCode()==KeyEvent.VK_DOWN) down=true;
                if(e.getKeyCode()==KeyEvent.VK_LEFT) left=true;
                if(e.getKeyCode()==KeyEvent.VK_RIGHT) right=true;
                if(e.getKeyCode()==KeyEvent.VK_P) p=true;
	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_W) w=false;
                if(e.getKeyCode()==KeyEvent.VK_S) s=false;
                if(e.getKeyCode()==KeyEvent.VK_A) a=false;
                if(e.getKeyCode()==KeyEvent.VK_D) d=false;
                if(e.getKeyCode()==KeyEvent.VK_R) r=false;
                
                if(e.getKeyCode()==KeyEvent.VK_UP) up=false;
                if(e.getKeyCode()==KeyEvent.VK_DOWN) down=false;
                if(e.getKeyCode()==KeyEvent.VK_LEFT) left=false;
                if(e.getKeyCode()==KeyEvent.VK_RIGHT) right=false;
                if(e.getKeyCode()==KeyEvent.VK_P) p=false;
	}

	public void keyTyped(KeyEvent e) {
		if (e.getKeyChar() == 27) {
			System.exit(0);
		}
                if (projectSuperSmash.loop.CompleteLoopWithPhysics2players.waitingForKeyPress) {
				if (pressCount == 1) {
					// since we've now recieved our key typed
					// event we can mark it as such and start 
					// our new game
					projectSuperSmash.loop.CompleteLoopWithPhysics2players.waitingForKeyPress = false;
					projectSuperSmash.samples.dosPersonajes.start();
					pressCount = 0;
				} else {
					pressCount++;
				}
			}
	}
        
}
