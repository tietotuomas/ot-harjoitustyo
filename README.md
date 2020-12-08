# IT ENGLISH

Sovelluksen käyttötarkoitus on englannin kielen IT-sanaston opiskelu. Käyttäjä valitsee sopivan vaikeustason ja sovellus arpoo käyttäjän valinnan mukaisesti joukon sanoja, jotka käyttäjä kääntää suomesta englanniksi. Sovellus muistaa pysyväistallennuksen avulla käyttäjän aikaisemmat ennätystulokset, joiden perusteella käyttäjä voi seurata kehitystään.

## Dokumentaatio

[Vaatimusmäärittely](https://github.com/tietotuomas/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)  
[Tuntikirjanpito](https://github.com/tietotuomas/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)
[Käyttöohje](https://github.com/tietotuomas/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)
[Arkkitehtuurikuvaus](https://github.com/tietotuomas/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

## Komentorivitoiminnot

mvn compile exec:java -Dexec.mainClass=itenglish.Main  
mvn test  
mvn jacoco:report  
mvn jxr:jxr checkstyle:checkstyle  
mvn package

## Releaset
[Viikko 6](https://github.com/tietotuomas/ot-harjoitustyo/releases/tag/viikko6)
