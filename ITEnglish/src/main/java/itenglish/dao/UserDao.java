package itenglish.dao;

import itenglish.domain.User;

/**
 * Rajapinta käyttäjätietojen käsittelyyn.
 */

public interface UserDao {

    void save() throws Exception;

    void create(String name, String password) throws Exception;

    User findByName(String name);

}
