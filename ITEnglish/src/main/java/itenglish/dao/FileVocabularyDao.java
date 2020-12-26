package itenglish.dao;

import itenglish.domain.Vocabulary;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 *
 * Luokka toteuttaa VocabularyDao-rajapinnan ja vastaa sanastojen käsittelystä.
 */
public class FileVocabularyDao implements VocabularyDao {

    private HashMap<String, String> files;
    private List<Vocabulary> vocabularies;

    public FileVocabularyDao(HashMap<String, String> files) throws Exception {
        this.vocabularies = new ArrayList<>();
        this.files = files;
        loadFiles(files);
    }

    /**
     * Metodi lukee sanoja sisältävät pysyväistallennus-tiedostot ja luo niistä
     * vocabularies-listaan (luokan oliomuuttuja) tallennettavat
     * Vocabulary-oliot.
     *
     * @see itenglish.domain.Vocabulary
     * @param Sanastojen nimet ja tiedostojen nimet avain-arvo-parina.
     */
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
            }
            this.vocabularies.add(vocabulary);
        }

    }

    public List<Vocabulary> getVocabularies() {
        return vocabularies;
    }
    

    /**
     * Metodi etsii ja palauttaa vaikeustason perusteella Vocabulary-olion.
     *
     * @param Vaikeustaso englanninkielisenä
     * @return Palauttaa sanaston Vocabulary-oliona
     */
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
