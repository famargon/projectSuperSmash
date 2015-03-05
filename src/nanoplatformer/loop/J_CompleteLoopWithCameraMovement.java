/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nanoplatformer.loop;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import nanoplatformer.graphics.renderer.IRenderer;
import nanoplatformer.input.IPollableInput;
import nanoplatformer.model.LevelComponent;
import nanoplatformer.model.character.MainCharacterWithPhysics;
import nanoplatformer.model.levelholder.ILevelContainer;

/**
 *
 * @author Nestor
 */
public class J_CompleteLoopWithCameraMovement extends BaseGameLoop {

    private IPollableInput input;
    private ILevelContainer level;
    private MainCharacterWithPhysics character;
    private IRenderer renderer;
    
    private Point2D.Float cameraLocation;

    public J_CompleteLoopWithCameraMovement(int framesPerSecond, IPollableInput input, ILevelContainer level, MainCharacterWithPhysics character, IRenderer renderer) {
        super(framesPerSecond);
        this.input = input;
        this.level = level;
        this.character = character;
        this.renderer = renderer;
        this.cameraLocation=new Point2D.Float();
    }

    @Override
    protected void performLoopOperations(long previousLoopTime_ms, long currentLoopTime_ms) {

        boolean[] pressedKeys = pollKeyboard();
        
        List<Rectangle> collisions=getLevelCollisions();
        
        character.update(pressedKeys,collisions, currentLoopTime_ms - previousLoopTime_ms);
        
        moveCamera();
        
        render();
    }

    private List<Rectangle> getLevelCollisions() {
        List<Rectangle> collisions=new ArrayList();
        level.getAllLevelElements().forEach(component->{collisions.add(createRectangle(component));});
        return collisions;
    }

    private static Rectangle createRectangle(LevelComponent component) {
        return new Rectangle(component.getPositionX(),component.getPositionY(),component.getImageInfo().getWidth(),component.getImageInfo().getHeight());
    }

    private boolean[] pollKeyboard() {
        input.poll();
        boolean[] pressedKeys = new boolean[4];
        for (int i = 0; i < 4; i++) {
            pressedKeys[i] = input.isPressed(i);
        }
        return pressedKeys;
    }

    private void render() {
        
        Point topLeftCorner=new Point();
        topLeftCorner.x=(int)cameraLocation.x-renderer.getRenderableArea().width/2;
        topLeftCorner.y=(int)cameraLocation.y-renderer.getRenderableArea().height/2;
        
        renderer.fillBackground(Color.GRAY);
        renderLevel(renderer, level,topLeftCorner);
        renderer.drawImage(character.getImageInfo().getName(), (int) character.getPositionX()-topLeftCorner.x, (int) character.getPositionY()-topLeftCorner.y);
        renderer.showBuffer();
    }

    private void renderLevel(IRenderer renderer, ILevelContainer levelHolder,Point topLeftCorner) {
       
        
        levelHolder.getAllLevelElements().forEach(component -> {
            renderer.drawImage(component.getImageInfo().getName(), component.getPositionX()-topLeftCorner.x, component.getPositionY()-topLeftCorner.y);
        });    
    }

    private void moveCamera() {
       
       cameraLocation.x=cameraLocation.x+ ((character.getPositionX()-cameraLocation.x)*0.1f);
       cameraLocation.y=cameraLocation.y+ ((character.getPositionY()-cameraLocation.y)*0.1f);
    }
}
