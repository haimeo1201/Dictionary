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
        //ArrayList<Word> list = dic.getList();
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
        } catch (FileNotFoundException e) {
            System.out.println("An error");
        }
    }

    /**
     * dictionaryLookup.
     */
    public void dictionaryLookup() {
        //ArrayList<Word> list = dic.getList();
        Scanner scanner = new Scanner(System.in);
        boolean check1 = true;
        while (check1) {
            boolean check2 = false;
            if (check1) System.out.println("moi ban tra tu:");

            String search = "";
            if (scanner.hasNextLine()) {
                search = scanner.nextLine();
            }
            for (Word word : list) {
                if (word.getWord_target().matches(search)) {
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
        //bw.newLine();
        bw.write("\n" + newWordTarget + "\t" + newWordExplain);
        bw.close();
        System.out.println("them tu thanh cong");
    }

    /**
     * removeFromDictionary (file .txt)
     */
    public void removeFromCommandline() throws IOException {
        System.out.println("Nhap tu ban muon xoa");
        Scanner inp = new Scanner(System.in);
        String toDelete = inp.nextLine();
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
    public void replaceFromCommandline() throws IOException {
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
            if(line.contains(toReplace)) {
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
