package design;

import common.TreeNode;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tao on 4/6/17.
 */
public class CodecTest {


    @Test
    public void testCodec(){
        TreeNode root=new TreeNode(1);
        root.left=new TreeNode(2);
        root.right=new TreeNode(3);
        Codec c=new Codec();
        TreeNode node=c.deserialize(c.serialize(root));
        System.out.println("haha");
    }
}