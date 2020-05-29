package ThermoModules;

import Main.Console;
import java.awt.*;
import java.awt.image.BufferedImage;

//GIVEN RANGE SEARCHING.
// Looking for pixel (or range) with RGB attributes given.
// All three attributes checking.


public class WhitePixelSearcher implements PixelSearcher{

    Color mycolor;


    public WhitePixelSearcher() {

        mycolor=null;

    }


    @Override
    public int przeszukajScreenshot(BufferedImage cropped_capture) {


        for(int j=0; j<cropped_capture.getHeight();j=j+1) {

            for(int i=0; i<cropped_capture.getWidth();i=i+1) {

                mycolor = new Color(cropped_capture.getRGB(i, j));
                System.out.println(i+" "+j+"\n");
                if(mycolor.getRed()>=255 && mycolor.getGreen()>=255 && mycolor.getBlue()>=255){
                    Console.temperature.setText("ALERT: RGB=(255,255,255) found");
                    return 0;
                }


            }
        }
        Console.temperature.setText("Searching for RGB=(255,255,255)");
        return 1;
    }
}