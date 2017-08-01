package com.softserve.fineui;

import org.junit.Assert;

import java.util.ArrayList;

/**
 * Created by dimafirsov on 01.08.17.
 */
public class TestHelper {

    public void assertAllDiffs(ArrayList<Integer> results){
        for(int i=0; i<results.size(); i++){
            Assert.assertEquals(0, results.get(i).intValue());
        }
    }

}
