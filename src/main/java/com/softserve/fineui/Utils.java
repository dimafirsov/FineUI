package com.softserve.fineui;

import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by Dmytro Firsov on 12/2/2016.
 */

public class Utils {

    public static WebDriver chromeDriverInit()
    {
        if (SystemUtils.IS_OS_WINDOWS){
            System.setProperty("webdriver.chrome.driver", "driver" + File.separator + "chromedriver.exe");
            return new ChromeDriver();
        } else {
            System.setProperty("webdriver.chrome.driver", "driver" + File.separator + "chromedriver_mac");
            return new ChromeDriver();
        }
    }

    public static WebDriver fireFoxDriverInit()
    {
        if (SystemUtils.IS_OS_WINDOWS){
            System.setProperty("webdriver.gecko.driver", "driver" + File.separator + "geckodriver.exe");
        } else {
            System.setProperty("webdriver.gecko.driver", "driver" + File.separator + "geckodriver_mac");
        }
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("marionette", true);
        return new FirefoxDriver(capabilities);
    }

    public static void closeAllDrivers(WebDriver driver, WebDriverType webDriverType){
        for (int i = 0; i>WebDriverType.values().length; i++ ) {
            switch (webDriverType) {
                case CHROME:
                case FF:
                case IE:
                    try{
                        driver.close();
                    }catch(Exception e){
                        System.out.println("No " + webDriverType + " driver instance is detected. Nothing to close");
                    }finally{
                        return;
                    }
            }
        }
    }

    public static void clearFolder(File folder){
        if(folder.exists()){
            File[] folderContents = folder.listFiles();
            for(File file : folderContents){
                if(file.isDirectory()){
                    removeFolder(file);
                }else{
                    file.delete();
                }
            }
        } else {
            System.out.println("Folder with name '" + folder + "' doesn't exist");
        }
    }

    public static void clearFolder(String folder){
        File targetFolder = new File(folder);
        if(targetFolder.exists()){
            File[] folderContents = targetFolder.listFiles();
            for(File file : folderContents){
                if(file.isDirectory()){
                    removeFolder(file);
                }else{
                    file.delete();
                }
            }
        } else {
            System.out.println("Folder with name '" + folder + "' doesn't exist");
        }
    }

    public static void removeFolder(File folder){
        clearFolder(folder);
        folder.delete();
    }
    public static void removeFolder(String folder){
        File targetFolder = new File(folder);
        clearFolder(targetFolder);
        targetFolder.delete();
    }

    public static boolean fileExists(String path){
        File file = new File(path);
        return file.exists();
    }

    public static boolean createDir(String path){
        Boolean success = true;
        File file = new File(path);
        if(!file.exists()){
            file.mkdir();
        }else{
            System.out.println("Folder with name `" + file + "` already exists");
        }
        return success;
    }

    public static boolean createDirsRecursively(String path){
        Boolean success = true;
        String pathPart = "";
        String pattern = Pattern.quote(File.separator);
        String[] pathSplit = path.split(pattern);
        for(int i=0; i<pathSplit.length;i++){
            pathPart+=pathSplit[i] + File.separator;
            createDir(pathPart);
        }
        return success;
    }

    public static void createDirs(ArrayList<String> paths){
        for (String path : paths) {
            if(!fileExists(path))
                new File(path).mkdir();
        }
    }

    public static Properties getProps()
    {
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

    public static String getCurrentDate(){
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_hhmmss");
        return dateFormat.format(date);
    }

    public static String getCurrentDate(String pattern){
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

/*    public static String getCurrentDate(String yyyyMMddHHmmSS){
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat(yyyyMMddHHmmSS);
        return dateFormat.format(date);
    }*/

    public static String getCurrentDay(){
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        switch (day) {
            case Calendar.SUNDAY: return "Sun";
            case Calendar.MONDAY: return "Mon";
            case Calendar.TUESDAY: return "Tue";
            case Calendar.WEDNESDAY: return "Wed";
            case Calendar.THURSDAY: return "Thu";
            case Calendar.FRIDAY: return "Fri";
            case Calendar.SATURDAY: return "Sat";
        }
        return "Wrong day of week";
    }

    public static Dimension getCurrentWindowSize(){
        return Toolkit.getDefaultToolkit().getScreenSize();
    }

    public static double getCurrentWindowHeigth(){
        return getCurrentWindowSize().getHeight();
    }

    public static double getCurrentWindowWidth(){
        return getCurrentWindowSize().getWidth();
    }


    public static ChromeOptions allowChromeNotifications(){
        //Create prefs map to store all preferences
        Map<String, Object> prefs = new HashMap<String, Object>();
        //Put this into prefs map to switch off browser notification
        prefs.put("profile.default_content_setting_values.notifications", 2);
        //Create chrome options to set this prefs
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        return options;
    }
}