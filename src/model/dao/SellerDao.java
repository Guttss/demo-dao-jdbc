package model.dao;

import model.SellerModel;

import java.util.List;

public interface SellerDao {

    void insert(SellerModel sellerModel);
    void update(SellerModel sellerModel);
    void deleteById(Integer id);
    SellerModel getById(Integer id);
    List<SellerModel> findAll();
}
