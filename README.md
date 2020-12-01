# IT ENGLISH

Sovelluksen käyttötarkoitus on englannin kielen IT-sanaston opiskelu. Käyttäjä valitsee sopivan vaikeustason ja sovellus arpoo käyttäjän valinnan mukaisesti joukon sanoja, jotka käyttäjä kääntää suomesta englanniksi. Sovellus muistaa pysyväistallennuksen (ainakin aluksi tekstimuotoinen) avulla käyttäjän aikaisemmat tulokset, joiden perusteella käyttäjä voi seurata kehitystään.

## Dokumentaatio

[Vaatimusmäärittely](https://github.com/tietotuomas/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)  
[Tuntikirjanpito](https://github.com/tietotuomas/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)  
[Arkkitehtuurikuvaus](https://github.com/tietotuomas/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

## Komentorivitoiminnot

mvn compile exec:java -Dexec.mainClass=itenglish.Main  
mvn test  
mvn jacoco:report  
mvn jxr:jxr checkstyle:checkstyle  
mvn package

## Releaset
[Viikko 5](https://github.com/tietotuomas/ot-harjoitustyo/releases/tag/viikko5)  

- *Graafisessa käyttöliittymässä on joitakin komponentteja, joiden takana ei vielä ole oikeaa toiminnallisuutta.*
- *Itselleni tuntemattomasta syystä JavaFX ei ilmeisesti käyttäydy (näyttäydy) luomani jar-paketin kautta kaikilla kokoonpanoilla niin kuin olisi toivottavaa.*
