package PM.login.PM;

import PM.login.model.User;
import PM.login.DAO.UserDAO;
import java.util.ArrayList;

/**
 *
 * @author andreendo
 */
public class EfetuarLoginPM {
    String login;
    String password;
    UserDAO userDao;

    public EfetuarLoginPM() {
        login = "";
        password = "";
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }    
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getPassword() {
        return password;
    }    

    public void clear() {
        login = "";
        password = "";
    }
    public void list_block(String nome){
        String nomes[] = {"Gabriel", "Luiz", "Andre"};
        
        for(int i=0; i < 3; i++){
            if(nome.equals(nomes[i])){
                System.out.println("Usuario Bloqueado");
            }
        }  
    }
    
    public AdminMainPagePM pressLogin() throws Exception {
        login = login.trim();
        password = password.trim();
        if(login.isEmpty() || password.isEmpty())
            throw new Exception("Empty fields");
        
        User user = userDao.getByName(login);
        if(user == null)
            throw new Exception("Inexistent username");
        
        if(! user.getPassword().equals(password))
            throw new Exception("Wrong password");
        
        AdminMainPagePM adminMainPagePM = new AdminMainPagePM();
        adminMainPagePM.setLoggedUser(user);
        
        return adminMainPagePM;
    }

    void setUserDao(UserDAO userDao) {
        this.userDao = userDao;
    }
}
