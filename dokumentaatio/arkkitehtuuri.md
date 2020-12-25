# Arkkitehtuurikuvaus  

## Sovelluksen rakenne ja riippuvuudet

Sovelluks hyödyntää kerrosarkkitehtuurin ja DAO-suunnittelumallin periaatteita. Sovelluslogiikka, käyttöliittymä ja tietojen tallentaminen/lukeminen on pyritty eriyttämään toisistaan. Pysyväistallennuksen muotona käytetään tekstitiedostoja, mutta esimerkiksi tietokantoihin siirtyminen pitäisi arkkitehtuurin vuoksi olla suhteellisen kivutonta.
 
### Pakkauskaavio
<img src="https://github.com/tietotuomas/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/UML-kaavio.png?raw=true">  
Pakkauskaavioon on merkattu pakkauksien ja luokkien väliset riippuvuudet. Sovelluksessa on pyritty ehkäisemään turhia riippuvuuksia. Pakkaus itenglish.ui on riippuvainen pakkauksesta itenglish.domain, mutta ei pakkauksesta itenglish.dao. Pakkauksen ui luokassa ItEnglishUi käsitellään dao-pakkauksen luokkia vain riippuvuuksien injektoinnin yhteydessä. Domain-pakkauksen luokat ovat riippuvaisia dao-pakkauksen luokista kuvaan merkityllä tavalla.

## Käyttöliittymä

## Sovelluslogiikka  

## Pysyväistallennus

Sovellus lukee ja tallentaa käyttäjien tiedot erilliseen tekstitiedostoon. Sovellus myös lukee käännettävät sanat kolmesta erillisestä tekstitiedostosta.

Sovelluksen juureen sijoitettu konfiguraatiotiedosto [config.properties](https://github.com/tietotuomas/ot-harjoitustyo/blob/master/ITEnglish/config.properties) määrittelee tiedostojen nimet.

Sovellus tallettaa käyttäjät seuraavalla tavalla:

<pre>
käyttäjä,salasana,0,0,0
Testaaja,testi,15,12,3
</pre>

Kullakin rivillä on ensiksi käyttäjän kirjautumistiedot - käyttäjätunnus ja salasana - joiden jälkeen käyttäjän ennätyspisteet kolmella eri vaikeustasolla. Tiedot erotellaan pilkulla.

Sanasto on talletettu seuraavalla tavalla:

<pre>
informaatiosodankäynti, information warfare 
SaaS, Software as a Service
</pre>

Kullakin rivillä on ensiksi käännettävä sana/lyhenne, jota seuraa pilkulla ja (sanaston luettavuuden kohentamiseksi) välilyönnillä eroteltuna käännös ko. sanaan.

## Toiminnallisuudet

<img src="https://github.com/tietotuomas/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/Uuden%20satunnaisen%20sanan%20hakeminen.png?raw=true">  

## Sovelluksen arkkitehtuurin ja toiminnallisuuksien ongelmat/vajaavaisudet

### Käyttöliittymä
Sovelluksen ilmeisin ongelma-alue lienee sen käyttöliittymä. Käyttöliittymä on toteutettu vain yhden luokan sisällä ja sisältää huomattavan määrän toisteista koodia. Käyttöliitymän voisi siis eriyttää useaksi eri luokaksi ja/tai toteuttaa isommalla määrällä yksinkertaisempia metodeja. Käyttöliittymän voisi myös rakentaa päätason asettelun ja alinäkymien pohjalle niin, että näkymät jakaisivat jonkinlaisen yhteisen valikon, eikä uutta näkymää tarvitsisi joka kertaa luoda tyhjästä (niin kuin nyt). Käyttöliittymän koodista löytyy varmasti myös muita ratkaisuja, joita voisi toteuttaa tarkoituksenmukaisemmalla tavalla.

### Riippuvuudet
QuestionService-luokka on riippuvainen StatsService-luokasta. Toiminnallisuudet olisi mahdollista toteuttaa sovelluksen rakennetta hieman muokkaamalla myös ilman tätä "turhaa" riippuvuutta.

### Sanaston muokkaus
Sovellus ei sisällä toiminnallisuutta, jolla sanastoa voitaisiin suoraan muokata. Nyt sanoja voidaan lisätä/poistaa vain tekstitiedoston kautta (sovellus on kuitenkin rakennettu niin, ettei tämä riko sovelluksen toimintaa).

