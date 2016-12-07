package com.softserve.fineui;

import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException; 

import static com.softserve.fineui.Utils.getProps;

/**
 * Created by Dmytro Firsov on 12/3/2016.
 */
public class Screenshots {

    public void makeScreenshot(String path, WebDriver driver){
        Screenshot screenshot = new AShot().takeScreenshot(driver);
        File newScreenshot = new File(path);
        try {
            ImageIO.write(screenshot.getImage(), "png", newScreenshot);

        }catch(IOException e){
            System.out.print("WTF! Screenshot was not created!");
        }
    }

    public Screenshot getExpectedScreenshot(String name) throws IOException{
        Screenshot screenshot = new Screenshot(ImageIO.read(new File(name)));
        return screenshot;
    }

    public Screenshot getActualScreenshot(String name)  throws IOException{
            Screenshot screenshot = new Screenshot(ImageIO.read(new File(name)));
        return screenshot;
    }

    public void runCounter(){
        try {
            String anim = "|/-\\";
            for (int x = 0; x < 100; x++) {
                String data = "\r" + anim.charAt(x % anim.length()) + " " + x;
                System.out.write(data.getBytes());
                Thread.sleep(100);
            }
        }catch(IOException e){
        }catch (InterruptedException e){}
    }

    public void makeDiff(String actual, String expected){
        try {
            System.out.println("\nMaking diff...");
            runCounter();
            ImageDiff diff = new ImageDiffer().makeDiff(getActualScreenshot(actual), getExpectedScreenshot(expected));
            File diffFile = new File(getProps().getProperty("diff.screenshot.path")+ File.separator+"diff.png");
            ImageIO.write(diff.getMarkedImage(), "png", diffFile);
        }catch(IOException e){
        }finally{
            System.out.println("\nDiff completed.");
        }
    }
}

