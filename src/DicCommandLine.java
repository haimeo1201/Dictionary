public class DicCommandLine {
    /**
     * dictionaryBasic.
     */
    public void dictionaryBasic(Dictionary dic) {
        DictionaryManagement read = new DictionaryManagement();
        DictionaryCommandline order = new DictionaryCommandline();
//        read.insertFromCommandline(dic);
        order.showAllWords(dic);
    }

    /**
     * dictionaryAdvanced.
     */
    public void dictionaryAdvanced(Dictionary dic) {
        DictionaryManagement read = new DictionaryManagement();
        DictionaryCommandline order = new DictionaryCommandline();
        read.insertFromFile(dic);
        order.showAllWords(dic);

    }
}
