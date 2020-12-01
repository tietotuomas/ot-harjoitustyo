/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import itenglish.dao.FileVocabularyDao;
import itenglish.dao.VocabularyDao;
import itenglish.domain.ItEnglishService;
import itenglish.domain.Vocabulary;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Properties;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aaltonet
 */
public class ItEnglishServiceTest {

    private VocabularyDao vocabularyDao;
    private ItEnglishService service;

    public ItEnglishServiceTest() throws Exception {
        Properties properties = new Properties();

        properties.load(new FileInputStream("config.properties"));

        HashMap<String, String> files = new HashMap<>();
        files.put("beginner", properties.getProperty("beginner"));
        files.put("average", properties.getProperty("average"));
        files.put("master", properties.getProperty("master"));
        FileVocabularyDao fileVocabularyDao = new FileVocabularyDao(files);
        this.vocabularyDao = fileVocabularyDao;
        this.service = new ItEnglishService(vocabularyDao);
    }

    @Before
    public void setUp() {

    }

    @Test
    public void checkUserInputReturnsCorrectStringIfDifficultyBeginner() {
        assertEquals("Vastauksesi \"" + "file" + "\" oli oikein!", this.service.checkUserInput("file", "tiedosto", "Aloittelija"));
    }

    @Test
    public void checkUserInputReturnsCorrectStringIfDifficultyAverage() {
        assertEquals("Vastauksesi \"" + "input" + "\" oli oikein!", this.service.checkUserInput("input", "syöte", "Keskiverto"));
    }

    @Test
    public void checkUserInputReturnsCorrectStringIfDifficultyMaster() {
        assertEquals("Vastauksesi \"" + "heap" + "\" oli oikein!", this.service.checkUserInput("heap", "keko", "Mestari"));
    }

    @Test
    public void checkUserInputReturnsCorrectStringIfDifficultyBeginnerAndWrongAnswer() {
        assertEquals("Vastauksesi \"" + "wrong" + "\" oli väärin!\nOikea vastaus oli file.", this.service.checkUserInput("wrong", "tiedosto", "Aloittelija"));
    }

    @Test
    public void checkUserInputReturnsCorrectStringIfDifficultyAverageAndWrongAnswer() {
        assertEquals("Vastauksesi \"" + "wrong" + "\" oli väärin!\nOikea vastaus oli input.", this.service.checkUserInput("wrong", "syöte", "Keskiverto"));
    }

    @Test
    public void checkUserInputReturnsCorrectStringIfDifficultyMasterAndWrongAnswer() {
        assertEquals("Vastauksesi \"" + "wrong" + "\" oli väärin!\nOikea vastaus oli heap.", this.service.checkUserInput("wrong", "keko", "Mestari"));
    }

    @Test
    public void checkIfAcronymReturnsTrueIfWordStartsWithCapitalLetter() {
        assertTrue(this.service.checkIfAcronym("FBI"));
    }

    @Test
    public void checkIfAcronymReturnsFalseIfWordSDoesntStartWithCapitalLetter() {
        assertFalse(this.service.checkIfAcronym("kameli"));
    }

    @Test
    public void infoTextReturnsCorrectStringIfWordIsAcronym() {
        assertEquals("Kirjoita auki lyhenne:", this.service.infoText("FBI"));

    }

    @Test
    public void infoTextReturnsCorrectStringIfWordIsNotAcronym() {
        assertEquals("Käännä englanniksi:", this.service.infoText("kameli"));

    }
}
