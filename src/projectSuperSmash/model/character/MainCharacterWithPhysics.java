/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectSuperSmash.model.character;

import java.awt.Rectangle;
import java.util.List;
import projectSuperSmash.graphics.ImageInfo;

/**
 *
 * @author Nestor
 */
public class MainCharacterWithPhysics {

    private static final float MAX_RUNNING_SPEED = 85;
    private static final float MAX_FALLING_SPEED = 75;

    private static final float HORIZONTAL_ACCELERATION = 200f;
    private static final float GRAVITY = 4f;
    
    private static final float JUMPING_SPEED = 160;

    private float positionX;
    private float positionY;

    private float speedX;
    private float speedY;

    private ImageInfo graphicInfo;

    public MainCharacterWithPhysics(int positionX, int positionY, ImageInfo graphicInfo) {
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

    public void update(boolean[] keys, List<Rectangle> collisions, long timespan) {

        boolean collision_top    = collides(new Rectangle((int) positionX+2, (int) positionY, graphicInfo.getWidth()-4, 1), collisions);
        boolean collision_bottom = collides(new Rectangle((int) positionX+2, (int) positionY + graphicInfo.getHeight(), graphicInfo.getWidth()-4, 1), collisions);
        
        boolean collision_left = collides(new Rectangle((int) positionX, (int) positionY, 1, graphicInfo.getHeight()-2), collisions);
        boolean collision_right = collides(new Rectangle((int) positionX + graphicInfo.getWidth(), (int) positionY, 1, graphicInfo.getHeight()-2), collisions);
        
        float elapsedSeconds=timespan*0.001f;
                
        if (collision_bottom) {
         speedY=0;   
         if (keys[0]) speedY-=JUMPING_SPEED;
        }
        else {speedY+=GRAVITY;}
        
        
        if (keys[2]) speedX-=HORIZONTAL_ACCELERATION*elapsedSeconds;
        if (keys[3]) speedX+=HORIZONTAL_ACCELERATION*elapsedSeconds;   
         
        if (!keys[2] && !keys[3]) {
             if (Math.abs(speedX)>1f)  speedX-=HORIZONTAL_ACCELERATION*elapsedSeconds*Math.signum(speedX);    
             else speedX=0;
        }
        
        if (Math.abs(speedX)>MAX_RUNNING_SPEED) speedX=MAX_RUNNING_SPEED*Math.signum(speedX);
        
        if (collision_top && speedY<0) speedY=0;    
        if (collision_left && speedX<0)  speedX=0;
        if (collision_right && speedX>0)  speedX=0;
        
        positionX+=speedX*elapsedSeconds;
        positionY+=speedY*elapsedSeconds;  
    }

    private boolean collides(Rectangle rectangle, List<Rectangle> otherCollisions) {

        for (Rectangle collision : otherCollisions) {
            if (collision.intersects(rectangle)) {
                return true;
            }
        }

        return false;
    }

}
