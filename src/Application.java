public class Application {
    public static void main(String[] argc) {
        Dictionary dic = new Dictionary();
        DictionaryCommandline basic = new DictionaryCommandline();
        //basic.dictionaryBasic();
        DictionaryCommandline advance = new DictionaryCommandline();
        DictionaryManagement read = new DictionaryManagement();
        //read.insertFromFile();
        advance.dictionaryAdvanced();
        read.dictionaryLookup();
    }
}
