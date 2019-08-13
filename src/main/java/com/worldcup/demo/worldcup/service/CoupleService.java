package com.worldcup.demo.worldcup.service;

import com.worldcup.demo.worldcup.entiy.Husband;
import com.worldcup.demo.worldcup.entiy.Wife;
import com.worldcup.demo.worldcup.exceptions.CoupleException;
import com.worldcup.demo.worldcup.repository.HusbandRepository;
import com.worldcup.demo.worldcup.repository.WifeRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class responsible to create couples
 *
 * @author tamas.kiss
 */
@Data
@Service
public class CoupleService {

    private Map<Husband, Wife> couples = new HashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(CoupleService.class);
    private List<Husband> husbandListDB = new ArrayList<>();
    private  List<Wife> wifeListDB = new ArrayList<>();

    /**
     * Init Couple object
     */
    public CoupleService() {

    }

    @Autowired
    HusbandRepository husbandRepository;

    @Autowired
    WifeRepository wifeRepository;

    /**
     * Create Husband list
     * @return Husband list
     */
    public List<Husband> createHusbandList() {
//        List<Husband> husbandList = Arrays.stream(getLine(inputData, 0)
//                                 .split("\\s*,\\s*")).map(Husband::new).collect(Collectors.toList());
        husbandRepository.findAll().forEach(husbandListDB::add);
        Collections.shuffle(husbandListDB);
        return husbandListDB;
    }

    /**
     * Create Wife list
     * @return Wife list
     */
    public List<Wife> createWifeList() {
//        List<Wife> wifeList = Arrays.stream(getLine(inputData, 1)
//                                                .split("\\s*,\\s*")).map(Wife::new).collect(Collectors.toList());
        wifeRepository.findAll().forEach(wifeListDB::add);
        Collections.shuffle(wifeListDB);
        return wifeListDB;
    }

    /**
     * Create couples from the Husband and Wife list
     */
    public void createCouples() {
        List<Husband> husbandList = createHusbandList();
        List<Wife> wifeList = createWifeList();
        if ((husbandList.isEmpty() || wifeList.isEmpty()) || wifeList.size() != husbandList.size()) {
            logger.warn("Husband or wife count are incorrect");
            throw new CoupleException("Husbands: " + husbandList.size() + " Wives: " + wifeList.size());
        }
        for (int i = 0; i < husbandList.size(); i++) {
            couples.put(husbandList.get(i), wifeList.get(i));
        }
    }
}
