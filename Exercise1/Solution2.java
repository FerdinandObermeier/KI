import java.io.*;
import java.util.*;

public class Solution2 {

    static char[][] solveTask2(char[][] board) {
        /*
        Enter your code here
        Return the updated board
         */
        return board;
    }

    public static void main(String[] args) {
        try {
            /* Read the input matrix*/
            char[][] input = readFile(args[0]);

            /* Your main function to solve the problem*/
            char[][] output = solveTask2(input);

            /*Print the solution matrix to a txt file*/
            printMatrix(output, "output_for_task2(solution).txt");

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
