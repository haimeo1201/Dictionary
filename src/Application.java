public class Application {
    public static void main(String[] argc) {
        Dictionary dic = new Dictionary();
//        DicCommandLine basic = new DicCommandLine();
//        basic.dictionaryBasic(dic);
        DicCommandLine advance = new DicCommandLine();
        DictionaryManagement read = new DictionaryManagement();
//        read.insertFromFile(dic);
        advance.dictionaryAdvanced(dic);
        read.dictionaryLookup(dic);
    }
}
