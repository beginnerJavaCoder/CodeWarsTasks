/*
Original task - https://www.codewars.com/kata/matrix-determinant

Write a function that accepts a square matrix (N x N 2D array) and returns the determinant of the matrix.
How to take the determinant of a matrix -- it is simplest to start with the smallest cases:
A 1x1 matrix |a| has determinant a.
A 2x2 matrix [ [a, b], [c, d] ] or
|a  b|
|c  d|
has determinant: a*d - b*c.
The determinant of an n x n sized matrix is calculated by reducing the problem to the calculation of 
the determinants of n matrices ofn-1 x n-1 size.
For the 3x3 case, [ [a, b, c], [d, e, f], [g, h, i] ] or
|a b c|  
|d e f|  
|g h i|
the determinant is: a * det(a_minor) - b * det(b_minor) + c * det(c_minor) 
where det(a_minor) refers to taking the determinant of the 2x2 matrix created by crossing out 
the row and column in which the element a occurs:
|- - -|
|- e f|
|- h i|
Note the alternation of signs.
The determinant of larger matrices are calculated analogously, 
e.g. if M is a 4x4 matrix with first row [a, b, c, d], then:
det(M) = a * det(a_minor) - b * det(b_minor) + c * det(c_minor) - d * det(d_minor)
 */
 
public class Matrix {
    
    public static int determinant(int[][] matrix) {
        if(matrix.length == 1) return matrix[0][0];
        int determinant = 0;
        for(int i = 0; i < matrix.length; i++) {
            if(i % 2 == 0) {
                determinant += matrix[0][i] * determinant(newMatrix(matrix, i));
            }
            else determinant -= matrix[0][i] * determinant(newMatrix(matrix, i));
        }
        return determinant;
    }
    
    //return matrix without column and first line
    private static int[][] newMatrix(int[][] matrix, int column) {
        int length = matrix.length;
        int[][] result = new int[length-1][length-1];
        int resultLine = 0;
        int resultColumn = 0;
        for(int i = 1; i < length; i++) {
            for(int j = 0; j < length; j++) {
                if(j == column) continue;
                result[resultLine][resultColumn] = matrix[i][j];
                resultColumn++;
            }
            resultLine++;
            resultColumn = 0;
        }
        return result;
    }
}