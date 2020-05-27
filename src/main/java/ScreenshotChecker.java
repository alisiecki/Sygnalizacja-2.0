
import java.util.Timer;
import java.util.TimerTask;

import ThermoModules.PixelSearcher;
import ThermoModules.WhitePixelSearcher;


public class ScreenshotChecker {

    Timer timer;
    ScreenshotMaker sm;
    PixelSearcher pixelSearcher;
    Blinker blinker;


    public ScreenshotChecker(){

      sm = new ScreenshotMaker();
      pixelSearcher = new WhitePixelSearcher();
      blinker = new Blinker();



    }


    class ScreenshootCheckerTimerTask extends TimerTask{

        @Override
        public void run() {

            //zamrugajOdpowiednio //przeszukajScreenshoot  //takeScreenshoot
            blinker.mrugaj(pixelSearcher.przeszukajScreenshot(sm.takeScreenshot()));
            System.out.println("Pyk");

        }
    }


    public void run(){
        timer = new Timer();
        timer.schedule(new ScreenshootCheckerTimerTask(),0,2000);
    }

    public void stop(){
        timer.cancel();
    }


}
