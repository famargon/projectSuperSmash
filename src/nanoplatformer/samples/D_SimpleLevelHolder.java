/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nanoplatformer.samples;

import nanoplatformer.graphics.graphicspool.IGraphicsPool;
import nanoplatformer.graphics.graphicspool.java2D.Java2DGraphicsPool;
import nanoplatformer.model.LevelComponent;
import nanoplatformer.model.levelholder.ILevelContainer;
import nanoplatformer.model.levelholder.ListBasedLevelHolder;

/**
 *
 * @author Nestor
 */
public class D_SimpleLevelHolder {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        IGraphicsPool graphicsPool = loadImagesFromDisk();
        
        ILevelContainer levelHolder=createLevel(graphicsPool);
      
        levelHolder.getAllLevelElements().forEach(component-> {
            System.out.println(
                    "Our level has a component called "+component.getImageInfo().getName()+
                    ", of size "+component.getImageInfo().getWidth()+"x"+component.getImageInfo().getHeight()+
                    ", at location x:"+component.getPositionX()+" y:"+component.getPositionY());  
    });
    }

    private static ILevelContainer createLevel(IGraphicsPool graphicsPool) {
        ILevelContainer levelHolder=new ListBasedLevelHolder();
        levelHolder.addLevelElement(new LevelComponent(32,32,graphicsPool.getGraphicsInfoFromName("squareDev")));
        levelHolder.addLevelElement(new LevelComponent(64,32,graphicsPool.getGraphicsInfoFromName("squareDev")));
        levelHolder.addLevelElement(new LevelComponent(96,32,graphicsPool.getGraphicsInfoFromName("squareDev")));
        levelHolder.addLevelElement(new LevelComponent(128,32,graphicsPool.getGraphicsInfoFromName("squareDev")));
        return levelHolder;
    }

    private static IGraphicsPool loadImagesFromDisk() {
        IGraphicsPool graphicsPool = new Java2DGraphicsPool();
        graphicsPool.loadFromCurrentDirectory();
        return graphicsPool;
    }

    

    

}
