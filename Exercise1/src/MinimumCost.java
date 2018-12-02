import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MinimumCost {

    static char[][] inputArray;
    static int[][] fuelArray;
    static int height;
    static int width;
    static int currentPositionX;
    static int currentPositionY;
    static int goalPositionX;
    static int goalPositionY;
    static List<Integer> outputList = new ArrayList<Integer>();


    public static void main(String[] args) throws IOException {
        readInput();
    }

    private static void readInput() throws IOException{
        String rawInput = Files.lines(Paths.get("input_for_task1.txt")).collect(Collectors.joining("\n"));
        String input = rawInput.replace(" ", "");
        System.out.print(input);
        String[] inputList = input.split("\n");
        fillArray(inputList);
    }

    private static void fillArray(String[] inputList){

        height = inputList.length;
        String currentString = inputList[0];
        width = currentString.length();
        inputArray = new char[height][width];
        fuelArray = new int[height][width];

        for(int i=0; i<height; i++){
            currentString = inputList[i];
            for (int j=0; j<width; j++){
                inputArray[i][j] = currentString.charAt(j);

                if(currentString.charAt(j) == '*'){
                    fuelArray[i][j] = -1;
                }
                else{
                    fuelArray[i][j] = 0;
                }

                if(currentString.charAt(j) == 'R'){
                    currentPositionX = i;
                    currentPositionY = j;
                }
                if(currentString.charAt(j) == 'X'){
                    goalPositionX = i;
                    goalPositionY = j;
                }
            }
        }
    }

    private static void findPossiblePaths(){

        if(!continueSearching()){
            returnFuel();
        }
        else if (inputArray[currentPositionX][currentPositionY] == 'X'){
            outputList.add(fuelArray[currentPositionX][currentPositionY]);
        }
        else{
            if(goUp()){

            }
            else if(goDown()){

            }
            else if(goRight()){

            }
            else if(goLeft()){

            }
            else if (goDiagonal()) {
                diagonalDirection();
            }
        }
    }

    private static void findDirection(){

    }
    private static boolean goUp() {
        if(currentPositionX == 0){
            return false;
        }
        else if(inputArray[currentPositionX-1][currentPositionY] != '*'){
            return false;
        }
        else{
            return true;
        }
    }

    private static boolean goDown() {
        if(currentPositionX == height-1 || inputArray[currentPositionX+1][currentPositionY] != '*'){
            return false;
        }
        else{
            return true;
        }
    }

    private static boolean goRight() {
        if(currentPositionY == width-1 || inputArray[currentPositionX][currentPositionY+1] != '*'){
            return false;
        }
        else{
            return true;
        }
    }

    private static boolean goLeft() {
        if(currentPositionY == 0 || inputArray[currentPositionX][currentPositionY-1] != '*'){
            return false;
        }
        else{
            return true;
        }
    }

    private static boolean goDiagonal() {
        if(currentPositionX == 0){
            return false;
        }
        else if(inputArray[currentPositionX-1][currentPositionY] != '*'){
            return false;
        }
        else{
            return true;
        }
    }

    private static boolean diagonalDirection() {
        return true;
    }

    private static boolean continueSearching(){

        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                if(fuelArray[i][j]==0){
                    return true;
                }
            }
        }
        return false;
    }

    private static void returnFuel(){

        int fuelCost;

        if(true){
            Collections.sort(outputList);
            fuelCost = outputList.get(0);
            System.out.println(fuelCost);
            System.exit(0);
        }
        else{
            System.out.println("No possible path!");
            System.exit(0);
        }
    }
}
