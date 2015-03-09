/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectSuperSmash.loop;


import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Float;
import java.util.ArrayList;
import java.util.List;
import nanoplatformer.graphics.renderer.IRenderer;
import projectSuperSmash.input.keyboardKeyListener;
import nanoplatformer.model.LevelComponent;
import projectSuperSmash.model.character.MainCharacterWithPhysics2playersedition;
import projectSuperSmash.model.levelholder.ILevelContainer;

/**
 *
 * @author Nestor
 */
public class CompleteLoopWithPhysics2players extends BaseGameLoop {

    private keyboardKeyListener teclado;
    private ILevelContainer level;
    private MainCharacterWithPhysics2playersedition character1;
    private MainCharacterWithPhysics2playersedition character2;
    private IRenderer renderer;
    private boolean firstTimeRender;
    private String activeImagec1,activeImagec2;
    public static boolean waitingForKeyPress ;
    public static String message;

    public CompleteLoopWithPhysics2players(int framesPerSecond, keyboardKeyListener input1, ILevelContainer level, MainCharacterWithPhysics2playersedition character1,MainCharacterWithPhysics2playersedition character2, IRenderer renderer) {
        super(framesPerSecond);
        this.teclado = input1;
        
        this.level = level;
        this.character1 = character1;
        this.character2 = character2;
        this.renderer = renderer;
        firstTimeRender=true;
        waitingForKeyPress=true;
        
    }

    @Override
    protected void performLoopOperations(long previousLoopTime_ms, long currentLoopTime_ms) {
       if (waitingForKeyPress) {
				renderer.drawMessage("Pulsa cualquier tecla para empezar");
				renderer.showBuffer();
                                render();
			}else{
        
        boolean[] keys1;
        boolean[] keys2;
        keys1=teclado.getValues(1);
        keys2=teclado.getValues(2);
      
        
        List<Rectangle2D.Float> collisions=getLevelCollisions();
        
        //actualiza la posición de los persnajes, para que luego render los pinte donde toca
        character1.update(keys1,collisions, currentLoopTime_ms - previousLoopTime_ms,character2);
        character2.update(keys2,collisions, currentLoopTime_ms - previousLoopTime_ms,character1);
        
        render(keys1,keys2);
       }
    }

    private List<Rectangle2D.Float> getLevelCollisions() {
        List<Rectangle2D.Float> collisions=new ArrayList();
        level.getAllLevelElements().forEach(component->{collisions.add(createRectangle(component));});
        return collisions;
    }

    private static Rectangle2D.Float createRectangle(LevelComponent component) {
        return new Rectangle2D.Float((int)component.getPositionX(), (int) component.getPositionY(),component.getImageInfo().getWidth(),component.getImageInfo().getHeight());
    }
    
    private void render(){
        renderer.fillBackground(Color.lightGray);
        renderLevel(renderer, level);
        
        if(firstTimeRender){
            
            renderer.drawImage(character1.getImageInfoR().getName(), (int) character1.getPositionX(), (int) character1.getPositionY());
            renderer.drawImage(character2.getImageInfoL().getName(), (int) character2.getPositionX(), (int) character2.getPositionY());
            
            activeImagec1=character1.getImageInfoR().getName();
            character1.setAAPoint("r");
            
            activeImagec2=character2.getImageInfoL().getName();
            character2.setAAPoint("l");
            
            
            firstTimeRender=false;
        }
        renderer.showBuffer();
    }
    private void render(boolean[] k1, boolean[] k2) {
         
        renderer.fillBackground(Color.lightGray);
        renderLevel(renderer, level);
        
        if(firstTimeRender){
            
            renderer.drawImage(character1.getImageInfoR().getName(), (int) character1.getPositionX(), (int) character1.getPositionY());
            renderer.drawImage(character2.getImageInfoL().getName(), (int) character2.getPositionX(), (int) character2.getPositionY());
            
            activeImagec1=character1.getImageInfoR().getName();
            character1.setAAPoint("r");
            
            activeImagec2=character2.getImageInfoL().getName();
            character2.setAAPoint("l");
            
            
            firstTimeRender=false;
        }else { if(k1[2]){
                    renderer.drawImage(character1.getImageInfoL().getName(), (int) character1.getPositionX(), (int) character1.getPositionY());
                    activeImagec1=character1.getImageInfoL().getName();
                    character1.setAAPoint("l");
                   
                }else{if(k1[3]){
                        renderer.drawImage(character1.getImageInfoR().getName(), (int) character1.getPositionX(), (int) character1.getPositionY());
                        activeImagec1=character1.getImageInfoR().getName();
                        character1.setAAPoint("r");
                       
                }else{if(!k1[2] && !k1[3]){
                            renderer.drawImage(activeImagec1, (int) character1.getPositionX(), (int) character1.getPositionY());
                            character1.setAAPoint();
                }}}
                
                if(k2[2]){
                    renderer.drawImage(character2.getImageInfoL().getName(), (int) character2.getPositionX(), (int) character2.getPositionY());
                    activeImagec2=character2.getImageInfoL().getName();
                    character2.setAAPoint("l");
                   
                }else{if(k2[3]){
                        renderer.drawImage(character2.getImageInfoR().getName(), (int) character2.getPositionX(), (int) character2.getPositionY());
                        activeImagec2=character2.getImageInfoR().getName();
                        character2.setAAPoint("r");
                       
                }else{if(!k2[2] && !k2[3]){
                            renderer.drawImage(activeImagec2, (int) character2.getPositionX(), (int) character2.getPositionY());
                            character2.setAAPoint();
                }}}
        
        
                }
        if(k1[4] && character1.canAA()){renderer.drawImage(character1.getImageInfoAA().getName(),(int)character1.getAAPoint(), (int)character1.getPositionY());}
        if(k2[4] && character2.canAA()){renderer.drawImage(character2.getImageInfoAA().getName(),(int)character2.getAAPoint(), (int)character2.getPositionY());}
        
        
        renderer.showBuffer();
        
    }

    private void renderLevel(IRenderer renderer, ILevelContainer levelHolder) {

        levelHolder.getAllLevelElements().forEach(component -> {
            renderer.drawImage(component.getImageInfo().getName(), (int)component.getPositionX(), (int)component.getPositionY());
        });
    }
}
