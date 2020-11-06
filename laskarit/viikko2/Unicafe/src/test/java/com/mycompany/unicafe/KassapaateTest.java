/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

;
import org.junit.Before;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aaltonet
 */


public class KassapaateTest {

    Kassapaate kassa;

    @Before
    public void setUp() {
        kassa = new Kassapaate();
    }

    @Test
    public void alussaRahaa1000() {
        assertTrue(kassa.kassassaRahaa() == 100000);
    }

    @Test
    public void alussaMyyty0() {
        assertTrue(kassa.maukkaitaLounaitaMyyty() + kassa.edullisiaLounaitaMyyty() == 0);
    }

    @Test
    public void kateinenMyydytLounaatMaukkaanJalkeen() {
        kassa.syoMaukkaasti(400);
        assertTrue(kassa.maukkaitaLounaitaMyyty() > 0);
    }

    @Test
    public void kateinenMyydytLounaatEdullisenJalkeen() {
        kassa.syoEdullisesti(240);
        assertTrue(kassa.edullisiaLounaitaMyyty() > 0);
    }

    @Test
    public void kateinenKassanSaldoMaukkaanJalkeen() {
        kassa.syoMaukkaasti(400);
        assertTrue(kassa.kassassaRahaa() == 100400);
    }

    @Test
    public void kateinenKassanSaldoEdullisenJalkeen() {
        kassa.syoEdullisesti(240);
        assertTrue(kassa.kassassaRahaa() == 100240);
    }

    @Test
    public void kateinenPalautusMaukkaanJalkeen() {
        int palautus = kassa.syoMaukkaasti(500);
        assertTrue(palautus == 100);
    }

    @Test
    public void kateinenPalautusEdullisenJalkeen() {
        int palautus = kassa.syoEdullisesti(500);
        assertTrue(palautus == 260);
    }

    @Test
    public void kateinenMyydytLounaatMaukkaanJalkeenKunMaksuEiRiittava() {
        kassa.syoMaukkaasti(300);
        assertTrue(kassa.maukkaitaLounaitaMyyty() == 0);
    }

    @Test
    public void kateinenMyydytLounaatEdullisenJalkeenKunMaksuEiRiittava() {
        kassa.syoEdullisesti(200);
        assertTrue(kassa.edullisiaLounaitaMyyty() == 0);
    }

    @Test
    public void kateinenKassanSaldoMaukkaanJalkeenKunMaksuEiRiittava() {
        kassa.syoMaukkaasti(300);
        assertTrue(kassa.kassassaRahaa() == 100000);
    }

    @Test
    public void kateinenKassanSaldoEdullisenJalkeenKunMaksuEiRiittava() {
        kassa.syoEdullisesti(200);
        assertTrue(kassa.kassassaRahaa() == 100000);
    }

    @Test
    public void kateinenPalautusMaukkaanJalkeenKunMaksuEiRiittava() {
        int palautus = kassa.syoMaukkaasti(300);
        assertTrue(palautus == 300);
    }

    @Test
    public void kateinenPalautusEdullisenJalkeenKunMaksuEiRiittava() {
        int palautus = kassa.syoEdullisesti(200);
        assertTrue(palautus == 200);
    }

    @Test
    public void korttiMyydytLounaatMaukkaanJalkeen() {
        kassa.syoMaukkaasti(new Maksukortti(1000));
        assertTrue(kassa.maukkaitaLounaitaMyyty() > 0);
    }

    @Test
    public void korttiMyydytLounaatEdullisenJalkeen() {
        kassa.syoEdullisesti(new Maksukortti(1000));
        assertTrue(kassa.edullisiaLounaitaMyyty() > 0);
    }

    @Test
    public void korttiKortinSaldoMaukkaanJalkeen() {
        Maksukortti kortti = new Maksukortti(1000);
        kassa.syoEdullisesti(kortti);
        assertEquals(760, kortti.saldo());
    }

    @Test
    public void korttiKortinSaldoEdullisenJalkeen() {
        Maksukortti kortti = new Maksukortti(1000);
        kassa.syoMaukkaasti(kortti);
        assertEquals(600, kortti.saldo());
    }

    @Test
    public void korttiPalauttaaTrueMaukkaanJalkeen() {
        assertTrue(kassa.syoMaukkaasti(new Maksukortti(1000)));
    }

    @Test
    public void korttiPalauttaaTrueEdullisenJalkeen() {
        assertTrue(kassa.syoEdullisesti(new Maksukortti(1000)));
    }

    @Test
    public void korttiMyydytLounaatMaukkaanJalkeenKunMaksuEiRiita() {
        kassa.syoMaukkaasti(new Maksukortti(100));
        assertTrue(kassa.maukkaitaLounaitaMyyty() == 0);
    }

    @Test
    public void korttiMyydytLounaatEdullisenJalkeenKunMaksuEiRiita() {
        kassa.syoEdullisesti(new Maksukortti(100));
        assertTrue(kassa.edullisiaLounaitaMyyty() == 0);
    }

    @Test
    public void korttiKortinSaldoMaukkaanJalkeenKunMaksuEiRiita() {
        Maksukortti kortti = new Maksukortti(100);
        kassa.syoEdullisesti(kortti);
        assertEquals(100, kortti.saldo());
    }

    @Test
    public void korttiKortinSaldoEdullisenJalkeenKunMaksuEiRiita() {
        Maksukortti kortti = new Maksukortti(100);
        kassa.syoMaukkaasti(kortti);
        assertEquals(100, kortti.saldo());
    }

    @Test
    public void korttiPalauttaaFalseMaukkaanJalkeenKunMaksuEiRiita() {
        assertFalse(kassa.syoMaukkaasti(new Maksukortti(100)));
    }

    @Test
    public void korttiPalauttaaFalseEdullisenJalkeenKunMaksuEiRiita() {
        assertFalse(kassa.syoEdullisesti(new Maksukortti(100)));
    }

    @Test
    public void korttiKassanSummaEiMuutuEdullisenJalkeen() {
        kassa.syoEdullisesti(new Maksukortti(1000));
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void korttiKassanSummaEiMaukkaanJalkeen() {
        kassa.syoMaukkaasti(new Maksukortti(1000));
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void korttiKassanSummaEiMuutuEdullisenJalkeenKunMaksuEiRiita() {
        kassa.syoEdullisesti(new Maksukortti(100));
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void korttiKassanSummaEiMaukkaanJalkeenKunMaksuEiRiita() {
        kassa.syoMaukkaasti(new Maksukortti(100));
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void kortinSaldoKasvaaLadattaessaOikein() {
        Maksukortti kortti = new Maksukortti(100);
        kassa.lataaRahaaKortille(kortti, 100);
        assertEquals(200, kortti.saldo());
    }

    @Test
    public void kassanSaldoKasvaaLadattaessaOikein() {
        Maksukortti kortti = new Maksukortti(100);
        kassa.lataaRahaaKortille(kortti, 100);
        assertEquals(100100, kassa.kassassaRahaa());
    }
    
        @Test
    public void kortinSaldoEiMuutuKunLadataanNegativiinenSumma() {
        Maksukortti kortti = new Maksukortti(100);
        kassa.lataaRahaaKortille(kortti, -100);
        assertEquals(100, kortti.saldo());
    }

    @Test
    public void kassanSaldoEiMuutuKunLadataanNegatiivinenSumma() {
        Maksukortti kortti = new Maksukortti(100);
        kassa.lataaRahaaKortille(kortti, -100);
        assertEquals(100000, kassa.kassassaRahaa());
    }

}
