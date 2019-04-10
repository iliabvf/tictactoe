package TicTacToe;

import java.util.Random;
import java.util.Scanner;

// содержит бизнес-логику игры
class GameLogic {

    void showField(Field field){
        char[][] fieldData = field.getFieldData();
        System.out.println();
        System.out.println("     0  1  2 ");
        for (int r = 0; r < 3; r++){
            System.out.print(" " + r + ": ");
            for (int c = 0; c < 3; c++){
                System.out.print("[" + fieldData[r][c] + "]");
            }
            System.out.println();
        }
    }

    String UserInput(String message){
        System.out.println();
        System.out.print(message);
        try {
            Scanner inputReader = new Scanner(System.in);
            return inputReader.nextLine();
        } catch (Exception e)
        {
            return "";
        }
    }

    boolean checkWinner(Field field, char value){
        char[][] filedData = field.getFieldData();

        boolean win;

        for (int i = 0; i < 2; i++) {
            for (int r = 0; r < 3; r++) {
                win = true;
                for (int c = 0; c < 3; c++) {
                    if (i == 0)
                        win = filedData[r][c] == value ? win : false;
                    else
                        win = filedData[c][r] == value ? win : false;
                }
                if (win)
                    return true;
            }
        }

        win = true;
        for (int r = 0; r < 3; r++) {
            win = filedData[r][r] == value ? win : false;
        }
        if (win)
            return true;

        win = true;
        for (int r = 0; r < 3; r++) {
            win = filedData[r][2-r] == value ? win : false;
        }
        if (win)
            return true;


        return false;
    }

    boolean checkFieldFull(Field field){
        char[][] filedData = field.getFieldData();

        for (int c = 0; c < 3; c++) {
            for (int r = 0; r < 3; r++) {
                if (filedData[r][c] == ' ')
                    return false;
            }
        }

        return true;
    }

    void startGame(){
        // Creating game field
        Field field = new Field();

		System.out.println("TicTacToe by Budeanu Vasile. v.1.1");
		
        // main game cycle
        while (true) {
            showField(field);

            String input = "";
            int row;
            int col;
            while (true) {
                input = UserInput("Enter Row and Column number (ex.11) or Q for quit: ");
                if (input.toUpperCase().equals("Q")){
                    System.out.println();
                    System.out.println("Closing game...");
                    return;
                }

                if (input.length() != 2) {
                    System.out.println("Error: Please enter TWO digits (ex.11).");
                    continue;
                }

                if (input.charAt(0) < 48 || input.charAt(0) > 50 || input.charAt(1) < 48 || input.charAt(1) > 50){
                    System.out.println("Error: Please enter row/col value from 0 to 2.");
                    continue;
                }

                row = Character.getNumericValue(input.charAt(0));
                col = Character.getNumericValue(input.charAt(1));

                if (row <0 || row > 2 || col <0 || col > 2){
                    System.out.println("Error: Please enter row/col value from 0 to 2.");
                    continue;
                }

                break;

            }



            if (field.getFieldCell(row, col) != ' '){
                System.out.println("Cell " + row + " " + col + " si occupied");
                continue;
            }

            field.setFieldCell(row, col, 'X');

            // Check winner
            if (checkWinner(field, 'X')){
                System.out.println("You win!");
                showField(field);
                break;
            }

            // AI turn
            char[][] fieldData = field.getFieldData();
            Random rand = new Random();
            while (true) {
                int r = rand.nextInt(3);
                int c = rand.nextInt(3);
                if (fieldData[r][c] != ' ')
                    continue;
                fieldData[r][c] = 'O';
                System.out.println("AI turn ...");
                break;
            }
			
            if (checkWinner(field, 'O')){
                System.out.println("AI wins!");
                showField(field);
                break;
            }
			
            // Check game end
            if (checkFieldFull(field)){
                System.out.println("Game ended, nobody wins.");
                showField(field);
                break;
            }


        }
    }
}
