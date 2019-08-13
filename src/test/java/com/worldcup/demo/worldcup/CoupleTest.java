package com.worldcup.demo.worldcup;

import static org.mockito.Mockito.when;

import com.worldcup.demo.worldcup.entiy.Husband;
import com.worldcup.demo.worldcup.entiy.Wife;
import com.worldcup.demo.worldcup.exceptions.CoupleException;
import com.worldcup.demo.worldcup.repository.HusbandRepository;
import com.worldcup.demo.worldcup.repository.WifeRepository;
import com.worldcup.demo.worldcup.service.CoupleService;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
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
    public void testGetWife() {
        Optional<Wife> wife1 = Optional.of(new Wife());
        wife1.get().setName("Katarina");
        wife1.get().setId(Integer.toUnsignedLong(1));

        when(wifeRepository.findById(Integer.toUnsignedLong(1))).thenReturn(wife1);

        Assert.assertEquals(wifeRepository.findById(Integer.toUnsignedLong(1)), wife1);
    }

    @Test
    public void testGetWives() {
        Wife wife1 = new Wife();
        wife1.setName("Katarina");
        Wife wife2 = new Wife();
        wife2.setName("Bella");
        List<Wife> wives = Arrays.asList(wife1, wife2);

        when(wifeRepository.findAll()).thenReturn(wives);
        when(coupleService.createWifeList()).thenReturn(wives);

        Assert.assertEquals(2, coupleService.getWifeListDB().size());
    }

    @Test
    public void testGetHusband() {
        Optional<Husband> husband = Optional.of(new Husband());
        husband.get().setName("Laci");
        husband.get().setId(Integer.toUnsignedLong(1));

        when(husbandRepository.findById(Integer.toUnsignedLong(1))).thenReturn(husband);

        Assert.assertEquals(husbandRepository.findById(Integer.toUnsignedLong(1)), husband);
    }

    @Test
    public void testHusbandList() {
        Husband husband1 = new Husband();
        husband1.setName("Laca");
        Husband husband2 = new Husband();
        husband2.setName("Ferko");
        List<Husband> husbands = Arrays.asList(husband1, husband2);

        when(husbandRepository.findAll()).thenReturn(husbands);
        when(coupleService.createHusbandList()).thenReturn(husbands);

        Assert.assertEquals(2, coupleService.getHusbandListDB().size());    }

    @Test
    public void testCoupleMap() {
        Husband husband1 = new Husband();
        husband1.setName("Laca");
        Wife wife1 = new Wife();
        wife1.setName("Katarina");

        when(coupleService.createHusbandList()).thenReturn(Collections.singletonList(husband1));
        when(coupleService.createWifeList()).thenReturn(Collections.singletonList(wife1));
        coupleService.createCouples();

        Assert.assertTrue(coupleService.getCouples().containsKey(husband1));
        Assert.assertTrue(coupleService.getCouples().containsValue(wife1));
    }

    @Test(expected = CoupleException.class)
    public void testCoupleException() {
        Husband husband1 = new Husband();
        husband1.setName("Laca");
        Wife wife1 = new Wife();
        wife1.setName("Katarina");
        Wife wife2 = new Wife();
        wife2.setName("Bella");

        when(coupleService.createHusbandList()).thenReturn(Collections.singletonList(husband1));
        when(coupleService.createWifeList()).thenReturn(Arrays.asList(wife1, wife2));
        coupleService.createCouples();
    }

}
