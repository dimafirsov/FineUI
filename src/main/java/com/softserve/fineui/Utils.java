package com.softserve.fineui;

import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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


    public static WebDriver webDriverInit(){
        if (SystemUtils.IS_OS_WINDOWS){
            System.setProperty("webdriver.chrome.driver", "driver" + File.separator + "chromedriver.exe");
            return new ChromeDriver();
        } else {
            System.setProperty("webdriver.chrome.driver", "driver" + File.separator + "chromedriver_mac");
            return new ChromeDriver();
        }
    }

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

    public static void createDir(String path){
        File file = new File(path);
        file.mkdir();
    }

    public static void createDirs(ArrayList<String> paths){
        for (String path : paths) {
            if(!isExist(path)) new File(path).mkdir();
        }
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
