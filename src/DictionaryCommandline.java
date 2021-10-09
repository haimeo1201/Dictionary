import java.util.Collections;
import java.util.Comparator;

public class DictionaryCommandline extends Dictionary {
    static DictionaryManagement read = new DictionaryManagement();
    static DictionaryCommandline order = new DictionaryCommandline();
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

    public void dictionarySearcher() {
        String searching = read.getSearch();
        System.out.println("Cac tu tuong tu:");
        boolean check = false;
        for (Word word : list) {
            if (word.getWord_target().startsWith(searching)) {
                System.out.println( word.getWord_target());
                check = true;
            }
        }
        if (!check) {
            System.out.println("khong co tu nao tuong tu");
        }
    }
}
