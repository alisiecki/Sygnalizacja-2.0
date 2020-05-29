package Main;

import java.util.Timer;
import java.util.TimerTask;


import ThermoModules.PixelSearcher;
import ThermoModules.WhitePixelSearcher;



public class ScreenshotChecker {

    Timer timer;
    ScreenshotMaker sm;
    PixelSearcher pixelSearcher;
    Blinker blinker;

    boolean isTimerRun = false;



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

        }
    }


    public void run(){
        if (isTimerRun==false){
            timer = new Timer();
            timer.schedule(new ScreenshootCheckerTimerTask(),0,2000);
            isTimerRun=true;
        }

    }

    public void stop(){
        timer.cancel();
        isTimerRun=false;
    }


}
