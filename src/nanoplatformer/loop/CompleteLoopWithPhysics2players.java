/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nanoplatformer.loop;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import nanoplatformer.graphics.renderer.IRenderer;
import nanoplatformer.input.keyboardKeyListener;
import nanoplatformer.model.LevelComponent;
import nanoplatformer.model.character.MainCharacterWithPhysics;
import nanoplatformer.model.character.MainCharacterWithPhysics2playersedition;
import nanoplatformer.model.levelholder.ILevelContainer;

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

    public CompleteLoopWithPhysics2players(int framesPerSecond, keyboardKeyListener input1, ILevelContainer level, MainCharacterWithPhysics2playersedition character1,MainCharacterWithPhysics2playersedition character2, IRenderer renderer) {
        super(framesPerSecond);
        this.teclado = input1;
        
        this.level = level;
        this.character1 = character1;
        this.character2 = character2;
        this.renderer = renderer;
    }

    @Override
    protected void performLoopOperations(long previousLoopTime_ms, long currentLoopTime_ms) {

        teclado.update();
        boolean[] keys1=new boolean[4];
        boolean[] keys2=new boolean[4];
        keys1=teclado.getValues(1);
        keys2=teclado.getValues(2);
      
        
        List<Rectangle> collisions=getLevelCollisions();
        
        character1.update(keys1,collisions, currentLoopTime_ms - previousLoopTime_ms);
        character2.update(keys2,collisions, currentLoopTime_ms - previousLoopTime_ms);
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

   /* private boolean[] pollKeyboard1() {
        input1.poll();
        boolean[] pressedKeys = new boolean[4];
        for (int i = 0; i < 4; i++) {
            pressedKeys[i] = input1.isPressed(i);
        }
        return pressedKeys;
    }
     private boolean[] pollKeyboard2() {
        input2.poll();
        boolean[] pressedKeys = new boolean[4];
        for (int i = 0; i < 4; i++) {
            pressedKeys[i] = input2.isPressed(i);
        }
        return pressedKeys;
    }*/

    private void render() {
        renderer.fillBackground(Color.yellow);
        renderLevel(renderer, level);
        renderer.drawImage(character1.getImageInfo().getName(), (int) character1.getPositionX(), (int) character1.getPositionY());
        renderer.drawImage(character2.getImageInfo().getName(), (int) character2.getPositionX(), (int) character2.getPositionY());
        renderer.showBuffer();
    }

    private void renderLevel(IRenderer renderer, ILevelContainer levelHolder) {

        levelHolder.getAllLevelElements().forEach(component -> {
            renderer.drawImage(component.getImageInfo().getName(), component.getPositionX(), component.getPositionY());
        });
    }
}
