package ThermoModules;

import Main.AverageManager;
import Main.Console;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Queue;

import static Main.PixelToTemperatureConverter.convertPixelToTemperature;


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

// Funkcja saveDataToFiles() - odpowiada za zapis zdjec, godziny i wartosci maxRedLowestGreen do plikow bmp i txt.




// Jeszcze więcej:

// Zakres kolorów obrazu: niebiesko-żółto-czerwony.
// Algorytm przeszukuje obraz pixel po pixelu w poszukiwaniu koloru, którego atrybuty RGB są równe (255,minimum,dowolne).
// Atrybut B jest dowolny ponieważ RGB=(255,0,0) to kolor czerwony, a RGB=(255,0,255) to kolor fioletowy niewystępujący w
// obrazach źródłowych.
// Zmienna "isManFind" to flaga oznaczajaca, ze w zbiorze pixelów znaleziono kolor odpowiadający człowiekowi na termogramie.
// "isManFind"=true pozwala uruchamiać kod drukujący obraz do folderu "./TestTemperatury" oraz txt z danymi.




public class MaxRedLowestGreenPixelSearcher implements PixelSearcher {

    // Zmienne dla funkcji searchGivenPixel().
    Color pixelSearched;
    boolean isManFound;
    int maxRedLowestGreen;

    // Zmienne dla funkcji updateAverage().
    Double average;
    int howManySamplesForAverage;
    Double sum;
    Queue<Integer> samplesQueue;

    // Zmienne dla funkcji convertPixelToTemperature().
    Double result, number, nominator, denominator,first;
    DecimalFormat rounding;

    // Zmienne dla funkcji saveDataToFiles().
    int iterator_for_isManFound_screenshots;

    AverageManager averageManager;



    public MaxRedLowestGreenPixelSearcher(){

        pixelSearched =null;
	    average=0.0;
	    howManySamplesForAverage=7;
	    samplesQueue = new LinkedList<>();
	    rounding = new DecimalFormat("0.00");
	    iterator_for_isManFound_screenshots=0;

        averageManager = new AverageManager(7);


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

            saveDataToFiles(cropped_capture);

            if(maxRedLowestGreen <averageManager.getAverage()-50){
                return 0;
            }
            else{
                averageManager.updateAverage(maxRedLowestGreen);
                return 1;
            }
        }

        return 2;
    }




    public void updateAverage(int maxRedLowestGreen){

        sum=0.0;

        if(samplesQueue.size()==howManySamplesForAverage)
            samplesQueue.remove();
        samplesQueue.add(maxRedLowestGreen);


        for (Integer integer : samplesQueue) {
            sum = sum + integer;
        }

        average = sum/ samplesQueue.size();

        Console.mainInformationForUserTextField.setText("Source for average[px]:" + samplesQueue +" Last temp:" + convertPixelToTemperature((double) maxRedLowestGreen)+ " Average[°C]: "+ convertPixelToTemperature(sum/ samplesQueue.size()));


    }



    public String convertPixelToTemperature(Double maxRedLowestGreen){

            number = maxRedLowestGreen/500.7978;
            denominator = Math.pow(number,1.366587)+1;
            nominator = (42.03134 - 9.085402);
            first=9.085402;

        result = first + nominator / denominator;


        return rounding.format(result);
    }


    public void saveDataToFiles(BufferedImage cropped_capture){

        try {
            iterator_for_isManFound_screenshots++;
            ImageIO.write(cropped_capture, "bmp", new File("./TestTemperatury/manFound_screenshoot_"+iterator_for_isManFound_screenshots+".bmp"));

            PrintWriter writer = new PrintWriter(new FileWriter("./TestTemperatury/CelsjuszTest.txt", true));
            writer.println(LocalDateTime.now()+ " rGb: " + maxRedLowestGreen);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }





    }



}
