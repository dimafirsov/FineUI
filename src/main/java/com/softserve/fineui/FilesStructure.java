package com.softserve.fineui;

import java.io.File;
import java.util.ArrayList;

import static com.softserve.fineui.Utils.*;

/**
 * Created by dimafirsov on 05.07.17.
 */
public class FilesStructure {
    private final String testResultsName = "test_results";
    private String testSuiteName;
    private String testName;

    FilesStructure(String testSuiteName, String testName) {
        this.testSuiteName = testSuiteName;
        this.testName = testName;
    }

    public String getPath(){
        return testResultsName + File.separator +
                testSuiteName + File.separator +
                testName + File.separator;
    }

    public void createFileStructure(){
        Utils.createDirsRecursively(getPath());
    }




}
