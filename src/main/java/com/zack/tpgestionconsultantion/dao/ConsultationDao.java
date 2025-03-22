package com.zack.tpgestionconsultantion.dao;

import com.zack.tpgestionconsultantion.entities.Consultation;
import com.zack.tpgestionconsultantion.entities.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsultationDao implements IConsultationDao {

    @Override
    public void create(Consultation entity) throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO consultations(date_consultation, description, patient_id)"+
                "VALUES(?,?,?)");
        pstm.setDate(1, entity.getDateConsultation());
        pstm.setString(2, entity.getDescription());
        pstm.setLong(3, entity.getPatient().getId());
        pstm.executeUpdate();
    }

    @Override
    public void delete(Consultation entity) throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM consultations WHERE id_consultation=?");
        pstm.setLong(1, entity.getId());
        pstm.executeUpdate();
    }

    @Override
    public void update(Consultation entity) {

    }

    @Override
    public List<Consultation> findAll() throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement(
                "SELECT c.*, p.nom, p.prenom, p.tel " +
                        "FROM consultations c " +
                        "INNER JOIN patients p ON c.patient_id = p.id_patient"
        );
        return getConsultations(pstm);
    }

    @Override
    public Consultation findById(Long id) {
        return null;
    }

    private List<Consultation> getConsultations(PreparedStatement pstm) throws SQLException {
        ResultSet rs = pstm.executeQuery();
        List<Consultation> consultations = new ArrayList<>();

        while (rs.next()) {
            Consultation consultation = new Consultation();
            consultation.setId(rs.getLong("id_consultation"));
            consultation.setDateConsultation(rs.getDate("date_consultation"));
            consultation.setDescription(rs.getString("description"));

            // Create full Patient object
            Patient patient = new Patient();
            patient.setId(rs.getLong("patient_id"));
            patient.setNom(rs.getString("nom"));
            patient.setPrenom(rs.getString("prenom"));
            patient.setTel(rs.getString("tel"));

            consultation.setPatient(patient);
            consultations.add(consultation);
        }
        return consultations;
    }
}
