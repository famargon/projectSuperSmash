/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nanoplatformer.samples;
/**
 *
 * @author Fabian
 */
import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;
import java.io.IOException;
import nanoplatformer.graphics.graphicspool.java2D.Java2DGraphicsPool;
import nanoplatformer.graphics.output.java2D.AcceleratedFrame;
import nanoplatformer.graphics.renderer.IRenderer;
import nanoplatformer.graphics.renderer.java2D.Java2DRenderer;
import nanoplatformer.input.keyboardKeyListener;
import nanoplatformer.loader.LevelLoader;
import nanoplatformer.loop.BaseGameLoop;
import nanoplatformer.loop.CompleteLoopWithPhysics2players;
import nanoplatformer.model.character.MainCharacterWithPhysics;
import nanoplatformer.model.character.MainCharacterWithPhysics2playersedition;
import nanoplatformer.model.levelholder.ILevelContainer;

/**
 *
 * @author Nestor
 */
public class dosPersonajes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
            
        keyboardKeyListener teclado = new keyboardKeyListener();
        addKeyListener(teclado);
        
        
        Java2DGraphicsPool graphicsPool = new Java2DGraphicsPool();
        graphicsPool.loadFromCurrentDirectory();

        IRenderer renderer = new Java2DRenderer(graphicsPool, new AcceleratedFrame(640, 480));
        
        ILevelContainer levelHolder=new LevelLoader().loadLevel("basicLevel.txt", graphicsPool);
        
        MainCharacterWithPhysics2playersedition character1= new MainCharacterWithPhysics2playersedition(1,50,32,graphicsPool.getGraphicsInfoFromName("orangeGuy"));
        MainCharacterWithPhysics2playersedition character2= new MainCharacterWithPhysics2playersedition(2,350,32,graphicsPool.getGraphicsInfoFromName("orangeGuy"));
        BaseGameLoop loop1= new CompleteLoopWithPhysics2players(60,teclado,levelHolder,character1,character2,renderer);
                                
        loop1.start();
        
    }
    
}

