package net.iliabvf.tictactoe;

import java.util.Random;
import java.util.Scanner;

// содержит бизнес-логику игры
class GameLogic {

    final String WELCOME_STRING =  "TicTacToe by Budeanu Vasile. v.1.2";
    final String INPUT_STRING =  "Enter cell number (1 - 9) or Q for quit: ";
    final String CLOSING_STRING =  "Closing game...";
    final String ERROR_NUM_DIGITS_STRING =  "Error: Please enter ONE digit (ex.1).";
    final String ERROR_CELL_RANGE_STRING =  "Error: Please enter cell value from 0 to 9.";
    final String ERROR_CELL_RANGE2_STRING =  "Error: Please enter cell value from 1 to 9.";
    final String ERROR_CELL_OCCUPIED_STRING =  "Cell occupied!";
    final String WIN_STRING =  "You win!";
    final String AI_TURN_STRING =  "AI turn ...";
    final String AI_WIN_STRING = "AI wins!";
    final String NOBODY_WINS_STRING =  "Game ended, nobody wins.";

    private Field field;
    private Scanner scanner;

    GameLogic(){
        this.field = new Field();
        this.scanner = new Scanner(System.in);
    }

    void showField(){
        char[] fieldData = this.field.getFieldData();
        System.out.println();
        for (int cell = 0; cell < 9; cell++){
            if (cell % 3 == 0) {
                System.out.println();
            }
            System.out.print("[" + (fieldData[cell]) + "]");


        }
    }

    String userInput(String message){
        System.out.println();
        System.out.print(message);
        try {
            return this.scanner.nextLine();
        } catch (Exception e)
        {
            return "";
        }
    }

    boolean checkWinner(char value){
        char[] filedData = this.field.getFieldData();

        boolean win;

        for (int row = 0; row < 3; row++) {
            win = true;
            for (int column = 0; column < 3; column++) {
                win = filedData[row*3 + column] == value ? win : false;
            }
            if (win) {
                return true;
            }
        }

        for (int column = 0; column < 3; column++) {
            win = true;
            for (int row = 0; row < 7; row=row+3) {
                win = filedData[row + column] == value ? win : false;
            }
            if (win) {
                return true;
            }
        }

        win = true;
        for (int cell = 1; cell < 10; cell=cell+4) {
            win = filedData[cell-1] == value ? win : false;
        }
        if (win) {
            return true;
        }

        win = true;
        for (int cell = 3; cell < 8; cell=cell+2) {
            win = filedData[cell-1] == value ? win : false;
        }
        if (win) {
            return true;
        }

        return false;
    }

    boolean checkFieldFull(){
        char[] filedData = this.field.getFieldData();

        for (int cell = 0; cell < 9; cell++) {
            if (filedData[cell] != 'X' && filedData[cell] != 'O') {
                return false;
            }
        }

        return true;
    }

    byte validateImput(String input){
        if (input.toUpperCase().equals("Q")){
            System.out.println();
            System.out.println(CLOSING_STRING);
            return 2;
        }

        if (input.length() != 1) {
            System.out.println(ERROR_NUM_DIGITS_STRING);
            return 0;
        }

        if (input.charAt(0) < 49 || input.charAt(0) > 57){
            System.out.println(ERROR_CELL_RANGE_STRING);
            return 0;
        }

        if (Character.getNumericValue(input.charAt(0)) <1 || Character.getNumericValue(input.charAt(0)) > 9){
            System.out.println(ERROR_CELL_RANGE2_STRING);
            return 0;
        }

        return 1;
    }

    void aiTurn(){
        char[] fieldData = this.field.getFieldData();
        Random rand = new Random();
        while (true) {
            int randomCell = rand.nextInt(8);
            if (fieldData[randomCell] == 'X' || fieldData[randomCell] == 'O') {
                continue;
            }
            fieldData[randomCell] = 'O';
            System.out.println(AI_TURN_STRING);
            return;
        }
    }

    void startGame(){
		System.out.println(WELCOME_STRING);
		
        // main game cycle
        while (true) {
            showField();

            String input = "";
            int cell;
            while (true) {
                input = userInput(INPUT_STRING);
                if (validateImput(input) == 0) {
                    continue;
                } else if (validateImput(input) == 2) {
                    return;
                }

                cell = Character.getNumericValue(input.charAt(0));

                break;
            }



            if (this.field.getFieldCell(cell-1) == 'X' || this.field.getFieldCell(cell-1) == 'O'){
                System.out.println(ERROR_CELL_OCCUPIED_STRING);
                continue;
            }

            this.field.setFieldCell(cell-1, 'X');

            // Check winner
            if (checkWinner('X')){
                System.out.println(WIN_STRING);
                showField();
                break;
            }

            // AI turn
            aiTurn();

            if (checkWinner('O')){
                System.out.println(AI_WIN_STRING);
                showField();
                break;
            }
			
            // Check game end
            if (checkFieldFull()){
                System.out.println(NOBODY_WINS_STRING);
                showField();
                break;
            }


        }
    }
}
