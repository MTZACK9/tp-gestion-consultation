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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class ConsultationController implements Initializable {

    @FXML private DatePicker dateConsultation;
    @FXML private ComboBox<Patient> comboBoxPatient;
    @FXML private TextArea textAriaDescription;
    private ICabinetService cabinetService;
    private final ObservableList<Patient> patients = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cabinetService = new CabinetService(new PatientDao(), new ConsultationDao());
        comboBoxPatient.setItems(patients);
        patients.setAll(cabinetService.getAllPatient());
    }

    public void addConsultation() {
        Consultation consultation = new Consultation();
        consultation.setDescription(textAriaDescription.getText());
        consultation.setDateConsultation(Date.valueOf(dateConsultation.getValue()));
        consultation.setPatient(comboBoxPatient.getSelectionModel().getSelectedItem());
        cabinetService.addConsultation(consultation);
    }
}
