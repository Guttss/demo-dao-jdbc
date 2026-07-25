import model.DepartmentModel;
import model.SellerModel;
import model.dao.FactoryDao;
import model.dao.SellerDao;

import java.util.Date;

public class Main{

    public static void main(String[] args) {

        DepartmentModel dm = new DepartmentModel(1, "books");
        SellerModel sm = new SellerModel(1, "Ana", "ana@gmail.com", new Date(), 3000.0, dm);

        SellerDao sd = FactoryDao.createSellerDao();



        System.out.println(sm);
    }
}