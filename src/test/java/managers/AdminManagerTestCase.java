package managers;

import dao.AdminDAO;
import entities.Admin;
import exceptions.UserNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@RunWith(MockitoJUnitRunner.class)
public class AdminManagerTestCase {

    @Mock
    private AdminDAO adminDAO=new AdminDAO();

    @InjectMocks
    AdminManager adminManager=AdminManager.getInstance();


    @Test
    public void shouldGetAdmin(){
        Admin element=new Admin(1,"Kleinsberg","Coco","coco.kleinsberg@gmail.com","test1");
        Mockito.when(adminDAO.getAdmin(element.getMail())).thenReturn(element);
        Mockito.when(adminDAO.getAdmin(element.getId())).thenReturn(element);

        //WHEN
        Admin resultMail=adminManager.getAdmin("coco.kleinsberg@gmail.com");
        Admin resultId=adminManager.getAdmin(1);

        //THEN
        assertThat(resultMail).isEqualTo(element);
        assertThat(resultId).isEqualTo(element);
    }

    @Test(expected = UserNotFoundException.class)
    public void shouldNotGetAdminByIdAndThrowException() throws UserNotFoundException{
        //GIVEN

        //WHEN
        adminManager.getAdmin(99);

        //THEN
        fail("");
    }

    @Test(expected = UserNotFoundException.class)
    public void shouldNotGetAdminByMailAndThrowException() throws UserNotFoundException{
        //GIVEN

        //WHEN
        adminManager.getAdmin("n'importe quoi");

        //THEN
        fail("");
    }

    @Test
    public void shouldListAdmin(){
        //WHEN
        adminManager.listAdmin();
        //THEN
        Mockito.verify(adminDAO,Mockito.atLeastOnce()).listAdmin();

    }

}
