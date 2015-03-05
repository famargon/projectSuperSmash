/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nanoplatformer.samples;

import java.awt.Color;
import java.io.IOException;
import nanoplatformer.graphics.graphicspool.java2D.Java2DGraphicsPool;
import nanoplatformer.graphics.output.java2D.AcceleratedFrame;
import nanoplatformer.graphics.renderer.IRenderer;
import nanoplatformer.graphics.renderer.java2D.Java2DRenderer;
import nanoplatformer.loader.LevelLoader;
import nanoplatformer.model.levelholder.ILevelContainer;

/**
 *
 * @author Nestor
 */
public class F_SimpleLevelLoader {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        Java2DGraphicsPool graphicsPool = new Java2DGraphicsPool();
        graphicsPool.loadFromCurrentDirectory();

        IRenderer renderer = new Java2DRenderer(graphicsPool, new AcceleratedFrame(640, 480));
        
        ILevelContainer levelHolder=new LevelLoader().loadLevel("basicLevel.txt", graphicsPool);

        renderLevel(renderer, levelHolder); 
    }

    private static void renderLevel(IRenderer renderer, ILevelContainer levelHolder) {
        
        renderer.fillBackground(Color.yellow);
        levelHolder.getAllLevelElements().forEach(component-> {
            renderer.drawImage(component.getImageInfo().getName(), component.getPositionX(), component.getPositionY());     
        });
        renderer.showBuffer();
    }





    

    

}
