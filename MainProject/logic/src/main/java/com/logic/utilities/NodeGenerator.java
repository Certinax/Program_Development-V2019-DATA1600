package com.logic.utilities;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NodeGenerator {

    public static ArrayList<Node> getAllNodes(Parent root) {
        ArrayList<Node> nodes = new ArrayList<>();
        addAllChildrens(root, nodes);
        return nodes;
    }

    public static void addAllChildrens(Parent parent, ArrayList<Node> nodes) {

        List<Node> children = Collections.EMPTY_LIST;

        if(parent instanceof ScrollPane) {
            //children = ((ScrollPane) parent).getContent();
            System.out.println("KOMMER JEG HIT?" + parent.getTypeSelector() + " Children: " + children.toString());
        } else if (parent instanceof GridPane) {
            //for (GridPane gridPane : ((GridPane) parent).getGridpane())
            System.out.println("SCROLLPANE");
        } else {
            children = parent.getChildrenUnmodifiable();
        }

        System.out.println(children.size());

        for (Node node : nodes) {
            nodes.add(node);
            if(node instanceof Parent) {
                addAllChildrens((Parent) node, nodes);
            }
        }

    }
}
