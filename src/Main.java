import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static int result = 0;

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(new BufferedReader(new FileReader("input.txt")));
        //get args from first line
        String[] splittedArgs = sc.nextLine().trim().split(" ");
        int rows = Integer.parseInt(splittedArgs[0]);
        int columns = Integer.parseInt(splittedArgs[1]);
        //get 2d array input from file
        int[][] arrayInput = get2dArrayInput(sc, rows, columns);

        //close file reading
        sc.close();

        getMaxSumOfAllRoads(arrayInput, rows, columns, 0, 0, new ArrayList<>());

        //write result with output file and close file writing
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("output.txt"));
        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.close();

        //System.out.println(result);
    }

    private static int[][] get2dArrayInput(Scanner sc, int rows, int columns) {
        int [][] myArray = new int[rows][columns];
        while(sc.hasNextLine()) {
            for (int i=0; i<myArray.length; i++) {
                String[] line = sc.nextLine().trim().split(" ");
                for (int j=0; j<line.length; j++) {
                    myArray[i][j] = Integer.parseInt(line[j]);
                }
            }
        }
        return myArray;
    }

    private static void getMaxSumOfAllRoads(int[][] arr, int rows, int columns,
                                            int startingPosDown, int startingPosRight, List<Integer> route)
    {
        //if already on max right or max bottom item return
        if(startingPosDown >= rows || startingPosRight >= columns) return;
        //adding current item to list
        route.add(arr[startingPosDown][startingPosRight]);
        //go bottom
        getMaxSumOfAllRoads(arr, rows, columns, startingPosDown+1, startingPosRight, route);
        //go right
        getMaxSumOfAllRoads(arr, rows, columns, startingPosDown, startingPosRight+1, route);
        //if reached the end (right bottom corner) check sum of all elements in route
        if(startingPosDown == rows-1 && startingPosRight == columns-1){
 //           System.out.println(route);
            int tmpSum = route.stream().mapToInt(Integer::intValue).sum();
            if (tmpSum > result) {
                result = tmpSum;
            }
        }
        //remove last element of the list until we reach a possibility to go with new road
        route.remove(route.size()-1);
    }
}