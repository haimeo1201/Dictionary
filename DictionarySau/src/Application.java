import java.io.IOException;

public class Application {
    public static void main(String[] argc) throws IOException {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        DictionaryCommandline advance = new DictionaryCommandline();
        DictionaryManagement read = new DictionaryManagement();
        advance.dictionaryAdvanced();
        PanelStart start = new PanelStart();
    }
}
