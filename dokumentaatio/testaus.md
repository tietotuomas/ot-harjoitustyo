# Testausdokumentti

Sovellusta on testattu JUnitilla luoduilla automaattisilla yksikkö- ja integraatiotesteillä sekä lisäksi manuaalisesti järjestelmätason testein. Kaikki testaus on tehty Linux-ympäristössä. Järjestelmätason testit on suoritettu sekä suoraan konsolista että erikseen generoidun jar-tiedoston kautta.

## Yksikkö- ja integraatiotestaus

Testauksessa on hyödynnetty Fake

### Testauskattavuus

Käyttöliittymän rakentava koodi eli käytännössä ItEnglishUi on jätetty pois testikattavuusraportista. Tämä huomioonottaen sovelluksen testauksen rivikattavuus on 92% ja haarautumakattavuus 88%.

<img src="https://github.com/tietotuomas/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/raportti.png" width="800">

Relevantteja testejä jäi luomatta mm. muutamalle UserService-luokan haaralle, jossa kutsuttiin DAO-luokan metodia tekstitiedostoon kirjoittamista varten.

## Järjestelmätestaus

Järjestelmätestaus on suoritettu kokonaan manuaalisesti. Testauksessa on pyritty huomioimaan tyypillisten käyttötapauksien lisäksi myös epätavallisimpia skenaariota, kuten ohjeistuksen vastaisia tai tyhjiä syötteitä. Sovellus testattu toimivaksi myös tilanteessa, jossa sanastotiedostoihin tehdään muutoksia, ts. sanoja lisätään tai poistetaan tekstitiedostoissa. Tällaisessa tapauksessa ominaisuudet toimivat odotetunlaisesti, kunhan sanoja on vähintään 10 per tiedosto. Esimerkiksi tilanteessa, jossa uusi sana tai sanoja lisätään beginner.txt-tiedostoon, käyttäjä menettää aikaisemmin ansaitun ruksin "I survived beginner ItEnglish"- valintaruudusta.

## Sovellukseen jääneet laatuongelmat

Sovellus ei ilmoita käyttäjälle kaikista virhetilanteista. Esimerkiksi jos sanasto-tiedostoja ei jostain syystä löydy, ei sovellus ilmoita tästä käyttäjälle millään tavalla.
