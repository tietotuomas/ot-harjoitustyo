package itenglish.domain;

/**
 * Sovelluksen käyttäjää edustava luokka
 *
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

}
