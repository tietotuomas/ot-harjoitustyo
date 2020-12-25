package itenglish.domain;

import itenglish.dao.VocabularyDao;
import java.util.ArrayList;
import java.util.Random;

/**
 * Sovelluslogiikan luokka, jonka vastuualueena on erityisesti kysyttävien
 * sanojen ja niihin liittyvien vastausten käsittely.
 *
 */
public class QuestionService {

    private VocabularyDao vocabularyDao;
    private int howManyQuestions;
    private ArrayList<String> currentSet;
    private StatsService statsService;
    private Random random;

    public QuestionService(VocabularyDao vocabularyDao, StatsService statsService) {
        this.random = new Random();
        this.vocabularyDao = vocabularyDao;
        this.statsService = statsService;

    }

    public void setCurrentSet(ArrayList<String> currentSet) {
        this.currentSet = currentSet;
    }

    public void setHowManyQuestions(int howManyQuestions) {
        this.howManyQuestions = howManyQuestions;
    }

    public int getHowManyQuestions() {
        return howManyQuestions;
    }

    public void questionAnswered() {
        this.howManyQuestions--;
    }

    /**
     * Metodi hakee vaikeustason mukaisen sanaston avaimet, jotka tallennetaan
     * ArrayList-muotoiseen oliomuuttujaan.
     *
     * @param difficulty Käyttäjän valitsema vaikeustaso suomenkielisenä
     */
    public void createNewSet(String difficulty) {

        if (difficulty.equals("Aloittelija")) {
            setCurrentSet(new ArrayList<>(vocabularyDao.getByDifficulty("beginner").getVocabulary().keySet()));
        }
        if (difficulty.equals("Keskiverto")) {
            setCurrentSet(new ArrayList<>(vocabularyDao.getByDifficulty("average").getVocabulary().keySet()));
        }
        if (difficulty.equals("Mestari")) {
            setCurrentSet(new ArrayList<>(vocabularyDao.getByDifficulty("master").getVocabulary().keySet()));
        }

    }

    /**
     * Metodi asettaa kysymysmäärän oliomuuttujaan käyttäjän valinnan
     * mukaisesti.
     *
     * @param button Käyttäjän valitsema kysymysmäärä
     */
    public void setHowManyQuestionsByUser(String button) {
        if (button.equals("5 satunnaista")) {
            setHowManyQuestions(5);
        } else if (button.equals("10 satunnaista")) {
            setHowManyQuestions(10);
        } else {
            setHowManyQuestions(currentSet.size());
        }
        statsService.setTotalQuestions(howManyQuestions);
    }

    /**
     * Metodi arpoo Random-luokan random-olion avulla kysyttävän sanan
     * kysyttävien sanojen listasta. Arvonnan jälkeen metodi poistaa sanan
     * listasta.
     *
     * @return Palauttaa arvotun sanan.
     */
    public String randomWord() {
        String randomWord = currentSet.get(random.nextInt(currentSet.size()));
        currentSet.remove(randomWord);
        return randomWord;
    }

    /**
     * Metodi tarkistaa vastauksen oikeellisuuden.
     * <p>
     * Käyttäjän kirjoittamaa vastausta verrataan kysytyn sanan (avaimen)
     * oikeaan käännökseen (arvoon). Ennen vertailua metodi siistii käyttäjän
     * syötteen alusta ja lpusta mahdolliset turhat välilyönnit. Vertaillessa
     * vastauksen oikeellisuutta metodi välitä kirjainten koosta.
     *
     * @param input Käyttäjän kirjoittama vastaus
     * @param word Kysytty sana
     * @param difficulty Valittu vaikeustaso suomenkielisenä
     * @return Palauttaa merkkijono-muotoisen palautteen vastauksen
     * oikeellisuudesta.
     */
    public String checkUserInput(String input, String word, String difficulty) {

        if (difficulty.equals("Aloittelija")) {
            if (!(vocabularyDao.getByDifficulty("beginner").getVocabulary().get(word).equalsIgnoreCase(input.trim()))) {
                return "Vastauksesi \"" + input + "\" oli väärin!\nOikea vastaus oli " + vocabularyDao.getByDifficulty("beginner").getVocabulary().get(word) + ".";
            }
        }

        if (difficulty.equals("Keskiverto")) {
            if (!(vocabularyDao.getByDifficulty("average").getVocabulary().get(word).equalsIgnoreCase(input.trim()))) {
                return "Vastauksesi \"" + input + "\" oli väärin!\nOikea vastaus oli " + vocabularyDao.getByDifficulty("average").getVocabulary().get(word) + ".";
            }
        }

        if (difficulty.equals("Mestari")) {
            if (!(vocabularyDao.getByDifficulty("master").getVocabulary().get(word).equalsIgnoreCase(input.trim()))) {
                return "Vastauksesi \"" + input + "\" oli väärin!\nOikea vastaus oli " + vocabularyDao.getByDifficulty("master").getVocabulary().get(word) + ".";
            }

        }
        statsService.addCorrectAnswer();
        return "Vastauksesi \"" + input + "\" oli oikein!";
    }

    /**
     * Metodi tarkistaa, onko kysyttävä sana lyhenne ja palauttaa sen
     * perusteella ohjetekstin.
     *
     * @param word Kysyttävä sana
     * @return Palauttaa ohjetekstin.
     */
    public String infoText(String word) {
        if (checkIfAcronym(word)) {
            return "Kirjoita auki lyhenne:";
        } else {
            return "Käännä englanniksi:";
        }

    }

    /**
     * Metodi tarkistaa, onko sanan ensimmäinen kirjain iso. Jos on, niin sana
     * on lyhenne ja metodi palauttaa true. Muuten metodi palauttaa false.
     *
     * @param word Kysyttävä sana
     * @return Palauttaa totuusarvon sen perusteella, onko sana lyhenne vai ei.
     */
    public boolean checkIfAcronym(String word) {
        return Character.isUpperCase(word.charAt(0));
    }

    /**
     * Metodi tarkistaa sanaston koon.
     *
     * @param difficulty Valittu vaikeustaso englanninkielisenä
     * @return Palauttaa sanaston koon kokonaislukuna.
     */
    public int totalWords(String difficulty) {
        return vocabularyDao.getByDifficulty(difficulty).getVocabulary().size();
    }

}
