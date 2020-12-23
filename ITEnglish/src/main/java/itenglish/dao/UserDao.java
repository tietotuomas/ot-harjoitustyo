
package itenglish.dao;


import java.util.List;
import itenglish.domain.User;

public interface UserDao {
    
    void save() throws Exception;

    void create(String name, String password) throws Exception;

    User findByName(String name);

//    List<User> getAll();

}

