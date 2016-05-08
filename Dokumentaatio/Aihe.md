__Aihe:__ Toteutetaan shakkipeli, jota kaksi pelaajaa voi pelata siirtelemällä nappuloita vuorotellen.

__Käyttäjät:__ Pelaaja

__Pelaajan toiminnot:__

- pelin aloittaminen
- nappulan siirtäminen pelissä


__Ohjelman rakenne__

Pelin toimintaa ohjaa peliä kuvaava luokka Game. Käyttäjät tekevät Game-oliolle käyttöliittymän kautta siirtopyyntöjä, jotka Game-olio toteuttaa niiden ollessa sääntöjen mukaisia.
Game luo tälöin väliaikaisen Move olion, johon siirtoon liittyvät tallentuvat ja siirtyvät tarkistusolioille. MoveLegalityIdentifier luokan ilmentymältä Game-olio tarkistaa käyttäjän valitseman siirron laillisuuden.
CheckIdentifier luokan tehtävä on tarkistaa, syntyykö laudalle shakkitilanne siirron jälkeen. Board hallinoi shakkinappuloita ja niiden sijaintia. MovingRules sisältää nappulatyyppeihin liittyvät säännöt,
joita MoveLegalityIdentifier käyttää tarkistaessaan siirtojen laillisuutta. GameState olio kuvaa pelin tilaa, eli onko shakki, ja onko pelipäättynyt ja kuka voitti.
Pelin tilan selvittää StateResolver olio siirron jälkeen käyttäen MoveLister ja CheckIdentifier olioita. MoveLister tarkistaa pyydetyn nappulan mahdolliset siirrot, jota tarvitaan esim. pelin päättymisen
tarkistamiseen. SpecialEventHandler suorittaa onnistuneen siirron jälkeen siirtoon mahdollisesti liittyvät erikoissiirrot, esim. linnoituksen yhteydessä tornin liikuttamisen, sotilaan
ylenemisen kuningattareksi.


![Luokkakaavio:](Luokkakaavio.png)

__Sekvenssikaavioita__

![Onnistunut siirto](siirtoonnistunut.png)

![Epäonnistunut siirto](epaonnistunutsiirto.png)