package com.logic.utilities.validators;

import javafx.scene.Node;

import java.util.Map;
import java.util.Objects;

public class SubstituteDataValidator {


    public static boolean dataMatching(Map<Node, Object> nodeAndValues) {

        int fieldNotSet = 0;

        // <variableName, type>
        Map<String,String> requiredData = RequiredDataContainer.SUBSTITUTE.requiredData();

        for(Map.Entry<String, String> entry : requiredData.entrySet()) {
            for (Map.Entry<Node, Object> item : nodeAndValues.entrySet()) {
                if(entry.getKey().equals(item.getKey().getId())) {
                    if(item.getValue() == null || item.getValue().toString().equals("")) {
                        System.out.println("Item asdds: " + item.getKey().getId() + " Object value: " + item.getValue());
                        fieldNotSet++;
                        break;
                    }
                    System.out.println("Item asdds: " + item.getKey().getId() + " Object value: " + item.getValue());
                }
            }
        }
        System.out.println(fieldNotSet);
        return fieldNotSet == 0;
    }

}
