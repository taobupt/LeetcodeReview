package design;

/**
 * Created by tao on 4/7/17.
 */


public class NumMatrix {

    private int[][]sum=null;
    public NumMatrix(int[][] matrix) {
        if(matrix.length==0||matrix[0].length==0)
            return;
        int m=matrix.length,n=matrix[0].length;
        sum=new int[m][n];
        for(int i=0;i<m;++i)
            sum[i][0]=(i==0?matrix[i][0]:matrix[i][0]+sum[i-1][0]);
        for(int j=0;j<n;++j)
            sum[0][j]=(j==0?matrix[0][j]:matrix[0][j]+sum[0][j-1]);
        for(int i=1;i<m;++i){
            for(int j=1;j<n;++j)
                sum[i][j]=matrix[i][j]+sum[i-1][j]+sum[i][j-1]-sum[i-1][j-1];
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {

        if(row1<1 && col1<1)
            return sum[row2][col2];
        else if(row1<1)
            return sum[row2][col2]-sum[row2][col1-1];
        else if(col1<1)
            return sum[row2][col2]-sum[row1-1][col2];
        else
            return sum[row2][col2]+sum[row1-1][col1-1]-sum[row2][col1-1]-sum[row1-1][col2];

    }

}
