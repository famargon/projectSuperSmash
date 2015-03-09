/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nanoplatformer.graphics.renderer;

import java.awt.Color;
import java.awt.Rectangle;

/**
 *
 * @author Nestor
 */

public interface IRenderer {
    
    
    public void drawImage(String imageName,int positionX, int positionY);
   
    public void fillBackground(Color color);
    
    public void showBuffer();
    
    public Rectangle getRenderableArea();

    public void drawMessage(String pulsa_cualquier_tecla_para_empezar);
    
}
