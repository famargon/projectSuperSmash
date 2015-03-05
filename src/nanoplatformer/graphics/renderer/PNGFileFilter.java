/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package nanoplatformer.graphics.renderer;

import java.io.*;
import java.io.File;


/**
 *
 * @author nestor
 */
public class PNGFileFilter implements FilenameFilter {

    String nameStart=null;
    
        public PNGFileFilter(){}
    
    public PNGFileFilter(String nameStart){
    this.nameStart=nameStart+"_";
    }

public boolean accept(File dir, String name) {
return (name.endsWith(".png")  && (nameStart==null || name.startsWith(nameStart)));
}
}
