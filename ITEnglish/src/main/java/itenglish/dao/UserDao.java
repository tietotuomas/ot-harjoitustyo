/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itenglish.dao;

/**
 *
 * @author aaltonet
 */
import java.util.List;
import itenglish.domain.User;

public interface UserDao {
    
    void save() throws Exception;

    User create(User user) throws Exception;

    User findByName(String name);

    List<User> getAll();

}

