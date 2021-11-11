package com.leverx;

public class Step {

    static final String PLAYER_1 = "X";

    static final String PLAYER_2 = "0";

    private final Desk desk = new Desk();

    private int x; //horizontal coordinate

    private int y; //vertical coordinate

    private int count; //count of similar cells

    public void doPlayerStep(String[][] array, String player, InputDataFromScanner inputDataFromScanner) {
        System.out.print("Player: (" + player + ") ");
        while (true) {
            x = inputDataFromScanner.checkInput("horizontal coordinate:", array);
            y = inputDataFromScanner.checkInput("vertical coordinate:", array);
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
            if (isTheBestStepForComputer(array, PLAYER_2) || isTheBestStepForComputer(array, PLAYER_1)) {
                    y = (int) (Math.random() * array.length);
                    x = (int) (Math.random() * array.length);
                    if (array[x][y].equals(".")) {
                        array[x][y] = player;
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

    public boolean isTheBestStepForComputerForDiagonalOne(String[][] array, String player) {
        count = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
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

    public boolean isTheBestStepForComputerForDiagonalTwo(String[][] array, String player) {
        count = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
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
                (!isTheBestStepForComputerForDiagonalOne(array, player)) &&
                (!isTheBestStepForComputerForDiagonalTwo(array, player));
    }
}
