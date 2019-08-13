package com.worldcup.demo.worldcup;

import com.worldcup.demo.worldcup.entiy.Husband;
import com.worldcup.demo.worldcup.entiy.Wife;
import com.worldcup.demo.worldcup.repository.HusbandRepository;
import com.worldcup.demo.worldcup.repository.WifeRepository;
import com.worldcup.demo.worldcup.service.CoupleService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CoupleTest {

    @Mock
    WifeRepository wifeRepository;
    @Mock
    HusbandRepository husbandRepository;

    @InjectMocks
    private CoupleService coupleService;

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
