package managers;

import dao.AdminDAO;
import entities.Admin;
import exceptions.UserNotFoundException;

import java.util.List;

public class AdminManager {
    private static AdminManager ourInstance = new AdminManager();

    public static AdminManager getInstance() {
        return ourInstance;
    }

    private AdminManager() {
    }

    private AdminDAO adminDAO = new AdminDAO();

    public Admin getAdmin(Integer id){
        if (adminDAO.getAdmin(id) == null){
            throw new UserNotFoundException("Admin not fount");
        }
        return adminDAO.getAdmin(id);
    }

    public Admin getAdmin(String mail){
        if (adminDAO.getAdmin(mail) == null){
            throw new UserNotFoundException("Admin not fount");
        }
        return adminDAO.getAdmin(mail);
    }

    public List<Admin> listAdmin(){return adminDAO.listAdmin();}

}
