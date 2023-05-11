package org.example;

public interface Matrix_interface {
    void fill ();
    int[] get_column( int column);
    int[] get_row( int row );
    int[] get_element( int row , int column);
    int[] get_dimension();
    int[] fget_dimension(float[][] mat);
    void print_matrix();
    int[][] unit_mat();
    int[][] transposition();
    int determinant(int[][] mat);
    float[][] reverse_matrix();
}
