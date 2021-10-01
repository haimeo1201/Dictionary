import java.util.ArrayList;
import java.util.Objects;

public class DictionaryCommandline {
    public void showAllWords(Dictionary dic) {
        ArrayList<Word> list = dic.getList();
        System.out.printf("%-6s |%-20s  |%s \n", "No", "English", "Vietnamese");
        for (int i = 0; i < list.size(); i++) {
            String S = ".";
            System.out.printf("%d%-5s |%-25s |%s%n", i + 1, S, list.get(i).getWord_target(), list.get(i).getWord_explain());
        }
    }
}
