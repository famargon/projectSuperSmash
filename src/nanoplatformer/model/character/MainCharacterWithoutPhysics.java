/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nanoplatformer.model.character;

import nanoplatformer.graphics.ImageInfo;

/**
 *
 * @author Nestor
 */
public class MainCharacterWithoutPhysics  {
    
    
    private static float PIXELS_PER_SECOND=64;
    
    private float positionX;
    private float positionY;
    private ImageInfo graphicInfo;

    public MainCharacterWithoutPhysics(int positionX, int positionY, ImageInfo graphicInfo) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.graphicInfo = graphicInfo;
    }
    

    public float getPositionX() {
        return positionX;
    }

    public float getPositionY() {
        return positionY;
    }

    public ImageInfo getImageInfo() {
        return graphicInfo;
    }

    public void update(boolean[] keys,long timespan) {
        
        float pixelsToMove=  (timespan*PIXELS_PER_SECOND)*0.001f;
                
        if (keys[0]) positionY-=pixelsToMove;
        if (keys[1]) positionY+=pixelsToMove;
        if (keys[2]) positionX-=pixelsToMove;
        if (keys[3]) positionX+=pixelsToMove;
        
    }
    
    
}
