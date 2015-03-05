/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nanoplatformer.model;

import nanoplatformer.graphics.ImageInfo;

/**
 *
 * @author Nestor
 */
public class LevelComponent {
    
    private int positionX;
    private int positionY;
    private ImageInfo graphicInfo;

    public LevelComponent(int positionX, int positionY, ImageInfo graphicResource) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.graphicInfo = graphicResource;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public ImageInfo getImageInfo() {
        return graphicInfo;
    }
    
    
    
    
}
