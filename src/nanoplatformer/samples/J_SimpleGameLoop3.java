/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nanoplatformer.samples;

import java.io.IOException;
import nanoplatformer.graphics.graphicspool.java2D.Java2DGraphicsPool;
import nanoplatformer.graphics.output.java2D.AcceleratedFrame;
import nanoplatformer.graphics.renderer.IRenderer;
import nanoplatformer.graphics.renderer.java2D.Java2DRenderer;
import nanoplatformer.input.IPollableInput;
import nanoplatformer.input.Keyboard;
import nanoplatformer.loader.LevelLoader;
import nanoplatformer.loop.BaseGameLoop;
import nanoplatformer.loop.J_CompleteLoopWithCameraMovement;
import nanoplatformer.model.character.MainCharacterWithPhysics;
import nanoplatformer.model.levelholder.ILevelContainer;

/**
 *
 * @author Nestor
 */
public class J_SimpleGameLoop3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
               
        IPollableInput keyboard=new Keyboard(new String[]{"W","S","A","D"});
        
        
        Java2DGraphicsPool graphicsPool = new Java2DGraphicsPool();
        graphicsPool.loadFromCurrentDirectory();

        IRenderer renderer = new Java2DRenderer(graphicsPool, new AcceleratedFrame(640, 480));
        
        ILevelContainer levelHolder=new LevelLoader().loadLevel("basicLevel.txt", graphicsPool);
        
        MainCharacterWithPhysics character= new MainCharacterWithPhysics(32,320,graphicsPool.getGraphicsInfoFromName("orangeGuy"));
        
        BaseGameLoop loop= new J_CompleteLoopWithCameraMovement(60,keyboard,levelHolder,character,renderer);
        loop.start();
    }
    
}
