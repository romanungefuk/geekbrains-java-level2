package tools;

import java.util.Random;

public class Tool {

    public static String[][] generateTwoDimObjectArray(int row, int col) {
        String[][] twoDimObjectArray = new String[row][col];
        for (int i = 0; i < twoDimObjectArray.length; i++) {
            for (int j = 0; j < twoDimObjectArray[0].length; j++) {
                twoDimObjectArray[i][j]= String.valueOf((new Random().nextInt(100)));
            }
        }
        return twoDimObjectArray;
    }

    public static void printTwoDimObjectArray(String[][] objTwoDimArray) {
        for (int i = 0; i < objTwoDimArray.length ; i++) {
            for (int j = 0; j < objTwoDimArray[0].length; j++) {
                System.out.print(objTwoDimArray[i][j].toString()+"\t");
            }
            System.out.println();
        }
    }

}
