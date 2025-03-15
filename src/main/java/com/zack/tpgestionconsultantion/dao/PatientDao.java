package com.zack.tpgestionconsultantion.dao;

import com.zack.tpgestionconsultantion.entities.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientDao implements IPatientDao{
    @Override
    public void create(Patient entity) throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO patients(nom, prenom, tel)"+
        "VALUES(?,?,?)");
        pstm.setString(1, entity.getNom());
        pstm.setString(2, entity.getPrenom());
        pstm.setString(3, entity.getTel());
        pstm.executeUpdate();
    }

    @Override
    public void delete(Patient entity) throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM patients WHERE id_patient=?");
        pstm.setLong(1, entity.getId());
        pstm.executeUpdate();
    }

    @Override
    public void update(Patient entity) throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE patients SET nom=?, prenom=?, tel=? WHERE id_patient=?");
        pstm.setString(1, entity.getNom());
        pstm.setString(2, entity.getPrenom());
        pstm.setString(3, entity.getTel());
        pstm.setLong(4, entity.getId());
        pstm.executeUpdate();
    }

    @Override
    public List<Patient> findAll() throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM patients");
        return getPatients(pstm);
    }

    @Override
    public Patient findById(Long id) throws SQLException {

        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM patients WHERE id_patient=?");
        pstm.setLong(1, id);
        ResultSet rs = pstm.executeQuery();
        Patient patient = new Patient();
        if (rs.next()) {
            patient.setId(rs.getLong("id_patient"));
            patient.setNom(rs.getString("nom"));
            patient.setPrenom(rs.getString("prenom"));
            patient.setTel(rs.getString("tel"));
        }
        return patient;
    }

    @Override
    public List<Patient> searchByQuery(String query) throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM patients WHERE nom LIKE ? OR prenom LIKE ?");
        pstm.setString(1, "%" + query + "%");
        pstm.setString(2, "%" + query + "%");
        return getPatients(pstm);
    }

    private List<Patient> getPatients(PreparedStatement pstm) throws SQLException {
        ResultSet rs = pstm.executeQuery();
        List<Patient> patients = new ArrayList<>();
        while (rs.next()) {
            Patient patient = new Patient();
            patient.setId(rs.getLong("id_patient"));
            patient.setNom(rs.getString("nom"));
            patient.setPrenom(rs.getString("prenom"));
            patient.setTel(rs.getString("tel"));
            patients.add(patient);
        }
        return patients;
    }
}
