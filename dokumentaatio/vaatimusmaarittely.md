# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovelluksen käyttötarkoitus on englannin kielen IT-sanaston opiskelu. Käyttäjä valitsee sopivan vaikeustason ja sovellus arpoo käyttäjän valinnan mukaisesti joukon sanoja, jotka käyttäjä kääntää (kirjoittaen tai mahdollisesti oikean vaihtoehdoista valiten) suomesta englanniksi tai päinvastoin. Sovellus muistaa pysyväistallennuksen (ainakin aluksi tekstimuotoinen) avulla käyttäjän aikaisemmat tulokset, joiden perusteella käyttäjä voi seurata kehitystään.

## Käyttäjät

Sovelluksessa on aluksi vain yksi käyttäjärooli ("käyttäjä"). Myöhemmin mahdollisesti myös pääkäyttäjä.

## Käyttöliittymä

Sovelluksessa on graafinen käyttöliittymä.

## Perusversion toiminnallisuus

### Ennen kirjautumista

- käyttäjä voi rekisteröityä sovellukseen luomalla käyttäjätunnuksen
  - käyttäjätunnuksen täytyy olla uniikki ja sopivan pituinen

- käyttäjä voi kirjautua sovellukseen syöttämllä olemassa olevan käyttäjätunnuksen
  - jos käyttäjää ei olemassa, ilmoittaa sovellus tästä ja pyytää tunnuksen

### Kirjautumisen jälkeen

- käyttäjä voi valita vaikeustason sanastolle
  - aloittelija
  - keskiverto
  - mestari
 
- käyttäjä voi nähdä aikaisemmat pisteensä (oikeat vastaukset/kysymykset)

- käyttäjä voi vastata kysymyssarjaan, jossa on vakio määrä käännettäviä sanoja
  - kysymyksiin vastataan sana kerrallaan
  - jokaisen sanan jälkeen näytetään oikea vastaus ja ilmoitetaan käyttäjälle, oliko hänen vastauksena oikein vai väärin
  - sovellus antaa palautteen kysymyssarjaan vastaamisen jälkeen sekä sanallisesti että pisteiden muodossa

- käyttäjä voi kirjautua ulos järjestelmästä

## Jatkokehitysideoita

- käyttäjätunnuksen lisäksi salasana
- käyttäjälle mahdollisuus valita kysymyssarjan koko (esim. 5-20 sanan välillä)
- tietokantojen hyödyntäminen tallennuksessa
- pääkäyttäjä-luokan luominen
- pääkäyttäjälle mahdollisuus poistaa käyttäjätunnuksia ja lisätä sanastoa
