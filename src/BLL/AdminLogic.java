package BLL;

import BE.Admin;
import DAL.AdminDAO;

public class AdminLogic {
    AdminDAO adminDAO=new AdminDAO();

    public void getAdmin(Admin admin){
        adminDAO.getAdmin();
    }
}
