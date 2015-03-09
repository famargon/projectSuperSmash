/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectSuperSmash.model.character;

import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Float;
import java.util.List;
import projectSuperSmash.graphics.ImageInfo;
import nanoplatformer.model.LevelComponent;
/**
 *
 * @author Nestor
 */
public class MainCharacterWithPhysics2playersedition extends LevelComponent{

    private static final float MAX_RUNNING_SPEED = 85;
    private static final float MAX_FALLING_SPEED = 140;

    private static final float HORIZONTAL_ACCELERATION = 330f;
    private static final float HORIZONTAL_DECCELERATION = 80f;
    private static final float GRAVITY = 3f;
    
    private static final float JUMPING_SPEED = 210;
    
    /** The interval between our players attack (ms) */
    private static final long AAInterval= 500;
    
    private float positionX;
    private float positionY;

    private float speedX;
    private float speedY;

    private ImageInfo graphicInfoL;
    private ImageInfo graphicInfoR;
    private ImageInfo graphicInfoAA;
    
    //es un punto respecto a la positionX, donde se creará el rectangulo de colision del AA
    private float AAPoint;
    private String lastAAside;
    
    private static float AAForce = 210;
    
    private long lastAA;
    
    private boolean printAA;
    
    
   

    public MainCharacterWithPhysics2playersedition(int positionX, int positionY, ImageInfo graphicInfoL, ImageInfo graphicInfoR,ImageInfo AA) {
        super(positionX,positionY,graphicInfoR);
        this.positionX = positionX;
        this.positionY = positionY;
        this.graphicInfoL = graphicInfoL;
        this.graphicInfoR= graphicInfoR;
        this.graphicInfoAA=AA;
        lastAA=0;
        printAA=false;
        
        
       
    }

    
    
    public float getPositionX() {
        return positionX;
    }

    public float getPositionY() {
        return positionY;
    }
    
    public float getSpeedX() {
        return speedX;
    }
    
    public float getSpeedY() {
        return speedX;
    }

    public ImageInfo getImageInfoL() {
        return graphicInfoL;
    }
    public ImageInfo getImageInfoR() {
        return graphicInfoR;
    }
    public ImageInfo getImageInfoAA() {
        return graphicInfoAA;
    }
    public void setAAPoint(){
    
    if(lastAAside.equals("l")) {
                AAPoint=positionX-graphicInfoAA.getWidth();
                AAForce=-AAForce;}
    if(lastAAside.equals("r")) {
                AAPoint=positionX+graphicInfoL.getWidth();
                }
    }
    public void setAAPoint(String s){
    lastAAside=s;
    if(s.equals("l")) {
                AAPoint=positionX-graphicInfoAA.getWidth();
                AAForce=-AAForce;}
    if(s.equals("r")) {
                AAPoint=positionX+graphicInfoL.getWidth();
                }
    }
    public float getAAPoint() {
      return AAPoint; 
    }
    public void recieveAA(float force){
        speedX+=force;
        speedY-=JUMPING_SPEED;
    }
    public boolean canAA(){ 
        if(printAA) {
            printAA=false;
            return true;}
        return printAA;
        
    }
    public void tryToAA(MainCharacterWithPhysics2playersedition otherCharacter){
        if (System.currentTimeMillis() - lastAA < AAInterval) {
			return ;
		}
		
	// if we waited long enough, try to hit
	lastAA = System.currentTimeMillis();
        printAA=true;
        
        //se comprueba si el AA colisiona con el rival y lo mueve
        if(new Rectangle2D.Float(AAPoint,positionY,graphicInfoAA.getWidth(),graphicInfoAA.getHeight())
                .intersects(otherCharacter.positionX,otherCharacter.positionY,
                        otherCharacter.graphicInfoL.getWidth(),otherCharacter.graphicInfoL.getHeight()))
            otherCharacter.recieveAA(AAForce);
        
    }

    public void update(boolean[] keys, List<Rectangle2D.Float> collisions, long timespan,MainCharacterWithPhysics2playersedition otherCharacter) {

        boolean collision_top    = collides(new Rectangle2D.Float((int) positionX+2, (int) positionY, graphicInfoL.getWidth()-4, 1), collisions);
        boolean collision_bottom = collides(new Rectangle2D.Float((int) positionX+2, (int) positionY + graphicInfoL.getHeight(), graphicInfoL.getWidth()-4, 1), collisions);
        
        boolean collision_left = collides(new Rectangle2D.Float((int) positionX, (int) positionY, 1, graphicInfoL.getHeight()-2), collisions);
        boolean collision_right = collides(new Rectangle2D.Float((int) positionX + graphicInfoL.getWidth(), (int) positionY, 1, graphicInfoL.getHeight()-2), collisions);
        
        float elapsedSeconds=timespan*0.001f;
        
            
        if (collision_bottom) {
         speedY=0;   
         if (keys[0]) speedY-=JUMPING_SPEED;
        }
        else {speedY+=GRAVITY;}
        
        if (keys[2]) speedX-=HORIZONTAL_ACCELERATION*elapsedSeconds;
        if (keys[3]) speedX+=HORIZONTAL_ACCELERATION*elapsedSeconds;           
         
        if (!keys[2] && !keys[3]) {
             if (Math.abs(speedX)>1f)  speedX-=HORIZONTAL_DECCELERATION*elapsedSeconds*Math.signum(speedX);    
             else speedX= 0;
        }
        
        if (Math.abs(speedX)>MAX_RUNNING_SPEED) speedX=MAX_RUNNING_SPEED*Math.signum(speedX);
        if (Math.abs(speedY)>MAX_FALLING_SPEED) speedY=MAX_FALLING_SPEED*Math.signum(speedY);
        
        if (collision_top && speedY<0) speedY=0;    
        if (collision_left && speedX<0)  speedX=0;
        if (collision_right && speedX>0)  speedX=0;
        
        /*Rectangle2D.Float me = new Rectangle2D.Float(positionX,positionY,graphicInfoL.getWidth(),graphicInfoL.getHeight());
        if(me.intersects(otherCharacter.positionX,otherCharacter.positionY,
                        otherCharacter.graphicInfoL.getWidth(),otherCharacter.graphicInfoL.getHeight()) && speedX==0){
            
        speedX += otherCharacter.getSpeedX();
        speedY =0;
        
            
        } 
        if(me.intersects(otherCharacter.positionX,otherCharacter.positionY,
                        otherCharacter.graphicInfoL.getWidth(),otherCharacter.graphicInfoL.getHeight()) && speedX!=0){
            
        speedX = - speedX;
        speedY = 0;        
            
        }*/
        
         if(keys[4]) tryToAA(otherCharacter);
        
        positionX+=speedX*elapsedSeconds;
        positionY+=speedY*elapsedSeconds;  
    }

    private boolean collides(Rectangle2D.Float rectangle, List<Rectangle2D.Float> otherCollisions) {

        for (Rectangle2D.Float collision : otherCollisions) {
            if (collision.intersects(rectangle)) {
                return true;
            }
        }

        return false;
    }

   

}
