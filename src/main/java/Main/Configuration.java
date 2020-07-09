package Main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


//Klasa obsługująca załądowanie ustawień początkowych tj. lokalizacji i rozmiaru odczytywanego
//obrazu oraz używany port.


public class Configuration {


    static int x_start, y_start, width, height;
    static String port;



    public Configuration(){

        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream("./bin/config.properties"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        this.x_start=Integer.parseInt(prop.getProperty("x_start"));
        this.y_start=Integer.parseInt(prop.getProperty("y_start"));
        this.width =Integer.parseInt(prop.getProperty("cropped_width"));
        this.height =Integer.parseInt(prop.getProperty("cropped_height"));
        this.port = prop.getProperty("port");





    }



}
