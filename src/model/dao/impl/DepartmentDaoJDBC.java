package model.dao.impl;

import db.DB;
import db.DbException;
import model.DepartmentModel;
import model.dao.DepartmentDao;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        PreparedStatement ps = null;

        try{
            ps = connection.prepareStatement("update department set Name=? " +
                                            "where Id=?");

            ps.setString(1, departmentModel.getName());
            ps.setInt(2, departmentModel.getId());

            ps.executeUpdate();
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("delete from department " +
                                            "where Id=?");

            ps.setInt(1, id);

            ps.executeUpdate();
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public DepartmentModel getById(Integer id) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = connection.prepareStatement(
                    "select * from department "
                    + "where Id=?"
            );

            ps.setInt(1, id);
            rs = ps.executeQuery();

            if(rs.next()){
                DepartmentModel department = new DepartmentModel();
                department.setId(rs.getInt("Id"));
                department.setName(rs.getString("Name"));
                return department;
            }
            return null;

        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<DepartmentModel> findAll() {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            ps = connection.prepareStatement(
                    "select * from department"
            );
            rs = ps.executeQuery();

            List<DepartmentModel> departments = new ArrayList<>();
            while(rs.next()){
                DepartmentModel department = new DepartmentModel();
                department.setId(rs.getInt("Id"));
                department.setName(rs.getString("Name"));
                departments.add(department);
            }
            return departments;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally {
            DB.closeResultSet(rs);
            DB.closeStatement(ps);
        }


    }
}
