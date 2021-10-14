import javax.swing.*;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

public class DictionaryCommandline extends Dictionary {
    static DictionaryManagement read = new DictionaryManagement();
    static DictionaryCommandline order = new DictionaryCommandline();

    /**
     * showAllWords.
     */
    public void showAllWords() {
        System.out.printf("%-6s |%-20s  |%s \n", "No", "English", "Vietnamese");
        for (int i = 0; i < list.size(); i++) {
            String S = ".";
            System.out.printf("%d%-5s |%-25s |%s%n", i + 1, S, list.get(i).getWord_target(), list.get(i).getWord_explain());
        }
    }

    /**
     * dictionaryBasic.
     */
    public void dictionaryBasic() {
        DictionaryManagement read = new DictionaryManagement();
        DictionaryCommandline order = new DictionaryCommandline();
        //read.insertFromCommandline(dic);
        order.showAllWords();
    }

    /**
     * dictionaryAdvanced.
     */
    public void dictionaryAdvanced() throws IOException {
        list.clear();
        read.insertFromFile();
        order.sortDictionary();
        order.showAllWords();
    }

    /**
     * sortDictionary.
     */
    public void sortDictionary() {
        Collections.sort(list, new Comparator<Word>() {
            @Override
            public int compare(Word o1, Word o2) {
                return o1.getWord_target().compareTo(o2.getWord_target());
            }
        });
    }

    public void dictionarySearcher(JComboBox comboBox) {
        for(Word word: list) {
            String temp = word.getWord_target().toLowerCase(Locale.ROOT);
            comboBox.addItem(temp);
        }
    }
}
