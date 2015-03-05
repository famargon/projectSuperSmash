/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nanoplatformer.input;

/**
 *
 * @author Nestor
 */
public interface IPollableInput {
  
    public boolean isPressed(int keyPosition);
    
    public boolean poll();
    
    
}
