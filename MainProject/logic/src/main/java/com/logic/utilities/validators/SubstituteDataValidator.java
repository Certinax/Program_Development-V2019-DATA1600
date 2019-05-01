package com.logic.utilities.validators;

import javafx.scene.Node;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public class SubstituteDataValidator {


    public static boolean dataMatching(Map<Node, Object> nodeAndValues) {

        int match = 0;

        // <variableName, type>
        Map<String,String> requiredData = RequiredDataContainer.SUBSTITUTE.requiredData();

        for(Map.Entry<String, String> entry : requiredData.entrySet()) {
            for (Map.Entry<Node, Object> item : nodeAndValues.entrySet()) {
                if(entry.getKey().equals(item.getKey().getId())) {
                    System.out.println("Entry key: " + entry.getKey() + "Item id : " + item.getKey().getId());
                    match++;
                    break;
                }
            }
        }
        return match == requiredData.size();
    }

}
