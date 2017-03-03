package common;

/**
 * Created by tao on 3/3/17.
 */
public class TrieNode {
    public boolean isEnd;
    public TrieNode []children=null;
    public int size;
    public StringBuilder word;
    public TrieNode(){
        this.isEnd=false;
        this.size=0;
        this.word=new StringBuilder();
        children=new TrieNode[52];//包括大小写
    }

}
