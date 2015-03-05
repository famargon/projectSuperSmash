/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nanoplatformer.samples;

import nanoplatformer.loop.BaseGameLoop;
import nanoplatformer.loop.A_JustTimingGameLoop;

/**
 *
 * @author Nestor
 */
public class A_SimpleTimingLoop {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        BaseGameLoop loop= new A_JustTimingGameLoop(300);
        loop.start();
    }
    
}
