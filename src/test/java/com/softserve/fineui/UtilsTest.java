package com.softserve.fineui;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by Dmytro Firsov on 12/3/2016.
 */
public class UtilsTest {

    @Before
    public void createTempFolder(){
        File tempDir = new File("temp");
            if(!tempDir.exists()){
                tempDir.mkdir();
            }
    }

    @After
    public void clearTempFolder(){
        File tempDir = new File("temp");
        Utils.clearTempFolder(tempDir);
    }

    @Test
    public void isExist() throws Exception {
        String path = "temp\\test.txt";
        File file = new File(path);
        file.createNewFile();
        assertEquals(file.exists(), Utils.isExist(path));
    }

    @Test
    public void createDir() throws Exception {
        String dirName = "temp";
    }

    @Test
    public void createDirs() throws Exception {

    }

    @Test
    public void setScreenshotDirs() throws Exception {

    }

    @Test
    public void getProps() throws Exception {

    }

}