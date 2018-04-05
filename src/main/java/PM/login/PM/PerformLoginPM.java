package PM.login.PM;

import PM.login.model.UserType;
import PM.login.model.User;
import PM.login.DAO.UserDAO;

/**
 *
 * @author andreendo
 */
public class PerformLoginPM {
    String login;
    String password;
    UserDAO userDao;

    public PerformLoginPM() {
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
        System.out.println("PM.login.EfetuarLoginPM.clear()");
    }
    
    public PagePM pressLogin() throws Exception {
        login = login.trim();
        password = password.trim();
        if(login.isEmpty() || password.isEmpty())
            throw new Exception("Empty fields");
        
        User user = userDao.getByName(login);
        if(user == null)
            throw new Exception("Inexistent username");
        
        if(! user.getPassword().equals(password)){ 
            user.erroSenha();
            System.out.println("tent"+user.getTentativas());
            if (user.getTentativas() == 1){
                blockUser(login);
                throw new Exception("Blocked user");
            }
            throw new Exception("Wrong password");
        
        }
        if(list_block(login))
            throw new Exception("Blocked user");

        PagePM pagePM = null;
        if(user.getType() == UserType.ADMIN)
            pagePM = new AdminMainPagePM();
        else
            pagePM = new NormalUserMainPagePM();
        
        pagePM.setLoggedUser(user);
        
        return pagePM;
    }

    void setUserDao(UserDAO userDao) {
        this.userDao = userDao;
    }
    String nomes[] = {"userBlocked"};

    public boolean list_block(String nome){
        for(int i=0; i < nomes.length; i++){
            if(nome.equals(nomes[i])){
                return true;
            }
        }  
        
        return false;
    }
    
    public void blockUser(String nome){
        nomes[nomes.length] = nome;
    }
}
