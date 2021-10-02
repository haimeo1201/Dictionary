import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryManagement extends Dictionary {
    /**
     * insertFromCommandline.
     */
    public void insertFromCommandline() {
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
            list.add(a);
        }
    }

    /**
     * insertFromFile.
     */
    public void insertFromFile(Dictionary dic) {
        ArrayList<Word> list = dic.getList();
        try {
                File myObj = new File("data\\dictionaries.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] word = data.split("\t");
                Word temp = new Word(word[0], word[1]);
                list.add(temp);
            }
            myReader.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("An error");
        }
    }

    /**
     * dictionaryLookup.
     */
    public void dictionaryLookup(Dictionary dic) {
        ArrayList<Word> list = dic.getList();
        boolean check1 = true;
        while (check1) {
            boolean check2 = false;
            if (check1) System.out.println("moi ban tra tu:");
            Scanner scanner = new Scanner(System.in);
            String search = scanner.nextLine();
            for (Word word : list) {
                if (search.isBlank()) continue;
                else if (word.getWord_target().contains(search)) {
                    check2 = true;
                    System.out.println("tu ban tra co nghia: " + word.getWord_explain());
                    break;
                }
            }
            if (!check2) {
                System.out.println("khong thay tu ban tim");
            }
            System.out.println("Continue Y/N?");
            Scanner sc = new Scanner(System.in);
            String y = sc.nextLine();
            check1 = y.equalsIgnoreCase("y");
        }
        System.out.println("cam on da su dung");
    }
}
