package ThermoModules;

import Main.UtilitiesManager;
import java.awt.*;
import java.awt.image.BufferedImage;


// Moduł MaxRedLowestGreenPixelSearcher wyszukuje najbardziej czerwony piksel w obrazie niebiesko-żółto-czerwonym.
// Jeśli atrybut Green znalezionego piksela przekracza zadaną tolerancje, funkcja searchForGivenPixel()
// zwraca liczbe 0. Jeśli atrybut Green  pozostaje w tolerancji, funkcja searchForGivenPixel()
// zwraca liczbe 1, a wartość zapisywana jest do średniej z 7 ostatnich wyszukań.


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
