package org.example;

public class Main {
    public static void main(String[] args) {
        Matrix matrix = new Matrix(3,3);
        matrix.fill();
        matrix.print_matrix();
        System.out.println();
        System.out.println(matrix.determinant(matrix.matrix));
        System.out.println();
        matrix.unit_mat();
        matrix.print_matrix();
    }
}