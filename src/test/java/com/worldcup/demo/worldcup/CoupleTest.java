package com.worldcup.demo.worldcup;

import com.worldcup.demo.worldcup.entiy.Husband;
import com.worldcup.demo.worldcup.entiy.Wife;
import com.worldcup.demo.worldcup.service.Couples;
import org.junit.Assert;
import org.junit.Test;

public class CoupleTest {


    private final static String DATA = "C:\\Training\\worldcup\\src\\main\\resources\\data.txt";
    private Couples couples = new Couples(DATA);

    @Test
    public void testWifeList() {
       Assert.assertEquals(4,couples.createWifeList((DATA)).size());
    }

    @Test
    public void testHusbandList() {
        Assert.assertEquals(4,couples.createHusbandList((DATA)).size());
    }

    @Test
    public void testCoupleMap() {

        for (Husband h : couples.getCouples().keySet()) {
            System.out.println(h.getName());
        }
        for (Wife w : couples.getCouples().values()) {
            System.out.println(w.getName());
        }
    }

}
