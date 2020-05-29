package Main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ScreenshotMaker {

    Robot rb;
    Rectangle screenRect;
    BufferedImage capture;
    BufferedImage cropped_capture;
    int x_start, y_start, width, height;


    public ScreenshotMaker(){

        try {
            rb = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        screenRect= new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());


        this.x_start=Configuration.x_start;
        this.y_start=Configuration.y_start;
        this.width =Configuration.width;
        this.height =Configuration.height;


    }


    public BufferedImage takeScreenshot(){

        capture = rb.createScreenCapture(screenRect);
        cropped_capture = capture.getSubimage(x_start, y_start, width, height);
        return cropped_capture;
    }

}
