package edu.grinnell.csc207.util;

import static edu.grinnell.csc207.util.MatrixAssertions.assertMatrixEquals;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

/**
 * A variety of tests for the Matrix class.
 *
 * @author Yash Malik
 */
public class TestsByStudent {
    // Test the constructors
    @Test
    public void testConstructor() {
        Matrix<Integer> matrix = new MatrixV0<>(3, 4, 0);
        assertMatrixEquals(new Integer[][] {{0, 0, 0} , {0, 0, 0}, {0, 0, 0}, {0, 0, 0}}, matrix, 
                "3x4 matrix initialized with zeros");

        Matrix<String> emptyMatrix = new MatrixV0<>(2, 2);
        assertMatrixEquals(new String[][] {{null, null}, {null, null}}, emptyMatrix, 
                "2x2 matrix initialized with null values");
    } // constructors test 1.

    // Test getting and setting values
    @Test
    public void testSetGet() {
        Matrix<String> matrix = new MatrixV0<>(2, 2);
        matrix.set(0, 0, "Hello");
        matrix.set(0, 1, "World");
        assertEquals("Hello", matrix.get(0, 0), "Get value at (0, 0)");
        assertEquals("World", matrix.get(0, 1), "Get value at (0, 1)");
    } // setters and getters test 1.

    // Test row and column insertion
    @Test
    public void testInsertRowCol() {
        Matrix<Integer> matrix = new MatrixV0<>(2, 2, 0);
        
        // Insert row
        matrix.insertRow(1);
        assertMatrixEquals(new Integer[][] {{0, 0}, {0, 0}, {0, 0}}, matrix, 
                "After inserting a row, matrix should have 3 rows");
        
        // Insert column
        matrix.insertCol(1);
        assertMatrixEquals(new Integer[][] {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}, matrix, 
                "After inserting a column, matrix should have 3 columns");
    } // row & col insertion test 1.

    // Test row and column deletion
    @Test
    public void testDeleteRowCol() {
        Matrix<Integer> matrix = new MatrixV0<>(3, 3, 0);
        
        // Delete row
        matrix.deleteRow(1);
        assertMatrixEquals(new Integer[][] {{0, 0, 0}, {0, 0, 0}}, matrix, 
                "After deleting a row, matrix should have 2 rows");
        
        // Delete column
        matrix.deleteCol(0);
        assertMatrixEquals(new Integer[][] {{0, 0}, {0, 0}}, matrix, 
                "After deleting a column, matrix should have 2 columns");
    } // row & col deletion test 1.

    // Test fillRegion method
    @Test
    public void testFillRegion() {
        Matrix<Integer> matrix = new MatrixV0<>(3, 3, 0);
        matrix.fillRegion(0, 0, 2, 2, 5);
        assertMatrixEquals(new Integer[][] {{5, 5, 0}, {5, 5, 0}, {0, 0, 0}}, matrix, 
                "Filling a 2x2 region with 5");
    } // fillRegion test 1.

    // Test fillLine method
    @Test
    public void testFillLine() {
        Matrix<Integer> matrix = new MatrixV0<>(3, 3, 0);
        matrix.fillLine(0, 0, 1, 1, 3, 3, 7); // Fill diagonal line with 7
        assertMatrixEquals(new Integer[][] {{7, 0, 0}, {0, 7, 0}, {0, 0, 7}}, matrix, 
                "Filling a diagonal line with 7");
    } // fillLine test 1.

    // Test cloning
    @Test
    public void testClone() {
      Matrix<String> matrix = new MatrixV0<>(2, 2, "X");
      Matrix<String> clonedMatrix = matrix.clone();
      
      // Create the expected 2D array for comparison
      String[][] expectedArray = {{"X", "X"}, {"X", "X"}};
      
      // Use assertMatrixEquals to compare the original matrix with the cloned matrix
      assertMatrixEquals(expectedArray, clonedMatrix, "Cloned matrix should be equal to the original");
      
      // Modify the original matrix and check that the cloned matrix is unaffected
      matrix.set(0, 0, "Y");
      assertNotEquals(matrix.get(0, 0), clonedMatrix.get(0, 0), 
              "Changes to the original should not affect the clone");
  } // Cloning test 1.

    // Test equals and hashCode
    @Test
    public void testEqualsAndHashCode() {
        Matrix<Integer> matrix1 = new MatrixV0<>(2, 2, 0);
        Matrix<Integer> matrix2 = new MatrixV0<>(2, 2, 0);
        assertTrue(matrix1.equals(matrix2), "Two equal matrices should be equal");

        matrix1.set(0, 0, 1);
        assertFalse(matrix1.equals(matrix2), "Different matrices should not be equal");

        matrix2.set(0, 0, 1);
        assertTrue(matrix1.equals(matrix2), "After setting equal values, they should be equal");
        assertEquals(matrix1.hashCode(), matrix2.hashCode(), "Equal matrices should have the same hash code");
    } // hashCode and eqauals test 1.

    // Test exceptions
    @Test
    public void testAccessExceptions() {
        Matrix<Integer> matrix = new MatrixV0<>(3, 3);
        assertThrows(IndexOutOfBoundsException.class, () -> matrix.get(-1, 0), 
                "Negative row access should throw an exception");
        assertThrows(IndexOutOfBoundsException.class, () -> matrix.set(0, 3, 1), 
                "Out-of-bounds column access should throw an exception");
    } // Exception test 1

    @Test
    public void testInsertExceptions() {
        Matrix<String> matrix = new MatrixV0<>(2, 2, "x");
        assertThrows(ArraySizeException.class, 
                () -> matrix.insertRow(1, new String[] {"x", "y", "z"}), 
                "Inserting row with incorrect size should throw exception");
        assertThrows(ArraySizeException.class, 
                () -> matrix.insertCol(1, new String[] {"x"}), 
                "Inserting column with incorrect size should throw exception");
    } // Exception test 2
}
