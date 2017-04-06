package _04_07;

/**
 * Created by tao on 4/6/17.
 */
public class ThreeHundredSixToFifteen {



    //311. Sparse Matrix Multiplication
    //有普通做法，就是一行乘以一列的
    //这个对于c而言，并不是一步到位的，而是分步达到的。
    //better way
    //对于矩阵来说，随便乘都是可以的，只要你保证c[i][j]+=A[I][M]*B[M][J]
    public int[][] multiply(int[][] A, int[][] B) {
        if(A.length==0||A[0].length==0||B[0].length==0)
            return new int[0][0];
        int m=A.length,n=A[0].length,k=B[0].length;
        //A is m*n B is n*k
        //C is m*k;
        int [][]C=new int[m][k];
        for(int x=0;x<m;++x){
            for(int y=0;y<n;++y){
                if(A[x][y]==0)
                    continue;
                for(int z=0;z<k;++z){
                    C[x][z]+=A[x][y]*B[y][z];
                }
            }
        }
        return C;
    }

    //common way 就是调换了下循环的顺序，但是这样就不能判断A[I][J]

}
