import java.io.*;
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
     * dictionaryLookup.Not using in this version.
     */
    public String dictionaryLookup(String search) {
        String out = "";
        //inputWord();
        boolean check = false;
        for (Word word : list) {
            if (word.getWord_target().matches(search)) {
                check = true;
                out = word.getWord_explain();
                System.out.println("tu ban tra co nghia: " + word.getWord_explain());
                break;
            }
        }
        if (!check) {
            System.out.println("khong thay tu ban tim");
            out = "Tu ban tra khong hop le hoac khong co trong tu dien";
        }
        return out;
    }

    /**
     * addToDictionary (.txt file) .
     */
    public void addFromCommandline(String newWordTarget, String newWordExplain) throws IOException {
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
        //System.out.println("Nhap tu ban muon xoa");
        // inp = new Scanner(System.in);
        //String toDelete = inp.nextLine();
        File file = new File("data\\dictionaries.txt");
        File temp = File.createTempFile("data\\tempFile", ".txt", file.getParentFile());
        String charset = "UTF-8";
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(temp), charset));
        for (String line; (line = reader.readLine()) != null; ) {
            if (!line.contains(toDelete)) {
                writer.println(line);
            }
        }
        reader.close();
        writer.close();
        file.delete();
        temp.renameTo(file);
        System.out.println("Xoa tu thanh cong");
    }

    /**
     * sua tu (file .txt).
     */
    public void replaceFromCommandline(String toReplace, String replaceWord_explain) throws IOException {
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
            }
            writer.println(line);
        }
        reader.close();
        writer.close();
        file.delete();
        tempFile.renameTo(file);
        System.out.println("Sua tu thanh cong");
    }
}
