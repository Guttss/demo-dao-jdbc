import model.DepartmentModel;
import model.dao.DepartmentDao;
import model.dao.FactoryDao;

import java.util.List;
import java.util.Scanner;

public class Main2{

    public static void main(String[] args) {

        DepartmentDao departmentDao = FactoryDao.createDepartmentDao();
        Scanner sc = new Scanner(System.in);

        System.out.println("=== TEST 1: insert department ===");
        DepartmentModel dep = new DepartmentModel(null, "Tecnologia");
        departmentDao.insert(dep);
        System.out.println("Department inserted successfully");

        System.out.println("=== TEST 2: update department ===");
        dep = new DepartmentModel(2, "Moveis");
        departmentDao.update(dep);
        System.out.printf("Department updated successfully\n");

        System.out.println("=== TEST 3: delete department ===");
        System.out.print("Id: ");
        int id = sc.nextInt();
        departmentDao.deleteById(id);
        System.out.printf("Department deleted successfully\n");

        System.out.println("=== TEST 4: getById department ===");
        dep = departmentDao.getById(4);
        System.out.println(dep);

        System.out.println("=== TEST 5: findAll department ===");
        List<DepartmentModel> list = departmentDao.findAll();
        for(DepartmentModel listDep : list){
            System.out.println(listDep);
        }
    }
}
