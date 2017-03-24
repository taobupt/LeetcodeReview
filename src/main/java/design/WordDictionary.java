package design;

import common.Trie;

import java.util.PriorityQueue;

/**
 * Created by tao on 3/23/17.
 */
public class WordDictionary {

    class TrieNode{
        public boolean isEnd;
        public TrieNode []children=null;
        public  TrieNode(){
            isEnd=false;
            children=new TrieNode[26];
        }
    }
    /** Initialize your data structure here. */
    private TrieNode root=null;
    public WordDictionary() {
        root=new TrieNode();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        char[]ss=word.toCharArray();
        int n=ss.length;
        TrieNode cur=root;
        for(int i=0;i<n;++i){
            TrieNode node=cur.children[ss[i]-'a'];
            if(node==null)
                cur.children[ss[i]-'a']=new TrieNode();
            cur=cur.children[ss[i]-'a'];
        }
        cur.isEnd=true;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return find(root,word,0);
    }
    public boolean find(TrieNode node,String word,int pos){
        if(node==null)
            return false;
        if(pos==word.length())
            return node.isEnd;//非常重要
        if(word.charAt(pos)=='.'){
            //遍历所有的子节点；
            for(TrieNode child:node.children){
                if(child!=null && find(child,word,pos+1))
                    return true;
            }
            return false;
        }
        TrieNode child=node.children[word.charAt(pos)-'a'];//如果循环之前不return false的话，那么很有可能这里是'.'-'a';runtime error
        return child!=null && find(child,word,pos+1);
    }

}
