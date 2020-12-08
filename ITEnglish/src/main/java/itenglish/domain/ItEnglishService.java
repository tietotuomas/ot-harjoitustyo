/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itenglish.domain;

import itenglish.dao.FileUserDao;
import itenglish.dao.UserDao;
import itenglish.dao.VocabularyDao;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author aaltonet
 */
public class ItEnglishService {

    private VocabularyDao vocabularyDao;
    private UserDao userDao;
    private User loggedUser;
    private Integer howManyQuestions;

    Random random;

    public ItEnglishService(VocabularyDao vocabularyDao, UserDao userDao) {
        this.random = new Random();
        this.vocabularyDao = vocabularyDao;
        this.userDao = userDao;
        this.howManyQuestions = 0;

    }

    public void setHowManyQuestions(Integer howManyQuestions) {
        this.howManyQuestions = howManyQuestions;
    }

    public Integer getHowManyQuestions() {
        return howManyQuestions;
    }

    public void setHowManyQuestionsByUser(String button, String difficulty) {
        if (button.equals("5 satunnaista")) {
            setHowManyQuestions(5);
        } else if (button.equals("10 satunnaista")) {
            setHowManyQuestions(10);

        } else {
            List<String> keys = new ArrayList<>();
            if (difficulty.equals("Aloittelija")) {
                keys = new ArrayList<>(vocabularyDao.getByDifficulty("beginner").getVocabulary().keySet());
            }
            if (difficulty.equals("Keskiverto")) {
                keys = new ArrayList<>(vocabularyDao.getByDifficulty("average").getVocabulary().keySet());
            }
            if (difficulty.equals("Mestari")) {
                keys = new ArrayList<>(vocabularyDao.getByDifficulty("master").getVocabulary().keySet());
            }
            setHowManyQuestions(keys.size());
        }
    }

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

        User user = new User(name, password);

        try {
            userDao.create(user);
        } catch (Exception e) {
            return "Käyttäjätunnuksen luonti epäonnistui kirjoitusvirheen vuoksi." + "\n";
        }

        return "Success";
    }

    public String isNameEligible(String name) {
        if (name.length() > 10) {
            return "Syöttämäsi käyttäjätunnus oli liian pitkä." + "\n";
        }
        if (name.length() < 3) {
            return "Syöttämäsi käyttäjätunnus oli liian lyhyt." + "\n";
        }
        return "OK";

    }

    public String isPasswordEligible(String password) {
        if (password.length() > 10) {
            return "Syöttämäsi salasana oli liian pitkä." + "\n";
        }
        if (password.length() < 3) {
            return "Syöttämäsi salasana oli liian lyhyt." + "\n";
        }
        return "OK";

    }

    public String logIn(String name, String password) {
        User user = userDao.findByName(name);
        if (user == null) {
            return "Antamaasi käyttäjätunnusta ei löytynyt.";
        }
        if (!(user.getPassword().equals(password))) {
            return "Antamasi salasana ei täsmännyt käyttäjätunnukseen.";
        }
        loggedUser = user;
        return "Success";

    }

    public String randomWord(String difficulty) {

        List<String> keys = new ArrayList<>();
        if (difficulty.equals("Aloittelija")) {
            keys = new ArrayList<>(vocabularyDao.getByDifficulty("beginner").getVocabulary().keySet());
        }
        if (difficulty.equals("Keskiverto")) {
            keys = new ArrayList<>(vocabularyDao.getByDifficulty("average").getVocabulary().keySet());
        }
        if (difficulty.equals("Mestari")) {
            keys = new ArrayList<>(vocabularyDao.getByDifficulty("master").getVocabulary().keySet());
        }
        return keys.get(random.nextInt(keys.size()));
    }

    public String checkUserInput(String input, String word, String difficulty) {

        if (difficulty.equals("Aloittelija")) {
            if (!(vocabularyDao.getByDifficulty("beginner").getVocabulary().get(word).equalsIgnoreCase(input.trim()))) {
                return "Vastauksesi \"" + input + "\" oli väärin!\nOikea vastaus oli " + vocabularyDao.getByDifficulty("beginner").getVocabulary().get(word) + ".";
            }
        }

        if (difficulty.equals("Keskiverto")) {
            if (!(vocabularyDao.getByDifficulty("average").getVocabulary().get(word).equalsIgnoreCase(input.trim()))) {
                return "Vastauksesi \"" + input + "\" oli väärin!\nOikea vastaus oli " + vocabularyDao.getByDifficulty("average").getVocabulary().get(word) + ".";
            }
        }

        if (difficulty.equals("Mestari")) {
            if (!(vocabularyDao.getByDifficulty("master").getVocabulary().get(word).equalsIgnoreCase(input.trim()))) {
                return "Vastauksesi \"" + input + "\" oli väärin!\nOikea vastaus oli " + vocabularyDao.getByDifficulty("master").getVocabulary().get(word) + ".";
            }

        }
        return "Vastauksesi \"" + input + "\" oli oikein!";
    }

    public String infoText(String word) {
        if (checkIfAcronym(word)) {
            return "Kirjoita auki lyhenne:";
        } else {
            return "Käännä englanniksi:";
        }

    }

    public boolean checkIfAcronym(String word) {
        return Character.isUpperCase(word.charAt(0));
    }

}
