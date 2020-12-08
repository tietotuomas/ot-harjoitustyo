# Käyttöohje

Lataa tiedosto [itenglish.jar]()

## Konfigurointi

Ohjelma olettaa, että sen käynnistyshakemistossa on konfiguraatiotiedosto *config.properties*, joka määrittelee käyttäjät ja harjoitussanaston. Tiedoston muoto on seuraava:

```
beginner=beginner.txt
average=average.txt
master=master.txt
users=users.txt
```

## Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla: 

```
java -jar itenglish.jar
```

## Kirjautuminen

Sovellus käynnistyy kirjautumisnäkymään:

<img src="https://github.com/tietotuomas/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/kirjaudu.png?raw=true">

Kirjautuminen onnistuu kirjoittamalla olemassaoleva käyttäjätunnus ja siihen liittyvän salasanan syötekenttään ja painamalla *Kirjaudu sisään*. Sovellus voidaan myös sulkea painamalla *Sulje sovellus*.

## Uuden käyttäjän luominen

Kirjautumisnäkymästä on mahdollista siirtyä uuden käyttäjän luomisnäkymään panikkeella *Luo uusi tunnus*.

Uusi käyttäjä luodaan syöttämällä tiedot (3-10 merkkiä pitkä käyttäjätunnus ja salasana) syötekenttiin ja painamalla *Luo uusi tunnus*.

<img src="https://github.com/tietotuomas/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/uusi.png?raw=true">

Jos käyttäjän luominen onnistuu, palataan kirjautumisnäkymään.

## Valintojen tekeminen

Onnistuneen kirjautumisen myötä siirrytään vaikeustason ja käännettävien sanojen lukumäärän valintaan. Ohjelma ohjeistaa käyttäjää vaikeustasojen eroista. Painamalla *Valitse* vahvistetaan valinnat. Painamalla *Kirjaudu ulos* päästään takaisin kirjautumisnäkymään.

<img src="https://github.com/tietotuomas/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/vaikeustaso.png?raw=true">

## Harjoitusnäkymä

Harjoitusnäkymässä käännetään sanoja ohjeistuksen mukaan. Painamalla *Lukitse vastaus* siirrytään seuraavaan sanaan ja näytetään samalla oikea vastaus. Painamalla *Lopeta* voidaan harjoitus lopettaa ja palata valintanäkymään (jo ennen kuin valittu määrä käännettävä sanoja on näytetty).

<img src="https://github.com/tietotuomas/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/harjoitus.png?raw=true">


