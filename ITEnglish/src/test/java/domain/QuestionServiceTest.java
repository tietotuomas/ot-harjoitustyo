/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import dao.FakeUserDao;
import dao.FakeVocabularyDao;
import itenglish.domain.ItEnglishService;
import itenglish.domain.StatsService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aaltonet
 */
public class ItEnglishServiceTest {

    private FakeUserDao userDao;
    private FakeVocabularyDao vocabularyDao;
    private ItEnglishService service;
    private StatsService statsService;

    @Before
    public void setUp() {
        userDao = new FakeUserDao();
        vocabularyDao = new FakeVocabularyDao();
        statsService = new StatsService(userDao);
        service = new ItEnglishService(vocabularyDao, statsService);
    }

    @Test
    public void questionAnsweredSubtractsOne() {
        service.setHowManyQuestions(10);
        service.questionAnswered();
        assertEquals(9, service.getHowManyQuestions());
    }

    @Test
    public void currentSetReturnsTheCorrectWordOnBeginnerDifficulty() {
        service.createNewSet("Aloittelija");
        assertEquals("tiedosto", service.randomWord());
    }

    @Test
    public void currentSetReturnsTheCorrectWordOnAverageDifficulty() {
        service.createNewSet("Keskiverto");
        assertEquals("reititin", service.randomWord());
    }

    @Test
    public void currentSetReturnsTheCorrectWordOnMasterDifficulty() {
        service.createNewSet("Mestari");
        assertEquals("keko", service.randomWord());
    }

    @Test
    public void setHowManyQuestionsByUserWorksCorrectlyWithFiveRandom() {
        service.setHowManyQuestionsByUser("5 satunnaista");
        assertEquals(5, service.getHowManyQuestions());
        assertEquals(5, statsService.getTotalQuestions());
    }

    @Test
    public void setHowManyQuestionsByUserWorksCorrectlyWithTenRandom() {
        service.setHowManyQuestionsByUser("10 satunnaista");
        assertEquals(10, service.getHowManyQuestions());
        assertEquals(10, statsService.getTotalQuestions());
    }

    @Test
    public void setHowManyQuestionsByUserWorksCorrectlyWithWholeSet() {
        service.createNewSet("Aloittelija");
        service.setHowManyQuestionsByUser("Kaikki");
        assertEquals(1, service.getHowManyQuestions());
        assertEquals(1, statsService.getTotalQuestions());
    }

    @Test
    public void checkUserInputReturnsCorrectStringIfDifficultyBeginner() {
        assertEquals("Vastauksesi \"" + "file" + "\" oli oikein!", this.service.checkUserInput("file", "tiedosto", "Aloittelija"));
    }

    @Test
    public void checkUserInputReturnsCorrectStringIfDifficultyAverage() {
        assertEquals("Vastauksesi \"" + "router" + "\" oli oikein!", this.service.checkUserInput("router", "reititin", "Keskiverto"));
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
        assertEquals("Vastauksesi \"" + "wrong" + "\" oli väärin!\nOikea vastaus oli router.", this.service.checkUserInput("wrong", "reititin", "Keskiverto"));
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
    
    @Test
    public void totalWordsReturnsCorrectInt() {
        assertEquals(1, service.totalWords("master"));
    }

}
