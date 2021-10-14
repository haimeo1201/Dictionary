import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Locale;

public class PanelManage extends PanelStart {
    private JPanel panelManage;
    private JButton addWordButton;
    private JButton deleteWordButton;
    private JButton backButton;
    private JButton showButton;
    private JTabbedPane tabbedPane1;
    private JTextField textField2;
    private JLabel label1;
    private JTextField textField1;
    private JButton addButton;
    private JPanel deletePanel;
    private JButton deleteButton;
    private JLabel addSuccess;
    private JPanel panelAdd;
    private JLabel delSuccessPop;
    private JButton redefineButton;
    private JLabel redefineSuccess;
    private JTextField textField4;
    private JTextField textField3;
    private JTextField textField5;

    public JPanel getPanelManage() {
        return panelManage;
    }

    public PanelManage() {
        addSuccess.setVisible(false);
        redefineSuccess.setVisible(false);
        delSuccessPop.setVisible(false);


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

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userNewWord = textField1.getText().toLowerCase(Locale.ROOT);
                String userDefinition = textField2 .getText().toLowerCase(Locale.ROOT);
                try {
                    if(userNewWord.trim().length() != 0) {
                        dicM.addFromCommandline(userNewWord, userDefinition);
                        advance.dictionaryAdvanced();
                        addSuccess.setText("Added: " + userNewWord + "- Meaning: " + userDefinition + "!");
                        addSuccess.setVisible(true);
                    }
                    else {
                        addSuccess.setText("Invalid! Please enter again.");
                        addSuccess.setVisible(true);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                mainFrame.pack();
            }
        });

        redefineButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String userWord = textField3.getText().toLowerCase(Locale.ROOT);
                String userDef = textField4.getText().toLowerCase(Locale.ROOT);
                String temp = "";
                try {
                    temp = dicM.dictionaryLookup(userWord);
                    if(temp != "Tu ban tra khong hop le hoac khong co trong tu dien") {
                        dicM.replaceFromCommandline(userWord, userDef);
                        advance.dictionaryAdvanced();
                        redefineSuccess.setText("Success! '" + userWord.toUpperCase() + "' now meaning: " + userDef);
                    }
                    else {
                        redefineSuccess.setText("Can't find word");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                redefineSuccess.setVisible(true);
                mainFrame.pack();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String userDelete = textField5.getText();
                String temp = "";
                try {
                    temp = dicM.dictionaryLookup(userDelete);
                    if(temp != "Tu ban tra khong hop le hoac khong co trong tu dien") {
                        dicM.removeFromCommandline(userDelete);
                        advance.dictionaryAdvanced();
                    }
                    else {
                        delSuccessPop.setText("Tu ban xoa khong hop le hoac khong co trong tu dien");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                delSuccessPop.setVisible(true);
                mainFrame.pack();
            }
        });
    }
}
