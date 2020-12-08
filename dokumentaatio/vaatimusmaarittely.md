# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovelluksen käyttötarkoitus on englannin kielen IT-sanaston opiskelu. Käyttäjä valitsee sopivan vaikeustason ja sovellus arpoo käyttäjän valinnan mukaisesti joukon sanoja, jotka käyttäjä kääntää (kirjoittaen tai mahdollisesti oikean vaihtoehdoista valiten) suomesta englanniksi tai päinvastoin. Sovellus muistaa pysyväistallennuksen avulla käyttäjän aikaisemmat ennätystulokset, joiden perusteella käyttäjä voi seurata kehitystään.

## Käyttäjät

Sovelluksessa on aluksi vain yksi käyttäjärooli ("käyttäjä"). Myöhemmin mahdollisesti myös pääkäyttäjä.

## Käyttöliittymä

Sovelluksessa on graafinen käyttöliittymä. (**tehty pieniä lisäyksiä taas**)

## Perusversion toiminnallisuus

### Ennen kirjautumista (**tehty**)

- käyttäjä voi rekisteröityä sovellukseen luomalla käyttäjätunnuksen ja siihen liittyvän salasanan (**tehty**)
  - käyttäjätunnuksen täytyy olla uniikki (**tehty**)
  - käyttäjätunnuksen ja salasanan täytyy olla sopivan pituinen (**tehty**)

- käyttäjä voi kirjautua sovellukseen syöttämllä olemassa olevan käyttäjätunnuksen (**tehty**)
  - jos käyttäjätunnusta ei löydy tai käyttäjätunnus ei täsmää salasanaan, ilmoittaa sovellus tästä (**tehty**)

### Kirjautumisen jälkeen

- käyttäjä voi valita vaikeustason sanastolle
  - aloittelija
  - keskiverto
  - mestari
 
- käyttäjä voi nähdä aikaisemmat pisteensä (oikeat vastaukset/kysymykset)

- käyttäjä voi vastata kysymyssarjaan, jossa on vakio määrä (5, 10 tai kaikki) käännettäviä sanoja
  - kysymyksiin vastataan sana kerrallaan
  - kysymykset arvotaan satunnaisesti
  - kukin kysymys esiintyy kysymyssarjassa korkeintaan kerran
  - jokaisen sanan jälkeen ilmoitetaan käyttäjälle, oliko hänen vastauksena oikein vai väärin 
  - jos käyttäjän vastaus oli väärin, näytetään vastaajalle oikea vastaus
  - sovellus antaa palautteen kysymyssarjaan vastaamisen jälkeen sekä sanallisesti että pisteiden muodossa

- käyttäjä voi palata kyselystä takaisin valintoihin ennen kuin valittu määrä käännettävä sanoja on näytetty (**tehty**)
- käyttäjä voi kirjautua ulos järjestelmästä (**tehty**)

## Jatkokehitysideoita

- vastauksien tulkinta niin, että esim. artikkelit tai kirjainten koko ei vaikuta vastauksen oikeellisuuteen
- salasana käsitellään tarkoituksenmukaisesti suojattuna/salattuna
- tietokantojen hyödyntäminen tallennuksessa
- pääkäyttäjä-luokan luominen
- pääkäyttäjälle mahdollisuus poistaa käyttäjätunnuksia ja lisätä sanastoa
- sanaston harjoittelun rinnalle toinen kysymysmuoto, jossa käyttäjä voi opiskella englanninkielisiä IT-lyhenteitä
