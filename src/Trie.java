import java.util.ArrayList;
import java.util.List;

public class Trie {
    static Node root;
    Trie(){
        root = new Node();
    }
    static class Node {
        String meaning;
        Node[] child = new Node[26];
        boolean isEnd;
        Node() {
            isEnd = false;
            for (int i = 0; i < 26; i++) {
                child[i] = null;
            }
        }
    }
    public void init(ArrayList<Word> list){
        for(Word word: list){
            insert(word);
        }
    }
    public void insert(Word word){
        Node start = root;
        String key = word.getWord_target();
        key = key.toLowerCase();
        for(int i=0;i<key.length();i++){
            if(start.child[(key.charAt(i)-'a')]==null){
                start.child[key.charAt(i)-'a'] = new Node();
            }
            start = start.child[key.charAt(i)-'a'];
        }
        start.isEnd = true;
        start.meaning = word.getWord_explain();
    }
    public String meaning(String key){
        Node start = root;
        key = key.toLowerCase();
        for(int i=0;i<key.length();i++){
            if(start.child[(key.charAt(i)-'a')]==null){
                return null;
            }
            start = start.child[key.charAt(i)-'a'];
        }
       return start.meaning;
    }
    public List<String> listStartFromNode(Node start, String cur){
        List<String> list = new ArrayList<>();
        if(start.isEnd){
            list.add(cur);
        }
        for(int i=0;i<26;i++){
            if(start.child[i]!=null){
                char c = (char)(i+'a');
                list.addAll(listStartFromNode(start.child[i],cur + c));
            }
        }
        return list;
    }
    public List<String> listMatchPrefix(String prefix){
        List<String> list = new ArrayList<>();
        Node start = root;
        for(int i=0;i<prefix.length();i++){
            if(start.child[(prefix.charAt(i)-'a')]==null){
                return new ArrayList<>();
            }
            start = start.child[(prefix.charAt(i)-'a')];
        }
        return listStartFromNode(start,prefix);
    }
}
