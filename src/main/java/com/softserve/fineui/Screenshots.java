package com.softserve.fineui;

import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static com.softserve.fineui.Utils.*;

/**
 * Created by Dmytro Firsov on 12/3/2016.
 */
public class Screenshots {

    private String SCREENSHOTS_ROOT_DIR;

    private String EXPECTED_SCREENSHOT_PATH;
    private String ACTUAL_SCREENSHOT_PATH;
    private String DIFF_SCREENSHOT_PATH;
    private String GIF_SCREENSHOT_PATH;

    private String EXPECTED_FILENAME="expected.png";
    private String ACTUAL_FILENAME="actual.png";
    private String DIFF_FILENAME="diff.png";
    private String GIF_FILENAME="gif.png";

    public String get_SCREENSHOTS_ROOT_DIR(){
        return SCREENSHOTS_ROOT_DIR;
    }

    public String get_EXPECTED_SCREENSHOT_PATH(){
        return EXPECTED_SCREENSHOT_PATH;
    }

    public String get_ACTUAL_SCREENSHOT_PATH(){
        return ACTUAL_SCREENSHOT_PATH;
    }

    public String get_DIFF_SCREENSHOT_PATH(){
        return DIFF_SCREENSHOT_PATH;
    }

    public String get_GIF_SCREENSHOT_PATH(){
        return GIF_SCREENSHOT_PATH;
    }

    public String getExpectedFilePath(){
        return this.EXPECTED_SCREENSHOT_PATH + File.separator + this.EXPECTED_FILENAME;
    }

    public String getActualFilePath(){
        return this.ACTUAL_SCREENSHOT_PATH + File.separator + this.ACTUAL_FILENAME;
    }

    public String getDiffFilePath(){
        return this.DIFF_SCREENSHOT_PATH + File.separator + this.DIFF_FILENAME;
    }

    public ArrayList<String> setScreenshotDirs(String path) {

        this.SCREENSHOTS_ROOT_DIR = path;
        this.EXPECTED_SCREENSHOT_PATH = path + File.separator + "expected" + File.separator;
        this.ACTUAL_SCREENSHOT_PATH = path + File.separator + "actual" + File.separator;
        this.DIFF_SCREENSHOT_PATH = path + File.separator + "diff" + File.separator;
        this.GIF_SCREENSHOT_PATH = path + File.separator + "gifs" + File.separator;

        if(!isExist(path)){ createDir(path); }

        ArrayList<String> screenshotDirs= new ArrayList<String>();
        screenshotDirs.add(get_EXPECTED_SCREENSHOT_PATH());
        screenshotDirs.add(get_ACTUAL_SCREENSHOT_PATH());
        screenshotDirs.add(get_DIFF_SCREENSHOT_PATH());
        screenshotDirs.add(get_GIF_SCREENSHOT_PATH());

        createDirs(screenshotDirs);
        return screenshotDirs;
    }

    public void makeExpectedScreenshot(WebDriver driver){
        Screenshot screenshot = new AShot().takeScreenshot(driver);
        File newScreenshot = new File(getExpectedFilePath());
        try {
            ImageIO.write(screenshot.getImage(), "png", newScreenshot);
        }catch(IOException e){
            System.out.print("WTF! Expected screenshot was not created!");
        }
    }

    public void makeActualScreenshot(WebDriver driver){
        Screenshot screenshot = new AShot().takeScreenshot(driver);
        File newScreenshot = new File(getActualFilePath());
        try {
            ImageIO.write(screenshot.getImage(), "png", newScreenshot);
        }catch(IOException e){
            System.out.print("WTF! Actual screenshot was not created!");
        }
    }

    public Screenshot getExpectedScreenshot()  throws IOException{
        Screenshot screenshot = new Screenshot(ImageIO.read(new File(getExpectedFilePath())));
        return screenshot;
    }

    public Screenshot getActualScreenshot()  throws IOException{
            Screenshot screenshot = new Screenshot(ImageIO.read(new File(getActualFilePath())));
        return screenshot;
    }

    public void makeDiff(){
        try {
            System.out.println("\nMaking diff...");
            ImageDiff diff = new ImageDiffer().makeDiff(getActualScreenshot(), getExpectedScreenshot());
            File diffFile = new File(getDiffFilePath());
            ImageIO.write(diff.getMarkedImage(), "png", diffFile);
        }catch(IOException e){
        }finally{
            System.out.println("\nDiff completed.");
        }
    }
}

