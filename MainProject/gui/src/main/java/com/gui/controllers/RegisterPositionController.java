package com.gui.controllers;

import com.data.Industry;
import com.data.clients.Employer;
import com.data.factory.AvailablePositionFactory;
import com.gui.alertBoxes.ErrorBox;
import com.gui.scene.SceneManager;
import com.gui.scene.SceneName;
import com.logic.concurrency.ReaderThreadStarter;
import com.logic.customTextFields.IntField;
import com.logic.filePaths.ActivePaths;
import com.logic.utilities.NodeGenerator;
import com.logic.utilities.NodeHandler;
import com.logic.utilities.exceptions.AvailablePositionException;
import com.logic.utilities.exceptions.ExtraStageException;
import com.logic.utilities.exceptions.NoPrimaryStageException;
import com.logic.utilities.exceptions.NumberGenerationException;
import com.logic.utilities.validators.ObjectDataValidator;
import com.logic.utilities.validators.RequiredDataContainer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ExecutionException;


/**
 * <h1>Register Position Controller</h1>
 *
 * @author Mathias Lund Ahrn
 * @since 02-05-2019
 */
public class RegisterPositionController implements Controller {

    private SceneManager sceneManager = SceneManager.INSTANCE;

    /* ------------------------------------- fx:id fields --------------------------------------- */
    @FXML
    private Label errorMsg, employerNode;

    @FXML
    private ToggleGroup selector;

    @FXML
    private RadioButton publicSector, privateSector;

    @FXML
    private IntField numberOfPositions, startingTime, endingTime, salary;

    @FXML
    private ComboBox<String> employerList, industry;

    @FXML
    private ComboBox<Employer> employer;

    @FXML
    private TextField position, workplace, duration, contactInfo;

    @FXML
    private TextArea requiredQualifications, positionDescription;

    @FXML
    private Button register;

    @FXML
    private AnchorPane parent;

    @FXML
    private ScrollPane scrollPane;


    // Keeping track of employer names and Id's.
    private ArrayList<Employer> employers = new ArrayList<>();
    private Employer selectedEmployer;
    private ErrorBox error;


    /* --------------------------------- Required Controller Methods ------------------------------------*/

    @Override
    public void initialize() {
        String error = "";

        ObservableList<String> oIndustrylist = FXCollections.observableArrayList(Industry.industryList());
        industry.setItems(oIndustrylist.sorted());

        try {
            employers = ReaderThreadStarter.startReader(ActivePaths.getEmployerCSVPath());
            clear();
        } catch (ExecutionException | InterruptedException e) {
            error += e.getMessage();
            scrollPane.setVvalue(0);
            errorMsg.setTextFill(Color.RED);
            errorMsg.setText(error);
            errorMsg.setVisible(true);
        }
        ObservableList<String> empList = FXCollections.observableArrayList();
        for (Employer employer : employers) {
            empList.add(employer.getName());
        }
        employerList.setItems(empList);
    }


    @Override
    public void refresh() {
    }

    @Override
    public void updateDataFromDataPasser() {
    }

    @Override
    public void exit() {
    }


    /* ------------------------------------------ Register Method ------------------------------------------ */

    @FXML
    private void registerPosition(ActionEvent event){

        String msg = "";

        Map<Node, Object> nodesAndValues = NodeGenerator.generateNodesAndValues(parent);
        nodesAndValues.put(employer, selectedEmployer);

        if (ObjectDataValidator.requiredDataMatching(nodesAndValues, RequiredDataContainer.AVAILABLE_POSITION.requiredData())) {
            // Opprett objekt
            try {
                AvailablePositionFactory availablePositionFactory = new AvailablePositionFactory(nodesAndValues);
                msg += "Temporary position added!";
                scrollPane.setVvalue(0);
                errorMsg.setTextFill(Color.GREEN);
                errorMsg.setText(msg);
                errorMsg.setVisible(true);
                clear();
            } catch (IllegalArgumentException
                    | InterruptedException
                    | AvailablePositionException
                    | NumberGenerationException e) {
                msg += e.getMessage();
                scrollPane.setVvalue(0);
                errorMsg.setTextFill(Color.RED);
                errorMsg.setText(msg);
                errorMsg.setVisible(true);
            }
        } else {
            msg += "You need to set Employer from list and fill the required fields:\n" +
                    "Sector" +
                    ", Number of positions";
            scrollPane.setVvalue(0);
            errorMsg.setTextFill(Color.RED);
            errorMsg.setText(msg);
            errorMsg.setVisible(true);
        }
    }

    @FXML
    public void setSelectedEmployer(ActionEvent event) {
        for (Employer emp : employers) {
            if(emp.getName().equals(employerList.getSelectionModel().getSelectedItem())) {
                selectedEmployer = emp;
            }
        }
    }

    private void clear() {
        NodeHandler.clearNodes(NodeGenerator.generateNodes(parent));
    }

    /* ------------------------------------------ Menu Methods ----------------------------------------------*/

    @FXML
    private void goToRegisterEmployer(ActionEvent event) {
        sceneManager.changeScene(SceneName.REGISTEREMPLOYER);
    }

    @FXML
    private void goToRegisterPosition(ActionEvent event) {
        sceneManager.changeScene(SceneName.REGISTERPOSITION);
    }

    @FXML
    private void goToRegisterSubstitute(ActionEvent event) {
        sceneManager.changeScene(SceneName.REGISTERSUBSTITUTE);
    }

    @FXML
    private void goToSubstitutes(ActionEvent event) {
        sceneManager.changeScene(SceneName.SUBSTITUTES);
    }

    @FXML
    private void goToTakenPositions(ActionEvent event) {
        sceneManager.changeScene(SceneName.TAKENPOSITIONS);
    }

    @FXML
    private void goToAvailablePositions(ActionEvent event) {
        sceneManager.changeScene(SceneName.AVAILABLEPOSITIONS);
    }

    @FXML
    private void goToEmployers(ActionEvent event) {
        sceneManager.changeScene(SceneName.EMPLOYERS);
    }


    @FXML
    private void openOptions(ActionEvent event) {
        try {
            sceneManager.createUndecoratedStageWithScene(new Stage(), SceneName.OPTIONS,2,3);
        } catch (NoPrimaryStageException | ExtraStageException e) {
            error = new ErrorBox(e.getMessage(), "Can't open new window");
        }
    }
}
