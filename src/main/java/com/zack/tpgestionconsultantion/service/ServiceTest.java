package com.zack.tpgestionconsultantion.service;

import com.zack.tpgestionconsultantion.dao.ConsultationDao;
import com.zack.tpgestionconsultantion.dao.PatientDao;
import com.zack.tpgestionconsultantion.entities.Patient;

import java.util.List;

public class ServiceTest {
    public static void main(String[] args) {
        ICabinetService service = new CabinetService(new PatientDao(), new ConsultationDao());
        /*Patient patient = new Patient();
        patient.setNom("Another");
        patient.setPrenom("Patient");
        patient.setTel("123456789");
        service.addPatient(patient);*/

        /*List<Patient> patients = service.getAllPatient();
        patients.forEach(System.out::println);*/

        /*Patient patient = service.getPatientById(1L);
        System.out.println(patient);*/

        /*Patient patient = service.getPatientById(1L);
        patient.setTel("1456779");
        service.updatePatient(patient);*/



    }
}
