package com.logic.utilities;

import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NodeHandler {

    public static void nodeFormatter(Map<Node, Object> dataSet) {
        Map<String, Object> convertedMap = new HashMap<>();
        for(Map.Entry<Node, Object> key : dataSet.entrySet()) {
            convertedMap.put(key.getKey().getId(), key.getValue());
        }
        headerChecker(convertedMap);
    }

    private static void headerChecker(Map<String, Object> convertedMap) {
        for (Map.Entry<String, Object> key : convertedMap.entrySet()) {
            if(key.getKey().equals("firstnameField") && key.getValue() != null) {
            }
        }
    }

    public static void clearNodes(ArrayList<Node> nodeList) {
        for (Node node : nodeList) {
            if(node instanceof TextField) {
                ((TextField) node).clear();
            }
            if(node instanceof RadioButton) {
                ((RadioButton) node).setSelected(false);
            }
            if(node instanceof DatePicker) {
                ((DatePicker) node).setValue(LocalDate.now());
            }
        }
    }


}
