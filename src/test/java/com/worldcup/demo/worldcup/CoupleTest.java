package com.worldcup.demo.worldcup;

import com.worldcup.demo.worldcup.entiy.Husband;
import com.worldcup.demo.worldcup.entiy.Wife;
import com.worldcup.demo.worldcup.service.CoupleService;
import org.junit.Assert;
import org.junit.Test;

public class CoupleTest {


    private final static String DATA = "C:\\Training\\worldcup\\src\\main\\resources\\data.txt";
    private CoupleService coupleService = new CoupleService();

    @Test
    public void testWifeList() {
       Assert.assertEquals(4, coupleService.createWifeList().size());
    }

    @Test
    public void testHusbandList() {
        Assert.assertEquals(4, coupleService.createHusbandList().size());
    }

    @Test
    public void testCoupleMap() {

        for (Husband h : coupleService.getCouples().keySet()) {
            System.out.println(h.getName());
        }
        for (Wife w : coupleService.getCouples().values()) {
            System.out.println(w.getName());
        }
    }

}
