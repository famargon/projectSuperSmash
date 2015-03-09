/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projectSuperSmash.graphics.renderer.java2D;

import projectSuperSmash.graphics.graphicspool.java2D.Java2DGraphicsPool;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;


import nanoplatformer.graphics.renderer.IRenderer;
import projectSuperSmash.graphics.output.java2D.AcceleratedFrame;

/**
 *
 * @author Nestor
 * modificado por Fabian
 */
public class Java2DRenderer implements IRenderer {
    
    private Java2DGraphicsPool graphicsPool;
    private AcceleratedFrame acceleratedFrame;

    public Java2DRenderer(Java2DGraphicsPool graphicsPool, AcceleratedFrame acceleratedFrame) {
        this.graphicsPool = graphicsPool;
        this.acceleratedFrame = acceleratedFrame;
    }

    @Override
    public void drawImage(String imageName, int positionX, int positionY) {
        
        Graphics2D g=acceleratedFrame.getCanvas().getGraphics();
        g.drawImage(graphicsPool.getBufferedImageFromName(imageName), positionX,positionY,null);
        g.dispose();
    }
    public void drawMessage(String message){
    Graphics2D g=acceleratedFrame.getCanvas().getGraphics();
    
    g.drawString(message,(800-g.getFontMetrics().stringWidth(message))/2,250);
    g.drawString("Press any key",(800-g.getFontMetrics().stringWidth("Press any key"))/2,300);
    g.dispose();
    }

    @Override
    public void showBuffer() {
        acceleratedFrame.getCanvas().showGraphics();
    }

    @Override
    public void fillBackground(Color color) {
       Graphics2D g=acceleratedFrame.getCanvas().getGraphics();
       g.setColor(color);
      
       g.fillRect(0, 0, acceleratedFrame.getWidth(), acceleratedFrame.getHeight());
       g.dispose();
    }
    
    @Override
    public Rectangle getRenderableArea() {
       return new Rectangle(0,0,acceleratedFrame.getCanvas().getWidth(),acceleratedFrame.getCanvas().getHeight());
    }
   
    
    
    
    
}
