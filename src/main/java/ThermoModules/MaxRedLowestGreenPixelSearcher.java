package ThermoModules;

import Main.Console;

import javax.imageio.ImageIO;
import javax.security.auth.login.Configuration;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Queue;


//Miarka czerwnoo-żółta.
//Znajdowanie najbardziej czerwonego pixela w obrazie, obliczanie średniej z poprzednich wyników, screenshoty, zapis danych RGB do txt, 
//konwertowanie wartości pixelowej na wartość temperaturową. Aktualnie brak wysyłania sygnałów 0,1 do Blinkera.

//Więcej:
//Algorytm przeszukuje obraz pixel po pixelu w poszukiwaniu koloru, którego atrybuty RGB = (255,minimum,dowolne).
//Atrybut B jest dowolny ponieważ RGB=(255,0,0) to kolor czerwony, a RGB=(255,0,255) to kolor fioletowy niewystępujący w
//obrazach źródłowych.
//Zmienna "isManFind" to flaga oznaczajaca, ze w zbiorze pixelów znaleziono kolor przyporządkowany człowiekowi.
// "isManFind"=true pozwala uruchamiać kod drukujący obraz do folderu "./TestTemperatury" oraz txt z danymi.
//



public class MaxRedLowestGreenPixelSearcher implements PixelSearcher {

    //zmienna dla przeszukiwania pixeli.
    Color mycolor;

    //zmienne dla wyszukiwania człowieka i najbardziej czerwonego punktu.
    boolean isManFind;
    int lowestGreen;

    //zmienne dla funkcji updateAverage.
    Double average;
    int howManySamplesForAverage;
    Double sum;
    Queue<Integer> q;

    //zmienne dla funkcji convertPixelToTemperature.
    Double d,w,licznik, mianownik,first;
    DecimalFormat df;

    //zmienne dla modulu Fota+Celsjusz w folderze
    int iterator_for_isManFound_screenshots;



    public MaxRedLowestGreenPixelSearcher(){

        mycolor=null;
	average=0.0; //unikniecie NullPointException
	howManySamplesForAverage=7;
	q = new LinkedList<>();
	df = new DecimalFormat("0.00");
	iterator_for_isManFound_screenshots=0;


    }


    @Override
    public int przeszukajScreenshot(BufferedImage cropped_capture) {


        //Przygotownie wartości początkowych przed przeszukowiwaniem.
        isManFind=false;
        lowestGreen=255;


        //Przeprocesowanie wszystkich pixeli w obrazku.
        for(int j=0; j<cropped_capture.getHeight();j=j+1) {

            for(int i=0; i<cropped_capture.getWidth();i=i+1) {

                mycolor = new Color(cropped_capture.getRGB(i, j));

                if(mycolor.getRed()==255) {

                    //flaga "czlowiek znaleziony".
                    isManFind=true;

                    //szukanie minimumGreen.
                    if(mycolor.getGreen() < lowestGreen) {
                        lowestGreen = mycolor.getGreen();
                    }

                }
            }
        }


        //Drukowanie zdjec. Drukowanie godziny i wartosci minimumGreen dla danego screenshot'a w pliku txt.
        if(isManFind==true) {

            try {
                iterator_for_isManFound_screenshots++;
                ImageIO.write(cropped_capture, "bmp", new File("./TestTemperatury/manFound_screenshoot_"+iterator_for_isManFound_screenshots+".bmp"));

                PrintWriter writer = new PrintWriter(new FileWriter("./TestTemperatury/CelsjuszTest.txt", true));
                writer.println(LocalDateTime.now()+ " rGb: " +lowestGreen);
                writer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }


        }


        //Implementacja średniej.
        updateAverage(lowestGreen);

        return 2;
    }




    public void updateAverage(int maxRedLowestGreen){

        sum=0.0;

        if(q.size()==howManySamplesForAverage)
            q.remove();
        q.add(maxRedLowestGreen);

        for (Integer integer : q) {
            sum = sum + integer;
        }
        System.out.println("Average: "+ sum/q.size() + "   Content:" + q);
        average = sum/q.size();

        //wyswietlanie w LABELACH informacji o sredniej itd.
        Console.temperature.setText("Kolejka w px:" + q +" Ostatnia temp:" + convertPixelToTemperature((double) maxRedLowestGreen)+ " Average: "+ convertPixelToTemperature(sum/q.size()));


    }



    public String convertPixelToTemperature(Double maxRedLowestGreen){


        if(maxRedLowestGreen<120){
            w=maxRedLowestGreen/19690520;
            mianownik=Math.pow(w,1.077747)+1;
            licznik=(39.95048 - -997907.2);
            first=-997907.2;


        }
        else{
            w=maxRedLowestGreen/500.7978;
            mianownik=Math.pow(w,1.366587)+1;
            licznik=(42.03134 - 9.085402);
            first=9.085402;

        }

        d=first+licznik/mianownik;


        return df.format(d);
    }



}
