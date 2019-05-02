package com.gui.controllers;

import com.data.work.AvailablePosition;
import com.gui.alertBoxes.ErrorBox;
import com.gui.scene.SceneManager;
import com.gui.scene.SceneName;
import com.logic.utilities.DataPasser;
import com.logic.utilities.exceptions.ExtraStageException;
import com.logic.utilities.exceptions.NoPrimaryStageException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

//TODO Write JavaDocs!
public class PositionInfoController implements Controller {

  private SceneManager sceneManager = SceneManager.INSTANCE;
  ErrorBox error;

  @FXML
  Label employerName, numberOfPositions, publicSector;
  @FXML
  Label workplace, positionType, industry, duration, startingTime, endingTime, contactInfo, salary;
  @FXML
  TextArea applicants, requiredQualifications, description;

  private void setData(AvailablePosition pos) {
    employerName.setText(pos.getEmployerName());
    numberOfPositions.setText(String.valueOf(pos.getNumberOfPositions()));
    publicSector.setText(pos.getSectorAsString());
    workplace.setText(pos.getWorkplace());
    positionType.setText(pos.getPositionType());
    industry.setText(pos.getIndustry());
    duration.setText(String.valueOf(pos.getDuration()));
    startingTime.setText(String.valueOf(pos.getStartingTime()));
    endingTime.setText(String.valueOf(pos.getEndingTime()));
    contactInfo.setText(pos.getContactInfo());
    salary.setText(String.valueOf(pos.getSalary()));
    applicants.setText(pos.getApplicantNames());
    requiredQualifications.setText(pos.getRequiredQualifications());
    description.setText(pos.getDescription());
  }

  @Override
  public void initialize() {
    setData((AvailablePosition) DataPasser.getData());
  }

  @Override
  public void refresh() {
  }

  @Override
  public void exit() {
    sceneManager.getCurrentPopUpStage().close();
    sceneManager.setCurrentPopUpStage(null);
  }


  @Override
  public void updateDataFromDataPasser() {
  }



  /* ------------------------------------------ Menu Methods ----------------------------------------------*/

  @FXML
  private void goToPositionInfo(ActionEvent event) {
    sceneManager.changeScene(SceneName.POSITIONINFO);
  }

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
  private void setFullscreenMode(ActionEvent event) {
    sceneManager.setFullscreen();
  }

  @FXML
  private void setWindowedMode() {
    sceneManager.setWindowed();
  }

  @FXML
  private void openOptions(ActionEvent event) {
    try {
      sceneManager.createUndecoratedStageWithScene(new Stage(), SceneName.OPTIONS, 2, 3);
    } catch (NoPrimaryStageException | ExtraStageException e) {
      error = new ErrorBox(e.getMessage(), "Can't open new window");
    }

  }
}

