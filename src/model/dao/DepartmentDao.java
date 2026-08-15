package model.dao;

import model.DepartmentModel;

import java.util.List;

public interface DepartmentDao {

    void insert(DepartmentModel departmentModel);
    void update(DepartmentModel departmentModel);
    void deleteById(Integer id);
    DepartmentModel getById(Integer id);
    List<DepartmentModel> aaafindAll();
}
