package com.zack.tpgestionconsultantion.dao;

import com.zack.tpgestionconsultantion.entities.Consultation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
    public void delete(Consultation entity) {

    }

    @Override
    public void update(Consultation entity) {

    }

    @Override
    public List<Consultation> findAll() {
        return List.of();
    }

    @Override
    public Consultation findById(Long id) {
        return null;
    }
}
