package com.worldcup.demo.worldcup.service;

import static com.worldcup.demo.worldcup.service.ProcessData.getLine;

import com.worldcup.demo.worldcup.WorldCupApplication;
import com.worldcup.demo.worldcup.entiy.Husband;
import com.worldcup.demo.worldcup.entiy.Wife;
import com.worldcup.demo.worldcup.exceptions.CoupleException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class responsible to create couples
 *
 * @author tamas.kiss
 */
@Data
public class Couples {

    private Map<Husband, Wife> couples = new HashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(Couples.class);

    /**
     * Init Couple object
     * @param inputData The input data
     */
    public Couples(String inputData) {
        createCouples(inputData);
    }

    /**
     * Create Husband list
     * @param inputData The input data
     * @return Husband list
     */
    public List<Husband> createHusbandList(String inputData) {
        return Arrays.stream(getLine(inputData, 0)
                                 .split("\\s*,\\s*")).map(Husband::new).collect(Collectors.toList());
    }

    /**
     * Create Wife list
     * @param inputData The input data
     * @return Wife list
     */
    public List<Wife> createWifeList(String inputData) {
        List<Wife> wifeList = Arrays.stream(getLine(inputData, 1)
                                                .split("\\s*,\\s*")).map(Wife::new).collect(Collectors.toList());
        Collections.shuffle(wifeList);
        return wifeList;
    }

    /**
     * Create couples from the Husband and Wife list
     * @param inputData the input data
     */
    private void createCouples(String inputData) {
        List<Husband> husbandList = createHusbandList(inputData);
        List<Wife> wifeList = createWifeList(inputData);
        if ((husbandList.isEmpty() || wifeList.isEmpty()) || wifeList.size() != husbandList.size()) {
            logger.warn("Husband or wife count are incorrect");
            throw new CoupleException("Husbands: " + husbandList.size() + " Wives: " + wifeList.size());
        }
        for (int i = 0; i < husbandList.size(); i++) {
            couples.put(husbandList.get(i), wifeList.get(i));
        }
    }
}
