package itenglish.domain;

import itenglish.dao.UserDao;
import java.util.Random;

/**
 * Sovelluslogiikan luokka, joka vastaa erityisesti tilastojen ja kyselyjen
 * jälkeisten palautteiden käsittelystä.
 */
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

    /**
     * Metodi tuottaa käyttäjälle palautteen tuloksen perusteella.
     * <p>
     * Metodi laskee ensiksi countScore-apumetodin avulla käyttäjän oikeiden
     * vastauksien ja kaikkien vastauksien suhteen. Seuraavaksi metodi hakee
     * tämän suhdeluvun perusteella kaksiuloitteisesta taulukosta suhdelukua
     * vastaavan "sisemmän" taulukon. Sitten metodi arpoo tästä valitusta,
     * sisemmästä taulukosta merkkijono-muotoisen palautteen Random-luokan
     * random-olion avulla.
     * <p>
     * Lopuksi metodi lisää merkkijonon alkuun kaikille palautteille yhteisen
     * alkuosan ja vielä mahdollisen lisäyksen ennätyspisteistä. Ennätyspisteet
     * metodi tarkistaa checkIfRecord-apumetodin avulla.
     *
     * @see StatsService#countScore()
     * @see StatsService#checkIfRecord(java.lang.String)
     * @param difficulty Vaikeustaso suomenkielisenä
     * @return Palauttaa merkkijono-muotoisen palautteen kyselyn tuloksista.
     */
    public String feedback(String difficulty) {
        int messageSelector = 0;
        double score = countScore();
        double[] feedbackLimit = {0, 0.2, 0.4, 0.6, 0.8, 1};
        String[][] feedbackArray = {{"...vaikeita sanoja?", "...oliko keskittyminen hukassa?"},
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
        return "Sait yhteensä " + getCorrectAnswers() + "/" + getTotalQuestions() + " oikein!\n" + feedbackArray[messageSelector][randomIndex];

    }

    /**
     * Metodi tarkistaa, ylittääkö oikeiden vastauksien määrä aikaisemman
     * ennätyksen vai ei. Jos ylittää, metodi kirjaa uuden ennätyksen
     * pysyväistallennukseen ja palauttaa true. Muuten palauttaa false.
     *
     * @param difficulty Vaikeustaso suomenkielisenä
     * @return Palauttaa totuusarvon sen perusteella, ylittääkö oikeiden
     * vastauksien määrä aikaisemman ennätyksen vai ei.
     */
    public boolean checkIfRecord(String difficulty) {

        if (difficulty.equals("Aloittelija") && (this.correctAnswers > this.userDao.findByName(this.loggedUser).getBeginner())) {
            this.userDao.findByName(this.loggedUser).setBeginner(correctAnswers);
        } else if (difficulty.equals("Keskiverto") && (this.correctAnswers > this.userDao.findByName(this.loggedUser).getAverage())) {
            this.userDao.findByName(this.loggedUser).setAverage(correctAnswers);
        } else if (difficulty.equals("Mestari") && (this.correctAnswers > this.userDao.findByName(this.loggedUser).getMaster())) {
            this.userDao.findByName(this.loggedUser).setMaster(correctAnswers);
        } else {
            return false;
        }
        try {
            this.userDao.save();
        } catch (Exception e) {
        }
        return true;
    }

    /**
     * Metodi laskee käyttäjän oikeiden vastauksien ja kaikkien vastauksien
     * suhteen.
     *
     * @return Palauttaa käyttäjän oikeiden vastauksien ja kaikkien vastauksien
     * suhteen liukulukuna.
     */
    public double countScore() {
        return (double) getCorrectAnswers() / getTotalQuestions();
    }

}
