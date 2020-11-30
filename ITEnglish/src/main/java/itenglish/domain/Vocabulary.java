/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itenglish.domain;

import java.util.HashMap;

/**
 *
 * @author aaltonet
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
