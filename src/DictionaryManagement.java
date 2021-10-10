import java.io.*;
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
    public void addFromCommandline() throws IOException {
        String newWordTarget;
        String newWordExplain;
        System.out.println("Add to dictionary");
        System.out.println("Moi nhap tu tieng anh:");
        Scanner input = new Scanner(System.in);
        newWordTarget = input.nextLine();
        System.out.println("Moi nhap giai nghia");
        newWordExplain = input.nextLine();
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
    public void removeFromCommandline() throws IOException {
        int count1 = 0;
        int count2 = 0;
        System.out.println("Nhap tu ban muon xoa");
        Scanner inp = new Scanner(System.in);
        String toDelete = inp.nextLine();
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
            System.out.println("khong co tu ban xoa");
        }
        else {
            System.out.println("Xoa tu thanh cong");
        }
    }

    /**
     * sua tu (file .txt).
     */
    public void replaceFromCommandline() throws IOException {
        int count1 = 0;
        System.out.println("Nhap tu ban muon sua");
        Scanner input = new Scanner(System.in);
        String toReplace = input.nextLine();
        System.out.println("Nhap lai dinh nghia tu:");
        String replaceWord_explain = input.nextLine();
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
