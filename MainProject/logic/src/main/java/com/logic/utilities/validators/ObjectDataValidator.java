package com.logic.utilities.validators;

import javafx.scene.Node;

import java.util.Map;

/**
 * <h1>ObjectDataValidator</h1>
 *
 * This class provides dataset comparing of two maps, where first map of <Node, Object> has to
 * match the required data the other map holds on to.
 *
 * @author Candidate 511
 * @since 26-04-2019
 *
 */

public class ObjectDataValidator {


    public static boolean requiredDataMatching(Map<Node, Object> nodeAndValues, Map<String, String> requiredData) {

        int fieldNotSet = 0;

        // requiredData = <variableName, type>
        for(Map.Entry<String, String> entry : requiredData.entrySet()) {
            for (Map.Entry<Node, Object> item : nodeAndValues.entrySet()) {
                if(entry.getKey().equals(item.getKey().getId())) {
                    if(item.getValue() == null || item.getValue().toString().equals("")) {
                        fieldNotSet++;
                        break;
                    }
                }
            }
        }
        return fieldNotSet == 0;
    }

}
