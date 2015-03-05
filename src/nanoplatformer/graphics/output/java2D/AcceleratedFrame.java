
/*
 * NewJFrame.java
 *
 * Created on 05-dic-2011, 20:08:51
 */
package nanoplatformer.graphics.output.java2D;

import java.awt.event.*;

/**
 *
 * @author nestor
 */
public class AcceleratedFrame extends java.awt.Frame {
     
    private AcceleratedCanvas canvas;

    public AcceleratedFrame(int width, int height) {

        this.setSize(width, height);
        this.setIgnoreRepaint(true);
        this.setResizable(false);
        this.setTitle("NanoPlatformer");

        initComponents(width, height);

        this.setVisible(true);
        this.canvas.init(true);        
    }

    private void initComponents(int width, int height) {

        canvas = new AcceleratedCanvas();
        canvas.setSize(width, height);
        this.add(canvas);

        addWindowListener(createWindowListeners());
    }

    private WindowAdapter createWindowListeners() {
        return new WindowAdapter() {
            
            @Override
            public void windowClosing(WindowEvent we) {
                dispose();
                System.exit(0);
            }
        };
    }

    public AcceleratedCanvas getCanvas() {
        return canvas;
    }
    

    @Override
    public void dispose() {
        if (canvas != null) {
            canvas.dispose();
        }
        canvas = null;
        super.dispose();
    }



}
