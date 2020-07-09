package ThermoModules;

import Main.Console;
import java.awt.*;
import java.awt.image.BufferedImage;

// Opis modułu:
// Moduł służy do wyszukiwania w dostarczonym obrazie piksela o zadanych wartościach red, green i blue.


public class WhitePixelSearcher implements PixelSearcher{

    Color mycolor;


    public WhitePixelSearcher() {

        mycolor=null;

    }


    @Override
    public int searchForGivenPixel(BufferedImage cropped_capture) {


        for(int j=0; j<cropped_capture.getHeight();j=j+1) {
            for(int i=0; i<cropped_capture.getWidth();i=i+1) {

                mycolor = new Color(cropped_capture.getRGB(i, j));

                if(mycolor.getRed()>=255 && mycolor.getGreen()>=255 && mycolor.getBlue()>=255){
                    Console.mainInformationForUserTextField.setText("ALERT: RGB=(255,255,255) found");
                    return 0;
                }


            }
        }
        Console.mainInformationForUserTextField.setText("Searching for RGB=(255,255,255)");
        return 1;
    }
}