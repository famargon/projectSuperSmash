/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nanoplatformer.loop;

/**
 *
 * @author Nestor
 */
public class A_JustTimingGameLoop extends BaseGameLoop {


    public A_JustTimingGameLoop(int framesPerSecond) {
        super(framesPerSecond);
    }
    
    
    
    @Override
    protected void performLoopOperations(long previousLoopTime_ms, long currentLoopTime_ms) {
        System.out.println("current time: "+currentLoopTime_ms+", elapsed time: "+(currentLoopTime_ms-previousLoopTime_ms));
        
        performRandomCalculations();
        
    }

    private void performRandomCalculations() {
        try {
            Thread.sleep( (long)(Math.random()*10));
        } catch (InterruptedException ex){}
    }

     
}
