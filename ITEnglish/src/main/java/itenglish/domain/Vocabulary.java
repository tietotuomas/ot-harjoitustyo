package itenglish.domain;

import java.util.HashMap;

/**
 * Sovelluksen yksittäistä sanastoa edustava luokka.
 *
 */
public class Vocabulary {

    private String difficulty;
    private HashMap<String, String> vocabulary;

    public Vocabulary(String difficulty) {
        this.vocabulary = new HashMap<>();
        this.difficulty = difficulty;
    }

    public HashMap<String, String> getVocabulary() {
        return vocabulary;
    }

    public String getDifficulty() {
        return difficulty;
    }

}
