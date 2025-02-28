package com.zack.tpgestionconsultantion.service;

import com.zack.tpgestionconsultantion.dao.IConsultationDao;
import com.zack.tpgestionconsultantion.dao.IPatientDao;
import com.zack.tpgestionconsultantion.entities.Consultation;
import com.zack.tpgestionconsultantion.entities.Patient;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CabinetService implements ICabinetService {

    private final IPatientDao patientDao;
    private IConsultationDao consultationDao;

    public CabinetService(IPatientDao patientDao, IConsultationDao consultationDao) {
        this.patientDao = patientDao;
        this.consultationDao = consultationDao;
    }

    @Override
    public void addPatient(Patient patient) {
        try {
            patientDao.create(patient);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updatePatient(Patient patient) {
        try {
            patientDao.update(patient);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deletePatient(Patient patient) {
        try {
            patientDao.delete(patient);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Patient> getAllPatient() {
        List<Patient> patients;
        try {
            patients = patientDao.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return patients;
    }

    @Override
    public Patient getPatientById(long id) {
        Patient patient;
        try {
            patient = patientDao.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return patient;
    }

    @Override
    public void addConsultation(Consultation consultation) {

    }

    @Override
    public void updateConsultation(Consultation consultation) {

    }

    @Override
    public void deleteConsultation(Consultation consultation) {

    }

    @Override
    public List<Consultation> getAllConsultation() {
        return List.of();
    }

    @Override
    public Consultation getConsultationById(long id) {
        return null;
    }
}
