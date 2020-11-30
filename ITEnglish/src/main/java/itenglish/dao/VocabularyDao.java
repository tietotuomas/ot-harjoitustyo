/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itenglish.dao;

import itenglish.domain.Vocabulary;

/**
 *
 * @author aaltonet
 */
public interface VocabularyDao {
    
    Vocabulary getByDifficulty(String name);
    
}
