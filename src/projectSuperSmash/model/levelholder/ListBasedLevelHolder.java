/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projectSuperSmash.model.levelholder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import nanoplatformer.model.LevelComponent;

/**
 *
 * @author Nestor
 */
public class ListBasedLevelHolder implements ILevelContainer {
    
  List<LevelComponent> levelComponents;

    public ListBasedLevelHolder() {
        
       levelComponents = new ArrayList();
        
    }

    @Override
    public void addLevelElement(LevelComponent element) {
        levelComponents.add(element);
    }

    @Override
    public List<LevelComponent> getAllLevelElements() {
        return Collections.unmodifiableList(levelComponents);
    }

    @Override
    public List<LevelComponent> getElementsInside(int x, int y, int width, int height) {
        
         List<LevelComponent> componentsInsideRegion=new ArrayList();
         
        
        for (LevelComponent component:componentsInsideRegion) {
            if (
                    component.getPositionX()+component.getImageInfo().getWidth()>x && 
                    component.getPositionY()+component.getImageInfo().getHeight()>y &&
                    component.getPositionX()<x+width && 
                    component.getPositionY()<y+height)
                componentsInsideRegion.add(component);     
        } 
         
         return componentsInsideRegion;
        
    }
    
}
