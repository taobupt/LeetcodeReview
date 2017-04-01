package design;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tao on 4/1/2017.
 */
public class CodecString {

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuffer sb=new StringBuffer();
        for(String str:strs)
            sb.append(str.length()).append("@").append(str);
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String>res=new ArrayList<>();
        int start=0,n=s.length();
        while(start<n){
            int index=s.indexOf('@',start);
            int length=Integer.parseInt(s.substring(start,index));
            res.add(s.substring(index+1,length+1+index));
            start=index+length+1;
        }
        return res;
    }
}
