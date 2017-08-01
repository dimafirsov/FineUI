package com.softserve.fineui;

import org.junit.Assert;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.ArrayList;

/**
 * Created by dimafirsov on 01.08.17.
 */
public class TestHelper {

    public void putCursorAway(int x, int y){
        try {
            Robot robot = new Robot();
            robot.mouseMove(x,y);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
        }catch(AWTException e){
            e.printStackTrace();
        }
    }

    public void assertAllDiffs(ArrayList<Integer> results){
        WebDriverType[] webDriverType = WebDriverType.values();
        for(int i=0; i<results.size(); i++){
            Assert.assertEquals("Assertion failed for "
                    + webDriverType[i].toString(), 0, results.get(i).intValue());
        }
    }

}
