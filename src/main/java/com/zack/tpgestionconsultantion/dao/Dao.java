package com.zack.tpgestionconsultantion.dao;

import java.sql.SQLException;
import java.util.List;

public interface Dao<E, U>{
    void create(E entity) throws SQLException;
    void delete(E entity) throws SQLException;
    void update(E entity) throws SQLException;
    List<E> findAll() throws SQLException;
    E findById(U id) throws SQLException;
}
