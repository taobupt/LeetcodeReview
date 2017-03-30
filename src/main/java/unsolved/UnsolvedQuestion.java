package unsolved;

/**
 * Created by tao on 3/29/17.
 */
//好好研究的题目
public class UnsolvedQuestion {

    // Sentence Screen Fitting
    //TLE
    public int wordsTyping(String[] sentence, int rows, int cols) {
        int n=sentence.length;
        int row=0,col=0,index=0,res=0;
        while(row<rows){
            while(index<n){
                if(sentence[index].length()>cols)
                    return 0;
                if(col==0)
                    col+=sentence[index++].length();
                else
                    col+=sentence[index++].length()+1;
                if(col+sentence[index%n].length()+1>cols){
                    col=0;
                    row++;
                    if(row>=rows)
                        break;
                }
            }
            if(index==n && col<=cols){
                index=0;
                res++;
            }

        }
        return res;
    }

    public int wordsTypingBetter(String[] sentence, int rows, int cols) {
        String s = String.join(" ", sentence) + " ";
        int start = 0, l = s.length();
        for (int i = 0; i < rows; i++) {
            start += cols;
            if (s.charAt(start % l) == ' ') {
                start++;
            } else {
                while (start > 0 && s.charAt((start-1) % l) != ' ') {
                    start--;
                }
            }
        }

        return start / s.length();
    }
}
