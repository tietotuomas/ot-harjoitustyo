/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itenglish.dao;

import itenglish.domain.Vocabulary;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author aaltonet
 */
public class FileVocabularyDao implements VocabularyDao {

    private HashMap<String, String> files;
    private List<Vocabulary> vocabularies;

    public FileVocabularyDao(HashMap<String, String> files) throws Exception {
        this.vocabularies = new ArrayList<>();
        this.files = files;
        loadFiles(files);
    }

    public void loadFiles(HashMap<String, String> files) {

        for (String difficulty : files.keySet()) {
            Vocabulary vocabulary = new Vocabulary(difficulty);
            try (Scanner fileReader = new Scanner(new File(files.get(difficulty)), "UTF-8")) {

                while (fileReader.hasNextLine()) {
                    String row = fileReader.nextLine();
                    if (row.trim().length() == 0) {
                        continue;
                    }
                    String[] parts = row.split(",");

                    String question = parts[0].trim();
                    String answer = parts[1].trim();
                    vocabulary.getVocabulary().put(question, answer);
                }
            } catch (Exception e) {
                System.out.println("Tiedoston lukeminen ei onnistunut: " + e.getMessage());
            }
            this.vocabularies.add(vocabulary);
        }

    }

    @Override
    public Vocabulary getByDifficulty(String difficulty) {
        for (Vocabulary vocabulary : this.vocabularies) {
            if (vocabulary.getDifficulty().equals(difficulty)) {
                return vocabulary;
            }
        }
        return null;
    }

}
