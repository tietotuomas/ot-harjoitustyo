/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import itenglish.domain.ItEnglishService;

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

    private ItEnglishService service;

    public ItEnglishServiceTest() {
        this.service = new ItEnglishService();
    }

    @Before
    public void setUp() {
        service.readFiles();

    }

    @Test
    public void checkUserInputReturnsTrueIfDifficultyBeginner() {
        assertTrue(this.service.checkUserInput("computer", "tietokone", "Aloittelija"));
    }

    @Test
    public void checkUserInputReturnsTrueIfDifficultyAverage() {
        assertTrue(this.service.checkUserInput("simulation", "simulaatio", "Keskiverto"));
    }

    @Test
    public void checkUserInputReturnsTrueIfDifficultyMaster() {
        assertTrue(this.service.checkUserInput("checksum", "tarkistussumma", "Mestari"));
    }

    @Test
    public void checkUserInputReturnsFalseIfWrongInput() {
        assertFalse(this.service.checkUserInput("checksumm", "tarkistussumma", "Mestari"));
    }

}
