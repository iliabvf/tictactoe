package net.iliabvf.tictactoe;

// сущность, описывающая игровое поле
class Field {
    private char[] fieldData;

    Field() {
        char[] fieldData = new char[9];
        for (int cell = 0; cell < 9; cell++){
            fieldData[cell] = (char)(49+cell);
        }

        this.fieldData = fieldData;
    }

    public char[] getFieldData() {
        return fieldData;
    }

    public void setFieldData(char[] fieldData) {
        this.fieldData = fieldData;
    }

    public void setFieldCell(int cell, char value) {
        this.fieldData[cell] = value;
    }

    public char getFieldCell(int cell) {
        return this.fieldData[cell];
    }
}
