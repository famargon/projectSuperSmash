/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nanoplatformer.loader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import nanoplatformer.graphics.graphicspool.IGraphicsPool;
import nanoplatformer.model.LevelComponent;
import nanoplatformer.model.levelholder.ILevelContainer;
import nanoplatformer.model.levelholder.ListBasedLevelHolder;

/**
 *
 * @author Nestor
 */
public class LevelLoader {

    public ILevelContainer loadLevel(String levelName, IGraphicsPool graphicsPool) throws FileNotFoundException, IOException {

        ListBasedLevelHolder levelHolder = new ListBasedLevelHolder();

        BufferedReader in = new BufferedReader(new FileReader(levelName));

        while (in.ready()) {
            String[] line = in.readLine().split(" ");
            LevelComponent component=new LevelComponent(Integer.parseInt(line[1]),Integer.parseInt(line[2]),graphicsPool.getGraphicsInfoFromName(line[0]));
            levelHolder.addLevelElement(component);
        }
        in.close();

        return levelHolder;
    }

}
