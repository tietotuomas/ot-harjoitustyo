/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import dao.FakeUserDao;
import itenglish.domain.UserService;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aaltonet
 */
public class UserServiceTest {

    private FakeUserDao userDao;
    private UserService service;

    @Before
    public void setUp() {
        userDao = new FakeUserDao();
        service = new UserService(userDao);
    }

    @Test
    public void logInDoesntWorkWithNonExistingUser() {
        assertEquals("Antamaasi käyttäjätunnusta ei löytynyt.", this.service.logIn("Haavisto", "Caruna"));
    }

    @Test
    public void createUserDoesntWorkWithExistingUser() {
        assertEquals("Käyttäjätunnus on jo käytössä, valitse jokin toinen nimi." + "\n", this.service.createUser("Kameli", "Caruna"));
    }

    @Test
    public void isPassWordEligibleReturnsOkIfCorrectLength() {
        assertEquals("OK", this.service.isPasswordEligible("password"));
    }

    @Test
    public void isPassWordEligibleReturnsCorrectStringIfPasswordTooShort() {
        assertEquals("Syöttämäsi salasana oli liian lyhyt." + "\n", this.service.isPasswordEligible("p"));
    }

    @Test
    public void isPassWordEligibleReturnsCorrectStringIfPasswordTooLong() {
        assertEquals("Syöttämäsi salasana oli liian pitkä." + "\n", this.service.isPasswordEligible("passwordpassword"));
    }

    @Test
    public void isNameEligibleReturnsOkIfCorrectLength() {
        assertEquals("OK", this.service.isNameEligible("name"));
    }

    @Test
    public void isNameEligibleReturnsCorrectStringIfNameTooShort() {
        assertEquals("Syöttämäsi käyttäjätunnus oli liian lyhyt." + "\n", this.service.isNameEligible("n"));
    }

    @Test
    public void isNameEligibleReturnsCorrectStringIfNameTooLong() {
        assertEquals("Syöttämäsi käyttäjätunnus oli liian pitkä." + "\n", this.service.isNameEligible("namenamename"));
    }
    
    @Test
    public void createHashedPasswordReturnsHashedPassword() {
        assertFalse("salasana".equals(service.createHashedPassword("salasana")));
        assertEquals(60, service.createHashedPassword("salasana").length());
    }

//    public String createUser(String name, String password) {
//
//        String nameEligible = isNameEligible(name);
//        if (!(nameEligible.equals("OK"))) {
//            return nameEligible;
//        }
//
//        String passwordEligible = isPasswordEligible(password);
//        if (!(passwordEligible.equals("OK"))) {
//            return passwordEligible;
//        }
//
//        if (userDao.findByName(name) != null) {
//            return "Käyttäjätunnus on jo käytössä, valitse jokin toinen nimi." + "\n";
//        }
//
//        try {
//            userDao.create(name, createHashedPassword(password));
//        } catch (Exception e) {
//            return "Käyttäjätunnuksen luonti epäonnistui kirjoitusvirheen vuoksi." + "\n";
//        }
//
//        return "Success";
//    }
//
//    public String logIn(String name, String password) {
//        User user = userDao.findByName(name);
//        if (user == null) {
//            return "Antamaasi käyttäjätunnusta ei löytynyt.";
//        }
//        if (!(BCrypt.checkpw(password, user.getPassword()))) {
//            return "Antamasi salasana ei täsmännyt käyttäjätunnukseen.";
//        }
//        return "Success";
//    }
//
//    public String isNameEligible(String name) {
//        if (name.length() > 10) {
//            return "Syöttämäsi käyttäjätunnus oli liian pitkä." + "\n";
//        }
//        if (name.length() < 3) {
//            return "Syöttämäsi käyttäjätunnus oli liian lyhyt." + "\n";
//        }
//        return "OK";
//
//    }
//
//    public String isPasswordEligible(String password) {
//        if (password.length() > 10) {
//            return "Syöttämäsi salasana oli liian pitkä." + "\n";
//        }
//        if (password.length() < 3) {
//            return "Syöttämäsi salasana oli liian lyhyt." + "\n";
//        }
//        return "OK";
//
//    }
//
//    public String createHashedPassword(String password) {
//        return BCrypt.hashpw(password, BCrypt.gensalt());
//    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
