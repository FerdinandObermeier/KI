import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Solution1 {

    static String solveTask1(char[][] inputMatrix) {
        /*
        Enter your code here
        Return the minimum cost or return No path found!
         */
        return "";
    }
    /*
    Use as many helper functions as you like
     */

    public static void main(String[] args) throws FileNotFoundException {
        /* Read the input matrix*/
        String filename = args[0];
        char [][] inputMatrix = readFile(filename);

        /* Your main function to solve the problem*/
        String out = solveTask1(inputMatrix);
        System.out.println(out);
    }


    public static char[][] readFile(String filepath) throws FileNotFoundException {
        ArrayList<ArrayList<String>> array = new ArrayList<>();
        Scanner input = new Scanner(new File(filepath));
        while(input.hasNextLine())
        {
            Scanner colReader = new Scanner(input.nextLine());
            ArrayList col = new ArrayList();
            while(colReader.hasNext("[*_RX]"))
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
