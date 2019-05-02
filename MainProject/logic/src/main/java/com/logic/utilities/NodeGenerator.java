package com.logic.utilities;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.*;

public class NodeGenerator {



    public static <T extends Pane> Map<Node, Object> generateNodesAndValues(T parent) {
        return generateNodesAndValues(parent, new HashMap<>());
    }

    private static <T extends Pane> Map<Node, Object> generateNodesAndValues(T parent, Map<Node, Object> map) {
        for (Node node : parent.getChildren()) {
            // Nodes - You can add more.
            if (node instanceof TextField) {
                map.put(node, ((TextField) node).getText());
            }
            if (node instanceof PasswordField) {
                map.put(node, ((PasswordField) node).getText());
            }
            if (node instanceof TextArea) {
                map.put(node, ((TextArea) node).getText());
            }
            if (node instanceof CheckBox) {
                map.put(node, ((CheckBox) node).isSelected());
            }
            if (node instanceof DatePicker) {
                map.put(node, ((DatePicker) node).getValue());
            }
            if (node instanceof ComboBox<?>) {
                map.put(node, ((ComboBox<?>) node).getValue());
            }
            if (node instanceof RadioButton) {
                map.put(node, ((RadioButton) node).isSelected());
            }
            if (node instanceof ListView) {
                map.put(node, ((ListView) node).getItems());
            }

            // This is used for recursive call
            if (node instanceof Pane) {
                generateNodesAndValues((Pane) node, map);
            }

        }

        return map;
    }

}
