package ThermoModules;

import Main.UtilitiesManager;
import java.awt.*;
import java.awt.image.BufferedImage;



// Opis modułu:

// Moduł służy do wykrywania podwyższonej temperatury człowieka znajdującego się
// w świetle kamery termowizyjnej.




//Więcej:

// Funkcja searchForGivenPixel() - odpowiada za przeszukanie wszystkich pixeli w obrazie, określenie czy znaleziony został piksel
// bliski czerwonemu (zmienna "isManFound") oraz odczytanie paramentrów najbardziej czerwonego
// piksela (zmienna "maxRedLowestGreen"). Funkcja zwraca wartości:
// 0 - gdy na obrazie znajduje sie człowiek oraz zadana tolerancja zostanie przekroczona,
// 1 - gdy na obrazie znajduje się człowiek oraz tolerancja nie została przekroczona,
// 2 - gdy na obrazie nie znajduje się człowiek.

// Funkcja updateAvarage() -  odpowiada za ciągłe aktualizowanie
// średniej temperatury badanych jednostek, która zmienia się w ciągu dnia. Przekroczenie
// średniej o zadaną wartość sygnalizowane jest kolorem czerwonym, a wynik nie jest wliczany do średniej.






// Jeszcze więcej:

// Zakres kolorów obrazu: niebiesko-żółto-czerwony.
// Algorytm przeszukuje obraz pixel po pixelu w poszukiwaniu koloru, którego atrybuty RGB są równe (255,minimum,dowolne).
// Atrybut B jest dowolny ponieważ RGB=(255,0,0) to kolor czerwony, a RGB=(255,0,255) to kolor fioletowy niewystępujący w
// obrazach źródłowych.
// Zmienna "isManFind" to flaga oznaczajaca, ze w zbiorze pixelów znaleziono kolor odpowiadający człowiekowi na termogramie.
// "isManFind"=true pozwala uruchamiać kod drukujący obraz do folderu "./TestTemperatury" oraz txt z danymi.




public class MaxRedLowestGreenPixelSearcher implements PixelSearcher {


    Color pixelSearched;
    boolean isManFound;
    int maxRedLowestGreen;


    UtilitiesManager utilitiesManager;


    public MaxRedLowestGreenPixelSearcher(){
        pixelSearched =null;
        utilitiesManager = new UtilitiesManager(7);
    }


    @Override
    public int searchForGivenPixel(BufferedImage cropped_capture) {


        isManFound =false;
        maxRedLowestGreen =255;


        for(int j=0; j<cropped_capture.getHeight();j=j+1) {
            for(int i=0; i<cropped_capture.getWidth();i=i+1) {

                pixelSearched = new Color(cropped_capture.getRGB(i, j));

                if(pixelSearched.getRed()==255) {

                    isManFound =true;

                    if(pixelSearched.getGreen() < maxRedLowestGreen) {
                        maxRedLowestGreen = pixelSearched.getGreen();
                    }

                }

            }
        }



        if(isManFound ==true) {

            utilitiesManager.saveDataToFiles(cropped_capture, maxRedLowestGreen);

            if(maxRedLowestGreen < utilitiesManager.getAverage()-50){
                return 0;
            }
            else{
                utilitiesManager.updateAverage(maxRedLowestGreen);
                return 1;
            }
        }

        return 2;
    }

}
