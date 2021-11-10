package com.leverx;

public class Step {

    private int x; //horizontal coordinate

    private int y; //vertical coordinate

    private static final String player1 = "X";

    private static final String player2 = "0";

    private final Desk desk = new Desk();

    public void doPlayerStep(String[][] array, String player, InputData inputData) {
        System.out.print("Player: (" + player + ") ");
        while (true) {
            x = inputData.checkInput("horizontal coordinate:");
            y = inputData.checkInput("vertical coordinate:");
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
            if (!isTheBestStepForComputer(array, player2)) {
                if (!isTheBestStepForComputer(array, player1)) {
                    y = (int) (Math.random() * 3);
                    x = (int) (Math.random() * 3);
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
            int count = 0; //count of similar cells
            for (int j = 0; j < array.length; j++) {
                if(isStepForWin(array, count, i, j, player)){
                    return true;
                }
            }
        }
        return false;
    }


    public boolean isTheBestStepForComputerForColumns(String[][] array, String player) {
        for (int j = 0; j < array.length; j++) {
            int count = 0; //count of similar cells
            for (int i = 0; i < array.length; i++) {
                if (array[i][j].equals(player)) {
                    count++;
                    if(isStepForWin(array, count, i, j, player)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isStepForWin(String[][] array, int count, int i, int j, String player) {
        if (array[i][j].equals(player)) {
            count++;
            if (count == array.length - 1) {
                for (j = 0; j < array[i].length; j++) {
                    if (array[i][j].equals(".")) {
                        array[i][j] = player2;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isTheBestStepForComputerForDiagonal1(String[][] array, String player) {
        int count = 0; //count of similar cells
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (i == j && array[i][j].equals(player)) {
                    count++;
                    if ((count == array.length - 1)) {
                        for (i = 0; i < array.length; i++) {
                            for (j = 0; j < array[i].length; j++) {
                                if (i == j && array[i][j].equals(".")) {
                                    array[i][j] = player2;
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
        int count = 0; //count of similar cells
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if ((i + j == array.length-1) && array[i][j].equals(player)) {
                    count++;
                    if ((count == array.length - 1)) {
                        for (i = 0; i < array.length; i++) {
                            for (j = 0; j < array[i].length; j++) {
                                if ((i + j == array.length-1) && array[i][j].equals(".")) {
                                    array[i][j] = player2;
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
        if (isTheBestStepForComputerForLines(array, player)) {
            return true;
        }else if(isTheBestStepForComputerForColumns(array, player)) {
            return true;
        }else if(isTheBestStepForComputerForDiagonal1(array, player)){
            return true;
        }else if(isTheBestStepForComputerForDiagonal2(array, player)){
            return true;
        }else {
            return false;
        }
    }
}
