package com.logic.utilities;

import javafx.scene.Node;

import java.util.HashMap;
import java.util.Map;

public class NodeHandler {

    public static void nodeFormatter(Map<Node, Object> dataSet) {
        Map<String, Object> convertedMap = new HashMap<>();
        for(Map.Entry<Node, Object> key : dataSet.entrySet()) {
            System.out.println("Node : " + key.getKey() + " Object : " + key.getValue());
            convertedMap.put(key.getKey().getId(), key.getValue());
        }

        headerChecker(convertedMap);
    }

    private static void headerChecker(Map<String, Object> convertedMap) {
        for (Map.Entry<String, Object> key : convertedMap.entrySet()) {
            if(key.getKey().equals("firstnameField") && key.getValue() != null) {
                System.out.println("HOOORAYY");
            }
        }
    }


}
