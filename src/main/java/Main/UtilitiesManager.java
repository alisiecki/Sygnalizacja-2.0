package Main;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Queue;
import static Main.PixelToTemperatureConverter.convertPixelToTemperature;

public class UtilitiesManager {

    Double averageTest;
    int howManySamplesForAverage;
    Double sum;
    Queue<Integer> samplesQueue;

    public UtilitiesManager(int howManySamplesForAverage) {

        this.averageTest = 0.0;
        this.howManySamplesForAverage = howManySamplesForAverage;
        this.samplesQueue = new LinkedList<>();

    }


    public Double getAverage() {
        return averageTest;
    }




    public void updateAverage(int maxRedLowestGreen) {

        this.sum = 0.0;

        if (samplesQueue.size() == howManySamplesForAverage)
            samplesQueue.remove();

        samplesQueue.add(maxRedLowestGreen);

        for (Integer integer : samplesQueue) {
            sum = sum + integer;
        }

        averageTest = sum / samplesQueue.size();

        Console.mainInformationForUserTextField.setText("Source for average[px]:" + samplesQueue + " Last temp:" + convertPixelToTemperature((double) maxRedLowestGreen) + " Average[°C]: " + convertPixelToTemperature(sum / samplesQueue.size()));

    }





    int iterator_for_manFound_screenshots = 0;

    public void saveDataToFiles(BufferedImage cropped_capture, int maxRedLowestGreen) {

        try {
            iterator_for_manFound_screenshots++;
            ImageIO.write(cropped_capture, "bmp", new File("./TestTemperatury/manFound_screenshoot_" + iterator_for_manFound_screenshots + ".bmp"));

            PrintWriter writer = new PrintWriter(new FileWriter("./TestTemperatury/CelsjuszTest.txt", true));
            writer.println(LocalDateTime.now() + " rGb: " + maxRedLowestGreen);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
