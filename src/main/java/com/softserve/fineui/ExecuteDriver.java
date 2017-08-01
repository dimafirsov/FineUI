package com.softserve.fineui;

import org.openqa.selenium.WebDriver;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by dfirsov on 8/1/2017.
 */
public class ExecuteDriver {

    private ArrayList<WebDriver> drivers;

    ExecuteDriver(ArrayList<WebDriver> drivers){
        this.drivers = drivers;
    }

    public void driversGet(String string){
        for(int i=0; i<this.drivers.size(); i++){
            this.drivers.get(i).get(string);
        }
    }

}
