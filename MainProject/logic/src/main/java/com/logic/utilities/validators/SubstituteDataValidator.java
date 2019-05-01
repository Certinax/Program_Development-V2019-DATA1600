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

        for (Map.Entry<String, String> entry : requiredData.entrySet()) {
            for (Map.Entry<Node, Object> item : nodeAndValues.entrySet()) {
                System.out.println(item.getKey().getId());
                if(entry.getKey().equals(item.getKey().getId())) {
                    match++;
                }
            }
        }

        /*System.out.println(match);
        if(requiredData.size() == match) {
            System.out.println("JIPPI");
        }*/

        return match == requiredData.size();
    }

}
