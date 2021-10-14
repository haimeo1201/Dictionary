import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class PanelStart extends Dictionary {

    static JFrame mainFrame = new JFrame();
    public JPanel panelStart;
    public DictionaryManagement dicM = new DictionaryManagement();
    public DictionaryCommandline advance = new DictionaryCommandline();
    private JButton translateEnglishToVietnameseButton;
    private JButton manageTheDictionaryButton;
    private JLabel Intro;
    private JButton showAllWordButton;
    public PanelStart() {
        mainFrame.setTitle("Dictionary");
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setContentPane(panelStart);
        //mainFrame.setSize(500 , 500);
        mainFrame.pack();
        //mainFrame.setLocation(800 , 400);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);

        translateEnglishToVietnameseButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panelTranslate = new PanelTranslate().getPanelTranslate();
                mainFrame.setContentPane(panelTranslate);
                mainFrame.pack();
            }
        });

        manageTheDictionaryButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panelManage = new PanelManage().getPanelManage();
                mainFrame.setContentPane(panelManage);
                mainFrame.pack();
            }
        });

        showAllWordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    File file = new File("data\\dictionaries.txt");
                    if (!Desktop.isDesktopSupported())//check if Desktop is supported by Platform or not
                    {
                        System.out.println("not supported");
                        return;
                    }
                    Desktop desktop = Desktop.getDesktop();
                    if (file.exists())         //checks file exists or not
                        desktop.open(file);              //opens the specified file
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    public static JFrame getMainFrame() {
        return mainFrame;
    }

    public JPanel getPanelStart() {
        return panelStart;
    }
}
