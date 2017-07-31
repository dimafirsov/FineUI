package com.softserve.fineui;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.softserve.fineui.Utils.*;

/**
 * Created by dimafirsov on 05.07.17.
 */
public class FilesStructure {
    private final static String currentDateTime = getCurrentDate("yyyy-MM-dd");
    private final static String currentDateSec = getCurrentDate("hh:mm:ss");
    private final String testResultsName = "test_results";
    private String testSuiteName;
    private String testName;

    FilesStructure(String testSuiteName, String testName) {
        this.testSuiteName = testSuiteName;
        this.testName = testName;
    }

    public String getPath(){
        return testResultsName + File.separator +
                currentDateTime + File.separator +
                currentDateSec + "_" + testSuiteName + File.separator +
                testName + File.separator;
    }

    public void createFileStructure(){
        Utils.createDirsRecursively(getPath());
    }




}
