/*
Original task - https://www.codewars.com/kata/battleship-field-validator

Write a method that takes a field for well-known board game "Battleship"
as an argument and returns true if it has a valid disposition of ships, false otherwise.
Argument is guaranteed to be 10*10 two-dimension array.
Elements in the array are numbers, 0 if the cell is free and 1 if occupied by ship.

Battleship (also Battleships or Sea Battle) is a guessing game for two players.
Each player has a 10x10 grid containing several "ships" and objective is to destroy enemy's forces
by targeting individual cells on his field.
The ship occupies one or more cells in the grid.
Size and number of ships may differ from version to version.
In this kata we will use Soviet/Russian version of the game.
Before the game begins, players set up the board and place the ships accordingly to the following rules:
There must be single battleship (size of 4 cells), 2 cruisers (size 3), 3 destroyers (size 2) and 4 submarines (size 1).
Any additional ships are not allowed, as well as missing ships.
Each ship must be a straight line, except for submarines, which are just single cell.
The ship cannot overlap or be in contact with any other ship, neither by edge nor by corner.
 */
 
public class BattleField {
    
    public static boolean fieldValidator(int[][] field) {
        //ships[0] - quantity of submarines, ships[1] - quantity of destroyers etc.
        int[] ships = new int[]{4, 3, 2, 1};

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (field[i][j] == 1) {
                    int shipType = getType(field, i, j);
                    if(shipType == -1 || checkIfInContact(field, i, j, shipType)) return false;
                    fillArea(field, i, j, shipType);
                    ships[shipType]--;
                }
            }
        }

        return ships[0] == 0 && ships[1] == 0 && ships[2] == 0 && ships[3] == 0;
    }

    //submarine has type 0, battleship has type 3, wrong ship has type -1
    private static int getType(int[][] field, int line, int column) {
        int typeOfShip = 0;
        if (isHorizontal(field, line, column)) {
            while (column < 9 && field[line][++column] == 1) {
                typeOfShip++;
            }
        } else {
            while (line < 9 && field[++line][column] == 1) {
                typeOfShip++;
            }
        }

        return typeOfShip > 3 ? -1 : typeOfShip;
    }

    private static boolean checkIfInContact(int[][] field, int line, int column, int shipType) {
        if(line != 9) {
            if (isHorizontal(field, line, column)) {
                for (int i = column - 1; i < column + shipType + 2; i++) {
                    if(i < 0 || i > 9) continue;
                    if(field[line+1][i] == 1) return true;
                }
            } else {
                for (int i = line + 1; i < line + shipType + 2; i++) {
                    if(i > 9) break;
                    for (int j = column - 1; j < column + 2; j++) {
                        if(j < 0 || j == column || j > 9) continue;
                        if(field[i][j] == 1) return true;
                    }
                }
            }
        }

        return false;
    }
    
    //cross out the ship from the battlefield so as not to check its cells in the future
    private static void fillArea(int[][] field, int line, int column, int shipType) {
        if (isHorizontal(field, line, column)) {
            for (int i = column; i <= column + shipType; i++) {
                field[line][i] = 0;
            }
        } else {
            for (int i = line; i <= line + shipType; i++) {
                field[i][column] = 0;
            }
        }
    }
    
    private static boolean isHorizontal(int[][] field, int line, int column) {
        return column < 9 ? field[line][column + 1] == 1 : false;
    }
}