import db.DB;
import model.DepartmentModel;
import model.SellerModel;
import model.dao.FactoryDao;
import model.dao.SellerDao;
import model.dao.impl.SellerDaoJDBC;

import java.util.Date;

public class Main{

    public static void main(String[] args) {

        SellerDao sellerDao = FactoryDao.createSellerDao();

        System.out.println("=== TEST 1: seller findById ===");

        SellerModel seller = sellerDao.findById(3);

        System.out.println(seller);

    }
}