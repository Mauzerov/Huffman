# Kompresja Huffmana

> Jeden z najprotsztych, bezstratnym algorytmów kompresujących, jednak nie należy do najszybszych. \
> Stosuje się go często jako ostatni etap kompresji po użyciu innych metod kompresji, bardzo żadko stosuje się go oddzilnie. \
> Kompresja Huffmana nie jest doskonała dlatego stosuje sie ją ze względu na prostote oraz brak ograniczeń patentowych. \
> Wykozystać można go do dowolnych danych, nalezy tylko pamiętać że proporcja komprsji danych bez powtórzeń będzie mniejsze niż 1, co w rezultacie spowoduje zwiekszenie sie pliku wynikowego

### Zalety:
- Zmienno długośiowy schemat kodowania, nie wymaga dodatkowych znaczników do oddzielania kolejnych wartości, gdyż zmienne długości kodów same wyznaczają koniec elementu
- Proporcje kompresji są wysokie, poprzez przypisywanie niskich wartości dużym ciągom danych 
- Bezstratna kompresja
- Szybka Dekompresja
- Prosty w implementacji
### Wady:
- Wymagana jest częstotliwość poszczególnych elementów przed rozpoczęciem
- Wynikiem kopresji jest drzewo, które może być trudne do zrozumienia, przez co utrudnić debugownaie
- Proces kompresji jest długi, zwłaszcza przy dużej ilości danych- interpretacja tablicy i generowanie drzewa zajmuje najwięcej czasu
- Nie efektywny przy danych z mała ilością powtórzeń jak i krótkich danych

### Kompresja:

> ### Proces Kompresji dzieli się na etapy:
> 1. Pozyskiwanie Danych
> 2. Tworzenie tablicy częstotliwości
> 3. Tworzenie kolejki piorytetowej w postaci kopca binarnego, gdzie najmniejsze są na szczycie
> 4. Tworzenie drzewa Huffmana
> 5. Przypisywanie Kodów
> 6. Finalna Kompresja
> ### 1. Pozyskiwanie Danych
> > Zdobycie ciągu danych do kompresji.
> ### 2. Tworzenie tablicy częstotliwości
> > Dla każdego unikalnego elementu w ciągu danych przypisać ilość jego wystąpień.
> ### 3. Tworzenie kolejki piorytetowej
> > Dla każdego elementu tablicy: do kolejki dodaje się (Leaf Node) przechowujący symbol z jego ilością wystąpień.
> ### 4. Tworzenie drzewa Huffmana
> > Dopóki kolejka posiada przynajmniej 2 elementy: \
> > zdejmuje się z wieszchu 2 najmniej częste elementy. \
> > tworzy się z nich nowy (Node), gdzie częstotliwością jest suma 2 zdjętych (Node). \
> > nowe (Node) przechowuje również lewy (Node) i prawy (Node) przypisane zdjętym (Node) w dowolnej kolejnosci. \
> > następnie dodaje się nowy (Node) do kolejki. \
> > ostatnim elementem kolejki jest głowa drzewa Huffmana.
> ### 5. Przypisywanie Kodów 
> > Kody przypisuje się przez przechodznie drzewa: \
> > Gdy przechodzi się w lewo przypisuje sie kod 0. \
> > Gdy przechodzi się w prawo przypisuje sie kod 1. \
> > Kodem dla elemnetu jest konkatenacja (0, 1) napotanych na drodze od głowy do danego elementu.
> ### 6. Finalna Kompresja
> > Każdy element w naszym ciągu danych zamieniamy na Kod przypisany z drzewa. \
> > Aby umożliwić dekompresacje należy zapisać również drzewo, lub tablice, albo oba. \
> > Przechowywanie obu jest najbardziej efektywne, jak i najszybsze do zdekodowania. \
> > Nie przechowywanie żadnego, sprawi że dokładne dekodowanie będzie praktycznie niewykonalne.
### Dekompresja:
> Proces Dekompresji polega na przejsciu drzewa, które mogło być odczytane z nagłówka pliku, bądź wygenerowane z tablicy częstotliwości, w zależności co zostało zapisane. \
> Następnym krokiem jest przejcie drzewa "bit po bicie"(0, 1), \
> z zasadami takimi samymi jak przy kompresji 0 - lewo, 1 - prawo. \
> w momencie napotkania wartości w drzewie wypisuje się ją i wraca na początek drzewa. \
> natomiast w momencie napotkania końca strumienia "bitów", dekompresja zostaje zakończona.
