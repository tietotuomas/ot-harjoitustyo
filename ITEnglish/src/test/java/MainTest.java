/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import itenglish.Main;
import java.util.HashMap;
import java.util.Scanner;
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
public class MainTest {

    
    HashMap<String, String> beginner;
    Scanner commandReader;

    @Before
    public void setUp() {
        HashMap<String, String> beginner = new HashMap<>();
        beginner.put("computer", "tietokone");
        beginner.put("file", "tiedosto");
        beginner.put("keyboard", "näppäimistö");
        beginner.put("mouse", "hiiri");
        beginner.put("password", "salasana");
        beginner.put("printer", "tulostin");
        beginner.put("key", "avain");
        Scanner commandReader = new Scanner(System.in, "UTF-8");

    }

    @Test
    public void englishTestReturns0CorrectAnswersIfQuestions0() {
        assertTrue(Main.englishTest(beginner, commandReader, 0) == 0);
    }

}
