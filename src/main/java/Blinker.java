import jssc.SerialPort;
import jssc.SerialPortException;

import java.awt.*;

public class Blinker {

    SerialPort port;
    Color szary;

    public Blinker(){

        port = new SerialPort(Configuration.port);
        szary = new Color(238,238,238);
        try {
            port.openPort();
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }

    public void mrugaj(int number){


        if(number==0){
            try {
                port.writeInt(0x34);
                Console.frame.getContentPane().setBackground(Color.green);
                Thread.sleep(1000);
                port.writeInt(0x33);
                Console.frame.getContentPane().setBackground(szary);

            } catch (SerialPortException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


        if(number==1){
            try {
                port.writeInt(0x31);
                Console.frame.getContentPane().setBackground(Color.green);
                Thread.sleep(1000);
                port.writeInt(0x30);
                Console.frame.getContentPane().setBackground(szary);

            } catch (SerialPortException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }




    }
}
