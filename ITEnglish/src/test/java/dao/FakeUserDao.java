/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import itenglish.dao.UserDao;
import itenglish.domain.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aaltonet
 */
public class FakeUserDao implements UserDao {

    private List<User> users;

    public FakeUserDao() {
        users = new ArrayList<>();
        users.add(new User("Kameli", "kameli"));
    }

    @Override
    public void save() {
        
    }

    @Override
    public User findByName(String name) {

        for (User user : users) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        return null;

    }

    @Override
    public void create(String name, String password) {
        users.add(new User(name, password));
    }

}
