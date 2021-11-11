package com.leverx;

import java.util.Scanner;

public class Step {

    private static final String PLAYER_1 = "X";

    private static final String PLAYER_2 = "0";

    private final Desk desk = new Desk();

    private int x; //horizontal coordinate

    private int y; //vertical coordinate

    private int count; //count of similar cells

    public void doPlayerStep(String[][] array, String player, InputData inputData, Scanner scanner) {
        System.out.print("Player: (" + player + ") ");
        while (true) {
            x = inputData.checkInput("horizontal coordinate:", scanner, array);
            y = inputData.checkInput("vertical coordinate:", scanner, array);
            if (!array[x][y].equals(".")) {
                System.out.println("Incorrect input!This cage is already occupied!");
            } else {
                array[x][y] = player;
                break;
            }
        }
        desk.drawDesk(array);
    }

    public void doComputerStep(String[][] array, String player) {
        System.out.println("Player 2. (0)");
        while (true) {
            if (isTheBestStepForComputer(array, PLAYER_2)) {
                if (isTheBestStepForComputer(array, PLAYER_1)) {
                    y = (int) (Math.random() * array.length);
                    x = (int) (Math.random() * array.length);
                    if (array[x][y].equals(".")) {
                        array[x][y] = player;
                        break;
                    }
                } else {
                    break;
                }
            } else {
                break;
            }
        }
        desk.drawDesk(array);
    }

    public boolean isTheBestStepForComputerForLines(String[][] array, String player) {
        for (int i = 0; i < array.length; i++) {
            count = 0;
            for (int j = 0; j < array.length; j++) {
                /* go over each element of the line,
                the number of identical elements in the line is "array.length - 1" means
                that the player can win in the next move,
                so we make a computer move to an empty cell
                */
                if (array[i][j].equals(player)) {
                    count++;
                    if (count == array.length - 1) {
                        for (j = 0; j < array[i].length; j++) {
                            if (array[i][j].equals(".")) {
                                array[i][j] = PLAYER_2;
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean isTheBestStepForComputerForColumns(String[][] array, String player) {
        for (int j = 0; j < array.length; j++) {
            count = 0;
            for (int i = 0; i < array.length; i++) {
                if (array[i][j].equals(player)) {
                    count++;
                    if (count == array.length - 1) {
                        for (i = 0; i < array.length; i++) {
                            if (array[i][j].equals(".")) {
                                array[i][j] = PLAYER_2;
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean isTheBestStepForComputerForDiagonal1(String[][] array, String player) {
        count = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                //if all line of elements contain coordinates, where i == j, then we are on the first diagonal
                if (i == j && array[i][j].equals(player)) {
                    count++;
                    if ((count == array.length - 1)) {
                        for (i = 0; i < array.length; i++) {
                            for (j = 0; j < array[i].length; j++) {
                                if (i == j && array[i][j].equals(".")) {
                                    array[i][j] = PLAYER_2;
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean isTheBestStepForComputerForDiagonal2(String[][] array, String player) {
        count = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                //if all line of elements contain coordinates, where i + j == array.length-1,
                //then we are on the second diagonal
                if ((i + j == array.length - 1) && array[i][j].equals(player)) {
                    count++;
                    if ((count == array.length - 1)) {
                        for (i = 0; i < array.length; i++) {
                            for (j = 0; j < array[i].length; j++) {
                                if ((i + j == array.length - 1) && array[i][j].equals(".")) {
                                    array[i][j] = PLAYER_2;
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean isTheBestStepForComputer(String[][] array, String player) {
        return (!isTheBestStepForComputerForLines(array, player)) &&
                (!isTheBestStepForComputerForColumns(array, player)) &&
                (!isTheBestStepForComputerForDiagonal1(array, player)) &&
                (!isTheBestStepForComputerForDiagonal2(array, player));
    }
}
