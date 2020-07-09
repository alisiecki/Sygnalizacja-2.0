package Main;


import java.util.LinkedList;
import java.util.Queue;
import static Main.PixelToTemperatureConverter.convertPixelToTemperature;

public class AverageManager {

    Double averageTest;
    int howManySamplesForAverage;
    Double sum;
    Queue<Integer> samplesQueue;

    public AverageManager(int howManySamplesForAverage){

        this.averageTest = 0.0;
        this.howManySamplesForAverage = howManySamplesForAverage;
        this.samplesQueue = new LinkedList<>();

    }


    public Double getAverage(){
        return averageTest;
    }


    public void updateAverage(int maxRedLowestGreen){

        this.sum = 0.0;

        if(samplesQueue.size()==howManySamplesForAverage)
            samplesQueue.remove();

        samplesQueue.add(maxRedLowestGreen);

        for (Integer integer : samplesQueue) {
            sum = sum + integer;
        }

        averageTest = sum/samplesQueue.size();

        Console.mainInformationForUserTextField.setText("Source for average[px]:" + samplesQueue +" Last temp:" + convertPixelToTemperature((double) maxRedLowestGreen)+ " Average[°C]: "+ convertPixelToTemperature(sum/ samplesQueue.size()));

    }



}
