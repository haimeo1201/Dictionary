public class DicCommandLine {
    public void dictionaryBasic(Dictionary dic) {
        DictionaryManagement read = new DictionaryManagement();
        DictionaryCommandline order = new DictionaryCommandline();
        read.insertFromCommandline(dic);
        order.showAllWords(dic);
    }
}
