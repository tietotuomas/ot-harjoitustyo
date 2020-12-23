
package itenglish.dao;

import itenglish.domain.User;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class FileUserDao implements UserDao {

    private List<User> users;
    private String file;

    public FileUserDao(String file) throws Exception {
        users = new ArrayList<>();
        this.file = file;
        loadUsers(file);

    }

    private void loadUsers(String file) throws Exception{
        try {
            Scanner fileReader = new Scanner(new File(file));

            while (fileReader.hasNextLine()) {
                String[] parts = fileReader.nextLine().split(",");
                User user = new User(parts[0], parts[1]);
                user.setBeginner(Integer.valueOf(parts[2]));
                user.setAverage(Integer.valueOf(parts[3]));
                user.setMaster(Integer.valueOf(parts[4]));
                users.add(user);
            }
        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();

        }
    }
    
    @Override
    public void save() throws Exception {
        try (FileWriter writer = new FileWriter(file)) {
            for (User user : users) {
                writer.write(user.getName() + "," + user.getPassword() + ","
                        + user.getBeginner() + "," + user.getAverage() + "," + user.getMaster() + "\n");
            }
        } catch (Exception e) {
            System.out.println("Tiedostoon tallentaminen ei onnistunut: " + e.getMessage());

        } 
    }

//    @Override
//    public List<User> getAll() {
//        System.out.println(users);
//        return users;
//    }

    @Override
    public void create(String username, String password) throws Exception {
        users.add(new User(username, password));
        save();
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

}
