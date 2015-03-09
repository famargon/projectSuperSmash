/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nanoplatformer.model;

import projectSuperSmash.graphics.ImageInfo;

/**
 *
 * @author Nestor
 */
public class LevelComponent {
    
    private float positionX;
    private float positionY;
    private ImageInfo graphicInfo;

    public LevelComponent(int positionX, int positionY, ImageInfo graphicResource) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.graphicInfo = graphicResource;
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
    
    
    
    
}
