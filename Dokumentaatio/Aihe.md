__Aihe:__ Toteutetaan shakkipeli, jota kaksi pelaajaa voi pelata siirtelemällä nappuloita vuorotellen. Lisäksi toteutetaan myös yksinkertainen tekoäly, jota vastaan voi pelata yksin.
Pelaajalla on mahdollisuus mm. aloittaa uusi peli normaalisti, tai itse rakentamastaan alkutilanteesta. Pelitilanteen voi tallentaa, sekä ladata keskeneräinen tallennettu peli.

__Käyttäjät:__ Pelaaja

__Pelaajan toiminnot:__

- pelin aloittaminen
- alkutilanteen luominen
- pelin tallenus
- pelin lataus
- nappulan siirtäminen pelissä
- siirron peruuttaminen


__Ohjelman rakenne__

Pelin toimintaa ohjaa peliä kuvaava luokka Game. Käyttäjät tekevät Game-oliolle käyttöliittymän kautta siirtopyyntöjä, jotka Game-olio toteuttaa niiden ollessa sääntöjen mukaisia.
MoveLegalityIdentifier luokan ilmentymältä Game-olio tarkistaa käyttäjän valitseman siirron laillisuuden. CheckIdentifier luokan tehtävä (kesken)
on tarkistaa, syntyykö laudalle shakkitilanne siirron jälkeen. Board hallinoi shakkinappuloita ja niiden sijaintia. MovingRules sisältää nappulatyyppeihin liittyvät säännöt,
joita MoveLegalityIdentifier käyttää tarkistaessaan siirtojen laillisuutta.

![Luokkakaavio:](Luokkakaavio.png)

__Sekvenssikaavioita__

![Onnistunut siirto](onnistunutsiirto.png)
![Epäonnistunut siirto](epaonnistunutsiirto.png)