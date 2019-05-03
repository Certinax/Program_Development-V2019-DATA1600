package com.gui.controllers;

import com.data.work.AvailablePosition;
import com.gui.alertBoxes.ErrorBox;
import com.gui.scene.SceneManager;
import com.logic.utilities.DataPasser;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 * <h1>Position Info Controller</h1>
 *
 * @author Candidate 778
 * @since 02-05-2019
 */
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

}

