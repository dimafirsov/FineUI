package com.softserve.fineui;

import org.openqa.selenium.WebDriver;

import javax.jws.WebService;
import java.util.ArrayList;

import static com.softserve.fineui.Utils.getCurrentDate;

/**
 * Created by dimafirsov on 30.07.17.
 */
public class SANDBOX {

    private WebDriver driver;
    private FilesStructure structure;
    private Screenshots s;

    public static void main(String[] args) {

        WebDriver driver = Utils.chromeDriverInit();
        driver.manage().window().maximize();



    }

}
