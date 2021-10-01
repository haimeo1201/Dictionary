import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryCommandline extends Dictionary {
    public void showAllWords(Dictionary dic) {
        ArrayList<Word> list = dic.getList();
        System.out.printf("%-6s |%-20s  |%s \n", "No", "English", "Vietnamese");
        for (int i = 0; i < list.size(); i++) {
            String S = ".";
            System.out.printf("%d%-5s |%-25s |%s%n", i + 1, S, list.get(i).getWord_target(), list.get(i).getWord_explain());
        }
    }

    public void insertFromFile() {
        try {
            File myObj = new File("C:\\Users\\TytyKiller\\Desktop\\Dictionary\\Dictionary\\data\\dictionaries.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {

                String data = myReader.nextLine();
                //System.out.println(data);
                String [] split = data.split("\t");
                //System.out.println(split[0]);
                //System.out.println(split[1]);
                Word temp = new Word(split[0] , split[1]);
                list.add(temp);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading file!");
            e.printStackTrace();
        }
    }

    public void dictionaryLookup() {

    }


}
