/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nanoplatformer.samples;

import java.awt.Color;
import nanoplatformer.graphics.graphicspool.java2D.Java2DGraphicsPool;
import nanoplatformer.graphics.output.java2D.AcceleratedFrame;
import nanoplatformer.graphics.renderer.IRenderer;
import nanoplatformer.graphics.renderer.java2D.Java2DRenderer;

/**
 *
 * @author Nestor
 */
public class C_SimpleRender {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Java2DGraphicsPool java2DGraphicsPool = new Java2DGraphicsPool();
        java2DGraphicsPool.loadFromCurrentDirectory();
        

        IRenderer renderer = new Java2DRenderer(java2DGraphicsPool, new AcceleratedFrame(640, 480));
        
        //------------------
        //render items from graphic names using only interfaces
        //------------------
        renderStuff(renderer);
    }

    

    
    private static void renderStuff(IRenderer renderer) {

        //we need to clear the background first
        renderer.fillBackground(Color.yellow);

        renderer.drawImage("squareDev", 64, 64);
        renderer.showBuffer();
    }

}
