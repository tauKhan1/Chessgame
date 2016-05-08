__K�ytt�ohjeet__

Ohjelman k�ynnistyess� ruudulla n�kyy shakkiruudukko
alkutilanteessa. Peli� pelataan siirt�m�ll� nappuloita vuorotellen.
Nappuloita siirret��n klikkaamalla hiirell� ensin ruutua, jossa siirrett�v�
nappula sijaitsee ja sitten ruutua, jonne nappula halutaan liikuttaa. T�m�n
j�lkeen painetaan laudan oikealla puolella n�kyv�� nappulaa, jossa lukee "Move!",
jolloin siirto toteutuu jos se on s��nt�jen mukainen. Vaihtoehtoisesti k�ytt�j� voi
peruuttaa siirron painamalla nappia, jossa lukee "Release!". 

Linnoitus suoritetaan antamalla siirtok�sky, jossa kuningas liikkuisi 2 askelta tornia kohti. Peli liikuttaa
t�ll�in tornin oikelle paikalle, jos linnoitus on laillinen.

Peli n�ytt�� valitun siirrett�v�n nappulan ruudun tekstikent�ss� "From: ", ja kohderuudun kohdassa "To: "
Vuorossa olevan v�rin ruudun oikeassa yl�laidassa n�kyv� tekstikentt�. Esim valkoisen vuorolla siin� lukee
"Turn: WHITE". Pelin p��ttymisest� ilmoittaa ruudun oikeaan alalaitaan ilmestyv� tekstikentt�, jossa lukee
joko voittajan v�ri tai ilmoitus tasapelist�. Peli lopetetaan sulkemalla ikkuna.


__Shakin s��nn�t lyhyesti__

Shakkia pelataan siirt�m�ll� nappuloita vuorotellen. Shakin aloittaa valkoista v�ri� pelaava pelaaja.
Shakin tavoite on uhata vastapuolen kuningasta niin, ettei vastapelaajalle j�� siirtomahdollisuuksia.

Nappula uhkaa toisen v�rist� nappulaa, mik�li t�lle olisi mahdollista siirty� toisen v�risen nappulan ruutuun.
Jos pelaajan aloittaessa h�nen kuninkaansa on uhattu, pelaajan on teht�v� siirto, joka purkaa tilanteen. Tilannetta
kutsutaan shakiksi. Jos shakkia ei voi purkaa, eli kaikkien siirtomahdollisuuksien j�lkeen kuningas olisi edelleen uhattua,
vastapelaaja voittaa pelin.

Nappulaa ei saa siirt�� niin, ett� oma kuningas tulisi uhatuksi. Jos toisella pelaajista ei ole siirtomahdollisuuksia
omalla vuorollaan, mutta h�nen kuninkaansa ei ole uhattu, on tulos tasapeli.

Vastustajan nappuloita voi sy�d� siirt�m�ll� oman v�risen nappulan niiden tilalle, jolloin sy�ty nappula poistetaan
laudalta. Oman v�risen nappulan p��lle ei voi liikkua, eik� nappuloiden yli voi liikkua (paitsi ratsut).

__Sallitut Siirrot__

Sotilas: ![Valkoinen sotilas](PawnWhite.png) ![Musta sotilas](PawnBlack.png)

Sotilas voi normaalisti liikkua yhden ruudun kohti vastustajan aloitusp��ty� suoraan, kun sotilas ei sy�.
T�ll�in sotilas ei saa sy�d�. Sotilas voi sy�d� liikkumalla yhden ruudun vinottain kohti vastustajan p��ty�.

Peliss� viel� liikuttamaton sotilas voi n�iden lis�ksi liikkua 2 ruutua vastustajan p��ty� kohti. 2 ruutua
edellisell� siirrolla liikutetun sotilaan voi sy�d� toinen sotilas liikkumalla ruutuun, johon t�m� olisi p��tynyt
jos se olisi liikkunut vain yhden ruudun. T�t� kutsutaan ohestaly�nniksi.

Jos sotilas p��tyy viimeiselle riville siirron seurauksena, se muuttuu kuningattareksi.


L�hetti: ![Valkoinen l�hetti](BishopWhite.png) ![Musta l�hetti](BishopBlack.png)

L�hetti voi liikkua niin monta ruutua kuin haluaa vinottain, esim 3 ruutua sivulle 3 yl�s, 1 ruutu vasemmalle 1 ruutu alas
jne. L�hetti siis pysyy aina samanv�risell� ruudulla kuin mist� l�hti.


Ratsu: ![Valkoinen ratsu](KnightWhite.png) ![Musta ratsu](KnightBlack.png)

Ratsu liikkuu siirrossa joko yhden askeleen vaakasuunnassa ja kaksi pystysuunnassa, tai sitten 2 askelta pystysuunnassa ja
yhden vaakasuunnassa. Ratsu voi ainoana nappulana peliss� loikata toisen nappulan yli siirrett�ess�.


Torni: ![Valkoinen torni](RookWhite.png) ![Musta torni](RookBlack.png)

Torni liikkuu joko pystysuuntaan tai vaakasuuntaan, niin monta ruutua yhteen suuntaan kuin pelaaja haluaa.


Kuningatar: ![Valkoinen Kuningatar](QueenWhite.png) ![Musta kuningatar](QueenBlack.png)

Kuningatar voi liikkua niin monta askelta kuin haluaa mihin tahansa yhteen vaaka-, pysty- tai vinottaissuuntaan.
Kuningatar voi liikkua kuin sek� torni ett� l�hetti.


Kuningas: ![Valkoinen Kuningas](KingWhite.png) ![Musta kuningas](KingBlack.png)

Kuningas liikkuu kuten kuningatar, mutta vain yhden ruudun siirrossa. Lis�ksi kuningas ei voi siirty� ruutuun, jota
toisenv�rinen pelaaja uhkaa.

Kuningas voi yhdess� tornin kanssa tehd� linnoituksen. Linnoituksessa sek� tornia ett� kuningasta siirret��n yht�aikaa.
Kuningas liikkuu linnoituksessa 2 ruutua tornia kohti, kun torni puolestaan liikkuu t�m�n j�lkeen kuninkaan viereen toiselle
puolelle, eli kuninkaan yli. Linnoituksen ehtona on, ett� sek� kuningas ett� saman v�rinen linnoittautuva torni eiv�t ole saaneet liikkua pelin aikana.
Lis�ksi kuningas ei saa linnoituksen yhteydess� liikkua sellaisen ruudun yli, jossa toisenv�rinen nappula uhkaisi sit� (Torni saa tulla uhatuksi).
Kuningas ei saa tehd� linnoitusta, jos se on parhaillaan uhattuna (shakki).
 