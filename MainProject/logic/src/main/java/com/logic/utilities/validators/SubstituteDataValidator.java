package com.logic.utilities.validators;

import javafx.scene.Node;

import java.util.Map;

public class SubstituteDataValidator {


    public static boolean dataMatching(Map<Node, Object> nodeAndValues) {

        int noMatch = 0;

        // <variableName, type>
        Map<String,String> requiredData = RequiredDataContainer.SUBSTITUTE.requiredData();

        for (Map.Entry<String, String> entry : requiredData.entrySet()) {
            if(!nodeAndValues.containsValue(entry.getKey())) {
                noMatch++;
            }
        }

        return noMatch == 0;
    }

}
