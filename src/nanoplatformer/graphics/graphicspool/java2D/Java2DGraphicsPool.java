/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nanoplatformer.graphics.graphicspool.java2D;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import nanoplatformer.graphics.ImageInfo;
import nanoplatformer.graphics.graphicspool.BaseGraphicsPool;
import nanoplatformer.graphics.renderer.PNGFileFilter;

/**
 *
 * @author Nestor
 */
public class Java2DGraphicsPool extends BaseGraphicsPool {

    
    private Map<String, BufferedImage> graphicsMap;
    

    public Java2DGraphicsPool() {
        this.graphicsMap = new HashMap();


    }

    public BufferedImage getBufferedImageFromName(String name) {
        return graphicsMap.get(name);
    }

    @Override
    protected Map<String, ImageInfo> loadSpecificImages() {

        Map<String, ImageInfo> graphicsItemsMap=new HashMap();
        String[] imageFileNames = new File(".").list(new PNGFileFilter());
        

        for (String filename : imageFileNames) {
            try {
                    BufferedImage image=ImageIO.read(new File(filename));
                    ImageInfo item=new ImageInfo(removeExtension(filename),image.getWidth(),image.getHeight());
                    
                graphicsMap.put(item.getName(), image);
                graphicsItemsMap.put(item.getName(), item);
            } catch (IOException ex) {
            }
        }
        return graphicsItemsMap;
        
    }

    private static String removeExtension(String image) {
        return image.substring(0, image.indexOf("."));
    }

}
