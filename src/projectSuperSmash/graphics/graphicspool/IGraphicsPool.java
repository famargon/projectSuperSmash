/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projectSuperSmash.graphics.graphicspool;

import projectSuperSmash.graphics.ImageInfo;

/**
 *
 * @author Nestor
 */
public interface IGraphicsPool {
    
    
public void loadFromCurrentDirectory();
    
public ImageInfo getGraphicsInfoFromName(String name);

   
    
    
}
