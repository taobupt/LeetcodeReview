package common;

/**
 * Created by tao on 3/3/17.
 */
public class Trie {
    public TrieNode root=null;

    public Trie(){
        root=new TrieNode();
    }

    public void addWords(String word){
        TrieNode cur=root;
        char []words=word.toCharArray();
        for(char c:words){
            TrieNode node=cur.children[c>=97?c-'a'+26:c-'A'];
            if(node==null){
                cur.children[c>=97?c-'a'+26:c-'A']=new TrieNode();
                cur.size++;
                cur.children[c>=97?c-'a'+26:c-'A'].word.append(cur.word).append(c);
            }
            cur=cur.children[c>=97?c-'a'+26:c-'A'];
            cur.cnt++;
        }
        cur.isEnd=true;
    }


}
