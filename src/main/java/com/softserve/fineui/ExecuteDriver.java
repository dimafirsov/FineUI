package com.softserve.fineui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by dfirsov on 8/1/2017.
 */
public class ExecuteDriver {

    private ArrayList<WebDriver> drivers;

    public ExecuteDriver(ArrayList<WebDriver> drivers){
        this.drivers = drivers;
    }

    public void driversGet(String string){
        for(int i=0; i<this.drivers.size(); i++){
            this.drivers.get(i).get(string);
        }
    }

    public void driversWaitSeconds(int time){
        for(int i=0; i<this.drivers.size(); i++){
            this.drivers.get(i).manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        }
    }

    public void driversMaximize(){
        for(int i=0; i<this.drivers.size(); i++){
            this.drivers.get(i).manage().window().maximize();
        }
    }

    public void focusOnElementByCssSelector(String selector){
        for(int i=0; i<this.drivers.size(); i++){
            this.drivers.get(i).findElement(By.cssSelector(selector)).click();
        }
    }

}
