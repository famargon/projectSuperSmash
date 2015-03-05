/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nanoplatformer.samples;


import nanoplatformer.graphics.ImageInfo;
import nanoplatformer.graphics.graphicspool.IGraphicsPool;
import nanoplatformer.graphics.graphicspool.java2D.Java2DGraphicsPool;


/**
 *
 * @author Nestor
 */
public class B_SimpleGraphicPool {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        IGraphicsPool graphicsPool = new Java2DGraphicsPool();
        graphicsPool.loadFromCurrentDirectory();
        
       ImageInfo info=graphicsPool.getGraphicsInfoFromName("squareDev");
       System.out.println("Image name: "+info.getName()+", image size: "+info.getWidth()+"x"+info.getHeight());  
       
    }

}
