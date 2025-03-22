package com.zack.tpgestionconsultantion.controllers;

import com.zack.tpgestionconsultantion.dao.ConsultationDao;
import com.zack.tpgestionconsultantion.dao.PatientDao;
import com.zack.tpgestionconsultantion.entities.Consultation;
import com.zack.tpgestionconsultantion.entities.Patient;
import com.zack.tpgestionconsultantion.service.CabinetService;
import com.zack.tpgestionconsultantion.service.ICabinetService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class ConsultationController implements Initializable {

    @FXML private DatePicker dateConsultation;
    @FXML private ComboBox<Patient> comboBoxPatient;
    @FXML private TextArea textAriaDescription;
    @FXML
    private TableColumn<Consultation, Long> columnId;
    @FXML
    private TableColumn<Consultation, Date> columnDateConsultation;
    @FXML
    private TableColumn<Consultation, String> columnDescription;
    @FXML
    private TableColumn<Consultation, String> columnPatient;
    @FXML
    private TableView<Consultation> tableConsultation;
    private ICabinetService cabinetService;
    private final ObservableList<Patient> patients = FXCollections.observableArrayList();
    private final ObservableList<Consultation> consultations = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cabinetService = new CabinetService(new PatientDao(), new ConsultationDao());
        comboBoxPatient.setItems(patients);
        patients.setAll(cabinetService.getAllPatient());

        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnDateConsultation.setCellValueFactory(new PropertyValueFactory<>("dateConsultation"));
        columnDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        columnPatient.setCellValueFactory(new PropertyValueFactory<>("patient"));


        tableConsultation.setItems(consultations);
        loadConsultations();
    }

    public void addConsultation() {
        Consultation consultation = new Consultation();
        consultation.setDescription(textAriaDescription.getText());
        consultation.setDateConsultation(Date.valueOf(dateConsultation.getValue()));
        consultation.setPatient(comboBoxPatient.getSelectionModel().getSelectedItem());
        cabinetService.addConsultation(consultation);
        loadConsultations();
        clearFields();
    }

    public void deleteConsultation() {
        Consultation selectedConsultation = tableConsultation.getSelectionModel().getSelectedItem();
        if (selectedConsultation != null) {
            cabinetService.deleteConsultation(selectedConsultation);
            loadConsultations();
        }
    }

    private void loadConsultations() {
        consultations.setAll(cabinetService.getAllConsultation());
    }

    private void clearFields() {
        dateConsultation.setValue(null);
        textAriaDescription.clear();
        comboBoxPatient.getSelectionModel().clearSelection();
    }
}