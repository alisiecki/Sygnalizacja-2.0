import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Configuration {




    static int x_start, y_start, width, height;





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






    }



}
