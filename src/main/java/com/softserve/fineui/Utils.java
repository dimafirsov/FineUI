package com.softserve.fineui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by Dmytro Firsov on 12/2/2016.
 */

public class Utils {

    public static void clearTempFolder(File folder){
        if(folder.exists()){
            File[] folderContents = folder.listFiles();
            for(File file : folderContents){
                if(file.isDirectory()){
                    clearTempFolder(file);
                }else{
                    file.delete();
                }
            }
        }
    }

    public static boolean isExist(String path){
        File file = new File(path);
        return file.exists();
    }

    public void createDir(String path){
        File file = new File(path);
        file.mkdir();
    }

    public void createDirs(ArrayList<String> paths){
        for (String path : paths) {
            if(!isExist(path)) new File(path).mkdir();
        }
    }

    public ArrayList<String> setScreenshotDirs(String absolutePath) {
        String screenshotsRootDir = absolutePath;
        if(!isExist(absolutePath)){
            createDir(absolutePath);
        }
        String expected = screenshotsRootDir + File.separator + "expected" + File.separator;
        String actual = screenshotsRootDir + File.separator + "actual" + File.separator;
        String diff = screenshotsRootDir + File.separator + "diff" + File.separator;
        String gifs = screenshotsRootDir + File.separator + "gifs" + File.separator;

        ArrayList<String> screenshotDirs= new ArrayList<String>();
        screenshotDirs.add(expected);
        screenshotDirs.add(actual);
        screenshotDirs.add(diff);
        screenshotDirs.add(gifs);

        createDirs(screenshotDirs);

        return screenshotDirs;
    }

    public static Properties getProps(){
        Properties props = new Properties();
        try {
            props.load(new FileInputStream(new File("cfg\\const.properties")));
        }catch(FileNotFoundException e){
            System.out.println("Properties file not found");
        }catch(IOException e){
            System.out.println("WTF");
        }
        return props;
    }
}

