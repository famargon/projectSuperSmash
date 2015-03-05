/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nanoplatformer.samples;

import nanoplatformer.graphics.output.java2D.AcceleratedFrame;
import nanoplatformer.input.IPollableInput;
import nanoplatformer.input.Keyboard;
import nanoplatformer.loop.BaseGameLoop;
import nanoplatformer.loop.G_KeyboardGameLoop;

/**
 *
 * @author Nestor
 */
public class G_SimpleKeyboardLoop {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        new AcceleratedFrame(640, 480);
        
        IPollableInput keyboard=new Keyboard(new String[]{"W","S","A","D"});
        
        BaseGameLoop loop= new G_KeyboardGameLoop(30,keyboard);
        loop.start();
    }
    
}
