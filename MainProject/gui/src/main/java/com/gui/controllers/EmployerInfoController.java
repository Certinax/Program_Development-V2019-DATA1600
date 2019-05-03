package com.gui.controllers;

import com.data.clients.Employer;
import com.gui.alertBoxes.ErrorBox;
import com.gui.scene.SceneManager;
import com.gui.scene.SceneName;
import com.logic.utilities.DataPasser;
import com.logic.utilities.exceptions.ExtraStageException;
import com.logic.utilities.exceptions.NoPrimaryStageException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

//TODO Write JavaDocs!
public class EmployerInfoController implements Controller {

    private SceneManager sceneManager = SceneManager.INSTANCE;
    private ErrorBox error;

    @FXML
    private Label name, address, zipcode, city, phoneNumber, email, sector, industry;

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
        sector.setText("private");
      }else {
        sector.setText("public");
      }
    }

    @Override
    public void initialize() {
      setData((Employer) DataPasser.getData());
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
}
