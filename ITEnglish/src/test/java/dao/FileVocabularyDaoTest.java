
package dao;

import itenglish.dao.FileVocabularyDao;
import itenglish.domain.Vocabulary;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;


public class FileVocabularyDaoTest {

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();
    private File beginnerFile;
    private File averageFile;
    private File masterFile;

    private HashMap<String, String> files = new HashMap<>();
    private FileVocabularyDao dao;

    @Before
    public void setUp() throws Exception {
        beginnerFile = tempFolder.newFile("aloittelija.txt");
        averageFile = tempFolder.newFile("keskiverto.txt");
        masterFile = tempFolder.newFile("mestari.txt");
        files.put("aloittelija", beginnerFile.getAbsolutePath());
        files.put("keskiverto", averageFile.getAbsolutePath());
        files.put("mestari", masterFile.getAbsolutePath());
        try (FileWriter file = new FileWriter(beginnerFile.getAbsolutePath())) {
            file.write("tiedosto, file\n");
        }
        try (FileWriter file = new FileWriter(averageFile.getAbsolutePath())) {
            file.write("reititin, router\n");
        }
        try (FileWriter file = new FileWriter(masterFile.getAbsolutePath())) {
            file.write("keko, heap\n");
        }

        dao = new FileVocabularyDao(files);
    }

    @Test
    public void loadWorksCorrectly() {
        List<Vocabulary> vocabularies = dao.getVocabularies();
        assertEquals(3, vocabularies.size());
        Vocabulary aloittelija = null;
        Vocabulary keskiverto = null;
        Vocabulary mestari = null;
        for (Vocabulary vocabulary : vocabularies) {
            if (vocabulary.getDifficulty() == "aloittelija") {
                aloittelija = vocabulary;
            }
            if (vocabulary.getDifficulty() == "keskiverto") {
                keskiverto = vocabulary;
            }
            if (vocabulary.getDifficulty() == "mestari") {
                mestari = vocabulary;
            }
        }
        
        assertEquals(1, aloittelija.getVocabulary().keySet().size());
        assertEquals(1, keskiverto.getVocabulary().keySet().size());
        assertEquals(1, mestari.getVocabulary().keySet().size());
        assertEquals("file", aloittelija.getVocabulary().get("tiedosto"));
        assertEquals("router", keskiverto.getVocabulary().get("reititin"));
        assertEquals("heap", mestari.getVocabulary().get("keko"));
    }
    
    @Test
    public void getByDifficultyReturnsCorrectVocabulary() {
            Vocabulary aloittelija = dao.getByDifficulty("aloittelija");
            assertEquals("aloittelija", aloittelija.getDifficulty());
}

    @After
    public void tearDown() {
        beginnerFile.delete();
        averageFile.delete();
        masterFile.delete();
    }

 
}
