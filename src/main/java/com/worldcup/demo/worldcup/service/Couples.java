package com.worldcup.demo.worldcup.service;

import static com.worldcup.demo.worldcup.service.ProcessData.getLine;

import com.worldcup.demo.worldcup.entiy.Husband;
import com.worldcup.demo.worldcup.entiy.Wife;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Data;

@Data
public class Couples {

    public Couples(String inputData) {
        createCouples(inputData);
    }

    private Map<Husband, Wife> couples = new HashMap<>();


    public List<Husband> createHusbandList(String inputData) {
        return Arrays.stream(getLine(inputData, 0)
                                 .split("\\s*,\\s*")).map(Husband::new).collect(Collectors.toList());
    }

    public List<Wife> createWifeList(String inputData) {
        List<Wife> wifeList = Arrays.stream(getLine(inputData, 1)
                                                .split("\\s*,\\s*")).map(Wife::new).collect(Collectors.toList());
        Collections.shuffle(wifeList);
        return wifeList;
    }

    private void createCouples(String inputData) {
        List<Husband> husbandList = createHusbandList(inputData);
        List<Wife> wifeList = createWifeList(inputData);
        for (int i = 0; i < husbandList.size(); i++) {
            couples.put(husbandList.get(i), wifeList.get(i));
        }
    }
}
