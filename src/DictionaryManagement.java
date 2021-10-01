import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryManagement {
    public void insertFromCommandline(Dictionary list) {
        int n;
        System.out.print("number of pairs: ");
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        for (int i = 0; i < n; i++) {
            Scanner scan1 = new Scanner(System.in);
            System.out.print("type english word: ");
            String target = scan1.nextLine();
            System.out.print("type meaning: ");
            String explain = scan1.nextLine();
            Word a = new Word(target, explain);
            list.addWord(a);
        }
    }
}
