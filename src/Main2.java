import model.DepartmentModel;
import model.dao.DepartmentDao;
import model.dao.FactoryDao;

import java.util.Scanner;

public class Main2{

    public static void main(String[] args){

        DepartmentDao departmentDao = FactoryDao.createDepartmentDao();
        Scanner sc = new Scanner(System.in);

        System.out.println("=== TEST 1: insert department ===");
        DepartmentModel dep = new DepartmentModel(null, "Tecnologia");
        departmentDao.insert(dep);
        System.out.println("Department inserted successfully");

    }
}
