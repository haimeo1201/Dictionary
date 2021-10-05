import java.util.Collections;
import java.util.Comparator;

public class DictionaryCommandline extends Dictionary {
    /**
     * showAllWords.
     */
    public void showAllWords() {
        //ArrayList<Word> list = dic.getList();
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
    public void dictionaryAdvanced() {
        DictionaryManagement read = new DictionaryManagement();
        DictionaryCommandline order = new DictionaryCommandline();
        read.insertFromFile();
        order.dictionarySearcher();
        order.showAllWords();
    }

    /**
     * dictionarySearcher.
     */
    public void dictionarySearcher() {
        /**
         * sortDictionary.
         */
        Collections.sort(list, new Comparator<Word>() {
            @Override
            public int compare(Word o1, Word o2) {
                return o1.getWord_target().compareTo(o2.getWord_target());
            }
        });
    }
}
