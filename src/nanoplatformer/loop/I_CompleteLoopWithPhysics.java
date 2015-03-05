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
import nanoplatformer.input.IPollableInput;
import nanoplatformer.model.LevelComponent;
import nanoplatformer.model.character.MainCharacterWithPhysics;
import nanoplatformer.model.levelholder.ILevelContainer;

/**
 *
 * @author Nestor
 */
public class I_CompleteLoopWithPhysics extends BaseGameLoop {

    private IPollableInput input;
    private ILevelContainer level;
    private MainCharacterWithPhysics character;
    private IRenderer renderer;

    public I_CompleteLoopWithPhysics(int framesPerSecond, IPollableInput input, ILevelContainer level, MainCharacterWithPhysics character, IRenderer renderer) {
        super(framesPerSecond);
        this.input = input;
        this.level = level;
        this.character = character;
        this.renderer = renderer;
    }

    @Override
    protected void performLoopOperations(long previousLoopTime_ms, long currentLoopTime_ms) {

        boolean[] pressedKeys = pollKeyboard();
        
        List<Rectangle> collisions=getLevelCollisions();
        
        character.update(pressedKeys,collisions, currentLoopTime_ms - previousLoopTime_ms);
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
        renderer.fillBackground(Color.yellow);
        renderLevel(renderer, level);
        renderer.drawImage(character.getImageInfo().getName(), (int) character.getPositionX(), (int) character.getPositionY());
        renderer.showBuffer();
    }

    private void renderLevel(IRenderer renderer, ILevelContainer levelHolder) {

        levelHolder.getAllLevelElements().forEach(component -> {
            renderer.drawImage(component.getImageInfo().getName(), component.getPositionX(), component.getPositionY());
        });
    }
}
