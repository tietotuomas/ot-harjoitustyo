
package itenglish.domain;

import itenglish.dao.UserDao;
import org.mindrot.jbcrypt.BCrypt;


public class UserService {

    private UserDao userDao;


    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public String createUser(String name, String password) {

        String nameEligible = isNameEligible(name);
        if (!(nameEligible.equals("OK"))) {
            return nameEligible;
        }

        String passwordEligible = isPasswordEligible(password);
        if (!(passwordEligible.equals("OK"))) {
            return passwordEligible;
        }

        if (userDao.findByName(name) != null) {
            return "Käyttäjätunnus on jo käytössä, valitse jokin toinen nimi." + "\n";
        }

        try {
            userDao.create(name, createHashedPassword(password));
        } catch (Exception e) {
            return "Käyttäjätunnuksen luonti epäonnistui kirjoitusvirheen vuoksi." + "\n";
        }
        
        return "Success";
    }
    
       public String logIn(String name, String password) {
        User user = userDao.findByName(name);
        if (user == null) {
            return "Antamaasi käyttäjätunnusta ei löytynyt.";
        }
        if (!(BCrypt.checkpw(password, user.getPassword()))) {
            return "Antamasi salasana ei täsmännyt käyttäjätunnukseen.";
        }
        return "Success";
       }

    public String isNameEligible(String name) {
        if (name.length() > 10) {
            return "Syöttämäsi käyttäjätunnus oli liian pitkä." + "\n";
        }
        if (name.length() < 3) {
            return "Syöttämäsi käyttäjätunnus oli liian lyhyt." + "\n";
        }
        return "OK";

    }

    public String isPasswordEligible(String password) {
        if (password.length() > 10) {
            return "Syöttämäsi salasana oli liian pitkä." + "\n";
        }
        if (password.length() < 3) {
            return "Syöttämäsi salasana oli liian lyhyt." + "\n";
        }
        return "OK";

    }


    public String createHashedPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
    
    

}
