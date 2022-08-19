package cmsc256;

import java.util.Arrays;

/***********************************************************************************************************************************************************************************
 * Grid.java
 ************************************************************************************************************************************************************************************
 * project one - grid
 ************************************************************************************************************************************************************************************
 * Project description
 * Tic tac toe with grid in java
 * Kevin Phung
 * 1/27/2022
 * CMSC-256
 ***********************************************************************************************************************************************************************************/
public class Grid {
    char grid[][] = new char[3][3];

    /*
     * Initializes a 3 by 3 grid to default char values ('\u0000')
     * */
    public Grid() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = ' ';
            }
        }

    }


    /**
     * Formats the grid row to a String that consists of a space, the char,
     * a space, a vertical pipe, a space, the char, a space, a vertical pipe,
     * a space, the char, and a final space,
     * for example: " X | X | X "
     *
     * @param rowIndex the index of the row to convert to a String
     * @return a formatted String representation of the row
     * @throws IllegalArgumentException if an invalid row index is given
     */
    public String getRow(int rowIndex) throws IllegalArgumentException {
        if (rowIndex >= 3) {
            throw new IllegalArgumentException();
        }
        String S = " " + grid[rowIndex][0] + " | " + grid[rowIndex][1] + " | " + grid[rowIndex][2] + " ";
        return S;
    }

    /**
     * Sets the grid location to the given value
     *
     * @param value       char value for the grid location
     * @param rowIndex    the index of the row position
     * @param columnIndex the index of the column position
     * @throws IllegalArgumentException if the row index or column index is invalid
     *                                  or if the position is not null
     */
    public void setPosition(char value, int rowIndex, int columnIndex) throws IllegalArgumentException {
        if (!checkInput(value) || rowIndex >= 3 || columnIndex >= 3) {
            throw new IllegalArgumentException();
        }


        grid[rowIndex][columnIndex] = value;

    }


    /**
     * Checks for valid input value
     *
     * @param inputValue the char value to be checked
     * @return true if input value is X, x, O, or o
     * @throws IllegalArgumentException if character is not X or O
     */
    public boolean checkInput(char inputValue) throws IllegalArgumentException {
        if (inputValue == 'x' || inputValue == 'X' || inputValue == 'o' || inputValue == 'O')
            return true;
        else
            throw new IllegalArgumentException();
    }

    /**
     * Checks if all positions have a char value
     *
     * @return true if none of the grid locations contain the null character ('\u0000')
     */
    public boolean isFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == ' ')
                    return false;
            }
        }
        return true;
    }

    /**
     * Check if row has all the same characters
     *
     * @param rowIndex the row index to check
     * @return true if row contains the same char value
     * @throws IllegalArgumentException if an invalid row index is given
     */
    public boolean isRowMatching(int rowIndex) throws IllegalArgumentException {
        if (rowIndex  >= 3)
            throw new IllegalArgumentException();
        char s = grid[rowIndex][0];
        return s == grid[rowIndex][1] && s == grid[rowIndex][2];
    }

    /**
     * Check if column has all the same characters
     *
     * @param columnIndex the column index to check
     * @return true if column contains the same char value
     * @throws IllegalArgumentException if an invalid column index is given
     */
    public boolean isColumnMatching(int columnIndex) throws IllegalArgumentException {
        if (columnIndex >= 3) {
            throw new IllegalArgumentException();
        }
        char s = grid[0][columnIndex];
        return s == grid[1][columnIndex] && s == grid[2][columnIndex];
    }

    /**
     * Checks if either diagonal has the same characters
     *
     * @return true if grid position 0,0; 1,1; and 2,2 are the same
     * if grid position 2,0; 1,1; and 0,2 are the same
     */
    public boolean hasDiagonalMatch() {
        boolean result = false;
        if (grid[0][0] == grid[1][1] && grid[0][0] == grid[2][2]) {
            result = true;
        } else if (grid[2][0] == grid[1][1] && grid[2][0] == grid[0][2]) {
            result = true;
        }
        return result;
    }

    /**
     * Checks if there is a character with three in a row on the grid
     *
     * @return winning character if there is a row, column or diagonal match
     * otherwise returns the null character
     */
    public char checkForWinner() {
        if (isRowMatching(0) || hasDiagonalMatch()) {
            return grid[0][0];
        } else if (isRowMatching(1)) {
            return grid[1][0];
        } else if (isRowMatching(2) || hasDiagonalMatch()) {
            return grid[2][0];
        } else if (isColumnMatching(0)) {
            return grid[0][0];
        } else if (isColumnMatching(1)) {
            return grid[1][1];
        } else if (isColumnMatching(2)) {
            return grid[2][2];
        }

        return ' ';
    }

    @Override
    /**
     * Returns a string representation of the grid with each row separated by a line
     * @return string
     */
    public String toString() {
        String R = "";
        for (int i = 0; i < 3; i++) {
            //space for the first
            R += " ";

            //print the element
            R += grid[i][0] + " | " + grid[i][1] + " | " + grid[i][2] + " ";


            //Spaces between for line
            R += "\n";

            //remove the extra line of bottom
            for (int x = 0; i != 3 - 1 && x < 1; x++) {
                R += "---------";
            }

            if (i < 2)
                R += "\n";
        }
        return R;
    }
}


