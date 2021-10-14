import java.io.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class DictionaryManagement extends Dictionary {
    static String search = "";
    public String getSearch() {
        return search;
    }

    public void inputWord() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("moi ban tra tu:");
        if (scanner.hasNextLine()) {
            search = scanner.nextLine();
        }
    }
    public void showAllWords() {
        System.out.printf("%-6s |%-20s  |%s \n", "No", "English", "Vietnamese");
        for (int i = 0; i < list.size(); i++) {
            String S = ".";
            System.out.printf("%d%-5s |%-25s |%s%n", i + 1, S, list.get(i).getWord_target(), list.get(i).getWord_explain());
        }
    }
    public void sortDictionary() {
        Collections.sort(list, new Comparator<Word>() {
            @Override
            public int compare(Word o1, Word o2) {
                return o1.getWord_target().compareTo(o2.getWord_target());
            }
        });
    }

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
    public void insertFromFile() {
        list.clear();
        try {
            File myObj = new File("data\\dictionaries.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data.trim().length() == 0) {
                    continue;  // Skip blank lines
                }
                String[] word = data.split("\t");
                Word temp = new Word(word[0], word[1]);
                list.add(temp);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error");
        }
    }

    /**
     * dictionaryLookup.
     */
    public void dictionaryLookup() {
        inputWord();
        boolean check = false;
        for (Word word : list) {
            if (word.getWord_target().matches(search)) {
                check = true;
                System.out.println("tu ban tra co nghia: " + word.getWord_explain());
                break;
            }
        }
        if (!check) {
            System.out.println("khong thay tu ban tim");
        }
    }

    /**
     * addToDictionary (.txt file) by commandline
     */
    public void addFromCommandline(Word word) throws IOException {
        String newWordTarget = word.getWord_target();
        String newWordExplain = word.getWord_explain();
        FileWriter fw = null;
        fw = new FileWriter("data\\dictionaries.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(newWordTarget + "\t" + newWordExplain);
        bw.newLine();
        bw.close();
        System.out.println("them tu thanh cong");
    }

    /**
     * removeFromDictionary (file .txt)
     */
    public void removeFromCommandline(String toDelete) throws IOException {
        int count1 = 0;
        int count2 = 0;
        File file = new File("data\\dictionaries.txt");
        File temp = File.createTempFile("data\\tempFile", ".txt", file.getParentFile());
        String charset = "UTF-8";
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(temp), charset));
        for (String line; (line = reader.readLine()) != null; ) {
            count1++;
            if (!line.contains(toDelete)) {
                writer.println(line);
                count2++;
            }
        }
        reader.close();
        writer.close();
        file.delete();
        temp.renameTo(file);
        if (count2 == count1) {
            System.out.println("Khong tim thay tu ban muon xoa");
        }
        else {
            System.out.println("Xoa tu thanh cong");
        }
    }

    /**
     * sua tu (file .txt).
     */
    public void replaceFromCommandline(Word word) throws IOException {
        int count1 = 0;
        String toReplace = word.getWord_target();
        String replaceWord_explain = word.getWord_explain();
        File file = new File("data\\dictionaries.txt");
        File tempFile = File.createTempFile("data\\tempFile", ".txt", file.getParentFile());
        String charset = "UTF-8";
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(tempFile), charset));
        for (String line; (line = reader.readLine()) != null; ) {
            if (line.contains(toReplace)) {
                String[] tempStringSplits = line.split("\t");
                tempStringSplits[1] = replaceWord_explain;
                line = tempStringSplits[0] + "\t" + tempStringSplits[1];
                count1++;
            }
            writer.println(line);
        }
        reader.close();
        writer.close();
        file.delete();
        tempFile.renameTo(file);
        if (count1 == 1) {
            System.out.println("Sua tu thanh cong");
        }
        else {
            System.out.println("Khong thay tu ban can sua");
        }
    }
}
