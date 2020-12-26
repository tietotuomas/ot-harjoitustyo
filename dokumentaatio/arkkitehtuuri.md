# Arkkitehtuurikuvaus  

## Sovelluksen rakenne ja riippuvuudet  
Sovelluks hyödyntää kerrosarkkitehtuurin ja DAO-suunnittelumallin periaatteita. Käyttöliittymä, sovelluslogiikka ja tietojen tallentaminen/lukeminen on pyritty eriyttämään toisistaan. Pysyväistallennuksen muotona käytetään tekstitiedostoja, mutta esimerkiksi tietokantoihin siirtyminen pitäisi arkkitehtuurin vuoksi olla suhteellisen kivutonta.
 
### Pakkauskaavio
<img src="https://github.com/tietotuomas/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/UML-kaavio.png?raw=true">  
Pakkauskaavioon on merkattu pakkauksien ja luokkien väliset riippuvuudet. Sovelluksessa on pyritty ehkäisemään turhia riippuvuuksia. Pakkaus itenglish.ui on riippuvainen pakkauksesta itenglish.domain, mutta ei pakkauksesta itenglish.dao. Pakkauksen ui luokassa ItEnglishUi käsitellään dao-pakkauksen luokkia vain riippuvuuksien injektoinnin yhteydessä. Domain-pakkauksen luokat ovat riippuvaisia dao-pakkauksen luokista kuvaan merkityllä tavalla.

## Käyttöliittymä  
Sovelluksen käyttöliittymä on ui-pakkauksen ItEnglishUi-luokan vastuulla. Käyttöliittymä on rakennettu JavaFX-kirjastolla. Se sisältää kuusi eri näkymää:
- login ("Kirjaudu")
- newUser ("Käyttäjätili"
- chooseMode ("Vaikeustaso")
- study ("Harjoitus")
- feedback ("Palaute")
- stats ("Tilastot")

Sovelluslogiikka on pyritty eriyttämään kokonaan ko. käyttöliittymäluokasta. Pääosin eriyttäminen on toteutettu niin, että sovelluslogiikan tapahtumat on yhdistetty käyttöliittymän graafisten nappien taakse. Kun nappia klikataan, kutsuu siihen yhdistetty tapahtumankäsittelijä domain-pakkauksen sovelluslogiikasta huolehtivien luokkien metodeja. Näiden metodien palauttamien arvojen perusteella näkymää muutetaan ja muokataan.

## Sovelluslogiikka  
Sovelluslogiikka on domain-pakkauksen vastuulla. Vocabulary on sovelluksen yksitäistä sanastoa edustava luokka ja User käyttäjää edustava luokka. Varsinainen sovelluslogiikka ja toiminnallisuudet ovat QuestionService-, UserService- ja StatsService-luokkien vastuulla. 

QuestionService-luokan vastuualueena on erityisesti kysyttävien sanojen ja niihin liittyvien vastausten käsittely.

UserService-luokan vastuualueena on erityisesti käyttäjien kirjautumis- ja rekisteröitymistoimintojen käsittely.

StatsService-luokan vastuualueena on erityisesti tilastojen ja kyselyjen jälkeisten palautteiden käsittely.

## Pysyväistallennus 
Tiedostojen luku- ja tallennustoiminnot on eriytettu DAO-suunnittelumallin mukaisesti dao-pakkauksen alle. FileUserDao toteuttaa UserDao-rajapinnan ja vastaa käyttäjätietojen käsittelystä, mm. käyttäjätietojen tallentamisesta erilliseen tekstitiedostoon. FileVocabularyDao toteuttaa VocabularyDao-rajapinnan ja vastaa sanastojen käsittelystä, mm. sanostojen lukemisen kolmesta erillisestä tekstitiedostosta.

Sovelluksen juureen sijoitettu konfiguraatiotiedosto [config.properties](https://github.com/tietotuomas/ot-harjoitustyo/blob/master/ITEnglish/config.properties) määrittelee erillisten tekstitiedostojen nimet.

Sovellus tallettaa käyttäjätiedot tekstitiedostoon seuraavalla tavalla:

<pre>
käyttäjä,$2a$10$2ay8znHtmEwleQNfgbyPU.UPg55y0QIP8xB/exEXfoo0exTdFXAgK,0,0,0
Testaaja,$2a$10$UtXQXjMEd1LFq7gksG4yA.1742St0o5uJ/LYHIZxGw3d65S.C5mxe,15,12,3
</pre>

Kullakin rivillä on ensiksi käyttäjän kirjautumistiedot - käyttäjätunnus ja salasana - joiden jälkeen käyttäjän ennätyspisteet kaikilla kolmella eri vaikeustasolla. Tiedot erotellaan pilkulla. Salasana on salakirjoitettu BCrypt-kirjaston avulla.

Sanastot on talletettu tekstitiedostoihin seuraavalla tavalla:

<pre>
informaatiosodankäynti, information warfare 
SaaS, Software as a Service
</pre>

Kullakin rivillä on ensiksi käännettävä sana/lyhenne, jota seuraa pilkulla ja (sanaston luettavuuden kohentamiseksi) välilyönnillä eroteltuna käännös ko. sanaan.

## Toiminnallisuuksia  
Seuraavassa joitakin oleellisia toiminnallisuuksia yksityiskohtaisesti läpikäytynä:

<img src="https://github.com/tietotuomas/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/K%C3%A4ytt%C3%A4j%C3%A4tilin%20luonti.png?raw=true">  
Sekvenssiokaaviossa on kuvattu tilanne, jossa käyttäjä luo uuden käyttäjätilin. Käyttäjän painettua "Luo uusi tunnus"-nappia, tapahtumankäsittelijä kutsuu UserServicen metodia createUser parametreinaan käyttäjän tekstikenttiin syöttämät tiedot (käyttäjätunnus ja salasana).  UserServicen createUser-metodi validoi ensiksi syötteet kutsumalla apumetodeja isNameEligible ja isPasswordEligible. Syötteet "Testaaja" ja "12345" ovat ohjeistuksen mukaisia.<br><br/>
Seuraavaksi createUser-metodi kutsuu UserDao-luokan metodia findByName parametrinaan "Testaaja". FindByName-metodi tarkistaa käyttäjien nimet sisältävän listan avulla, ettei sovelluksen käyttäjäkannasta löydy samannimistä käyttäjää. Tässä tapauksessa ei löydy, ja metodi palauttaa null - käyttäjätunnus on siis uniikki. Nyt syötteet on todettu tallennuskelpoisiksi. Tätä varten createUser kutsuu vielä apumetodia createHashedPassword, joka saa parametrinaan salasanan "12345" ja palauttaa sen salatussa muodossa BCrypt-kirjaston muuntamana.

Lopuksi createUser metodi kutsuu userDaon metodia create, joka luo parametriensa (käyttäjätunnus ja salakirjoitettu salasana) perusteella uuden ilmentymän User-luokasta, tallentaa sen users-listaan (oliomuuttuja) ja tallentaa sen myös pysyväistallennukseen kutsumalla save-metodia. Prosessi viedään loppuun, kun createUser palauttaa tämän jälkeen ItEnglishUi-luokalle merkkijonon "Success", jonka perusteella käyttöliittymän näkymä vaihdetaan.

<img src="https://github.com/tietotuomas/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/Palautteen%20luonti.png?raw=true">


## Sovelluksen arkkitehtuurin ja toiminnallisuuksien ongelmat/vajaavaisudet  

### Käyttöliittymä
Sovelluksen ilmeisin ongelma-alue lienee sen käyttöliittymä. Käyttöliittymä on toteutettu vain yhden luokan sisällä ja sisältää huomattavan määrän toisteista koodia. Käyttöliitymän voisi siis eriyttää useaksi eri luokaksi ja/tai toteuttaa isommalla määrällä yksinkertaisempia metodeja. Toisaalta näkymiä voisia yhdistää ja käyttöliittymän voisi myös rakentaa päätason asettelun ja alinäkymien pohjalle niin, että näkymät jakaisivat jonkinlaisen yhteisen valikon, eikä uutta näkymää tarvitsisi joka kertaa luoda tyhjästä (niin kuin nyt). Käyttöliittymän koodista löytyy varmasti myös muita ratkaisuja, joita voisi toteuttaa tarkoituksenmukaisemmalla tavalla.

### Riippuvuudet
QuestionService-luokka on riippuvainen StatsService-luokasta. Toiminnallisuudet olisi mahdollista toteuttaa sovelluksen rakennetta hieman muokkaamalla myös ilman tätä "turhaa" riippuvuutta.

### Sanaston muokkaus
Sovellus ei sisällä toiminnallisuutta, jolla sanastoa voitaisiin suoraan muokata. Nyt sanoja voidaan lisätä/poistaa vain tekstitiedoston kautta (sovellus on kuitenkin rakennettu niin, ettei tämä riko sovelluksen toimintaa).

### Tiedostorakenne
Sovelluksen tiedostorakenne voisi olla selkeämpi ainakin tekstitiedostojen osalta. Alunperin sovelluksessa oli niitä varten Resources-kansio, mutta jar-tiedoston suorittaminen tuntui tällöin tarpeettoman hankalalta.
