/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itenglish.domain;

import itenglish.dao.VocabularyDao;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author aaltonet
 */
public class ItEnglishService {

    private HashMap<String, String> beginner;
    private HashMap<String, String> average;
    private HashMap<String, String> master;
    private HashMap[] content;
    private List<String> keys;
    private VocabularyDao vocabularyDao;
    Random random;

    public ItEnglishService(VocabularyDao vocabularyDao) {
        this.beginner = new HashMap<>();
        this.average = new HashMap<>();
        this.master = new HashMap<>();
        this.keys = new ArrayList<>();
        this.random = new Random();
        this.content = new HashMap[]{beginner, average, master};
        this.vocabularyDao = vocabularyDao;

    }

//    public void readFiles() {
//        String[] filenames = {"beginner.txt", "average.txt", "master.txt"};
//        for (int i = 0; i < filenames.length; i++) {
//            String contentFile = "src/main/resources/" + filenames[i];
//            try (Scanner fileReader = new Scanner(new File(contentFile), "UTF-8")) {
//
//                while (fileReader.hasNextLine()) {
//                    String row = fileReader.nextLine();
//                    if (row.trim().length() == 0) {
//                        continue;
//                    }
//                    String[] parts = row.split(",");
//
//                    String english = parts[0].trim().toLowerCase();
//                    String finnish = parts[1].trim().toLowerCase();
//                    content[i].put(english, finnish);
//                }
//            } catch (Exception e) {
//                System.out.println("Virhe: " + e.getMessage());
//            }
//        }
//
//    }
    public String randomWord(String difficulty) {
        if (difficulty.equals("Aloittelija")) {
            List<String> keys = new ArrayList<String>(vocabularyDao.getByDifficulty("beginner").getVocabulary().keySet());
            System.out.println(keys);
            return vocabularyDao.getByDifficulty("beginner").getVocabulary().get(keys.get(random.nextInt(keys.size())));

        }
        if (difficulty.equals("Keskiverto")) {
            List<String> keys = new ArrayList<String>(this.average.keySet());
            return this.average.get(keys.get(random.nextInt(keys.size())));

        }
        if (difficulty.equals("Mestari")) {
            List<String> keys = new ArrayList<String>(this.master.keySet());
            return this.master.get(keys.get(random.nextInt(keys.size())));
        }
        return "randomWordError";
    }

    public String infoText(String word) {
        if (Character.isUpperCase(word.charAt(0))) {
            return "Kirjoita auki lyhenne:";
        } else {
            return "Käännä sana:";
        }
    }

    public boolean checkUserInput(String input, String word, String difficulty) {
        if (difficulty.equals("Aloittelija")) {
            if (vocabularyDao.getByDifficulty("beginner").getVocabulary().get(input) != null && vocabularyDao.getByDifficulty("beginner").getVocabulary().get(input).equals(word)) {
                return true;
            }

        }
        if (difficulty.equals("Keskiverto")) {
            if (this.average.get(input) != null && this.average.get(input).equals(word)) {
                return true;
            }

        }
        if (difficulty.equals("Mestari")) {
            if (this.master.get(input) != null && this.master.get(input).equals(word)) {
                return true;
            }

        }
        return false;
    }

}
