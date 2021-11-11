package com.leverx;

import java.util.Scanner;

public class InputDataFromScanner extends InputData{

    final Scanner scanner = new Scanner(System.in);

    public int checkInput(String message, String[][] array){
        int inputValue;
        while (true) {
            System.out.println("Please, enter " + message);
            if (!scanner.hasNextInt()) {
                String input = scanner.next();
                System.out.println("Incorrect input " + input + "!You have to enter a number!Please, try again!");
            } else {
                inputValue = scanner.nextInt();
                if (inputValue < 0 || inputValue > array.length-1) {
                    System.out.println("Incorrect input!You have to enter a number in the range of [0;2]!" +
                            "Please, try again!");
                } else {
                    break;
                }
            }
        }
        return inputValue;
    }
}