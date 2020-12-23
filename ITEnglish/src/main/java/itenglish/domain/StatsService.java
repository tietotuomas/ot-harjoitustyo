
package itenglish.domain;

import itenglish.dao.UserDao;
import java.util.Random;


public class StatsService {

    private UserDao userDao;
    private String loggedUser;
    private int correctAnswers;
    private int totalQuestions;

    public StatsService(UserDao userDao) {
        this.userDao = userDao;
        this.loggedUser = loggedUser;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public int getBeginnerRecord() {
        return this.userDao.findByName(loggedUser).getBeginner();
    }

    public int getAverageRecord() {
        return this.userDao.findByName(loggedUser).getAverage();
    }

    public int getMasterRecord() {
        return this.userDao.findByName(loggedUser).getMaster();
    }

    public void setLoggedUser(String user) {
        this.loggedUser = user;

    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
        this.correctAnswers = 0;
    }

    public void addCorrectAnswer() {
        this.correctAnswers++;
    }
    
    public String feedback(String difficulty) {
    int messageSelector = 0;
    double score = countScore();
    double[] feedbackLimit = {0, 0.2, 0.4, 0.6, 0.8, 1};
    String [][] feedbackArray = {{"...vaikeita sanoja?", "...oliko keskittyminen hukassa?"}, 
        {"No, olisihan se huonomminkin voinut mennä.", "Ei mennyt kuin Strömsössä."},
        {"Ei huono, jatka harjoittelua.", "Ihan kohtuullinen esitys."},
        {"Vähintäänkin tyydyttävä tulos!", "Ei hassummin!", "Tavattoman kelpo tulos!"},
        {"Wau, kiitettävä suoritus!", "Fantastista tekemistä!", "It-sanasto selvästi hallussa!"},
        {"No, terve! Ethän vain huijannut?", "Suorastaan timanttista!", "Ujon täydellistä!"}};
    while (messageSelector < feedbackLimit.length && score > feedbackLimit[messageSelector]) {
        messageSelector++;
    }

    Random random = new Random();
    int randomIndex = random.nextInt(feedbackArray[messageSelector].length);
    if (checkIfRecord(difficulty)) {
        return "Sait yhteensä " + getCorrectAnswers() + "/" + getTotalQuestions() + " oikein (uudet ennätyspisteesi)!\n" + feedbackArray[messageSelector][randomIndex];
    }
    return "Sait yhteensä " + getCorrectAnswers() + "/" + getTotalQuestions()+ " oikein!\n" + feedbackArray[messageSelector][randomIndex];

    }

    public boolean checkIfRecord(String difficulty) {

        if (difficulty.equals("Aloittelija") && (this.correctAnswers > this.userDao.findByName(this.loggedUser).getBeginner())) {
            this.userDao.findByName(this.loggedUser).setBeginner(correctAnswers);
        } else if (difficulty.equals("Keskiverto") && (this.correctAnswers > this.userDao.findByName(this.loggedUser).getAverage())) {
            this.userDao.findByName(this.loggedUser).setAverage(correctAnswers);
        } else if (difficulty.equals("Mestari") && (this.correctAnswers > this.userDao.findByName(this.loggedUser).getMaster())) {
            this.userDao.findByName(this.loggedUser).setBeginner(correctAnswers);
        } else {
            return false;
        }
        try {
            this.userDao.save();
        } catch (Exception e) {
            System.out.println("Uuden ennätyksen tallentaminen epäonnistui kirjoitusvirheen vuoksi.");
        }
        return true;
    }
    
    
    public double countScore() {
        return (double)getCorrectAnswers()/getTotalQuestions();
    }

}
