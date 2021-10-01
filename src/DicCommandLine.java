public class DicCommandLine {
    public void dictionaryBasic(Dictionary dic) {
        //DictionaryManagement read = new DictionaryManagement();
        DictionaryCommandline order = new DictionaryCommandline();
        //read.insertFromCommandline(dic);
        order.insertFromFile();
        order.showAllWords(dic);
    }

    public void dictionaryAdvanced() {

    }
}
