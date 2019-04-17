package com.gui.controllers;

import com.logic.customTextFields.IntField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class DataFilteringController implements Initializable {
  /*
  @FXML
  private TableView<Substitute> table = new TableView<>();

  private ObservableList<Substitute> listOfPeople = FXCollections.observableArrayList();
  private FilteredList<Substitute> filteredListofPeople = new FilteredList<>(listOfPeople, p -> true);
  private SortedList<Substitute> sortedListOfPeople = new SortedList<>(filteredListofPeople);


  @FXML
  private TextField category;
  @FXML
  private TextField education;
  @FXML
  private TextField workExperience;
  @FXML
  private TextField workExperience2;
  @FXML
  private IntField salaryRequirement;

  @FXML
  private TextField categoryFilter;
  @FXML
  private TextField educationFilter;
  @FXML
  private TextField workExperienceFilter;
  @FXML
  private TextField workExperience2Filter;
  @FXML
  private IntField salaryRequirementFilter;


  @FXML
  private void editCategory(TableColumn<Substitute,String> event){
    ((Substitute)event.getTableView().getItems().get(event.getTablePosition().getRow())).setID(event.getNewValue());
  }
  private void editEducation(TableColumn<Substitute,String> event){
    ((Substitute)event.getTableView().getItems().get(event.getTablePosition().getRow())).setID(event.getNewValue());
  }
  private void editWorkExperience(TableColumn<Substitute,String> event){
    ((Substitute)event.getTableView().getItems().get(event.getTablePosition().getRow())).setID(event.getNewValue());
  }
  private void editWorkExperience2(TableColumn<Substitute,String> event){
    ((Substitute)event.getTableView().getItems().get(event.getTablePosition().getRow())).setID(event.getNewValue());
  }
  private void editSalaryRequirement(TableColumn<Substitute, Integer> event){
    ((Substitute)event.getTableView().getItems().get(event.getTablePosition().getRow())).setID(event.getNewValue());
  }

  public void delete(ActionEvent event){
    listOfPeople.remove(table.getSelectionModel().getSelectedItem());
  }

  public void add(ActionEvent event){
    listOfPeople.add(new Substitute(category.getText(),education.getText(),workExperience.getText(),workExperience2.getText(),salaryRequirement.getAccessibleHelp()));
    category.setText("");
    education.setText("");
    workExperience.setText("");
    workExperience2.setText("");
    salaryRequirement.setText("");
  }

  public void filterCategory(KeyEvent event){
    filteredListofPeople.setPredicate(substitute -> {
      String lowCat = substitute.getCategory().toLowerCase();
      String toCompair = categoryFilter.getText().toLowerCase();
      if (categoryFilter.getText() == null || categoryFilter.getText().isEmpty()){
        return true;
      }if (lowCat.contains(toCompair)){
        return true;
      }
      return false;
    });
  }
*/
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    /*
    listOfPeople.add(new Substitute("a category","university","TG","work","500"));
    listOfPeople.add(new Substitute("a category","university","TG","work","500"));
    listOfPeople.add(new Substitute("a category","university","TG","work","500"));
    listOfPeople.add(new Substitute("a category","university","TG","work","500"));
    listOfPeople.add(new Substitute("a category","university","TG","work","500"));
    listOfPeople.add(new Substitute("a category","university","TG","work","500"));

    table.setItems(sortedListOfPeople);
    sortedListOfPeople.comparatorProperty().bind(table.comparatorProperty());
    
     */
  }
}
