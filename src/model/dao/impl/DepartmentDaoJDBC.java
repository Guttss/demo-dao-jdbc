package model.dao.impl;

import db.DB;
import db.DbException;
import model.DepartmentModel;
import model.dao.DepartmentDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {

    private Connection connection;

    public DepartmentDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(DepartmentModel departmentModel) {
        PreparedStatement ps = null;

        try{
            ps = connection.prepareStatement("insert into department "
                                            + "(Name) "
                                            + "values (?);"
            );

            ps.setString(1, departmentModel.getName());

            ps.executeUpdate();

        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(ps);
        }

    }

    @Override
    public void update(DepartmentModel departmentModel) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public DepartmentModel getById(Integer id) {
        return null;
    }

    @Override
    public List<DepartmentModel> findAll() {
        return List.of();
    }
}
