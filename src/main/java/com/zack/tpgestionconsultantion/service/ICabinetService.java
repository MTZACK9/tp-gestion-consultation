package com.zack.tpgestionconsultantion.service;

import com.zack.tpgestionconsultantion.entities.Consultation;
import com.zack.tpgestionconsultantion.entities.Patient;

import java.sql.SQLException;
import java.util.List;

public interface ICabinetService {
    void addPatient(Patient patient);
    void updatePatient(Patient patient);
    void deletePatient(Patient patient);
    List<Patient> getAllPatient();
    Patient getPatientById(long id);
    List<Patient> searchPatientsByQuery(String Query) throws SQLException;

    void addConsultation(Consultation consultation);
    void updateConsultation(Consultation consultation);
    void deleteConsultation(Consultation consultation);
    List<Consultation> getAllConsultation();
    Consultation getConsultationById(long id);
}
