package Main;

import java.text.DecimalFormat;




public  class PixelToTemperatureConverter {

    static Double result;
    static DecimalFormat rounding;

    static{
        rounding = new DecimalFormat("0.00");
    }


    public static String convertPixelToTemperature(Double maxRedLowestGreen){
        result = 9.085402 + (42.03134 - 9.085402) / (Math.pow((maxRedLowestGreen/500.7978),1.366587)+1);
        return rounding.format(result);
    }


}
