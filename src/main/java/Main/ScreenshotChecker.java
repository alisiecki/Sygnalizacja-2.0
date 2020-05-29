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
    int iterator_for_working_text=0;



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
            showThatProgramIsRunning();

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


    public void showThatProgramIsRunning(){

        
        switch(iterator_for_working_text%4) {
            case 0:
                Console.lb.setText("working");
                iterator_for_working_text++;
                break;
            case 1:
                Console.lb.setText("working.");
                iterator_for_working_text++;
                break;
            case 2:
                Console.lb.setText("working..");
                iterator_for_working_text++;
                break;
            case 3:
                Console.lb.setText("working...");
                iterator_for_working_text++;
                break;

        }


    }


}
