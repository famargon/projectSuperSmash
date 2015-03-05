/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nanoplatformer.graphics.graphicspool;

import java.util.HashMap;
import java.util.Map;
import nanoplatformer.graphics.ImageInfo;

/**
 *
 * @author Nestor
 */
public abstract class BaseGraphicsPool implements IGraphicsPool {
    
    private Map<String,ImageInfo> graphicsItems;

    public BaseGraphicsPool() {
        
        graphicsItems=new HashMap();   
    }
    
    @Override
    public void loadFromCurrentDirectory() {
        graphicsItems=loadSpecificImages();
    }
    

    @Override
    public ImageInfo getGraphicsInfoFromName(String name) {
       return graphicsItems.get(name);
    }
    
    protected abstract Map<String,ImageInfo> loadSpecificImages();
    
}
