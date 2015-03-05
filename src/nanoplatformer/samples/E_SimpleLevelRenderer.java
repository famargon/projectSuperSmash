/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nanoplatformer.samples;

import java.awt.Color;
import nanoplatformer.graphics.graphicspool.IGraphicsPool;
import nanoplatformer.graphics.graphicspool.java2D.Java2DGraphicsPool;
import nanoplatformer.graphics.output.java2D.AcceleratedFrame;
import nanoplatformer.graphics.renderer.IRenderer;
import nanoplatformer.graphics.renderer.java2D.Java2DRenderer;
import nanoplatformer.model.LevelComponent;
import nanoplatformer.model.levelholder.ILevelContainer;
import nanoplatformer.model.levelholder.ListBasedLevelHolder;

/**
 *
 * @author Nestor
 */
public class E_SimpleLevelRenderer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Java2DGraphicsPool graphicsPool = new Java2DGraphicsPool();
        graphicsPool.loadFromCurrentDirectory();

        IRenderer renderer = new Java2DRenderer(graphicsPool, new AcceleratedFrame(640, 480));
        
        ILevelContainer levelContainer=createLevel(graphicsPool);

        renderLevel(renderer, levelContainer); 
    }

    private static void renderLevel(IRenderer renderer, ILevelContainer levelHolder) {
        
        renderer.fillBackground(Color.yellow);
        levelHolder.getAllLevelElements().forEach(component-> {
            renderer.drawImage(component.getImageInfo().getName(), component.getPositionX(), component.getPositionY());     
        });
        renderer.showBuffer();
    }

    private static ILevelContainer createLevel(IGraphicsPool graphicsPool) {
        ILevelContainer levelHolder=new ListBasedLevelHolder();
        levelHolder.addLevelElement(new LevelComponent(32,32,graphicsPool.getGraphicsInfoFromName("squareDev")));
        levelHolder.addLevelElement(new LevelComponent(64,32,graphicsPool.getGraphicsInfoFromName("squareDev")));
        levelHolder.addLevelElement(new LevelComponent(96,32,graphicsPool.getGraphicsInfoFromName("squareDev")));
        levelHolder.addLevelElement(new LevelComponent(128,32,graphicsPool.getGraphicsInfoFromName("squareDev")));
        return levelHolder;
    }



    

    

}
