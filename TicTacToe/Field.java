package TicTacToe;

// сущность, описывающая игровое поле
class Field {
    private char[][] fieldData;

    Field() {
        char[][] fieldData = new char[3][3];
        for (int r = 0; r < 3; r++){
            for (int c = 0; c < 3; c++){
                fieldData[r][c] = ' ';
            }
        }

        this.fieldData = fieldData;
    }

    public char[][] getFieldData() {
        return fieldData;
    }

    public void setFieldData(char[][] fieldData) {
        this.fieldData = fieldData;
    }

    public void setFieldCell(int row, int col, char value) {
        this.fieldData[row][col] = value;
    }

    public char getFieldCell(int row, int col) {
        return this.fieldData[row][col];
    }
}
