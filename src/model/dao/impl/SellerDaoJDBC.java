package model.dao.impl;

import db.DB;
import db.DbException;
import model.DepartmentModel;
import model.SellerModel;
import model.dao.SellerDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SellerDaoJDBC implements SellerDao {

    private Connection connection;

    public SellerDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(SellerModel sellerModel) {

    }

    @Override
    public void update(SellerModel sellerModel) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public SellerModel findById(Integer id) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            ps = connection.prepareStatement(
                    "select seller.*, department.Name as DepName "
                    + "from seller inner join department "
                    + "on seller.DepartmentId = department.Id "
                    + "where seller.Id = ?");

            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
                DepartmentModel depModel = new DepartmentModel();
                depModel.setId(rs.getInt("DepartmentId"));
                depModel.setName(rs.getString("DepName"));

                SellerModel sellerModel = new SellerModel();
                sellerModel.setId(rs.getInt("Id"));
                sellerModel.setName(rs.getString("Name"));
                sellerModel.setEmail(rs.getString("Email"));
                sellerModel.setBirthDate(rs.getDate("BirthDate"));
                sellerModel.setBaseSalary(rs.getDouble("BaseSalary"));
                sellerModel.setDm(depModel);
                return sellerModel;
            }
            return null;
        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }finally{
            DB.closeResultSet(rs);
            DB.closeStatement(ps);
        }
    }

    @Override
    public List<SellerModel> findAll() {
        return List.of();
    }
}
