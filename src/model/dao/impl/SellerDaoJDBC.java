package model.dao.impl;

import db.DB;
import db.DbException;
import model.DepartmentModel;
import model.SellerModel;
import model.dao.SellerDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDaoJDBC implements SellerDao {

    private Connection connection;

    public SellerDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(SellerModel sellerModel) {
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement("INSERT INTO seller "
            + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
            + "VALUES "
            + "(?, ?, ?, ?, ?)",
            Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, sellerModel.getName());
            ps.setString(2, sellerModel.getEmail());
            ps.setDate(3, new java.sql.Date(sellerModel.getBirthDate().getTime()));
            ps.setDouble(4, sellerModel.getBaseSalary());
            ps.setInt(5, sellerModel.getDm().getId());

            int rowsAffected = ps.executeUpdate();

            if(rowsAffected > 0){
                ResultSet rs = ps.getGeneratedKeys();
                if(rs.next()){
                    int id = rs.getInt(1);
                    sellerModel.setId(id);
                    DB.closeResultSet(rs);
                }
            }
            else{
                throw new DbException("Unexpected error. No rows affected!");
            }
        }
        catch(SQLException e){
            throw new DbException(e.getMessage());
        }
        finally{
            DB.closeStatement(ps);
        }
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
                DepartmentModel depModel = instantiateDepartment(rs);
                SellerModel sellerModel = instantiateSeller(rs, depModel);
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

    private SellerModel instantiateSeller(ResultSet rs, DepartmentModel depModel) throws SQLException {
        SellerModel sellerModel = new SellerModel();
        sellerModel.setId(rs.getInt("Id"));
        sellerModel.setName(rs.getString("Name"));
        sellerModel.setEmail(rs.getString("Email"));
        sellerModel.setBirthDate(rs.getDate("BirthDate"));
        sellerModel.setBaseSalary(rs.getDouble("BaseSalary"));
        sellerModel.setDm(depModel);
        return sellerModel;
    }

    private DepartmentModel instantiateDepartment(ResultSet rs) throws SQLException {
        DepartmentModel depModel = new DepartmentModel();
        depModel.setId(rs.getInt("DepartmentId"));
        depModel.setName(rs.getString("DepName"));
        return depModel;
    }


    @Override
    public List<SellerModel> findByDepartment(DepartmentModel departmentModel) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            ps = connection.prepareStatement(
                    "SELECT seller.*,department.Name as DepName \n" +
                            "FROM seller INNER JOIN department \n" +
                            "ON seller.DepartmentId = department.Id \n" +
                            "WHERE DepartmentId = ? \n" +
                            "ORDER BY Name ");

            ps.setInt(1, departmentModel.getId());
            rs = ps.executeQuery();

            List<SellerModel> list = new ArrayList<>();
            Map<Integer, DepartmentModel> map = new HashMap<>();

            while(rs.next()){

                DepartmentModel depModel = map.get(rs.getInt("DepartmentId"));

                if(depModel == null){
                    depModel = instantiateDepartment(rs);
                    map.put(rs.getInt("DepartmentId"), depModel);
                }

                SellerModel sellerModel = instantiateSeller(rs, depModel);
                list.add(sellerModel);
            }
            return list;
        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }finally{
            DB.closeResultSet(rs);
            DB.closeStatement(ps);
        }
    }

    @Override
    public List<SellerModel> findAll() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            ps = connection.prepareStatement(
                    "SELECT seller.*,department.Name as DepName \n" +
                            "FROM seller INNER JOIN department \n" +
                            "ON seller.DepartmentId = department.Id \n" +
                            "ORDER BY Name ");

            rs = ps.executeQuery();

            List<SellerModel> list = new ArrayList<>();
            Map<Integer, DepartmentModel> map = new HashMap<>();

            while(rs.next()){

                DepartmentModel depModel = map.get(rs.getInt("DepartmentId"));

                if(depModel == null){
                    depModel = instantiateDepartment(rs);
                    map.put(rs.getInt("DepartmentId"), depModel);
                }

                SellerModel sellerModel = instantiateSeller(rs, depModel);
                list.add(sellerModel);
            }
            return list;
        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }finally{
            DB.closeResultSet(rs);
            DB.closeStatement(ps);
        }
    }
}
