/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectSuperSmash.samples;
/**
 *
 * @author Fabian
 */

import java.io.IOException;
import projectSuperSmash.graphics.graphicspool.java2D.Java2DGraphicsPool;
import projectSuperSmash.graphics.output.java2D.AcceleratedFrame;
import nanoplatformer.graphics.renderer.IRenderer;
import projectSuperSmash.graphics.renderer.java2D.Java2DRenderer;
import projectSuperSmash.input.keyboardKeyListener;
import projectSuperSmash.loader.LevelLoader;
import projectSuperSmash.loop.BaseGameLoop;
import projectSuperSmash.loop.CompleteLoopWithPhysics2players;
import projectSuperSmash.model.character.MainCharacterWithPhysics2playersedition;
import projectSuperSmash.model.levelholder.ILevelContainer;

/**
 *
 * @author Nestor
 */
public class dosPersonajes {

   
    private static BaseGameLoop loop1;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
            
        keyboardKeyListener teclado = new keyboardKeyListener(); 
                
        Java2DGraphicsPool graphicsPool = new Java2DGraphicsPool();
        graphicsPool.loadFromCurrentDirectory();

        IRenderer renderer = new Java2DRenderer(graphicsPool, new AcceleratedFrame(640, 480,teclado));
        
        MainCharacterWithPhysics2playersedition character1= new MainCharacterWithPhysics2playersedition(36,200,graphicsPool.getGraphicsInfoFromName("orangeGuyLeftbasic"),graphicsPool.getGraphicsInfoFromName("orangeGuyRightbasic"),graphicsPool.getGraphicsInfoFromName("orangeGuyAA"));
        MainCharacterWithPhysics2playersedition character2= new MainCharacterWithPhysics2playersedition(580,200,graphicsPool.getGraphicsInfoFromName("futureGuyLeftbasic"),graphicsPool.getGraphicsInfoFromName("futureGuyRightbasic"),graphicsPool.getGraphicsInfoFromName("futureGuyAA"));
 
        ILevelContainer levelHolder=new LevelLoader().loadLevel("basicLevel.txt", graphicsPool);
        
        loop1= new CompleteLoopWithPhysics2players(100,teclado,levelHolder,character1,character2,renderer);
        loop1.start();                        
         
            
		
        
        
    }
    public static void start(){}
    
    
    
}

