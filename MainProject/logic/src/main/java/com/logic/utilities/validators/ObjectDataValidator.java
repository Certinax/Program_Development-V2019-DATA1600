package com.logic.utilities.validators;

import javafx.scene.Node;

import java.util.Map;
import java.util.Objects;

public class ObjectDataValidator {


    public static boolean requiredDataMatching(Map<Node, Object> nodeAndValues, Map<String, String> requiredData) {

        int fieldNotSet = 0;

        // requiredData = <variableName, type>

        for(Map.Entry<String, String> entry : requiredData.entrySet()) {
            for (Map.Entry<Node, Object> item : nodeAndValues.entrySet()) {
                if(entry.getKey().equals(item.getKey().getId())) {
                    if(item.getValue() == null || item.getValue().toString().equals("")) {
                        System.out.println("Item name: " + item.getKey().getId() + " Object value: " + item.getValue());
                        fieldNotSet++;
                        break;
                    }
                    System.out.println("Item name: " + item.getKey().getId() + " Object value: " + item.getValue());
                }
            }
        }
        System.out.println(fieldNotSet);
        return fieldNotSet == 0;
    }

}
