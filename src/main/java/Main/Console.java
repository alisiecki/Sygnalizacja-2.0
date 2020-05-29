package Main;

import jssc.SerialPortList;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;


public class Console {


    Language language;
    Configuration configuration;
    ScreenshotChecker screenshotChecker;

    public static JFrame frame;
    public static JLabel lb;
    public static JLabel temperature;
    private JMenuBar menuBar;
    private Button buttonRun;
    private Button buttonAbout;
    private Button buttonPorts;
    private Button buttonSavePosition;







    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Console window = new Console();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public Console() {

        configuration = new Configuration();
        language = new Language();
        screenshotChecker=new ScreenshotChecker();

        initialize();

    }

    private void initialize() {

        //Frame
        frame = new JFrame();
        frame.setBounds(Configuration.x_start, Configuration.y_start, Configuration.width, Configuration.height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle(language.APP_TITLE);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));


        //Two JLabels
        lb = new JLabel(language.FIRST_LABEL);
        lb.setFont(new Font("Tahoma", Font.PLAIN, 12));
        frame.getContentPane().add(lb);
        lb.setAlignmentX(Component.CENTER_ALIGNMENT);

        temperature = new JLabel(language.SECOND_LABEL);
        temperature.setFont(new Font("Tahoma", Font.PLAIN, 12));
        frame.getContentPane().add(temperature);
        temperature.setAlignmentX(Component.CENTER_ALIGNMENT);


        //Buttons in MenuBar
        menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        buttonRun = new Button(language.RUN);
        buttonAbout = new Button(language.ABOUT);
        buttonPorts = new Button(language.CHECK_PORTS);
        buttonSavePosition = new Button(language.SAVE_POSITION);

        menuBar.add(buttonRun);
        menuBar.add(buttonAbout);
        menuBar.add(buttonPorts);
        menuBar.add(buttonSavePosition);



        buttonRun.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                screenshotChecker.run();
            }


        });



        buttonAbout.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                screenshotChecker.stop();

            }

        });




        buttonPorts.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame f=new JFrame();
                // getting serial ports list into the array
                String[] portNames = SerialPortList.getPortNames();

                if (portNames.length == 0)
                    JOptionPane.showMessageDialog(f,"There are no serial-ports :( You can use an emulator, such ad VSPE, to create a virtual serial port.");
                else
                    JOptionPane.showMessageDialog(f,portNames);
            }

        });



        buttonSavePosition.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                Properties prop = new Properties();
                Rectangle r = frame.getBounds();

                prop.setProperty("x_start", String.valueOf(r.x));
                prop.setProperty("y_start", String.valueOf(r.y));
                prop.setProperty("cropped_height", String.valueOf(r.height));
                prop.setProperty("cropped_weight", String.valueOf(r.width));
                prop.setProperty("precision", "+/- 10px");

                try {
                    prop.store(new FileOutputStream("./bin/newScanPosition.properties"),null);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }

        });



    }

}
