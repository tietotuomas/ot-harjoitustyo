package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void saldoAlustuuOikein() {
        assertEquals("saldo: 0.10 euroa", kortti.toString());
    }
    
    @Test
    public void lataaminenKasvattaaOikein() {
        kortti.lataaRahaa(1000);
        assertEquals("saldo: 10.10 euroa", kortti.toString());
    }
    
    @Test
    public void eiVoiLadataNegatiivisella() {
        kortti.lataaRahaa(-5);
        assertEquals("saldo: 0.10 euroa", kortti.toString());
    }
    
    @Test
    public void saldoVäheneeOikein() {
        kortti.otaRahaa(9);
        assertEquals("saldo: 0.01 euroa", kortti.toString());
    }
    
    @Test
    public void saldoEiMuutuJosSaldoaEiTarpeeksi() {
        kortti.otaRahaa(11);
        assertTrue(kortti.saldo()==10);

    }
    
}
