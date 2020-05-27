package ThermoModules;

import java.awt.*;
import java.awt.image.BufferedImage;

public class WhitePixelSearcher implements PixelSearcher{


    @Override
    public int przeszukajScreenshot(BufferedImage cropped_capture) {


        Color mycolor=null;

        for(int j=0; j<cropped_capture.getHeight();j=j+1) {

            for(int i=0; i<cropped_capture.getWidth();i=i+1) {

                mycolor = new Color(cropped_capture.getRGB(i, j));
                System.out.println(i+" "+j+"\n");
                if(mycolor.getRed()>=255 && mycolor.getGreen()>=255 && mycolor.getBlue()>=255)
                    return 0;
            }
        }

        return 1;
    }
}