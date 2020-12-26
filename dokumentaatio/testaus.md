# Testausdokumentti

Sovellusta on testattu JUnitilla luoduilla automaattisilla yksikkö- ja integraatiotesteillä sekä lisäksi manuaalisesti järjestelmätason testein. Kaikki testaus on tehty Linux-ympäristössä. Järjestelmätason testit on suoritettu sekä suoraan konsolista että erikseen generoidun jar-tiedoston kautta.

## Yksikkö- ja integraatiotestaus

Yksikkö- ja integraatiotestauksessa on pyritty monipuolisuuteen ja korkeaan kattavuuteen, mutta aivan kaikille haaroille ja reunatapauksille ei ole luotu testejä. Tämä on huomioitu järjestelmätestauksessa.

Sovelluslogiikan integraatiotestauksessa on hyödynnetty valekomponentteja FakeUserDao ja FakeVocabularyDao, joiden avulla on pystytty kiertämään pysyväistallennuksen aiheuttamat ongelmat (kuten jatkuvasti muuttuva sisältö) testauksen kontekstissa. DAO-luokkien testauksessa on puolestaan hyödynnetty JUnitin TemporaryFolder-ruleja, joiden avulla luodaan väliaikasia tiedostoja testauksen ajaksi.

### Testauskattavuus

Käyttöliittymän rakentava koodi eli käytännössä ItEnglishUi on jätetty pois testikattavuusraportista. Tämä huomioonottaen sovelluksen testauksen rivikattavuus on 92% ja haarautumakattavuus 88%.

<img src="https://github.com/tietotuomas/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/raportti.png" width="800">

Relevantteja testejä jäi luomatta mm. muutamalle UserService-luokan haaralle, jossa kutsuttiin DAO-luokan metodia tekstitiedostoon kirjoittamista varten.

## Järjestelmätestaus

Järjestelmätestaus on suoritettu kokonaan manuaalisesti. Testauksessa on pyritty huomioimaan tyypillisten käyttötapausten lisäksi myös epätavallisimpia skenaariota, kuten ohjeistuksen vastaisia tai tyhjiä syötteitä. Sovellus testattu toimivaksi myös tilanteessa, jossa sanastotiedostoihin tehdään muutoksia, ts. sanoja lisätään tai poistetaan tekstitiedostoissa. Tällaisessa tapauksessa ominaisuudet toimivat odotetunlaisesti, kunhan sanoja on vähintään 10 per tiedosto. Esimerkiksi tilanteessa, jossa uusi sana tai sanoja lisätään beginner.txt-tiedostoon, käyttäjä menettää aikaisemmin ansaitun ruksin "I survived beginner ItEnglish"- valintaruudusta.

## Sovellukseen jääneet laatuongelmat

Sovellus ei ilmoita käyttäjälle kaikista virhetilanteista. Esimerkiksi jos sanasto-tiedostoja ei jostain syystä löydy, ei sovellus ilmoita tästä käyttäjälle millään tavalla.
