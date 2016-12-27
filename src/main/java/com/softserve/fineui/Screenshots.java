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

    private final String SCREENSHOT_EXTENSION = ".png";

    private String SCREENSHOTS_ROOT_DIR;

    private String EXPECTED_SCREENSHOT_PATH;
    private String ACTUAL_SCREENSHOT_PATH;
    private String DIFF_SCREENSHOT_PATH;
    private String GIF_SCREENSHOT_PATH;

    private String EXPECTED_FILENAME = screenshotNameFormat("expected");
    private String ACTUAL_FILENAME = screenshotNameFormat("actual");
    private String DIFF_FILENAME = screenshotNameFormat("diff");
    private String GIF_FILENAME = screenshotNameFormat("gif");

    public String GET_SCREENSHOTS_ROOT_DIR(){
        return SCREENSHOTS_ROOT_DIR;
    }

    public String GET_EXPECTED_PATH(){
        return EXPECTED_SCREENSHOT_PATH;
    }

    public String GET_ACTUAL_PATH(){
        return ACTUAL_SCREENSHOT_PATH;
    }

    public String GET_DIFF_PATH(){
        return DIFF_SCREENSHOT_PATH;
    }

    public String GET_GIF_PATH(){
        return GIF_SCREENSHOT_PATH;
    }

    public String getExpectedFilePath(){return GET_EXPECTED_PATH() + File.separator + this.EXPECTED_FILENAME;}

    public String getActualFilePath(){
        return GET_ACTUAL_PATH() + File.separator + this.ACTUAL_FILENAME;
    }

    public String getDiffFilePath(){
        return GET_DIFF_PATH() + File.separator + this.DIFF_FILENAME;
    }

    public String getGifFilePath(){
        return GET_GIF_PATH() + File.separator + this.GIF_FILENAME;
    }

    public String screenshotNameFormat(String name){
        return (name + "_" + getCurrentDay() + "_" + getCurrentDate() + SCREENSHOT_EXTENSION );
    }

    public ArrayList<String> setScreenshotDirs(String path) {

        this.SCREENSHOTS_ROOT_DIR = path;
        this.EXPECTED_SCREENSHOT_PATH = path + File.separator + "expected" + File.separator;
        this.ACTUAL_SCREENSHOT_PATH = path + File.separator + "actual" + File.separator;
        this.DIFF_SCREENSHOT_PATH = path + File.separator + "diff" + File.separator;
        this.GIF_SCREENSHOT_PATH = path + File.separator + "gifs" + File.separator;

        if(!isExist(path)){ createDir(path); }

        ArrayList<String> screenshotDirs= new ArrayList<String>();
        screenshotDirs.add(GET_EXPECTED_PATH());
        screenshotDirs.add(GET_ACTUAL_PATH());
        screenshotDirs.add(GET_DIFF_PATH());
        screenshotDirs.add(GET_GIF_PATH());

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

    public Screenshot saveActualScreenshot() throws IOException {
        //TODO: everything
        Screenshot expected = getExpectedScreenshot();
        Screenshot actual = getActualScreenshot();
        return actual;
    }
}

