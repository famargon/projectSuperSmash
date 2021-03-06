
/*
 * NewJFrame.java
 *
 * Created on 05-dic-2011, 20:08:51
 */
package projectSuperSmash.graphics.output.java2D;

import java.awt.event.*;
import projectSuperSmash.input.keyboardKeyListener;

/**
 *
 * @author nestor
 */
public class AcceleratedFrame extends java.awt.Frame {
     
    private AcceleratedCanvas canvas;

    public AcceleratedFrame(int width, int height,keyboardKeyListener teclado) {

        this.setSize(width, height);
        this.setIgnoreRepaint(true);
        this.setResizable(false);
        this.setTitle("SuperPatataSmash");

        initComponents(width, height);

        this.setVisible(true);
        this.canvas.init(true);   
        
        //estas dos ordenes son para que la ventana reconozca los KeyEvents
        addKeyListener(teclado);
        requestFocus();
        //para captar cuando se pulsa el boton de cerrar ventana
        addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
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
