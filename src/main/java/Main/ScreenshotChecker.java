package Main;

import java.util.Timer;
import java.util.TimerTask;

import ThermoModules.MaxRedLowestGreenPixelSearcher;
import ThermoModules.PixelSearcher;


// Moduł obsługujący proces składający sie na:
// 1.wykonanie zrzutu ekranu - podmoduł ScreenshotMaker
// 2.przeszukanie zrzutu ekranu - odpowiedni podmoduł implementujący interfejs PixelSearcher
// 3.odpowiednia sygnalizacja - podmoduł Blinker
//
// Moduł ScreenshotChecker uzywa klasy Timer oraz interfejsu TimerTask do powtarzania
// powyższego procesu co 2 sekundy. Towarzyszy mu zmieniający sie równolegle tekst
// informujący o nieprzerwanym działaniu programu.


public class ScreenshotChecker {

    PixelSearcher pixelSearcher;
    Timer timer;
    ScreenshotMaker screenshotMaker;
    Blinker blinker;


    boolean isTimerRun = false;
    int iterator_for_working_text=0;




    public ScreenshotChecker(){

        pixelSearcher = new MaxRedLowestGreenPixelSearcher();
        screenshotMaker = new ScreenshotMaker();
        blinker = new Blinker();

    }


    class ScreenshootCheckerTimerTask extends TimerTask{

        @Override
        public void run() {

            blinker.blink(pixelSearcher.searchForGivenPixel(screenshotMaker.takeScreenshot()));
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
