/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nanoplatformer.loop;

import java.awt.Color;
import nanoplatformer.graphics.renderer.IRenderer;
import nanoplatformer.input.IPollableInput;
import nanoplatformer.model.character.MainCharacterWithoutPhysics;
import nanoplatformer.model.levelholder.ILevelContainer;

/**
 *
 * @author Nestor
 */
public class H_CompleteLoopWithoutPhysics extends BaseGameLoop {

    private IPollableInput input;
    private ILevelContainer level;
    private MainCharacterWithoutPhysics character;
    private IRenderer renderer;

    public H_CompleteLoopWithoutPhysics(int framesPerSecond, IPollableInput input, ILevelContainer level, MainCharacterWithoutPhysics character, IRenderer renderer) {
        super(framesPerSecond);
        this.input = input;
        this.level = level;
        this.character = character;
        this.renderer = renderer;
    }

    @Override
    protected void performLoopOperations(long previousLoopTime_ms, long currentLoopTime_ms) {

        boolean[] pressedKeys = pollKeyboard();
        character.update(pressedKeys, currentLoopTime_ms - previousLoopTime_ms);
        render();
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
