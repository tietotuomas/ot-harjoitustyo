# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovelluksen käyttötarkoitus on englannin kielen IT-sanaston opiskelu. Käyttäjä valitsee sopivan vaikeustason ja sanamäärän, joiden perusteella sovellus luo aineistosta sattumanvaraisen kokoelman sanoja. Käyttäjä kääntää sanat suomesta englanniksi tai kirjoittaa englanninkielisen lyhenteen auki ohjeistuksen mukaan. Sovellus muistaa pysyväistallennuksen avulla käyttäjän aikaisemmat ennätystulokset, joiden perusteella käyttäjä voi seurata kehitystään.

## Käyttäjät

Sovelluksessa on vain yksi käyttäjärooli ("käyttäjä").

## Käyttöliittymä

Sovelluksessa on graafinen käyttöliittymä.

## Perusversion toiminnallisuus

### Ennen kirjautumista

- käyttäjä voi rekisteröityä sovellukseen luomalla käyttäjätunnuksen ja siihen liittyvän salasanan
  - käyttäjätunnuksen täytyy olla uniikki
  - käyttäjätunnuksen ja salasanan täytyy olla sopivan pituinen
  - salasana tallennetaan sovelluksen pysyväistallenukseen asianmukaisesti suojattuna

- käyttäjä voi kirjautua sovellukseen syöttämllä olemassa olevan käyttäjätunnuksen ja siihen liittyvän salasanan
  - jos käyttäjätunnusta ei löydy tai käyttäjätunnus ei täsmää salasanaan, ilmoittaa sovellus tästä tarkoituksenmukaisesti

### Kirjautumisen jälkeen

- käyttäjä voi valita vaikeustason sanastolle
  - aloittelija
  - keskiverto
  - mestari
 
- käyttäjä voi tarkastaa tilastoista ennätyspisteensä (eniten oikeita vastauksia/vaikeustaso)

- käyttäjä voi vastata kysymyssarjaan, jossa on vakio määrä (5, 10 tai kaikki) käännettäviä sanoja
  - kysymyksiin vastataan sana kerrallaan
  - kysymykset arvotaan satunnaisesti
  - kukin kysymys esiintyy kysymyssarjassa korkeintaan kerran
  - kirjainkoko ei vaikuta vastauksen oikeellistuuteen
  - vastauksen voi lukita myös painamalla enteriä (klikkauksen lisäksi)
  - jokaisen sanan jälkeen ilmoitetaan käyttäjälle, oliko hänen vastauksensa oikein vai väärin
  - jokaisen sanan jälkeen käyttäjälle näytetään oikea vastaus
  - sovellus antaa palautteen kysymyssarjaan vastaamisen jälkeen sekä sanallisesti että pisteiden muodossa (montako oikeaa vastausta)

- käyttäjä voi palata kyselystä takaisin valintoihin ennen kuin valittu määrä käännettävä sanoja on näytetty
- käyttäjä voi kirjautua ulos järjestelmästä

## Jatkokehitysideoita

- tietokantojen hyödyntäminen pysyväistallennuksessa
- monipuolisempaa tilastointia, esimerkiksi käyttäjien välisiä pistevertailuja (TOP5 jne)
- pääkäyttäjä-luokan luominen
- pääkäyttäjälle mahdollisuus poistaa käyttäjätunnuksia ja muokata sanastoa
