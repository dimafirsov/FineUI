package com.softserve.fineui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static com.softserve.fineui.Utils.*;

/**
 * Created by Dmytro Firsov on 12/3/2016.
 */
public class Screenshots {

    Screenshots(WebDriver driver, String structurePath) {
        this.driver = driver;
        this.structurePath = structurePath;
    }

    Screenshots(ArrayList<Screenshots> screenshots) {
        this.screenshots = screenshots;
    }

    private ArrayList<Screenshots> screenshots;
    private WebDriver driver;
    private String structurePath;

    private String getCurrentStructure(){
        return this.structurePath;
    }

    /**
     * Setting the required extension for the screenshot files
     */
    private final String SCREENSHOT_EXTENSION = ".png";

    /**
     *  Setting up basic paths and names for the screenshots files infrastructure
     */
    private String SCREENSHOTS_ROOT_DIR;
    private String EXPECTED_SCREENSHOT_PATH;
    private String ACTUAL_SCREENSHOT_PATH;
    private String DIFF_SCREENSHOT_PATH;
    private String GIF_SCREENSHOT_PATH;

    private String EXPECTED_FILENAME = screenshotNameFormat("expected");;
    private String ACTUAL_FILENAME= screenshotNameFormat("actual");
    private String DIFF_FILENAME = screenshotNameFormat("diff");
    private String GIF_FILENAME = screenshotNameFormat("gif");

    /**
     * Getters for screenshot dirs and files, file name formats etc.
     */
    private String getScreenshotsRootDir(){
        return SCREENSHOTS_ROOT_DIR;
    }
    private String getExpectedDir(){
        return EXPECTED_SCREENSHOT_PATH;
    }
    private String getActualDir(){
        return ACTUAL_SCREENSHOT_PATH;
    }
    private String getDiffDir(){
        return DIFF_SCREENSHOT_PATH;
    }
    private String getGifDir(){
        return GIF_SCREENSHOT_PATH;
    }

    private String getExpectedFilePath(){
        return getExpectedDir() + File.separator + this.EXPECTED_FILENAME;
    }
    private String getActualFilePath(){
        return getActualDir() + File.separator + this.ACTUAL_FILENAME;
    }
    private String getDiffFilePath(){
        return getDiffDir() + File.separator + this.DIFF_FILENAME;
    }
    private String getGifFilePath(){
        return getGifDir() + File.separator + this.GIF_FILENAME;
    }

    private String screenshotNameFormat(String name){
        return (name + "_" + getCurrentDay() + "_" + getCurrentDate() + this.SCREENSHOT_EXTENSION);
    }
    private Screenshot getExpectedScreenshot()  throws IOException{
        return new Screenshot(ImageIO.read(new File(getExpectedFilePath())));
    }
    private Screenshot getActualScreenshot()  throws IOException{
        return new Screenshot(ImageIO.read(new File(getActualFilePath())));
    }
    public boolean actualScreenshotExists(){
        return fileExists(getActualFilePath());
    }
    public boolean expectedScreenshotExists(){
        return fileExists(getExpectedFilePath());
    }

    public ArrayList<String> setScreenshotDirs() {

        SCREENSHOTS_ROOT_DIR = getCurrentStructure();
        EXPECTED_SCREENSHOT_PATH =  SCREENSHOTS_ROOT_DIR + File.separator + "expected" + File.separator;
        ACTUAL_SCREENSHOT_PATH = SCREENSHOTS_ROOT_DIR + File.separator + "actual" + File.separator;
        DIFF_SCREENSHOT_PATH = SCREENSHOTS_ROOT_DIR + File.separator + "diff" + File.separator;
        GIF_SCREENSHOT_PATH = SCREENSHOTS_ROOT_DIR + File.separator + "gifs" + File.separator;

        if(!fileExists(SCREENSHOTS_ROOT_DIR)){
            createDir(SCREENSHOTS_ROOT_DIR);
        }

        ArrayList<String> screenshotDirs= new ArrayList<String>();
        screenshotDirs.add(EXPECTED_SCREENSHOT_PATH);
        screenshotDirs.add(ACTUAL_SCREENSHOT_PATH);
        screenshotDirs.add(DIFF_SCREENSHOT_PATH);
        screenshotDirs.add(GIF_SCREENSHOT_PATH);

        createDirs(screenshotDirs);
        return screenshotDirs;
    }

    public void makeExpectedScreenshot(){
        Screenshot screenshot = new AShot().takeScreenshot(this.driver);
        File newScreenshot = new File(getExpectedFilePath());
        try {
            ImageIO.write(screenshot.getImage(), this.SCREENSHOT_EXTENSION.replace(".",""), newScreenshot);
        }catch(IOException e){
            System.out.print("WTF! Expected screenshot was not created!");
        }
    }

    public void makeActualScreenshot(){
        Screenshot screenshot = new AShot().takeScreenshot(this.driver);
        File newScreenshot = new File(getActualFilePath());
        try {
            ImageIO.write(screenshot.getImage(), this.SCREENSHOT_EXTENSION.replace(".",""), newScreenshot);
        }catch(IOException e){
            System.out.print("WTF! Actual screenshot was not created!");
        }
    }

    public void makeExpectedScreenshotByCssSelector(String cssSelector) {
        WebElement webElement = this.driver.findElement(By.cssSelector(cssSelector));
        Screenshot screenshot = new AShot()
                .coordsProvider(new WebDriverCoordsProvider())
                .takeScreenshot(this.driver, webElement);
        File newScreenshot = new File(getExpectedFilePath());
        try {
            ImageIO.write(screenshot.getImage(), this.SCREENSHOT_EXTENSION.replace(".",""), newScreenshot);
        }catch (IOException e){
            System.out.println("Expected screenshot of the element with css selector " +
                    cssSelector + "was not created!");
        }
    }

    public void makeActualScreenshotByCssSelector(String cssSelector) {
        WebElement webElement = this.driver.findElement(By.cssSelector(cssSelector));
        Screenshot screenshot = new AShot()
                .coordsProvider(new WebDriverCoordsProvider())
                .takeScreenshot(this.driver, webElement);
        File newScreenshot = new File(getActualFilePath());
        try {
            ImageIO.write(screenshot.getImage(), this.SCREENSHOT_EXTENSION.replace(".",""), newScreenshot);
        }catch (IOException e){
            System.out.println("Actual screenshot of the element with css selector " +
                    cssSelector + " was not created!");
        }
    }
/*
    public void makeExpectedScreenshotByID(String elementID) {
        WebElement webElement = this.driver.findElement(By.id(elementID));
        Screenshot screenshot = new AShot().takeScreenshot(this.driver, webElement);
        File newScreenshot = new File(getExpectedFilePath());
        try {
            ImageIO.write(screenshot.getImage(), this.SCREENSHOT_EXTENSION.replace(".",""), newScreenshot);
        }catch (IOException e){
            System.out.println("Expected screenshot of the element with id " +
                    elementID + "was not created!");
        }
    }

    public void makeActualScreenshotByID(String elementID) {
        WebElement webElement = this.driver.findElement(By.id(elementID));
        Screenshot screenshot = new AShot().takeScreenshot(this.driver, webElement);
        File newScreenshot = new File(getActualFilePath());
        try {
            ImageIO.write(screenshot.getImage(), this.SCREENSHOT_EXTENSION.replace(".",""), newScreenshot);
        }catch (IOException e){
            System.out.println("Actual screenshot of the element with id " +
                    elementID + "was not created!");
        }
    }


*/
    public void makeDiff(){
        try {
            System.out.println("\nMaking diff...");
            ImageDiff diff = new ImageDiffer().makeDiff(getActualScreenshot(), getExpectedScreenshot());
            File diffFile = new File(getDiffFilePath());
            ImageIO.write(diff.getMarkedImage(), this.SCREENSHOT_EXTENSION.replace(".",""), diffFile);
            System.out.println("\nDiff completed.");
        }catch(IOException e){
            System.out.println("\nSomething went wrong");
        }
    }



    public Screenshot saveActualScreenshot() throws IOException {
        //TODO: everything
        Screenshot expected = getExpectedScreenshot();
        Screenshot actual = getActualScreenshot();
        return actual;
    }

    public void removeScreenshotsRootFolder(){
        removeFolder(getScreenshotsRootDir());
    }

    public void makeExpectedScreenshotsForAllBrowsers(){
        for(int i=0; i<this.screenshots.size(); i++){
            screenshots.get(i).makeExpectedScreenshot();
        }
    }
    public void makeActualScreenshotsForAllBrowsers(){
        for(int i=0; i<this.screenshots.size(); i++){
            screenshots.get(i).makeActualScreenshot();
        }
    }
    public void makeDiffScreenshotsForAllBrowsers(){
        for(int i=0; i<this.screenshots.size(); i++){
            screenshots.get(i).makeDiff();
        }
    }
}

