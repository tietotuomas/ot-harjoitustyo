# IT ENGLISH

Sovelluksen käyttötarkoitus on englannin kielen IT-sanaston opiskelu. Käyttäjä valitsee sopivan vaikeustason ja sanamäärän, joiden perusteella sovellus luo aineistosta sattumanvaraisen kokoelman sanoja. Käyttäjä kääntää sanat suomesta englanniksi tai kirjoittaa englanninkielisen lyhenteen auki ohjeistuksen mukaan. Sovellus muistaa pysyväistallennuksen avulla käyttäjän aikaisemmat ennätystulokset, joiden perusteella käyttäjä voi seurata kehitystään.

## Dokumentaatio

[Vaatimusmäärittely](https://github.com/tietotuomas/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)  
[Tuntikirjanpito](https://github.com/tietotuomas/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)  
[Käyttöohje](https://github.com/tietotuomas/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)  
[Arkkitehtuurikuvaus](https://github.com/tietotuomas/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)  

## Komentorivitoiminnot

Sovellus voidaan käynnistää komentorivillä komennolla

```
mvn compile exec:java -Dexec.mainClass=itenglish.Main
```

Testit voidaan suorittaa komennolla

```
mvn test
```

Testikattavuusraportti voidaan luoda komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto *target/site/jacoco/index.html*

### Suoritettavan jarin luonti

Komento

```
mvn package
```

luo hakemistoon *target* suoritettavan jar-tiedoston *ItEnglish-1.0-SNAPSHOT.jar*


### Checkstyle

Checkstylen määrittelemät tarkistukset suoritetaan komennolla

```
 mvn jxr:jxr checkstyle:checkstyle
```

Raporttia mahdollisista virheistä voi tarkastella avaamalla selaimella tiedosto *target/site/checkstyle.html*


## Releaset
[Viikko 6](https://github.com/tietotuomas/ot-harjoitustyo/releases/tag/viikko6)
