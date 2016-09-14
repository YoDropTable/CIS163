import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyTimerPanel extends JPanel {

    /** GeoCountDownTimer Variables */
    private GeoCountDownTimer todaysDate;
    private GeoCountDownTimer demolitionManDate;
    private GeoCountDownTimer firstContactDate;
    private GeoCountDownTimer linuxFailureDate;

    /** Timer and TimerListener */
    private Timer javaTimer;
    private TimerListener timerListener;

    /** Jlables */
    private JLabel demoManDateLbl;
    private JLabel firstContactDateLbl;
    private JLabel linuxFailDateLbl;
    private JLabel todaysDateLbl;

    private JButton button;




    public MyTimerPanel() {

        todaysDate = new GeoCountDownTimer(10,1,2016);
        demolitionManDate = new GeoCountDownTimer(8,3,2032);
        firstContactDate = new GeoCountDownTimer(4,5,2063);
        linuxFailureDate = new GeoCountDownTimer(1,18,2038);

        timerListener = new TimerListener();
        demoManDateLbl = new JLabel(demolitionManDate.toString());
        firstContactDateLbl = new JLabel(firstContactDate.toString());
        linuxFailDateLbl = new JLabel(linuxFailureDate.toString());
        todaysDateLbl = new JLabel(todaysDate.toString());

        add(todaysDateLbl,constraints(5,5,1,1));
        add(demoManDateLbl,constraints(1,1,1,1));
        add(firstContactDateLbl,constraints(1,2,1,1));
        add(linuxFailDateLbl,constraints(1,3,1,1));

        button = new JButton("stop");
        add(button,constraints(3,3,1,1));
        button.addActionListener(timerListener);




        javaTimer = new Timer(10,timerListener);
        javaTimer.start();
    }
    private GridBagConstraints constraints(int x,int y,int h, int w)
    {
        GridBagConstraints rtn = new GridBagConstraints();
        rtn.gridx = x;
        rtn.gridy = y;
        rtn.gridheight = h;
        rtn.gridwidth = w;
        rtn.insets = new Insets(5,5,5,5);
        rtn.anchor=GridBagConstraints.LINE_START;
        return rtn;
    }

    private class TimerListener implements ActionListener {
        //--------------------------------------------------------------
        //  Determines which button was pressed and sets the label
        //  text accordingly.
        //--------------------------------------------------------------
        public void actionPerformed(ActionEvent event) {

            todaysDate.inc();
            todaysDateLbl.setText(todaysDate.toDateString());

            if(todaysDate.equals(demolitionManDate)){
                demoManDateLbl.setText("ITS DEMOLITION MAN!");
            }
            if(todaysDate.equals(firstContactDate)){
                firstContactDateLbl.setText("Make First Contact!");
            }
            if(todaysDate.equals(linuxFailureDate)){
                linuxFailDateLbl.setText("Linux Just Failed!");
            }

            if (event.getSource() == button)
                javaTimer.stop();
        }


    }
}