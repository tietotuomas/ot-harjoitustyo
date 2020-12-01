/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itenglish.domain;

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
    Random random;

    public ItEnglishService(VocabularyDao vocabularyDao) {
        this.random = new Random();
        this.vocabularyDao = vocabularyDao;

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
