import tools.*;

public class Main {
    public static void main(String[] args) {
        String[][] stringArray1 = Tool.generateTwoDimObjectArray(4, 4);
        System.out.println("Массив без исключений:");
        stringArray1[1][2] = "15";
        stringArray1[3][2] = "9";
        Tool.printTwoDimObjectArray(stringArray1);
        System.out.println("Результат суммирование элементов двумерного массива: " + sumElementsOfArray(stringArray1));
//        String[][] stringArray2 = Tool.generateTwoDimObjectArray(4, 3);
//        System.out.println("Массив не правильной размерности:");
//        Tool.printTwoDimObjectArray(stringArray2);
//        sumElementsOfArray(stringArray2);
        String[][] stringArray3 = Tool.generateTwoDimObjectArray(4, 4);
        System.out.println("Массив c исключениями:");
        stringArray3[1][2] = "девять";
        stringArray3[3][2] = "пятнадцать";
        Tool.printTwoDimObjectArray(stringArray3);
        System.out.println("Результат суммирование элементов двумерного массива: " + sumElementsOfArray(stringArray3));

    }

    public static long sumElementsOfArray(String[][] stringArray) {
        long result = 0L;
        if (stringArray.length != 4 || stringArray[0].length != 4) {
            throw new MyArraySizeException("Вы должны передавать массив размером 4х4!");
        } else {
            for (int i = 0; i < stringArray.length; i++) {
                for (int j = 0; j < stringArray[0].length; j++) {
                    try {
                        result += Integer.parseInt(stringArray[i][j]);
                    } catch (NumberFormatException e) {
                        throw new MyArrayDataException("Элемент со значением " + stringArray[i][j].toString() +
                                " в строке " + i + " и колонке " + j + " не число.");
                    }
                }

            }
        }
        return result;
    }

}
