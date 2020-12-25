package itenglish.dao;

import itenglish.domain.Vocabulary;

/**
 * Rajapinta sanastojen käsittelyyn.
 *
 */
public interface VocabularyDao {

    Vocabulary getByDifficulty(String name);

}
