package nanoplatformer.input;

import net.java.games.input.Controller;
import net.java.games.input.*;

public class Keyboard implements IPollableInput {

    private Controller[] controllers;

    private String[] keyCodes;
    private int[][] keyMap;
    private boolean[] pressed;

    public Keyboard(String[] keyCodesStr) {

        this.controllers = null;
        this.keyCodes = keyCodesStr;
        initController(Controller.Type.KEYBOARD);

        keyMap = new int[controllers.length][keyCodes.length];

        for (int i = 0; i < controllers.length; i++) {
            for (int j = 0; j < keyCodes.length; j++) {

                String codigo = keyCodes[j];

                if (codigo.equals("SPACE")) {
                    codigo = " ";
                }

                Component[] c = controllers[i].getComponents();
                for (int k = 0; k < c.length; k++) {
                    if (c[k].getName().equals(codigo)) {
                        keyMap[i][j] = k;
                        break;
                    }
                }
            }
        }
        pressed = new boolean[keyCodes.length];
    }

    @Override
    public boolean isPressed(int pos) {
        return pressed[pos];
    }

    private void initController(Controller.Type controllerType) {

        Controller[] controllers_local;
        DirectAndRawInputEnvironmentPlugin directEnv = new DirectAndRawInputEnvironmentPlugin();
        if (directEnv.isSupported()) {
            controllers_local = directEnv.getControllers();
        } else {
            controllers_local = ControllerEnvironment.getDefaultEnvironment().getControllers();
        }

        int teclados = 0;
        for (Controller controller1 : controllers_local) {
            if (controller1.getType() == controllerType) {
                teclados++;
            }
        }
        controllers = new Controller[teclados];
        int cont = 0;

        for (Controller controller1 : controllers_local) {
            if (controller1.getType() == controllerType) {
                controllers[cont] = controller1;
                cont++;
            }
        }
    }

    @Override
    public boolean poll() {

        for (int i = 0; i < pressed.length; i++) {
            pressed[i] = false;
        }

        for (int ii = 0; ii < controllers.length; ii++) {

            boolean isControllerValid = controllers[ii].poll();
            if (!isControllerValid) {
                return false;
            }

            Component[] component = controllers[ii].getComponents();

            for (int i = 0; i < keyCodes.length; i++) {

                if (component[keyMap[ii][i]].getPollData() != 0) {
                    pressed[i] = true;
                }
            }
        }
        return true;
    }
}
