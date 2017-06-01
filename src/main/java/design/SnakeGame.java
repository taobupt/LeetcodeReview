package design;

import java.util.List;

/**
 * Created by tao on 5/31/17.
 */
public class SnakeGame {
    /** Initialize your data structure here.
     @param width - screen width
     @param height - screen height
     @param food - A list of food positions
     E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */

    private int ind;//the index of food
    private boolean [][]vis;//the snake;
    private int [][]foods;
    private int w;
    private int h;
    private List<int[]>snake=null;//装足迹
    private int[][]dir=null;
    public SnakeGame(int width, int height, int[][] food) {
        this.h=height;
        this.w=width;
        ind=0;
        vis=new boolean[width][height];
        foods=new int[food.length][];
        for(int i=0;i<food.length;++i)
            foods[i]=food[i].clone();
        snake.add(new int[]{0,0,-1});//x, y, direction
        vis[0][0]=true;
        dir=new int[][]{{1,0},{0,-1},{0,1},{-1,0}};
    }

    /** Moves the snake.
     @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
     @return The game's score after the move. Return -1 if game over.
     Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
        int x = snake.get(0)[0];
        int y=snake.get(0)[1];
        int dire = snake.get(0)[2];
        if(direction.equals("U")){
            if(x==0)//碰墙
                return -1;
            if(vis[x+1][y])//collision
                return -1;
        }
        return -1;
    }
}
