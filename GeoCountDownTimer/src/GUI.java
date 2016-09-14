import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class
GUI
    {
        /** Button & Menu Items */

        private JMenuBar menus;
        private JMenu fileMenu;
        private JMenuItem quitItem;
        private JMenuItem saveDate;
        private JMenuItem loadDate;

        public static void main (String[] args)
        {
            new GUI();
        }

        public GUI(){
            JFrame frame = new JFrame ("GEO Count Down Timer");
            frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

            MyTimerPanel panel = new MyTimerPanel();

            frame.getContentPane().add(panel);
            frame.setSize(500,500);
            frame.setLayout(new GridBagLayout());
            frame.setVisible(true);
            //sets up menus
            fileMenu = new JMenu("File");
            quitItem = new JMenuItem("Quit");
            saveDate = new JMenuItem("Save..");
            loadDate = new JMenuItem("Load..");



            ActionListener ml = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    if(e.getSource() == quitItem)
                        System.exit(1);
                    if(e.getSource() == saveDate)
                    {
                        JFileChooser fc = new JFileChooser();
                        fc.showOpenDialog(GUI);
                        //read from fc.getSelectedFile().getAbsolutePath();
                    }
                    if(e.getSource() == loadDate)
                    {
                        System.out.println("Load");
                    }

                }
            };

            quitItem.addActionListener(ml);
            saveDate.addActionListener(ml);
            loadDate.addActionListener(ml);

            fileMenu.add(saveDate);
            fileMenu.add(loadDate);
            fileMenu.add(quitItem);

            menus = new JMenuBar();
            menus.add(fileMenu);
            frame.setJMenuBar(menus);
        }
    }

