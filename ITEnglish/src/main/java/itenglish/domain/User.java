/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itenglish.domain;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author aaltonet
 */
public class User {
    private String name;
    private String password;
    private int beginner;
    private int average;
    private int master;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.beginner = 0;
        this.average = 0;
        this.master = 0;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
    

    public int getAverage() {
        return average;
    }

    public int getBeginner() {
        return beginner;
    }

    public int getMaster() {
        return master;
    }

    public void setAverage(int average) {
        this.average = average;
    }

    public void setBeginner(int beginner) {
        this.beginner = beginner;
    }

    public void setMaster(int master) {
        this.master = master;
    }




    
    

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User)) {
            return false;
        }
        
        User other = (User) obj;
        return name.equals(other.name);
    }
    
}