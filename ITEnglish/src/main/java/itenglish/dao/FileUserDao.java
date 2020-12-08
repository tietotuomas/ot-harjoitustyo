/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itenglish.dao;

import itenglish.domain.User;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author aaltonet
 */
public class FileUserDao implements UserDao {

    private List<User> users;
    private String file;

    public FileUserDao(String file) throws Exception {
        users = new ArrayList<>();
        this.file = file;
        loadUsers(file);

    }

    private void loadUsers(String file) {
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
            System.out.println("Tiedoston lukeminen ei onnistunut: " + e.getMessage());

        }
    }

    private void save() throws Exception {
        try (FileWriter writer = new FileWriter(file)) {
            for (User user : users) {
                writer.write(user.getName() + "," + user.getPassword() + ","
                        + user.getBeginner() + "," + user.getAverage() + "," + user.getMaster() + "\n");
            }
        }
    }

    @Override
    public List<User> getAll() {
        System.out.println(users);
        return users;
    }

    @Override
    public User create(User user) throws Exception {
        users.add(user);
        save();
        return user;
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
