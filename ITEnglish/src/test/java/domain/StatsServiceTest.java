package domain;

import dao.FakeUserDao;
import itenglish.domain.StatsService;
import itenglish.domain.UserService;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class StatsServiceTest {

    private FakeUserDao userDao;
    private StatsService service;

    @Before
    public void setUp() {
        userDao = new FakeUserDao();
        service = new StatsService(userDao);
    }

    @Test
    public void getBeginnerRecordInitsCorrectly() {
        assertEquals(0, this.userDao.findByName("Kameli").getBeginner());
    }

    @Test
    public void getAverageRecordInitsCorrectly() {
        assertEquals(0, this.userDao.findByName("Kameli").getAverage());
    }

    @Test
    public void getMasterRecordInitsCorrectly() {
        assertEquals(0, this.userDao.findByName("Kameli").getMaster());
    }

    @Test
    public void addCorrectAnswerAddsOne() {
        service.addCorrectAnswer();
        assertEquals(1, service.getCorrectAnswers());
    }

    @Test
    public void feedbackReturnsCorrectStringWithZeroScoreOnBeginner() {
        List<String> feedbacks = new ArrayList<>();
        service.setTotalQuestions(10);
        service.setLoggedUser("Kameli");
        feedbacks.add("Sait yhteensä " + "0" + "/" + "10" + " oikein!\n" + "...vaikeita sanoja?");
        feedbacks.add("Sait yhteensä " + "0" + "/" + "10" + " oikein!\n" + "...oliko keskittyminen hukassa?");
        assertTrue(feedbacks.contains(service.feedback("Aloittelija")));
    }

    @Test
    public void feedbackReturnsCorrectStringWithHighScoreOnMasterAndSetsNewRecord() {
        List<String> feedbacks = new ArrayList<>();
        service.setTotalQuestions(30);
        service.setLoggedUser("Kameli");
        for (int i = 0; i < 29; i++) {
            service.addCorrectAnswer();
        }
        feedbacks.add("Sait yhteensä " + "29" + "/" + "30" + " oikein (uudet ennätyspisteesi)!\n" + "No, terve! Ethän vain huijannut?");
        feedbacks.add("Sait yhteensä " + "29" + "/" + "30" + " oikein (uudet ennätyspisteesi)!\n" + "Suorastaan timanttista!");
        feedbacks.add("Sait yhteensä " + "29" + "/" + "30" + " oikein (uudet ennätyspisteesi)!\n" + "Ujon täydellistä!");
        String feedback = service.feedback("Mestari");
        assertTrue(feedbacks.contains(feedback));

    }

    @Test
    public void checkIfRecordReturnsFalseIfScoreNotEnoughOnBeginner() {
        service.setLoggedUser("Kameli");
        userDao.findByName("Kameli").setBeginner(10);
        for (int i = 0; i < 9; i++) {
            service.addCorrectAnswer();
        }

        assertFalse(service.checkIfRecord("Aloittelija"));
    }

    @Test
    public void checkIfRecordReturnsFalseIfScoreNotEnoughOnAverage() {
        service.setLoggedUser("Kameli");
        userDao.findByName("Kameli").setAverage(10);
        for (int i = 0; i < 9; i++) {
            service.addCorrectAnswer();
        }
        assertFalse(service.checkIfRecord("Keskiverto"));
    }

    @Test
    public void checkIfRecordReturnsFalseIfScoreNotEnoughOnMaster() {
        service.setLoggedUser("Kameli");
        userDao.findByName("Kameli").setMaster(10);
        for (int i = 0; i < 9; i++) {
            service.addCorrectAnswer();
        }
        assertFalse(service.checkIfRecord("Mestari"));
    }

    @Test
    public void checkIfRecordReturnsTrueIfScoreEnoughOnBeginner() {
        service.setLoggedUser("Kameli");
        service.addCorrectAnswer();
        assertTrue(service.checkIfRecord("Aloittelija"));
    }

    @Test
    public void checkIfRecordReturnsTrueIfScoreEnoughOnAverage() {
        service.setLoggedUser("Kameli");
        service.addCorrectAnswer();
        assertTrue(service.checkIfRecord("Keskiverto"));
    }

    @Test
    public void checkIfRecordReturnsTrueIfScoreEnoughOnMaster() {
        service.setLoggedUser("Kameli");
        userDao.findByName("Kameli").setMaster(8);
        for (int i = 0; i < 9; i++) {
            service.addCorrectAnswer();
        }
        assertTrue(service.checkIfRecord("Mestari"));
    }

    @Test
    public void countScoreReturnsCorrectDouble() {
        service.setTotalQuestions(10);
        for (int i = 0; i < 5; i++) {
            service.addCorrectAnswer();
        }
        double delta = 1e-10;
        assertEquals(0.5, service.countScore(), delta);
    }
}
