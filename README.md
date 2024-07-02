# Haladó Java beadandó

ELTE IK
_2023/24 tavaszi félév_
_Tárgyfelelős: Kitlei Róbert_

## Feltételek
A beadandó általános feltételei az előadás Canvasében, a Tematika oldalon olvashatók.
Csalni tilos. Aki mégis megteszi, és kiderül, elesik a tárgy teljesítésétől.
További részletekért lásd a Tematika oldal megfelelő aloldalát.
A megoldás legyen a lehető legjobb minőségű.
A feladatban megadott neveket betűre pontosan úgy kell használni, ahogy meg vannak adva.
A Java nyelv szokásos konvencióit követni kell.
A kód szerkezete, a változók nevei legyenek megfelelők.
Beadás határideje.
Aki egyáltalán nem tölt fel megoldást, elesik a tárgy teljesítésétől.
A megoldás a határidőn belül többször is beadható.
Az utoljára beadott megoldás kerül értékelésre.
A határidő éles.
Nem célszerű kicentizni az időt. Aki mégis megteszi, és lekésik akár pár perccel, csak magára vethet.
Beadás formátuma.
Az elkészített megoldást zip formátumba csomagolva kell feltölteni a Canvasbe.
A zip csak a megoldás forrásfájljait tartalmazza, a megfelelő könyvtárszerkezetben.
Ha a megoldás működtetéséhez szükségesek további (pl. bemeneti) fájlok, azok a zip gyökerében legyenek.
Más fájlokat (pl. .class) ne tartalmazzon a zip.

## Horgászverseny
Ebben a feladatban egy horgászverseny győztesét kell tudni megtalálni különböző szabályok alapján. A feladat megoldása során figyelned kell a helyes láthatósági szabályok megalkotására is. A feladat minden osztályát a hu.elte csomagban helyezd el.

### 1. Készítsd el a következő típusokat (5 pont):

#### FishSpecies
Ez egy enum aminek 4 értéke van:

- BREAM
- CARP
- PIKE
- RUDD

Mindegyik érték ismeri magáról, mennyi a legkisebb kifogható méret belőlük, és hogy hány pontot ér egy versenyen. Ezek rendre:

- BREAM: 0 cm, 2 pont
- CARP: 30 cm, 5 pont
- PIKE: 40 cm, 10 pont
- RUDD: 0 cm, 1 pont

Minden enum értéken legyen egy boolean isProtected() metódus, mely eldönti, hogy a hal méretkorlátozás alá esik-e. (A legkisebb kifogható méret nem nulla.)

Minden enum értéken legyen egy boolean canBeKept(int) függvény, mely abban az esetben tér vissza igaz értékkel, ha a paraméterül kapott szám legalább annyi, mint a tárolt legkisebb kifogható méret.

Minden enum értéken legyen egy int getPoints() függvény, mely visszaadja, hogy az adott hal hány pontot ér egy versenyen.

#### Fish
Ez az osztály egy halat ír le, és 2 adattagot tartalmaz:

- FishSpecies species a hal fajtája
- int length a hal hossza

Legyenek rajta a következő függvények:

- int getLength() visszaadja a hosszát
- FishSpecies getSpecies() visszaadja a halfajt
- boolean isProtected() visszadja, hogy a hal védett-e
- boolean isValid() visszaadja, hogy az adott hal megtartható-e (legalább olyan nagy, mint a méretkorlátozás)

#### FisherMan
Ez az osztály egy horgászt ír le, és 2 adattagot tartalmaz:

- String name a horgász neve
- List<Fish> caught a kifogott halak listája

Legyenek rajta a következő függvények:

- String getName() visszaadja a horgász nevét
- Stream<Fish> streamCatches() visszaadja a kifogott halak egy folyamát
- void addCatch(Fish) egy újabb halat tesz a kifogott halak listájába

### 2. Bírák (5 pont)

#### Judge
Ez az osztály egy bíró. A bíró 2 generikus paraméterrel rendelkezik (T1 és T2), valamint 3 darab lambda kifejezést tartalmaz, melyeket konstruktorparaméterként kap meg. A lambda-kifejezések helyes típusának megválasztása a te feladatod.

1. <?> toStream ez a lambda T1 típusból Stream<T2>-t képes előállítani
2. <?> isValid ez a lambda egy T2 típusról eldönti, hogy a bíró figyelembe veszi-e
3. <?> toValue ez a lambda egy T2 típusból egy int-et állít elő.

A bírónak 2 függvénye van:

- int judgeParticipant(T1), mely egy T1 típusú értéket kap, és a következőket teszi vele:
    - az 1. lambda segítségével Stream<T2>-vé alakítja
    - a 2. lambda segítségével kiszűri az elfogadható elemeket
    - a 3. lambda segítségével a Stream-ből IntStream-et készít.
    - az értékeket összeadja
    - az összeggel visszatér
- T1 findBestParticipant(List<T1>) ez a függvény a következőket teszi:
    - a listából Stream<T1>-et képez
    - a Stream-ből megkeresi azt az értéket, amire a judgeParticipant értéke a legnagyobb
    - a kapott értékkel visszatér
    - ha nincs ilyen érték, NoSuchElementException-t dob.

#### FishingJudges
Ez az osztály 2 statikus, final adattagot tartalmaz. Mindkettő típusa Judge<FisherMan, Fish>

- POINT_JUDGE ez a bíró csak olyan halakat vesz figyelmbe, melyeket szabad volt kifogni (a mérete legalább a legkisebb kifogható méret), és azt a horgászt hozza ki győztesen, aki a legtöbb pontot szerezte
- COUNT_JUDGE ez a bíró bármely halat elfogadja, és azt a horgászt hozza ki győztesen, amely a legtöbb halat fogta.

### 3. Teszteld az osztályaidat (5 pont)
A tesztek a FishingTest osztályba kerüljenek.

- írj teszteket, mely validálják, hogy az enum értékek a megfelelően valósították meg a függvényeiket
- írj tesztet, mely megmutatja, hogy a POINT_JUDGE nem veszi figyelembe a méreten aluli halakat
- írj tesztet, mely megmutatja, hogy a POINT_JUDGE képes a kevesebb, de több pontértékű halat fogó horgászt győztesen kihozni
- írj tesztet, mely megmutatja, hogy a COUNT_JUDGE a legtöbb halat fogó horgászt hozza ki győztesen
- írj tesztet, mely megmutatja, hogy egy Judge NoSuchElementException-t dob, ha nincs legjobb résztvevő