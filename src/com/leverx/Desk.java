package com.leverx;

public class Desk {

    public void initializeDesk(String[][] array){
        drawTheFirstLineOfDesk(array);
        for (int i = 0; i < array.length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = ".";
                System.out.print(" " + array[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void drawDesk(String[][] array){
        drawTheFirstLineOfDesk(array);
        for (int i = 0; i < array.length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(" " + array[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void drawTheFirstLineOfDesk(String[][] array) {
        System.out.print("   ");
        for (int j = 0; j < array.length; j++) {
            System.out.print(j + "  ");
        }
        System.out.println();
    }
}
