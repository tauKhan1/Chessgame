__Käyttöohjeet__

Ohjelman käynnistyessä ruudulla näkyy shakkiruudukko
alkutilanteessa. Peliä pelataan siirtämällä nappuloita vuorotellen.
Nappuloita siirretään klikkaamalla hiirellä ensin ruutua, jossa siirrettävä
nappula sijaitsee ja sitten ruutua, jonne nappula halutaan liikuttaa. Tämän
jälkeen painetaan laudan oikealla puolella näkyvää nappulaa, jossa lukee "Move!",
jolloin siirto toteutuu jos se on sääntöjen mukainen. Vaihtoehtoisesti käyttäjä voi
peruuttaa siirron painamalla nappia, jossa lukee "Release!". 

Linnoitus suoritetaan antamalla siirtokäsky, jossa kuningas liikkuisi 2 askelta tornia kohti. Peli liikuttaa
tällöin tornin oikelle paikalle, jos linnoitus on laillinen.

Peli näyttää valitun siirrettävän nappulan ruudun tekstikentässä "From: ", ja kohderuudun kohdassa "To: "
Vuorossa olevan värin ruudun oikeassa ylälaidassa näkyvä tekstikenttä. Esim valkoisen vuorolla siinä lukee
"Turn: WHITE". Pelin päättymisestä ilmoittaa ruudun oikeaan alalaitaan ilmestyvä tekstikenttä, jossa lukee
joko voittajan väri tai ilmoitus tasapelistä. Peli lopetetaan sulkemalla ikkuna.


__Shakin säännöt lyhyesti__

Shakkia pelataan siirtämällä nappuloita vuorotellen. Shakin aloittaa valkoista väriä pelaava pelaaja.
Shakin tavoite on uhata vastapuolen kuningasta niin, ettei vastapelaajalle jää siirtomahdollisuuksia.

Nappula uhkaa toisen väristä nappulaa, mikäli tälle olisi mahdollista siirtyä toisen värisen nappulan ruutuun.
Jos pelaajan aloittaessa hänen kuninkaansa on uhattu, pelaajan on tehtävä siirto, joka purkaa tilanteen. Tilannetta
kutsutaan shakiksi. Jos shakkia ei voi purkaa, eli kaikkien siirtomahdollisuuksien jälkeen kuningas olisi edelleen uhattua,
vastapelaaja voittaa pelin.

Nappulaa ei saa siirtää niin, että oma kuningas tulisi uhatuksi. Jos toisella pelaajista ei ole siirtomahdollisuuksia
omalla vuorollaan, mutta hänen kuninkaansa ei ole uhattu, on tulos tasapeli.

Vastustajan nappuloita voi syödä siirtämällä oman värisen nappulan niiden tilalle, jolloin syöty nappula poistetaan
laudalta. Oman värisen nappulan päälle ei voi liikkua, eikä nappuloiden yli voi liikkua (paitsi ratsut).

__Sallitut Siirrot__

Sotilas: ![Valkoinen sotilas](PawnWhite.png) ![Musta sotilas](PawnBlack.png)

Sotilas voi normaalisti liikkua yhden ruudun kohti vastustajan aloituspäätyä suoraan, kun sotilas ei syö.
Tällöin sotilas ei saa syödä. Sotilas voi syödä liikkumalla yhden ruudun vinottain kohti vastustajan päätyä.

Pelissä vielä liikuttamaton sotilas voi näiden lisäksi liikkua 2 ruutua vastustajan päätyä kohti. 2 ruutua
edellisellä siirrolla liikutetun sotilaan voi syödä toinen sotilas liikkumalla ruutuun, johon tämä olisi päätynyt
jos se olisi liikkunut vain yhden ruudun. Tätä kutsutaan ohestalyönniksi.

Jos sotilas päätyy viimeiselle riville siirron seurauksena, se muuttuu kuningattareksi.


Lähetti: ![Valkoinen lähetti](BishopWhite.png) ![Musta lähetti](BishopBlack.png)

Lähetti voi liikkua niin monta ruutua kuin haluaa vinottain, esim 3 ruutua sivulle 3 ylös, 1 ruutu vasemmalle 1 ruutu alas
jne. Lähetti siis pysyy aina samanvärisellä ruudulla kuin mistä lähti.


Ratsu: ![Valkoinen ratsu](KnightWhite.png) ![Musta ratsu](KnightBlack.png)

Ratsu liikkuu siirrossa joko yhden askeleen vaakasuunnassa ja kaksi pystysuunnassa, tai sitten 2 askelta pystysuunnassa ja
yhden vaakasuunnassa. Ratsu voi ainoana nappulana pelissä loikata toisen nappulan yli siirrettäessä.


Torni: ![Valkoinen torni](RookWhite.png) ![Musta torni](RookBlack.png)

Torni liikkuu joko pystysuuntaan tai vaakasuuntaan, niin monta ruutua yhteen suuntaan kuin pelaaja haluaa.


Kuningatar: ![Valkoinen Kuningatar](QueenWhite.png) ![Musta kuningatar](QueenBlack.png)

Kuningatar voi liikkua niin monta askelta kuin haluaa mihin tahansa yhteen vaaka-, pysty- tai vinottaissuuntaan.
Kuningatar voi liikkua kuin sekä torni että lähetti.


Kuningas: ![Valkoinen Kuningas](KingWhite.png) ![Musta kuningas](KingBlack.png)

Kuningas liikkuu kuten kuningatar, mutta vain yhden ruudun siirrossa. Lisäksi kuningas ei voi siirtyä ruutuun, jota
toisenvärinen pelaaja uhkaa.

Kuningas voi yhdessä tornin kanssa tehdä linnoituksen. Linnoituksessa sekä tornia että kuningasta siirretään yhtäaikaa.
Kuningas liikkuu linnoituksessa 2 ruutua tornia kohti, kun torni puolestaan liikkuu tämän jälkeen kuninkaan viereen toiselle
puolelle, eli kuninkaan yli. Linnoituksen ehtona on, että sekä kuningas että saman värinen linnoittautuva torni eivät ole saaneet liikkua pelin aikana.
Lisäksi kuningas ei saa linnoituksen yhteydessä liikkua sellaisen ruudun yli, jossa toisenvärinen nappula uhkaisi sitä (Torni saa tulla uhatuksi).
Kuningas ei saa tehdä linnoitusta, jos se on parhaillaan uhattuna (shakki).
 