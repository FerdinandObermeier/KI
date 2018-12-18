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
        int [][] fuelMatrix = createFuelMatrix(inputMatrix);

        int[] positions = getPositions(inputMatrix);
        int currentPositionI = positions[0];
        int currentPositionJ = positions[1];
        int goalPositionI = positions[2];
        int goalPositionJ = positions[3];

        ArrayList<Integer> startList = new ArrayList<>();
        startList.add(0);

        return findPossiblePaths(inputMatrix, fuelMatrix, currentPositionI, currentPositionJ, goalPositionI, goalPositionJ, startList);
    }
    /*
    Use as many helper functions as you like
     */

    public static int [][] createFuelMatrix(char [][] inputMatrix){

        int [][] fuelMatrix = new int[inputMatrix.length][inputMatrix[0].length];

        for(int i=0; i<inputMatrix.length; i++){
            for(int j=0; j<inputMatrix[0].length; j++){
                if(inputMatrix[i][j]=='*'){
                    fuelMatrix[i][j] = -1;
                }
            }
        }

        return fuelMatrix;
    }

    public static String findPossiblePaths(char[][] inputMatrix, int[][] fuelMatrix, int currentPositionI, int currentPositionJ, int goalPositionI, int goalPositionJ, ArrayList<Integer> history){

        ArrayList<Integer> newHistory = history;

        while(continueSearching(fuelMatrix)){
            if(goRight(inputMatrix, currentPositionI, currentPositionJ)&&betterPath(fuelMatrix, currentPositionI, currentPositionJ, 1))
            {
                newHistory.add(1);
                currentPositionJ +=1;
                fuelMatrix[currentPositionI][currentPositionJ] = fuelMatrix[currentPositionI][currentPositionJ-1]+5;
            }
            else if(goLeft(inputMatrix, currentPositionI, currentPositionJ)&&betterPath(fuelMatrix, currentPositionI, currentPositionJ, 2))
            {
                newHistory.add(2);
                currentPositionJ -=1;
                fuelMatrix[currentPositionI][currentPositionJ] = fuelMatrix[currentPositionI][currentPositionJ+1]+5;
            }
            else if(goUp(inputMatrix, currentPositionI, currentPositionJ)&&betterPath(fuelMatrix, currentPositionI, currentPositionJ, 3))
            {
                newHistory.add(3);
                currentPositionI -=1;
                fuelMatrix[currentPositionI][currentPositionJ] = fuelMatrix[currentPositionI+1][currentPositionJ]+6;
            }
            else if(goDown(inputMatrix, currentPositionI, currentPositionJ)&&betterPath(fuelMatrix, currentPositionI, currentPositionJ, 4))
            {
                newHistory.add(4);
                currentPositionI +=1;
                fuelMatrix[currentPositionI][currentPositionJ] = fuelMatrix[currentPositionI-1][currentPositionJ]+6;
            }
            else if(goDiagonalUpperRight(inputMatrix, currentPositionI, currentPositionJ)&&betterPath(fuelMatrix, currentPositionI, currentPositionJ, 5))
            {
                newHistory.add(5);
                currentPositionI -=1;
                currentPositionJ +=1;
                fuelMatrix[currentPositionI][currentPositionJ] = fuelMatrix[currentPositionI+1][currentPositionJ-1]+10;
            }
            else if(goDiagonalUpperLeft(inputMatrix, currentPositionI, currentPositionJ)&&betterPath(fuelMatrix, currentPositionI, currentPositionJ, 6))
            {
                newHistory.add(6);
                currentPositionI -=1;
                currentPositionJ -=1;
                fuelMatrix[currentPositionI][currentPositionJ] = fuelMatrix[currentPositionI+1][currentPositionJ+1]+10;
            }
            else if(goDiagonalLowerRight(inputMatrix, currentPositionI, currentPositionJ)&&betterPath(fuelMatrix, currentPositionI, currentPositionJ, 7))
            {
                newHistory.add(7);
                currentPositionI +=1;
                currentPositionJ +=1;
                fuelMatrix[currentPositionI][currentPositionJ] = fuelMatrix[currentPositionI-1][currentPositionJ-1]+10;
            }
            else if(goDiagonalLowerLeft(inputMatrix, currentPositionI, currentPositionJ)&&betterPath(fuelMatrix, currentPositionI, currentPositionJ, 8))
            {
                newHistory.add(8);
                currentPositionI +=1;
                currentPositionJ -=1;
                fuelMatrix[currentPositionI][currentPositionJ] = fuelMatrix[currentPositionI-1][currentPositionJ+1]+10;
            }
            else{
                if(newHistory.get(newHistory.size()-1) == 0 || inputMatrix[currentPositionI][currentPositionJ] == 'R'){
                    return getOutput(fuelMatrix, goalPositionI, goalPositionJ);
                }
                else{
                    switch (newHistory.get(newHistory.size()-1)){
                        case 1:
                            currentPositionJ -=1;
                            newHistory.remove(newHistory.size()-1);
                            break;
                        case 2:
                            currentPositionJ +=1;
                            newHistory.remove(newHistory.size()-1);
                            break;
                        case 3:
                            currentPositionI +=1;
                            newHistory.remove(newHistory.size()-1);
                            break;
                        case 4:
                            currentPositionI -=1;
                            newHistory.remove(newHistory.size()-1);
                            break;
                        case 5:
                            currentPositionI +=1;
                            currentPositionJ -=1;
                            newHistory.remove(newHistory.size()-1);
                            break;
                        case 6:
                            currentPositionI +=1;
                            currentPositionJ +=1;
                            newHistory.remove(newHistory.size()-1);
                            break;
                        case 7:
                            currentPositionI -=1;
                            currentPositionJ -=1;
                            newHistory.remove(newHistory.size()-1);
                            break;
                        case 8:
                            currentPositionI -=1;
                            currentPositionJ +=1;
                            newHistory.remove(newHistory.size()-1);
                            break;
                    }
                }
            }
        }
        return getOutput(fuelMatrix, goalPositionI, goalPositionJ);
    }

    public static boolean continueSearching(int[][] fuelMatrix){

        for(int i=0; i<fuelMatrix.length; i++){
            for(int j=0; j<fuelMatrix[0].length; j++){
                if(fuelMatrix[i][j]==0){
                    return true;
                }
            }
        }
        return false;
    }

    public static String getOutput(int[][] fuelMatrix, int goalPositionI, int goalPositionJ){
        if(fuelMatrix[goalPositionI][goalPositionJ] != 0){
            return String.valueOf(fuelMatrix[goalPositionI][goalPositionJ]);
        }
        else{
            return "No path found!";
        }
    }

    public static boolean goUp(char[][] inputMatrix, int currentPositionI, int currentPositionJ) {
        if(currentPositionI == 0 || inputMatrix[currentPositionI-1][currentPositionJ] == '*' || inputMatrix[currentPositionI-1][currentPositionJ] == 'R'){
            return false;
        }
        else{
            return true;
        }
    }

    public static boolean goDown(char[][] inputMatrix, int currentPositionI, int currentPositionJ) {
        if(currentPositionI == inputMatrix.length-1 || inputMatrix[currentPositionI+1][currentPositionJ] == '*' || inputMatrix[currentPositionI+1][currentPositionJ] == 'R'){
            return false;
        }
        else{
            return true;
        }
    }

    public static boolean goRight(char[][] inputMatrix, int currentPositionI, int currentPositionJ) {
        if(currentPositionJ == inputMatrix[0].length-1 || inputMatrix[currentPositionI][currentPositionJ+1] == '*' || inputMatrix[currentPositionI][currentPositionJ+1] == 'R'){
            return false;
        }
        else{
            return true;
        }
    }

    public static boolean goLeft(char[][] inputMatrix, int currentPositionI, int currentPositionJ) {
        if(currentPositionJ == 0 || inputMatrix[currentPositionI][currentPositionJ-1] == '*' || inputMatrix[currentPositionI][currentPositionJ-1] == 'R'){
            return false;
        }
        else{
            return true;
        }
    }

    public static boolean goDiagonalUpperRight(char[][] inputMatrix, int currentPositionI, int currentPositionJ) {
        if(currentPositionI == 0 || currentPositionJ == inputMatrix[0].length-1 || inputMatrix[currentPositionI-1][currentPositionJ+1] == '*' || inputMatrix[currentPositionI-1][currentPositionJ+1] == 'R'){
            return false;
        }
        else{
            return true;
        }
    }

    public static boolean goDiagonalUpperLeft(char[][] inputMatrix, int currentPositionI, int currentPositionJ) {
        if(currentPositionI == 0 || currentPositionJ == 0 || inputMatrix[currentPositionI-1][currentPositionJ-1] == '*' ||inputMatrix[currentPositionI-1][currentPositionJ-1] == 'R'){
            return false;
        }
        else{
            return true;
        }
    }

    public static boolean goDiagonalLowerRight(char[][] inputMatrix, int currentPositionI, int currentPositionJ) {
        if(currentPositionI == inputMatrix.length-1 || currentPositionJ == inputMatrix[0].length-1 || inputMatrix[currentPositionI+1][currentPositionJ+1] == '*' ||inputMatrix[currentPositionI+1][currentPositionJ+1] == 'R'){
            return false;
        }
        else{
            return true;
        }
    }

    public static boolean goDiagonalLowerLeft(char[][] inputMatrix, int currentPositionI, int currentPositionJ) {
        if(currentPositionI == inputMatrix.length-1 || currentPositionJ == 0 || inputMatrix[currentPositionI+1][currentPositionJ-1] == '*' || inputMatrix[currentPositionI+1][currentPositionJ-1] == 'R'){
            return false;
        }
        else{
            return true;
        }
    }

    public static boolean betterPath(int[][] fuelMatrix, int currentPositionI, int currentPositionJ, int type) {

        switch (type) {
            case 1:
                if (((fuelMatrix[currentPositionI][currentPositionJ] + 5) < (fuelMatrix[currentPositionI][currentPositionJ + 1])) || fuelMatrix[currentPositionI][currentPositionJ + 1] == 0) {
                    return true;
                } else return false;

            case 2:
                if (((fuelMatrix[currentPositionI][currentPositionJ] + 5) < (fuelMatrix[currentPositionI][currentPositionJ - 1])) || fuelMatrix[currentPositionI][currentPositionJ - 1] == 0) {
                    return true;
                } else return false;
            case 3:
                if (((fuelMatrix[currentPositionI][currentPositionJ] + 6) < (fuelMatrix[currentPositionI - 1][currentPositionJ])) || fuelMatrix[currentPositionI - 1][currentPositionJ] == 0) {
                    return true;
                } else return false;
            case 4:
                if (((fuelMatrix[currentPositionI][currentPositionJ] + 6) < (fuelMatrix[currentPositionI + 1][currentPositionJ])) || fuelMatrix[currentPositionI + 1][currentPositionJ] == 0) {
                    return true;
                } else return false;
            case 5:
                if (((fuelMatrix[currentPositionI][currentPositionJ] + 10) < (fuelMatrix[currentPositionI - 1][currentPositionJ + 1])) || fuelMatrix[currentPositionI - 1][currentPositionJ + 1] == 0) {
                    return true;
                } else return false;
            case 6:
                if (((fuelMatrix[currentPositionI][currentPositionJ] + 10) < (fuelMatrix[currentPositionI - 1][currentPositionJ - 1])) || fuelMatrix[currentPositionI - 1][currentPositionJ - 1] == 0) {
                    return true;
                } else return false;
            case 7:
                if (((fuelMatrix[currentPositionI][currentPositionJ] + 10) < (fuelMatrix[currentPositionI + 1][currentPositionJ + 1])) || fuelMatrix[currentPositionI + 1][currentPositionJ + 1] == 0) {
                    return true;
                } else return false;
            case 8:
                if (((fuelMatrix[currentPositionI][currentPositionJ] + 10) < (fuelMatrix[currentPositionI + 1][currentPositionJ - 1])) || fuelMatrix[currentPositionI + 1][currentPositionJ - 1] == 0) {
                    return true;
                } else return false;

        }
        return false;
    }

    public static int[] getPositions(char[][] inputMatrix){

        int [] positions = new int[4];

        for(int i = 0; i<inputMatrix.length; i++){
            for(int j = 0; j<inputMatrix[0].length; j++){
                if(inputMatrix[i][j] == 'R'){
                    positions[0] = i;
                    positions[1] = j;
                }
                if(inputMatrix[i][j] == 'X'){
                    positions[2] = i;
                    positions[3] = j;
                }
            }
        }

        return positions;
    }

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
