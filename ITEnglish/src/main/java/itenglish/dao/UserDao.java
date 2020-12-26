package itenglish.dao;

import itenglish.domain.User;

/**
 * Rajapinta käyttäjätietojen käsittelyyn.
 */
public interface UserDao {

    /**
     * Metodi tallettaa käyttäjätiedot pysyväistallennukseen.
     */
    void save() throws Exception;

    /**
     * Metodi luo parametriensa perusteella uuden käyttäjän ja tallentaa sen.
     *
     * @param käyttäjätunnus
     * @param salasana
     */
    void create(String name, String password) throws Exception;

    /**
     * Palauttaa nimen perusteella user-tyyppisen olion tai null, jos
     * users-listasta ei löydy käyttäjää parametrin mukaisella nimellä.
     *
     * @param käyttäjätunnus
     * @return Palauttaa user-tyyppisen olion tai null, jos käyttäjäkannasta
     * löytyy
     */
    User findByName(String name);

}
