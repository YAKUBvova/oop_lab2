import org.example.Immutable_matrix;
import org.example.Matrix;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MatrixTest {
    Matrix mat = new Matrix(new int[][]{{1,5,-6},{3,0,7},{-1,2,4}});
    Matrix mat1 = new Matrix(new int[][]{{1,5,-6}});
    Matrix mat2 = new Matrix(new int[][]{{2,3},{2,3}});
    Matrix mat3 = new Matrix(new int[][]{{1,2},{2,3}});

    Immutable_matrix immat = new Immutable_matrix(new int[][]{{1,5,-6},{3,0,7},{-1,2,4}});

    @Test
    public void getters_test(){
        //get_column
        assertArrayEquals(new int[]{5,0,2},mat.get_column(1));
        assertArrayEquals(new int[]{-6,7,4},mat.get_column(2));
        //get_row
        assertArrayEquals(new int[]{3,0,7},mat.get_row(1));
        //get_element
        assertArrayEquals(new int[]{0},mat.get_element(1,1));
        assertArrayEquals(new int[]{2},mat.get_element(2,1));
        //get_dimension
        assertArrayEquals(new int[]{3,3},mat.get_dimension());
        assertArrayEquals(new int[]{1,3},mat.get_dimension(new int[][]{{1,2,3}}));
    }

    @Test
    public void eq_test(){
        Matrix mat1 = new Matrix(new int[][]{{1,5,-6},{3,0,7},{-1,2,4}});
        Matrix mat2 = new Matrix(new int[][]{{1,5,-6},{3,1,7},{-1,2,4}});
        Matrix mat3 = new Matrix(new int[][]{{1,5,-6},{3,0,7},{-1,2,4}});
        assertFalse(mat1.equals(mat2));
        assertTrue(mat1.equals(mat3));
    }

    @Test
    public void transposition_test(){
        //mutable
        assertArrayEquals(new int[][]{{1,3,-1},{5,0,2},{-6,7,4}},mat.transposition());
        assertArrayEquals(new int[][]{{1,5,-6},{3,0,7},{-1,2,4}},mat.transposition());
        assertArrayEquals(new float[][]{{1,4},{2,5},{3,6}},mat.transposition(new float[][]{{1,2,3},{4,5,6}}));
        //immutable
        assertArrayEquals(new int[][]{{1,3,-1},{5,0,2},{-6,7,4}},immat.transposition());
        assertArrayEquals(new int[][]{{1,5,-6},{3,0,7},{-1,2,4}},immat.matrix);
    }

    @Test
    public void determinant_test(){
        assertEquals(1000,mat.determinant(new int[][]{{1,0,1}}));
        assertEquals(0,mat.determinant(new int[][]{{1,2},{1,2}}));
        assertEquals(8,mat.determinant(new int[][]{{1,-2},{3,2}}));
    }

    @Test
    public void reverse_test(){
        assertArrayEquals(new float[0][0],mat1.reverse_matrix());
        assertArrayEquals(new float[0][0],mat2.reverse_matrix());
        assertArrayEquals(new float[][]{{-3,2},{2,-1}},mat3.reverse_matrix());
    }

    @Test
    public void unitmat_test(){
        assertArrayEquals(new int[0][0],mat1.unit_mat());
        assertArrayEquals(new int[0][0],mat2.unit_mat());
        //mutable
        assertArrayEquals(new int[][]{{1,0,0},{0,1,0},{0,0,1}},mat.unit_mat());
        //immutable
        assertArrayEquals(new int[][]{{1,0,0},{0,1,0},{0,0,1}},immat.unit_mat());
        assertArrayEquals(new int[][]{{1,5,-6},{3,0,7},{-1,2,4}},immat.matrix);
    }
}
