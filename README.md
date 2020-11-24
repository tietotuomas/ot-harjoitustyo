# IT ENGLISH

Sovelluksen käyttötarkoitus on englannin kielen IT-sanaston opiskelu. Käyttäjä valitsee sopivan vaikeustason ja sovellus arpoo käyttäjän valinnan mukaisesti joukon sanoja, jotka käyttäjä kääntää (kirjoittaen tai mahdollisesti oikean vaihtoehdoista valiten) suomesta englanniksi tai päinvastoin. Sovellus muistaa pysyväistallennuksen (ainakin aluksi tekstimuotoinen) avulla käyttäjän aikaisemmat tulokset, joiden perusteella käyttäjä voi seurata kehitystään.

## Dokumentaatio

[Vaatimusmäärittely](https://github.com/tietotuomas/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)  
[Tuntikirjanpito](https://github.com/tietotuomas/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)  
[Luokka/pakkauskaavio](https://github.com/tietotuomas/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md) (alustava runko)

## Komentorivitoiminnot

mvn compile exec:java -Dexec.mainClass=itenglish.Main 
mvn test  
mvn jacoco:report  
mvn jxr:jxr checkstyle:checkstyle  
