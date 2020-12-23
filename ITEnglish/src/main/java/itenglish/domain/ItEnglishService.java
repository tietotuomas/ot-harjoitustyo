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
    private Integer howManyQuestions;
    private ArrayList<String> currentSet;
    private StatsService statService;

    Random random;

    public ItEnglishService(VocabularyDao vocabularyDao, StatsService statsService) {
        this.random = new Random();
        this.vocabularyDao = vocabularyDao;
        this.statService = statsService;

    }

    public void setHowManyQuestions(Integer howManyQuestions) {
        this.howManyQuestions = howManyQuestions;
    }

    public Integer getHowManyQuestions() {
        return howManyQuestions;
    }
    
    public void questionAnswered() {
        this.howManyQuestions--;
    }

    public void createNewSet(String difficulty) {
        this.currentSet = new ArrayList<>();
        if (difficulty.equals("Aloittelija")) {
            currentSet = new ArrayList<>(vocabularyDao.getByDifficulty("beginner").getVocabulary().keySet());
        }
        if (difficulty.equals("Keskiverto")) {
            currentSet = new ArrayList<>(vocabularyDao.getByDifficulty("average").getVocabulary().keySet());
        }
        if (difficulty.equals("Mestari")) {
            currentSet = new ArrayList<>(vocabularyDao.getByDifficulty("master").getVocabulary().keySet());
        }

    }

    public void setHowManyQuestionsByUser(String button) {
        if (button.equals("5 satunnaista")) {
            setHowManyQuestions(5);
        } else if (button.equals("10 satunnaista")) {
            setHowManyQuestions(10);
        } else {
            setHowManyQuestions(currentSet.size());
        }
        statService.setTotalQuestions(howManyQuestions);
    }
    

    public String randomWord(String difficulty) {

        String randomWord = currentSet.get(random.nextInt(currentSet.size()));
        currentSet.remove(randomWord);
        return randomWord;
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
        statService.addCorrectAnswer();
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
    
    public int totalWords(String difficulty) {
        return vocabularyDao.getByDifficulty(difficulty).getVocabulary().size();
    }

}
