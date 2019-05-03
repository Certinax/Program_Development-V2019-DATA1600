package com.logic.utilities;

import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.util.ArrayList;

/**
 * <h1>NodeHandler</h1>
 *
 * This is a utility-class for nodes
 *
 * @author Candidate 511
 * @since 24-04-2019
 */

public class NodeHandler {

    public static void clearNodes(ArrayList<Node> nodeList) {
        for (Node node : nodeList) {
            if(node instanceof TextField) {
                ((TextField) node).clear();
            }
            if(node instanceof RadioButton) {
                ((RadioButton) node).setSelected(false);
            }
        }
    }
}
