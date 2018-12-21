import javafx.util.Pair;

import java.io.*;
import java.util.*;

public class Solution2 {

    static char[][] solveTask2(char[][] board) {
        /*
        Enter your code here
        Return the updated board
         */
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[0].length; j++){
                if(board[i][j]== '.'){
                    board = updateBoard(board, i, j);
                }
            }
        }

        return board;
    }

    public static char[][] updateBoard(char[][] board, int i, int j){

        ArrayList<Pair> alreadyVisited = new ArrayList<>();
        ArrayList<Integer> history = new ArrayList<>();
        alreadyVisited.add(new Pair<Integer,Integer>(i,j));
        history.add(0);
        char[][] newBoard = board;
        boolean canConquer = false;
        int startI = i;
        int startJ = j;

        while(i!=0&&i!=board.length-1&&j!=0&&j!=board[0].length-1&&!canConquer){
            if(board[i][j-1] == '.'&&!alreadyVisited.contains(new Pair<Integer,Integer>(i,j-1))){
                history.add(1);
                j -= 1;
                alreadyVisited.add(new Pair<Integer, Integer>(i,j));
            }
            else if(board[i][j+1] == '.'&&!alreadyVisited.contains(new Pair<Integer,Integer>(i,j+1))){
                history.add(2);
                j += 1;
                alreadyVisited.add(new Pair<Integer, Integer>(i,j));
            }
            else if(board[i-1][j] == '.'&&!alreadyVisited.contains(new Pair<Integer,Integer>(i-1,j))){
                history.add(3);
                i -= 1;
                alreadyVisited.add(new Pair<Integer, Integer>(i,j));
            }
            else if(board[i+1][j] == '.'&&!alreadyVisited.contains(new Pair<Integer,Integer>(i+1,j))){
                history.add(4);
                i +=1;
                alreadyVisited.add(new Pair<Integer, Integer>(i,j));
            }
            /*else if(board[i-1][j+1] == '.'&&!alreadyVisited.contains(new Pair<Integer,Integer>(i-1,j+1))){
                history.add(5);
                i -= 1;
                j += 1;
                alreadyVisited.add(new Pair<Integer, Integer>(i,j));
            }
            else if(board[i-1][j-1] == '.'&&!alreadyVisited.contains(new Pair<Integer,Integer>(i-1,j-1))){
                history.add(6);
                i -= 1;
                j -= 1;
                alreadyVisited.add(new Pair<Integer, Integer>(i,j));
            }
            else if(board[i+1][j+1] == '.'&&!alreadyVisited.contains(new Pair<Integer,Integer>(i+1,j+1))){
                history.add(7);
                i += 1;
                j += 1;
                alreadyVisited.add(new Pair<Integer, Integer>(i,j));
            }
            else if(board[i+1][j-1] == '.'&&!alreadyVisited.contains(new Pair<Integer,Integer>(i+1,j-1))){
                history.add(8);
                i += 1;
                j -= 1;
                alreadyVisited.add(new Pair<Integer, Integer>(i,j));
            }*/
            else{
                if(i==startI && j==startJ){
                    canConquer = true;
                }
                else{
                    switch(history.get(history.size()-1)){
                        case 1:
                            history.remove(history.size()-1);
                            j +=1;
                            break;
                        case 2:
                            history.remove(history.size()-1);
                            j -=1;
                            break;
                        case 3:
                            history.remove(history.size()-1);
                            i +=1;
                            break;
                        case 4:
                            history.remove(history.size()-1);
                            i -=1;
                            break;
                        /*case 5:
                            history.remove(history.size()-1);
                            i +=1;
                            j -=1;
                            break;
                        case 6:
                            history.remove(history.size()-1);
                            i +=1;
                            j+=1;
                            break;
                        case 7:
                            history.remove(history.size()-1);
                            i -=1;
                            j -=1;
                            break;
                        case 8:
                            history.remove(history.size()-1);
                            i -=1;
                            j +=1;
                            break;*/
                    }
                }
            }
        }

        if(canConquer){
            for(int n=0; n<board.length; n++){
                for(int m=0; m<board[0].length; m++){
                    if(alreadyVisited.contains(new Pair<Integer,Integer>(n,m))){
                        newBoard[n][m] = 'X';
                    }
                }
            }
        }

        return newBoard;
    }

    public static void main(String[] args) {
        try {
            /* Read the input matrix*/
            char[][] input = readFile("input_for_task2(5).txt");

            /* Your main function to solve the problem*/
            char[][] output = solveTask2(input);

            /*Print the solution matrix to a txt file*/
            printMatrix(output, "output_for_task2.txt");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }



    }

    public static void printMatrix(char[][]board, String filepath){
        try (PrintWriter writer = new PrintWriter(filepath, "UTF-8")) {
            for(int i =0;i<board.length;i++){
                for(int j= 0;j<board[i].length;j++){
                    writer.print(board[i][j]);
                    if(j < board[i].length - 1){
                        writer.print(" ");
                    }
                }
                writer.println();
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static char[][] readFile(String filepath) throws FileNotFoundException {
        ArrayList<ArrayList<String>> array = new ArrayList<>();
        Scanner input = new Scanner(new File(filepath));
        while(input.hasNextLine())
        {
            Scanner colReader = new Scanner(input.nextLine());
            ArrayList col = new ArrayList();
            while(colReader.hasNext("[.X]"))
            {
                col.add(colReader.next());
            }
            if(col.size() > 0){
                array.add(col);
            }
        }
        input.close();

        char[][] inputMatrix = new char[array.size()][array.get(0).size()];
        for(int i=0;i<array.size();i++) {
            for(int j=0;j<array.get(0).size();j++){
                inputMatrix[i][j] = array.get(i).get(j).toCharArray()[0];
            }
        }
        return inputMatrix;
    }
}
