import java.util.ArrayList;

public class Dictionary {
    private ArrayList<Word> list = new ArrayList<>();

    public void setList(ArrayList<Word> list) {
        this.list = list;
    }

    public void addWord(Word a) {
        list.add(a);
    }

    public ArrayList<Word> getList() {
        return list;
    }
}
