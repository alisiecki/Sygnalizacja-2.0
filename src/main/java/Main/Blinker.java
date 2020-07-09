package Main;

import jssc.SerialPort;
import jssc.SerialPortException;

import java.awt.*;


// Klasa obsługuje połączenie z przekaźnikiem AVT sterującym danym urządzeniem zewnętrznym, najczęsciej
// czerwono-zieloną sygnalizacją świetlną. Rownolegle zmienia także kolor tła w oknie aplikacji.
// Dostarczenie liczb do funkcji "blink" uruchamia kolejno:
// 0 - kolor czerwony
// 1 - kolor zielony
// 2 - brak reakcji



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

    public void blink(int number){


        if(number==0){

            try {
                port.writeInt(0x34);
            } catch (SerialPortException e) {
                e.printStackTrace();
            }
            Console.frame.getContentPane().setBackground(Color.red);


            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                port.writeInt(0x33);
            } catch (SerialPortException e) {
                e.printStackTrace();
            }
            Console.frame.getContentPane().setBackground(szary);

        }


        if(number==1){

            try {
                port.writeInt(0x31);
            } catch (SerialPortException e) {
                e.printStackTrace();
            }
            Console.frame.getContentPane().setBackground(Color.green);


            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                port.writeInt(0x30);
            } catch (SerialPortException e) {
                e.printStackTrace();
            }
            Console.frame.getContentPane().setBackground(szary);

        }




    }
}
