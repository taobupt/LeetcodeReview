package design;

import java.util.*;

/**
 * Created by tao on 5/31/17.
 */

//将二维数组变成一维数组是一个主要的idea
public class SnakeGame {
    /** Initialize your data structure here.
     @param width - screen width
     @param height - screen height
     @param food - A list of food positions
     E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */

   //2D position info is encoded to 1d and stored as two copies
    Set<Integer>set;//the copy is good for fast loop-up for eating bldy case
    Deque<Integer>body;//this coy is good for updating tail
    int score;
    int [][]food;
    int foodIndex;
    int w;
    int h;

    public SnakeGame(int width, int height, int[][] food) {

        this.w=width;
        this.h=height;
        this.food=food;
        set=new HashSet<>();
        set.add(0);//initially at [0][0]
        body=new LinkedList<>();
        body.offerLast(0);
    }

    /** Moves the snake.
     @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
     @return The game's score after the move. Return -1 if game over.
     Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
        if(score==-1)
            return -1;
        int rowHead = body.peekFirst()/w;
        int colHead = body.peekFirst()%w;
        switch (direction){
            case "U":
                rowHead--;
                break;
            case "D":
                rowHead++;
                break;
            case "L":
                colHead--;
                break;
                default:
                    colHead++;
        }

        int head = rowHead*w+colHead;


        //case one out of boundary or eating body

        set.remove(body.peekLast());// new head is legal to be in old tail's pos
        if(rowHead<0||rowHead==h||colHead<0||colHead==w||set.contains(head)){
            return score=-1;
        }

        set.add(head);
        body.offerFirst(head);

        //case 2: eating food, keep tail .add head
        if(foodIndex<food.length && rowHead==food[foodIndex][0] && colHead==food[foodIndex][1]){
            set.add(body.peekLast());//old tail does not change, so add it back to set
            foodIndex++;
            return ++score;
        }

        //case3: normal move, remove tail. add head
        body.pollLast();
        return score;
    }
}
