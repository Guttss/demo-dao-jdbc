import db.DB;
import model.DepartmentModel;
import model.SellerModel;
import model.dao.FactoryDao;
import model.dao.SellerDao;
import model.dao.impl.SellerDaoJDBC;

import java.util.Date;
import java.util.List;

public class Main{

    public static void main(String[] args) {

        SellerDao sellerDao = FactoryDao.createSellerDao();

        System.out.println("=== TEST 1: seller findById ===");
        SellerModel seller = sellerDao.findById(3);
        System.out.println(seller);

        System.out.println("=== TEST 2: seller findByDepartment ===");
        DepartmentModel dep = new DepartmentModel(2, null);
        List<SellerModel> list = sellerDao.findByDepartment(dep);
        for(SellerModel sel : list){
            System.out.println(sel);
        }

        System.out.println("=== TEST 3: seller findAll ===");
        list = sellerDao.findAll();
        for(SellerModel sel : list){
            System.out.println(sel);
        }

        System.out.println("=== TEST 4: seller insert ===");
        SellerModel newSeller = new SellerModel(null, "Greg", "greg@gmail.com", new Date(), 4000.0, dep);
        sellerDao.insert(newSeller);
        System.out.println("Inserted! New id = " + newSeller.getId());

        System.out.println("=== TEST 5: seller update ===");
        seller = sellerDao.findById(1);
        seller.setName("Neymar Junior");
        sellerDao.update(seller);
        System.out.println("Update completed");
    }
}