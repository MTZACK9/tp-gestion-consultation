package com.zack.tpgestionconsultantion.controllers;

import com.zack.tpgestionconsultantion.dao.ConsultationDao;
import com.zack.tpgestionconsultantion.dao.PatientDao;
import com.zack.tpgestionconsultantion.entities.Patient;
import com.zack.tpgestionconsultantion.service.CabinetService;
import com.zack.tpgestionconsultantion.service.ICabinetService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PatientController implements Initializable {
    @FXML
    private TextField textFieldNom;
    @FXML
    private TextField textFieldPrenom;
    @FXML
    private TextField textFieldTel;
    @FXML
    private TextField textFieldSearch;
    @FXML
    private TableView<Patient> tablePatients;
    @FXML
    private TableColumn<Patient, Long> columnId;
    @FXML
    private TableColumn<Patient, String> columnNom;
    @FXML
    private TableColumn<Patient, String> columnPrenom;
    @FXML
    private TableColumn<Patient, String> columnTel;
    @FXML
    private Label labelSuccess;
    private ICabinetService cabinetService;
    private final ObservableList<Patient> patients = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cabinetService = new CabinetService(new PatientDao(), new ConsultationDao());
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        columnPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        columnTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        tablePatients.setItems(patients);
        loadPatients();
    }

    public void addPatient() {
        Patient patient = new Patient();
        patient.setNom(textFieldNom.getText());
        patient.setPrenom(textFieldPrenom.getText());
        patient.setTel(textFieldTel.getText());

        cabinetService.addPatient(patient);
        loadPatients();
    }

    public void deletePatient() {
        Patient patient = tablePatients.getSelectionModel().getSelectedItem();
        cabinetService.deletePatient(patient);
        loadPatients();
        labelSuccess.setText("Le patient a été bien supprimé");
    }

    private void loadPatients() {
        patients.setAll(cabinetService.getAllPatient());
    }
}
