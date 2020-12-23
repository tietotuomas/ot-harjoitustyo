/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import itenglish.dao.VocabularyDao;
import itenglish.domain.Vocabulary;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aaltonet
 */
public class FakeVocabularyDao implements VocabularyDao {

    private List<Vocabulary> vocabularies;

    public FakeVocabularyDao() {
        this.vocabularies = new ArrayList<>();
        Vocabulary vocabularyBeginner = new Vocabulary("beginner");
        vocabularyBeginner.getVocabulary().put("tiedosto", "file");
        Vocabulary vocabularyAverage = new Vocabulary("average");
        vocabularyAverage.getVocabulary().put("reititin", "router");
        Vocabulary vocabularyMaster = new Vocabulary("master");
        vocabularyMaster.getVocabulary().put("keko", "heap");
        this.vocabularies.add(vocabularyMaster);
        this.vocabularies.add(vocabularyAverage);
        this.vocabularies.add(vocabularyBeginner);
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
