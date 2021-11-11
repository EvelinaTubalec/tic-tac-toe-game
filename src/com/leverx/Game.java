package com.leverx;

import java.util.Scanner;

public class Game {

    private static final int SIZE = 3;

    private static final String[][] ARRAY = new String[SIZE][SIZE];

    private final Desk desk = new Desk();

    private final Step step = new Step();

    private final InputDataFromScanner inputDataFromScanner = new InputDataFromScanner();

    private int count; //count of similar cells

    public void chooseTheGame() {
        while (true) {
            System.out.println("Please, choose the variant of the game:");
            System.out.println("1. Game with 2 players");
            System.out.println("2. Game with computer bot");
            System.out.println("0. Exit");
            int i = inputDataFromScanner.checkInput("your choice:", ARRAY);
            desk.initializeDesk(ARRAY);
            if (i == 1) {
                gameWithEachOther(ARRAY);
            } else if (i == 2) {
                gameWithComputer(ARRAY);
            } else if (i == 0) {
                inputDataFromScanner.scanner.close();
                break;
            }
        }
    }

    public void gameWithEachOther(String[][] array) {
        while (true) {
            if (isActionForPlayer(array, Step.PLAYER_1) || isActionForPlayer(array, Step.PLAYER_2)) {
                break;
            }
        }
    }

    public void gameWithComputer(String[][] array) {
        while (true) {
            if (isActionForPlayer(array, Step.PLAYER_1) || isActionForComputer(array, Step.PLAYER_2)) {
                break;
            }
        }
    }

    public boolean isActionForPlayer(String[][] array, String player) {
        step.doPlayerStep(array, player, inputDataFromScanner);
        return isTheGameOver(array, player);
    }

    public boolean isActionForComputer(String[][] array, String player) {
        step.doComputerStep(array, player);
        return isTheGameOver(array, player);
    }

    public boolean isTheGameOverOnLines(String[][] array, String player) {
        for (String[] strings : array) {
            count = 0;
            for (String string : strings) {
                if (!string.equals(player)) {
                    break;
                } else {
                    count++;
                    if (isGameOverCheckAndWrite(array, count, player)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isTheGameOverInColumn(String[][] array, String player) {
        for (int j = 0; j < array.length; j++) {
            count = 0;
            for (String[] strings : array) {
                if (!strings[j].equals(player)) {
                    break;
                } else {
                    count++;
                    if (isGameOverCheckAndWrite(array, count, player)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isTheGameOverOnDiagonalOne(String[][] array, String player) {
        count = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (i == j && array[i][j].equals(player)) {
                    count++;
                    if (isGameOverCheckAndWrite(array, count, player)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isTheGameOverOnDiagonalTwo(String[][] array, String player) {
        count = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if ((i + j == array.length - 1) && array[i][j].equals(player)) {
                    count++;
                    if (isGameOverCheckAndWrite(array, count, player)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isGameOverCheckAndWrite(String[][] array, int count, String player) {
        if (count == array.length) {
            System.out.println("THE GAME IS OVER! " + player + " won!!!");
            return true;
        }
        return false;
    }

    public boolean isTheGameOverOfDeadHeat(String[][] array) {
        count = 0;
        for (String[] strings : array) {
            for (String string : strings) {
                if (string.equals(".")) {
                    count++;
                }
            }
        }
        if (count == 0) {
            System.out.println("THE GAME IS OVER!Dead heat!");
            return true;
        }
        return false;
    }

    public boolean isTheGameOver(String[][] array, String player) {
        return (isTheGameOverOnLines(array, player)) ||
                (isTheGameOverInColumn(array, player)) ||
                (isTheGameOverOnDiagonalOne(array, player)) ||
                (isTheGameOverOnDiagonalTwo(array, player)) ||
                (isTheGameOverOfDeadHeat(array));
    }
}



