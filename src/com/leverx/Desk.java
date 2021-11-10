package com.leverx;

public class Desk {

    public void initializeDesk(String[][] array){
        System.out.println("   0  1  2");
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
        System.out.println("   0  1  2");
        for (int i = 0; i < array.length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(" " + array[i][j] + " ");
            }
            System.out.println();
        }
    }
}
