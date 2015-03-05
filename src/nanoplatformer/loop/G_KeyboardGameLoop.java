/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nanoplatformer.loop;

import nanoplatformer.input.IPollableInput;

/**
 *
 * @author Nestor
 */
public class G_KeyboardGameLoop extends BaseGameLoop {

    private IPollableInput input;
    
    public G_KeyboardGameLoop(int framesPerSecond, IPollableInput input) {
        super(framesPerSecond);
        this.input=input;
    }
    
    
    
    @Override
    protected void performLoopOperations(long previousLoopTime_ms, long currentLoopTime_ms) {
       
        input.poll();
        if (input.isPressed(0)) System.out.println("key 0 pressed");
        if (input.isPressed(1)) System.out.println("key 1 pressed");
        if (input.isPressed(2)) System.out.println("key 2 pressed");
        if (input.isPressed(3)) System.out.println("key 3 pressed");
        
    }



     
}
