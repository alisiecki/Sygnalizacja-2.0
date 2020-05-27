

import java.awt.EventQueue;
import javax.swing.*;
import java.awt.Button;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;


public class Console {


    Language language;
    Configuration configuration;

    static JFrame frame;
    static JLabel lb;
    static JLabel temperature;
    private JMenuBar menuBar;
    private Button buttonRun;
    private Button buttonAbout;
    private Button buttonPorts;
    private Button buttonSavePosition;

    ScreenshotChecker screenshotChecker;





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
                screenshotChecker=new ScreenshotChecker();
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


            }

        });



        buttonSavePosition.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {


            }

        });



    }

}
