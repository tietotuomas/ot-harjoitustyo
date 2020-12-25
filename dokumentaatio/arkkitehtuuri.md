# Arkkitehtuurikuvaus  

## Sovelluksen rakenne ja riippuvuudet
 
### Pakkauskaavio
<img src="https://github.com/tietotuomas/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/UML-kaavio.png?raw=true">  
Pakkauskaavioon on merkattu pakkauksien ja luokkien väliset riippuvuudet. Sovelluksessa on pyritty noudattamaan kerrosarkkitehtuurin periaatteita ja ehkäisemään riippuvuuksia. Pakkaus itenglish.ui on riippuvainen pakkauksesta itenglish.domain, mutta ei pakkauksesta itenglish.dao. Pakkauksen ui luokassa ItEnglishUi käsitellään dao-pakkauksen luokkia vain riippuvuuksien injektointiin. Domain-pakkaus on riippuvainen pakkauksesta dao.

## Soveluslogiikka  

<img src="https://github.com/tietotuomas/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/Uuden%20satunnaisen%20sanan%20hakeminen.png?raw=true">  

### Tiedostot

Sovellus lukee ja tallentaa käyttäjien tiedot erilliseen tekstitiedostoon. Sovellus myös lukee käännettävät sanat kolmesta erillisestä tekstitiedostosta.

Sovelluksen juureen sijoitettu konfiguraatiotiedosto [config.properties](https://github.com/tietotuomas/ot-harjoitustyo/blob/master/ITEnglish/config.properties) määrittelee tiedostojen nimet.

Sovellus tallettaa käyttäjät seuraavalla tavalla:

<pre>
Testaaja,Testaaja,0,0,0
Käyttäjä,salasana,15,12,3
</pre>

Kullakin rivillä on ensiksi käyttäjän kirjautumistiedot - käyttäjätunnus ja salasana - joiden jälkeen käyttäjän ennätyspisteet kolmella eri vaikeustasolla. Tiedot erotellaan pilkulla.

Sanasto on talletettu seuraavalla tavalla:

<pre>
informaatiosodankäynti, information warfare 
SaaS, Software as a Service
</pre>

Kullakin rivillä on ensiksi käännettävä sana/lyhenne, jota seuraa pilkulla ja (sanaston luettavuuden kohentamiseksi) välilyönnillä eroteltuna käännös ko. sanaan.

