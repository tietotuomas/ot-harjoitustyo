
package dao;

import itenglish.dao.FileUserDao;
import itenglish.domain.User;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;


public class FileUserDaoTest {

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    private File usersFile;
    private FileUserDao dao;

    @Before
    public void setUp() throws Exception {
        usersFile = tempFolder.newFile("usersTest.txt");

        try (FileWriter file = new FileWriter(usersFile.getAbsolutePath())) {
            file.write("Testaaja,$2a$10$UtXQXjMEd1LFq7gksG4yA.1742St0o5uJ/LYHIZxGw3d65S.C5mxe,1,2,3\n");
        }

        dao = new FileUserDao(usersFile.getAbsolutePath());
    }

    @Test
    public void loadWorksCorrectly() {
        List<User> users = dao.getUsers();
        assertEquals(1, users.size());
        User user = users.get(0);
        assertEquals("Testaaja", user.getName());
        assertEquals("$2a$10$UtXQXjMEd1LFq7gksG4yA.1742St0o5uJ/LYHIZxGw3d65S.C5mxe", user.getPassword());
        assertEquals(1, user.getBeginner());
        assertEquals(2, user.getAverage());
        assertEquals(3, user.getMaster());
    }

    @Test
    public void creatingNewUserWorksCorrectly() throws Exception {
        dao.create("Elon Musk", "salakirjoittamatonSalasana");
        List<User> users = dao.getUsers();
        assertEquals(2, users.size());
        User user = users.get(1);
        assertEquals("Elon Musk", user.getName());
        assertEquals("salakirjoittamatonSalasana", user.getPassword());
        assertEquals(0, user.getBeginner());
        assertEquals(0, user.getAverage());
        assertEquals(0, user.getMaster());
    }

    @Test
    public void findByNameReturnsCorrectUserIfUserExists() throws Exception {
        dao.create("Elon Musk", "salakirjoittamatonSalasana");
        dao.create("Sanna Marin", "salakirjoittamatonSalasana");
        User testaaja = dao.findByName("Testaaja");
        User sanna = dao.findByName("Sanna Marin");
        assertEquals("Testaaja", testaaja.getName());
        assertEquals("Sanna Marin", sanna.getName());
    }

    @Test
    public void findByNameReturnsNullIfUserDoesntExist() throws Exception {
        assertEquals(null, dao.findByName("Sanna Marin"));
    }

    @After
    public void tearDown() {
        usersFile.delete();
    }
}
