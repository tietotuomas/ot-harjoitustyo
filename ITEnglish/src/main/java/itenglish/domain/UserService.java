package itenglish.domain;

import itenglish.dao.UserDao;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Sovelluslogiikan luokka, joka vastaa käyttäjien kirjautumis- ja
 * rekiteröitymistoiminnoista.
 */
public class UserService {

    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * Metodi validoi syötteet ja luo uuden käyttäjätunnuksen.
     * <p>
     * Metodi tarkista apumetodien isNameEligible ja isPasswordEligible avulla,
     * ovatko syötteet ohjeistuksen mukaisia. Lisäksi metodi tarkistaa
     * userDao-luokan avulla, ettei sovelluksen käyttäjäkannasta löydy
     * samannimistä käyttäjää. Jos kaikki on kunnossa, uusi käyttäjä luodaan
     * edelleen userDaon avulla. Samalla salasana suojataan tallennusta varten
     * BCryptillä.
     *
     * @param käyttäjätunnus
     * @param salasana
     * @return Palauttaa merkkijono-muotoisen viestin mahdollisesta epäkelvosta
     * syötteestä tai virheestä.
     */
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

    /**
     * Metodi kirjaa käyttäjän sisään, jos tiedot ovat oikeat. Muuten palauttaa
     * virheviestin.
     *
     * @param käyttäjätunnus
     * @param salasana
     * @return Palauttaa merkkijono-muotoisen viestin mahdollisesta epäkelvosta
     * syötteestä tai virheestä.
     */
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

    /**
     * Metodi tarkistaa, että käyttäjätunnuksen pituus on 3-10 merkkiä.
     *
     * @param käyttäjätunnus
     * @return Palauttaa merkkijono-muotoisen viestin mahdollisesta epäkelvosta
     * käyttäjätunnuksesta.
     */
    public String isNameEligible(String name) {
        if (name.length() > 10) {
            return "Syöttämäsi käyttäjätunnus oli liian pitkä." + "\n";
        }
        if (name.length() < 3) {
            return "Syöttämäsi käyttäjätunnus oli liian lyhyt." + "\n";
        }
        return "OK";

    }

    /**
     * Metodi tarkistaa, että salasanan pituus on 3-10 merkkiä.
     *
     * @param salasana
     * @return Palauttaa merkkijono-muotoisen viestin mahdollisesta epäkelvosta
     * salasanasta.
     */
    public String isPasswordEligible(String password) {
        if (password.length() > 10) {
            return "Syöttämäsi salasana oli liian pitkä." + "\n";
        }
        if (password.length() < 3) {
            return "Syöttämäsi salasana oli liian lyhyt." + "\n";
        }
        return "OK";

    }

    /**
     * Metodi luo salasanasta salakirjoitetun version BCryptin avullla.
     *
     * @param salasana
     * @return Palauttaa salakirjoitetun salasanan
     */
    public String createHashedPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

}
