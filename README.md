# Sygnalizacja-2.0


Program Sygnalizacja2.0/ScreenshotChecker wykonuje co 2 sekundy zrzut ekranu, który następnie przeszukuje w poszukiwaniu piksela o odpowiednich parametrach. W zależności od wyniku program przekazuje za pomocą przekaźnika USB odpowiedni sygnał do zewnętrznego urządzenia sygnalizacyjnego. Okno programu pojawia się w obszarze, który jest przeszukiwany przez algorytm.

Ideą stojącą za tworzeniem projektu jest stworzenie narzędzia do wykrywania gorączki u człowieka na obrazie pochodzącym z kamery termowizyjnej. W przypadku modułu MaxRedLowestGreenPixelSearcher zaimplementowany jest dodatkowo mechanizm średniej, który rozwiązuje problem zmieniającej się temperatury człowieka w ciągu dnia.

Plik jar umożlwiający uruchomienie znajduje się pod scieżką out\artifacts\Sygnalizacja_2_0_jar.

Link do filmu demonstracyjnego: https://www.youtube.com/watch?v=G1a3qlFjB2c







Opis klas:

@ScreenshotChecker - Moduł obsługuje klase Timer oraz TimerTask i odpowiada za uruchamianie głównego procesu w 2 sekundowych odstępach.

@MaxRedLowestGreenPixelSearcher - Moduł służy do przeszukiwania obrazu niebiesko-żółto-czerwonego i znajdowania najbardziej czerwonego piksela. 

@UtilitiesManager - Klasa odpowiadająca za machanizm średniej oraz zapis danych do pliku.


Blinker - Klasa obsługująca połączenie z przekaźnikiem AVT (urządzeniem sygnalizacyjnym).

Configuration - Klasa importująca ustawienia początkowe tj. lokalizacja obrazu, rozmiar, port USB.

Console - Klasa konstruująca okno aplikacji.

Language - Klasa Stringów.

PixetToTemperatureConverter - Klasa implementująca funkcję konwertującą.

ScreenshotMaker - Klasa obsługująca tworzenie zrzutu ekranu.

WhiteCheckPixelSearcher - Moduł służy do przeszukiwania obrazu i znajdowania piksela o zadanych parametrach.

