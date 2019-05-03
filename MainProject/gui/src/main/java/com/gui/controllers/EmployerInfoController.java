package com.gui.controllers;

import com.data.clients.Employer;
import com.data.work.AvailablePosition;
import com.gui.alertBoxes.ErrorBox;
import com.gui.scene.SceneManager;
import com.gui.scene.SceneName;
import com.logic.concurrency.ReaderThreadStarter;
import com.logic.filePaths.ActivePaths;
import com.logic.utilities.DataPasser;
import com.logic.utilities.exceptions.ExtraStageException;
import com.logic.utilities.exceptions.NoPrimaryStageException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * <h1>Employer Info Controller</h1>
 *
 * @author Candidate 778, Candidate 530
 * @since 01-05-2019
 */
public class EmployerInfoController implements Controller {

    private SceneManager sceneManager = SceneManager.INSTANCE;
    private ErrorBox error;

    @FXML
    private Label name, address, zipcode, city, phoneNumber, email, sector, industry;

    @FXML
    private TextArea positions;

    @FXML
    private void setData(Employer employer){
      name.setText(employer.getName());
      address.setText(employer.getAddress());
      zipcode.setText(String.valueOf(employer.getZipcode()));
      city.setText(employer.getCity());
      phoneNumber.setText(String.valueOf(employer.getPhoneNumber()));
      email.setText(employer.getEmail());
      industry.setText(employer.getIndustry());
      if (employer.getPrivateSector()){
        sector.setText("Private");
      }else {
        sector.setText("Public");
      }

      ArrayList<AvailablePosition> jobList;

      try {
          jobList = ReaderThreadStarter.startReader(ActivePaths.getAvailablePositionJOBJPath());
      } catch (InterruptedException | ExecutionException e) {
          jobList = new ArrayList<>();
          error = new ErrorBox("Couldn't read from file", "Couldn't read file");
      }

      StringBuilder sb = new StringBuilder();

      for (AvailablePosition job : jobList) {
          if(job.getEmployerId().equals(employer.getEmployerId())) {
              sb.append(job.getPositionType()).append(" at ").append(job.getWorkplace()).append("\n");
          }
      }
      if (sb.toString().equals("")) {
          positions.setText("No jobs posted");
      } else {
          positions.setText(sb.toString());
      }

    }

    @Override
    public void initialize() {
      setData((Employer) DataPasser.getData());
        System.out.println(DataPasser.getData());
    }

    @Override
    public void refresh() {
    }

    @Override
    public void updateDataFromDataPasser() {
    }

    @Override
    public void exit() {
        sceneManager.getCurrentPopUpStage().close();
        sceneManager.setCurrentPopUpStage(null);
    }
}
