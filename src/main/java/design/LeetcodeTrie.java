package design;

/**
 * Created by tao on 3/23/17.
 */


//典型的错误，要记住啊，你只是判断node是null，并且new 了node，但是没attache 到cur上啊
public class LeetcodeTrie {
    class TrieNode{
        public boolean isEnd;
        public TrieNode []children=null;
        public TrieNode(){
            isEnd=false;
            children=new TrieNode[26];
        }
    }

    /** Initialize your data structure here. */
    private TrieNode root=null;
    public LeetcodeTrie() {
        root=new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode cur=root;
        char []ss=word.toCharArray();
        int n=ss.length;
        for(int i=0;i<n;++i){
            TrieNode node=cur.children[ss[i]-'a'];
            if(node==null)
                node=new TrieNode();
            cur=node;
        }
        cur.isEnd=true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode cur=root;
        char []ss=word.toCharArray();
        int n=ss.length;
        for(int i=0;i<n;++i){
            TrieNode node=cur.children[ss[i]-'a'];
            if(node==null)
                return false;
            cur=node;
        }
        return cur.isEnd;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String word) {
        TrieNode cur=root;
        char []ss=word.toCharArray();
        int n=ss.length;
        for(int i=0;i<n;++i){
            TrieNode node=cur.children[ss[i]-'a'];
            if(node==null)
                return false;
            cur=node;
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */

