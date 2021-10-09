import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Application {
    public static void main(String[] argc) throws IOException {
        //Dictionary dic = new Dictionary();
        //DictionaryCommandline basic = new DictionaryCommandline();
        //basic.dictionaryBasic();
        DictionaryCommandline advance = new DictionaryCommandline();
        DictionaryManagement read = new DictionaryManagement();
        //read.insertFromFile();
        /*System.out.println("Nhap 'tra' de tim kiem tu");
        System.out.println("Nhap 'them' de them vao danh sach tu");
        System.out.println("Nhap 'xoa' de xoa 1 tu trong danh sach tu");
        System.out.println("Nhap 'sua' de sua 1 tu trong danh sach tu");
        Scanner input = new Scanner(System.in);
        String task = input.nextLine();
        if (Objects.equals(task, "tra")) {
            advance.dictionaryAdvanced();
            read.dictionaryLookup();
            advance.dictionarySearcher();
        } else if (Objects.equals(task, "them")) {
            advance.dictionaryAdvanced();
            read.addFromCommandline();
        } else if (Objects.equals(task, "xoa")) {
            advance.dictionaryAdvanced();
            read.removeFromCommandline();
        }
        else if (Objects.equals(task, "sua")) {
            advance.dictionaryAdvanced();
            read.replaceFromCommandline();
        }*/
        advance.dictionaryAdvanced();
    }
}
