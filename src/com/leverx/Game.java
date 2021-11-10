package com.leverx;

public class Game {

    private static final String player1 = "X";

    private static final String player2 = "0";

    private static final int size = 3;

    private static final String[][] array = new String[size][size];

    private final Desk desk = new Desk();

    private final Step step = new Step();

    private final InputData inputData = new InputData();

    public void chooseTheGame(){
        while(true) {
            System.out.println("Please, choose the variant of the game:");
            System.out.println("1. Game with 2 players");
            System.out.println("2. Game with computer bot");
            System.out.println("0. Exit");
            int i = inputData.checkInput("your choice:");
            desk.initializeDesk(array);
            if (i == 1) {
                gameWithEachOther(array);
            }else if (i == 2) {
                gameWithComputer(array);
            }else if (i == 0){
                break;
            }
        }
    }

    public void gameWithEachOther(String[][] array){
        while (true) {
            if (actionsForPlayer(array, player1)){
                break;
            }
            if (actionsForPlayer(array, player2)){
                break;
            }
        }
    }

    public void gameWithComputer(String[][] array){
        while (true) {
            if (actionsForPlayer(array, player1)){
                break;
            }
            if (actionsForComputer(array, player2)) {
                break;
            }
        }
    }

    public boolean actionsForPlayer(String[][] array, String player){
        step.doPlayerStep(array, player, inputData);
        return isTheGameOver(array, player);
    }

    public boolean actionsForComputer(String[][] array, String player){
        step.doComputerStep(array, player);
        return isTheGameOver(array, player);
    }

    public boolean isTheGameOverOnLines(String[][] array, String player){
        for (String[] strings : array) {
            int count = 0; //count of similar cells
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

    public boolean isTheGameOverInColumn(String[][] array, String player){
        for (int j = 0; j < array.length; j++) {
            int count = 0; //count of similar cells
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

    public boolean isTheGameOverOnDiagonal1(String[][] array, String player){
        int count = 0; //count of similar cells
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (i == j && array[i][j].equals(player)) {
                    count++;
                    if(isGameOverCheckAndWrite(array, count, player)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isTheGameOverOnDiagonal2(String[][] array, String player){
        int count = 0; //count of similar cells
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if ((i + j == array.length - 1) && array[i][j].equals(player)) {
                     count++;
                     if(isGameOverCheckAndWrite(array, count, player)){
                         return true;
                     }
                }
            }
        }
        return false;
    }

    public boolean isGameOverCheckAndWrite(String[][] array, int count, String player){
        if (count == array.length) {
            System.out.println("THE GAME IS OVER! " + player + " won!!!");
            return true;
        }
        return false;
    }

    public boolean isTheGameOverOfDeadHeat(String[][] array){
        int count = 0; //count of similar cells
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
        if (isTheGameOverOnLines(array, player)) {
            return true;
        }else if(isTheGameOverInColumn(array, player)) {
            return true;
        }else if(isTheGameOverOnDiagonal1(array, player)){
            return true;
        }else if(isTheGameOverOnDiagonal2(array, player)){
            return true;
        }else if(isTheGameOverOfDeadHeat(array)){
            return true;
        }else {
            return false;
        }
    }
}



