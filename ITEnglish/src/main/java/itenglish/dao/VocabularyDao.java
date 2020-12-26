package itenglish.dao;

import itenglish.domain.Vocabulary;

/**
 * Rajapinta sanastojen käsittelyyn.
 *
 */
public interface VocabularyDao {

    /**
     * Metodi etsii ja palauttaa vaikeustason perusteella Vocabulary-olion.
     *
     * @param Vaikeustaso englanninkielisenä
     * @return Palauttaa sanaston Vocabulary-oliona
     */
    Vocabulary getByDifficulty(String name);

}
