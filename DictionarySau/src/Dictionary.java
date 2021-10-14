import java.util.ArrayList;

public class Dictionary {
    static ArrayList<Word> list = new ArrayList<>();

    public ArrayList<Word> getList() {
        return list;
    }

    public void setList(ArrayList<Word> list) {
        Dictionary.list = list;
    }
}
