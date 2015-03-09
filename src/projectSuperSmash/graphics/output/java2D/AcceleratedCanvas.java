/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectSuperSmash.graphics.output.java2D;

import java.awt.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.*;
import java.lang.reflect.Constructor;


public class AcceleratedCanvas extends Canvas {

    protected BufferStrategy buffer;
    protected boolean enableRender = true;
    protected boolean paintBlack = true;
    private int refreshRate;

    public AcceleratedCanvas() {

        super();
        this.setIgnoreRepaint(true);
        this.setFocusable(false);
    }

    public void dispose() {
        enableRender(false);
        buffer.dispose();
        buffer = null;
    }

    public void init(boolean forceVsync) {

        if (buffer == null) {
            recreateBufferStrategy(forceVsync);
        }
    }

    @Override
    public GraphicsConfiguration getGraphicsConfiguration() {
 
        return GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
    }

    public int getRefreshRate() {
        return refreshRate;
    }

    protected void recreateBufferStrategy(boolean forceVsync) {


        GraphicsConfiguration gc =getGraphicsConfiguration();


        refreshRate = gc.getDevice().getDisplayMode().getRefreshRate();

        if (forceVsync) {

            BufferCapabilities caps = gc.getBufferCapabilities();
            try {
                createSyncedBufferStrategy(caps);
            } catch (Exception t) {
                t.printStackTrace();
            }
        } else {
            createBufferStrategy(2);
        }

        buffer = getBufferStrategy();
    }

    private void createSyncedBufferStrategy(BufferCapabilities caps) throws Exception {
        Class ebcClass = Class.forName("sun.java2d.pipe.hw.ExtendedBufferCapabilities");
        Class vstClass = Class.forName("sun.java2d.pipe.hw.ExtendedBufferCapabilities$VSyncType");
        
        Constructor ebcConstructor = ebcClass.getConstructor(new Class[]{BufferCapabilities.class, vstClass});
        Object vSyncType = vstClass.getField("VSYNC_ON").get(null);
        
        BufferCapabilities newCaps = (BufferCapabilities) ebcConstructor.newInstance(new Object[]{caps, vSyncType});
        createBufferStrategy(2, newCaps);
    }

    public void enableRender(boolean render) {
        this.enableRender = render;
        if (!render) {
            paintBlack = true;
        }
    }

    @Override
    public void paint(Graphics g) {
    }

    @Override
    public Graphics2D getGraphics() {

        if (!enableRender) {
            if (paintBlack) {
                Graphics2D g;
                try {
                    g = (Graphics2D) buffer.getDrawGraphics();
                } catch (Exception e) {
                    return null;
                }
                g.setColor(Color.black);
                g.fillRect(0, 0, this.getWidth(), this.getHeight());
                g.dispose();
                if (!buffer.contentsLost()) {
                    buffer.show();
                }
                paintBlack = false;
            }
            return null;
        }

        try {
            return (Graphics2D) buffer.getDrawGraphics();
        } catch (Exception e) {
            return null;
        }
    }

    public void showGraphics() {
        if (enableRender && !buffer.contentsLost()) {
            buffer.show();
        }
    }

}
