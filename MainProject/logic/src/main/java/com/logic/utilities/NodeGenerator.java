package com.logic.utilities;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.*;

public class NodeGenerator {

    public static ArrayList<Node> getAllNodes(Parent root) {
        ArrayList<Node> nodes = new ArrayList<>();
        addAllChildrens(root, nodes);
        return nodes;
    }

    public static void addAllChildrens(Parent parent, ArrayList<Node> nodes) {

        List<Node> children = Collections.EMPTY_LIST;

        if(parent instanceof ScrollPane) {
            Node nod = ((ScrollPane) parent).getContent();

            System.out.println("KOMMER JEG HIT?" + parent.getTypeSelector() + " Children: " + children.toString());
            addAllChildrens((Parent) nod, nodes);
        } else if (parent instanceof GridPane) {
            //for (GridPane gridPane : ((GridPane) parent).getGridpane())
            System.out.println("SCROLLPANE");
        } else {
            children = parent.getChildrenUnmodifiable();
        }

        System.out.println(children.toString());

        /*for (Node node : children) {
            nodes.add(node);
            if(node instanceof Parent) {
                addAllChildrens((Parent) node, nodes);
            }
        }*/

    }

    private static void addAllDescendents(Parent parent, ArrayList<Node> nodes) {
        // Get children.
        List<Node> children = Collections.EMPTY_LIST;
        if (parent instanceof ButtonBar) {
            children = ((ButtonBar) parent).getButtons();
        } else if (parent instanceof TabPane) {
            for (Tab tab : ((TabPane) parent).getTabs()) {
                Node tabContent = tab.getContent();
                if (tabContent instanceof Parent) {
                    addAllDescendents((Parent) tab.getContent(), nodes);
                } else {
                    // You can log and get a type that is not supported.
                }
            }
        } else {
            children = parent.getChildrenUnmodifiable();
        }

        // Add nodes.
        for (Node node : children) {
            nodes.add(node);
            if (node instanceof Parent) {
                addAllDescendents((Parent) node, nodes);
            }
        }
    }


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

            // This is used for recursive call
            if (node instanceof Pane) {
                generateNodesAndValues((Pane) node, map);
            }

        }

        return map;
    }
}
