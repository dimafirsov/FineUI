package com.softserve.fineui;

import org.apache.commons.exec.OS;

import java.io.File;

/**
 * Created by dimafirsov on 05.07.17.
 */
public class FilesStructure {
    private static String currentDateTime = Utils.getCurrentDate("yyyy-MM-dd");
    private final static String currentDateSec = new FilesStructure().getCurrentDateSec();
    private final String testResultsName = "test_results";
    private String testSuiteName;
    private String testName;

    private String getCurrentDateSec(){
        if(OS.isFamilyWindows()){
            return Utils.getCurrentDate("hh-mm-ss-(aa)");
        }else{
            return Utils.getCurrentDate("hh:mm:ss:aa");
        }
    }

    public FilesStructure(){ }

    public FilesStructure(String testSuiteName, String testName) {
        this.testSuiteName = testSuiteName;
        this.testName = testName;
    }

    public String getPath(){
        return testResultsName + File.separator +
                currentDateTime + File.separator +
                currentDateSec + "_" + testSuiteName + File.separator +
                testName + File.separator;
    }

    public String getPath(WebDriverType type){
        String path = "";
        switch(type) {
            case CHROME:
            case FF:
            case EDGE:
                path = getPath() + type.toString().toLowerCase() + File.separator;
        }
        return path;
    }

    public void createFilesStructure(){
        Utils.createDirsRecursively(getPath());
    }
    public void createFilesStructure(WebDriverType type){
        Utils.createDirsRecursively(getPath(type));
    }




}
