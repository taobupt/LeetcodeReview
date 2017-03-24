package design;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tao on 3/23/17.
 */
public class LeetcodeTrieTest {
    @Test
    public void testTrie(){
        LeetcodeTrie trie=new LeetcodeTrie();
        trie.insert("a");
        System.out.println(trie.search("a"));
        System.out.println(trie.startsWith("a"));
    }

}