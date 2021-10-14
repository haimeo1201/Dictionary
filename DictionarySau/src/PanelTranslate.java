import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import org.jdesktop.swingx.autocomplete.AutoCompleteComboBoxEditor;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class PanelTranslate extends PanelStart{
    private JTextField textField1;
    private JPanel panelTranslate;
    private JLabel labelDefinition;
    private JLabel translateLabel;
    private JButton translateButton;
    private JButton backButton;
    private JButton listenButton;
    private JComboBox comboBox1;

    DictionaryManagement dicM = new DictionaryManagement();

    public JPanel getPanelTranslate() {
        return panelTranslate;
    }

    public PanelTranslate() {
        labelDefinition.setVisible(false);
        translateLabel.setVisible(false);
        listenButton.setVisible(false);
        advance.sortDictionary();
        advance.dictionarySearcher(comboBox1);
        AutoCompleteDecorator.decorate(comboBox1);

        translateButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             * Bam Translate;
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String userText = comboBox1.getSelectedItem().toString().toLowerCase(Locale.ROOT);
                System.out.println(userText);
                //boolean check = false;
                String def = dicM.dictionaryLookup(userText);
                def.toLowerCase(Locale.ROOT);
                labelDefinition.setText(def);
                labelDefinition.setVisible(true);
                translateLabel.setVisible(true);
                listenButton.setVisible(true);
                mainFrame.pack();
            }
        });
        backButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setContentPane(panelStart);
                mainFrame.pack();
            }
        });
        listenButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String userText = comboBox1.getSelectedItem().toString().toLowerCase(Locale.ROOT);
                String out = dicM.dictionaryLookup(userText);
                Voice voice = VoiceManager.getInstance().getVoice("kevin");
                if(voice != null) {
                    voice.allocate();
                    voice.speak(userText);
                    System.out.println("Voice success");
                    voice.deallocate();
                }
                else {
                    System.out.println("Error getting voices!");
                }
            }
        });

    }


}
