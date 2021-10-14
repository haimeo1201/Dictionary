import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

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
        order.showAllWords();
    }

    /**
     * dictionaryAdvanced.
     */
    public void dictionaryAdvanced() throws IOException {
        String task;
        do {
            System.out.println("Nhap 'tra' de tim kiem tu");
            System.out.println("Nhap 'them' de them vao danh sach tu");
            System.out.println("Nhap 'xoa' de xoa 1 tu trong danh sach tu");
            System.out.println("Nhap 'sua' de sua 1 tu trong danh sach tu");
            System.out.println("Nhap 'show' de hien thi danh sach tu");
            System.out.println("Nhap 'quit' de thoat");
            System.out.println("Nhap yeu cau:");
            Scanner input = new Scanner(System.in);
            task = input.nextLine();
            while (task.isBlank()){
                task = input.nextLine();
            }
            switch (task) {
                case "tra": {
                    list.clear();
                    read.insertFromFile();
                    order.sortDictionary();
                    order.showAllWords();
                    read.dictionaryLookup();
                 //   order.dictionarySearcher();
                    System.out.print("nhan phim enter de tiep tuc... ");
                    input.nextLine();
                    continue;
                }
                case "them": {
                    order.showAllWords();
                    //read.addFromCommandline();
                    System.out.print("nhan phim enter de tiep tuc... ");
                    input.nextLine();
                    continue;
                }
                case "sua": {
//                    order.showAllWords();
                    //read.replaceFromCommandline();
                    list.clear();
                    read.insertFromFile();
                    System.out.print("nhan phim enter de tiep tuc... ");
                    input.nextLine();
                    continue;
                }
                case "xoa": {
//                    order.showAllWords();
                   // read.removeFromCommandline();
                    list.clear();
                    read.insertFromFile();
                    System.out.print("nhan phim enter de tiep tuc... ");
                    input.nextLine();
                    continue;
                }
                case "quit": {
                    System.out.print("cam on ban da su dung");
                    System.exit(0);
                    continue;
                }
                case "show": {
                    list.clear();
                    read.insertFromFile();
                    order.sortDictionary();
                    order.showAllWords();
                    System.out.print("nhan phim enter de tiep tuc... ");
                    input.nextLine();
                    continue;
                }
                default: {
                    System.out.println("yeu cau ban tra khong hop le");
                    System.out.print("nhan phim enter de tiep tuc... ");
                    input.nextLine();
                    continue;
                }
            }
        }while (task != "quit");
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

    public boolean dictionarySearcher(String searching) {
        boolean check = false;
        for (Word word : list) {
            if (word.getWord_target().startsWith(searching) && !word.getWord_target().equals(searching)) {
                System.out.println( word.getWord_target());
                check = true;
            }
        }
        if (!check) {
            System.out.println("khong co tu nao tuong tu");
        }
        return check;
    }
}
